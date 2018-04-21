package app.parser.impl;

import app.parser.api.Serializer;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Created by User on 23.7.2017 г..
 */
@Component(value = "XMLParser")
public class XMLFileParser implements Serializer {


    @Override
    public <T> T deserialize(Class<T> className, String fileName) {
        //impl
        return null;
    }


    @Override
    public <T> void serialize(T t, String fileName) {
        //impl
    }

    @Override
    public <T> String serialize(T t) {
        //impl
        return null;
    }
}
