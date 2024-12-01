package be.aoc.utils;

import java.io.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class FileReaderUtils {

    public <T> void readFile(String fileName,
                             Function<String, T> converter,
                             Consumer<T> execute) {
        String line;

        try(InputStream resourceAsStream = FileReaderUtils.class.getClassLoader().getResourceAsStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
            BufferedReader reader = new BufferedReader(inputStreamReader)){

            while ((line = reader.readLine()) != null){
                T apply = converter.apply(line);
                execute.accept(apply);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
