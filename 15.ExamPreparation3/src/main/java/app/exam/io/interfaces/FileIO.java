package app.exam.io.interfaces;

import org.springframework.stereotype.Component;

import java.io.IOException;


public interface FileIO {

    String read(String file) throws IOException;

    void write(String fileContent, String file) throws IOException;
}
