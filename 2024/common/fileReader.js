#!/usr/bin/env node
import * as fs from 'fs';
import * as readline from 'readline';

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