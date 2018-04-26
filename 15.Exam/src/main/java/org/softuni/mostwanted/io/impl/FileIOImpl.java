package org.softuni.mostwanted.io.impl;

import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOImpl implements FileIO {
    @Override
    public String read(String file) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (
                InputStream inputStream = getClass().getResourceAsStream(file);
                BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        ){
            String line;
            while((line = bf.readLine())!=null){
                sb.append(line);
            }
        }
        return sb.toString();
    }

    @Override
    public void write(String fileContent, String file) throws IOException {
        String path = System.getProperty("user.dir")+"/src/main/resources"+file;
        try(
                OutputStream outputStream = new FileOutputStream(path);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        ){
            bw.write(fileContent);
        }
    }
}
