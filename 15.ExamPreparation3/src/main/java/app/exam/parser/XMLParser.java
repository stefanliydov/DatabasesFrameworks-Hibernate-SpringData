package app.exam.parser;

import org.springframework.stereotype.Component;
import app.exam.parser.interfaces.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component(value = "XMLParser")
public class XMLParser implements Parser {


    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //  unmarshaller.setAdapter(new XmlAdapter<String,Date>() {
        //      private final SimpleDateFormat  format = new SimpleDateFormat("dd/MM/yyyy");
        //      @Override
        //      public Object unmarshal(Object v) throws Exception {
        //          return null;
        //      }

        //      @Override
        //      public Object marshal(Object v) throws Exception {
        //          return null;
        //      }
        //  });
        return (T)unmarshaller.unmarshal(new StringReader(fileContent));


    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(object,writer);
        return writer.toString();
    }
}
