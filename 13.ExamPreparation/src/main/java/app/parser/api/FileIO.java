package app.parser.api;

import java.io.IOException;

/**
 * Created by User on 23.7.2017 г..
 */
public interface FileIO {
    String read(String fileName) throws IOException;

    void write(String fileName, String content) throws IOException;
}
