package axiomatika.converter.soapservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SoapServiceTest {

    @Autowired
    private SoapService soapService;

    @Test
    public void convertStandardXml() {
        String xml = """
                <person>
                    <name>Тест</name>
                    <surname>Тестов</surname>
                    <patronymic>Тестович</patronymic>
                    <birthDate>1990-01-01</birthDate>
                    <gender>MAN</gender>
                    <document>
                        <series>1333</series>
                        <number>112233</number>
                        <type>PASSPORT</type>
                        <issueDate>2020-01-01</issueDate>
                    </document>
                </person>
                """;

        String expXslt = """
                <person name="Тест" surname="Тестов" patronymic="Тестович" birthDate="01.01.1990" gender="MAN">\r
                    <document series="1333" number="112233" type="PASSPORT" issueDate="01.01.2020"/>\r
                </person>\r
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        boolean equals = expXslt.equals(actualXslt);

        assertTrue(equals);
    }
}