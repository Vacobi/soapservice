package axiomatika.converter.soapservice.service;

import axiomatika.converter.soapservice.exception.IncorrectXmlException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class SoapService {

    public String convertXmlToXslt(String xml) {

        Document xmlDocument = parseXml(xml);

        Source xsltSource = new StreamSource(getClass().getClassLoader().getResourceAsStream("xslt/transform.xslt"));
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer(xsltSource);
        } catch (Exception e) {
            throw new IncorrectXmlException(e.getMessage());
        }
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");

        DOMSource domSource = new DOMSource(xmlDocument);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        try {
            transformer.transform(domSource, result);
        } catch (Exception e) {
            throw new IncorrectXmlException(e.getMessage());
        }

        return writer.toString();
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
}
