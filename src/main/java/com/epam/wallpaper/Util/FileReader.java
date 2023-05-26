package com.epam.wallpaper.Util;

import com.epam.wallpaper.model.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class FileReader implements Runnable {

    private final List<Room> result;
    private static final int LENGTH_INDEX = 0;
    private static final int WIDTH_INDEX = 1;
    private static final int HEIGHT_INDEX = 2;

    public FileReader(){
        result = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this);
    }
    @Override
    public void run() {
        String fileContent = readFile();
        String[] lines = fileContent.split(System.lineSeparator());
        for (String line : lines) {
            String[] values = line.split("x");
            result.add(new Room(Integer.parseInt(values[LENGTH_INDEX]),
                    Integer.parseInt(values[WIDTH_INDEX]), Integer.parseInt(values[HEIGHT_INDEX])));
        }
    }

    private String readFile() {
        StringBuilder sb = new StringBuilder();
        ClassPathResource resource = new ClassPathResource("sample-input.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (Exception e) {
            log.error("Error occurred while reading input file ", e);
        }
        return sb.toString();
    }

    public List<Room> getResult() {
        return result;
    }
}
