#!/usr/bin/env node
import * as fs from 'fs';
import * as readline from 'readline';

const result = [];
let validationCount = 0;

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

async function convert(line) {
    result.push({
        row:line.split(' '), 
        increasing: null, 
        validate: true, 
        correctDistance: true
    });
}

//OK
async function determineDirection(line) {
    if(line.row[0] <= line.row[1]) {
        line.increasing = true;
        return;
    }
    line.increasing = false;
}; 

//OK
async function validateDirection(line) {
    for (let index = 1; index <= line.row.length; index++) {
        const previous = line.row[index - 1];
        const current = line.row[index];

        if (line.increasing) {
            if (previous >= current) {
                line.validate = false;
                return;
            }
        } else {
            if (previous <= current) {
                line.validate = false;
                return;
            }
        }
    }
}

async function validateDistance(line) {
    for (let index = 1; index <= line.row.length; index++) {
        const previous = line.row[index - 1];
        const current = line.row[index];
        let difference = current - previous;
        if (Math.abs(difference) > 3) {
            line.correctDistance = false
            return;
        }; 
    }
}

await processFile("./input.txt", convert);

console.log("Size: " + result.length)

await result.forEach(determineDirection);

console.log("Size: " + result.length)
await result.forEach(validateDirection);

console.log("Size: " + result.length)
await result.forEach(validateDistance);

console.log("Size: " + result.length)


await console.log(result.filter(line => line.correctDistance )
        .length
);