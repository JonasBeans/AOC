package be.aoc.service;

import be.aoc.model.Cube;
import be.aoc.utils.FileReaderUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CubeService {

    private FileReaderUtils fileReaderUtils;

    public CubeService() {
        fileReaderUtils = new FileReaderUtils();
    }

    public void readFile(String fileName) {
        fileReaderUtils.readFile(fileName, CubeService::convertLine, CubeService::consumer);
    }

    private static String[] convertLine(String line) {
        String game = line.replaceAll("Game ", "").split(":")[0];
        System.out.println(game);
        return line.replaceAll("Game .*:", "").split(";");
    }

    private static void consumer(String[] object){
        
    }
}
