#!/usr/bin/env node
import * as fs from 'fs';
import * as readline from 'readline';
import os from 'os';
import path, { join } from 'path';
import moment from 'moment';

const left = [];
const right = [];
const joined = [];
var counter = new Map();
var counterTotal = 0;
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

function updateCounter(item, list){
    if(!list.includes(item)) {return;};
    if (counter.get(item) === undefined) {
        //count amount of times in right list
        var countInList = 0;
        list.forEach((itemInList) => {if (itemInList === item) countInList++});
        counter.set(item, {count: countInList});console.log(`First timer: ${item}`);
        return
    };
}

right.sort();
left.sort().forEach((t) => 
    {
        //count
        console.log('left: ' + t); 
        joined.push({left: t, right: right[index], distance: 0});
        index++;

        //search in right
        updateCounter(t, right)
    }
);


joined.forEach((t) => {
    t.distance = Math.abs(t.left - t.right);
    total += t.distance;
});

console.log("Total: " + total);


counter.forEach((value, key) => {
    console.log(`key: ${key} \nvalue: ${value.count}`);
    counterTotal += Number.parseInt(key) * Number.parseInt(value.count);
});

console.log(counterTotal);