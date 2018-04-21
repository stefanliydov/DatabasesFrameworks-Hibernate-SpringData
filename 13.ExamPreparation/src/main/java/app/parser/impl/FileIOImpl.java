package app.parser.impl;

import app.parser.api.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by User on 23.7.2017 г..
 */
@Component
public class FileIOImpl implements FileIO {

    @Override
    public String read(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String st;
        while ((st = br.readLine()) != null){
            sb.append(st);
    }
    return sb.toString();
}

    @Override
    public void write(String fileName, String content) throws IOException {

    }
}
