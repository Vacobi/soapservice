package axiomatika.converter.soapservice.service;

import axiomatika.converter.soapservice.exception.IncorrectXmlException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class SoapService {

    private final Transformer transformer;

    public SoapService(Transformer transformer) {
        this.transformer = transformer;
    }

    public String convertXmlToXslt(String xml) {

        Document xmlDocument = parseXml(xml);

        return convertToXslt(xmlDocument, transformer);
    }

    private Document parseXml(String xml) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        Document xmlDocument;
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            xmlDocument = documentBuilder.parse(new InputSource(new StringReader(xml)));
        } catch (Exception e) {
            throw new IncorrectXmlException(xml);
        }

        return xmlDocument;
    }

    private String convertToXslt(Document xmlDocument, Transformer transformer) {
        DOMSource domSource = new DOMSource(xmlDocument);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        try {
            transformer.transform(domSource, result);
        } catch (Exception e) {
            throw new IncorrectXmlException("Error during XSLT-transformation: " + e.getMessage());
        }

        String xslt = writer.toString().replaceAll("\\r\\n", "\n");
        return xslt;
    }
}
