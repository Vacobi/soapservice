package axiomatika.converter.soapservice.config;

import org.apache.logging.log4j.util.InternalException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

@Configuration
public class TransformerConfig {

    @Bean
    public Transformer xsltTransformer() {
        InputStream xsltStream = getClass().getClassLoader().getResourceAsStream("xslt/transform.xslt");
        if (xsltStream == null) {
            throw new InternalException("XSLT transform file could not be found");
        }

        Source xsltSource = new StreamSource(xsltStream);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer(xsltSource);
        } catch (Exception e) {
            throw new InternalException("Incorrect transformer");
        }
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");

        return transformer;
    }
}
