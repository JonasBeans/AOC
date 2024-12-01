#!/usr/bin/env node
import * as fs from 'fs';
import * as readline from 'readline';
import os from 'os';
import path, { join } from 'path';
import moment from 'moment';

const left = [];
const right = [];
const joined = [];
var total = 0;

async function processFile(path, processor,) {
    const fileStream = fs.createReadStream(path);

    const lr = readline.createInterface({
        input: fileStream,
        crlfDelay: Infinity
    });

    for await (const line of lr) {
        processor(line);
    }
}

async function leftProcessor(line) {
    left.push(line);
}

async function rightProcessor(line) {
    right.push(line);
}

await processFile('./left-list.txt', leftProcessor);
await processFile('./right-list.txt', rightProcessor);

var index = 0;
right.sort();
left.sort().forEach((t) => 
    {
        console.log('left: ' + t); 
        joined.push({left: t, right: right[index], distance: 0});
        index++;
    }
);

joined.forEach((t) => {
    t.distance = Math.abs(t.left - t.right);
    total += t.distance;
});

console.log(total);