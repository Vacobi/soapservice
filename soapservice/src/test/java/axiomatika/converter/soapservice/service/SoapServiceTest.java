package axiomatika.converter.soapservice.service;

import axiomatika.converter.soapservice.exception.IncorrectXmlException;
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
                <person name="Тест" surname="Тестов" patronymic="Тестович" birthDate="01.01.1990" gender="MAN">
                    <document series="1333" number="112233" type="PASSPORT" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void convertXmlWithoutNameElement() {
        String xml = """
                <person>
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
                <person name="" surname="Тестов" patronymic="Тестович" birthDate="01.01.1990" gender="MAN">
                    <document series="1333" number="112233" type="PASSPORT" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void convertXmlWithoutSurnameElement() {
        String xml = """
                <person>
                    <name>Тест</name>
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
                <person name="Тест" surname="" patronymic="Тестович" birthDate="01.01.1990" gender="MAN">
                    <document series="1333" number="112233" type="PASSPORT" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void convertXmlWithoutPatronymicElement() {
        String xml = """
                <person>
                    <name>Тест</name>
                    <surname>Тестов</surname>
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
                <person name="Тест" surname="Тестов" patronymic="" birthDate="01.01.1990" gender="MAN">
                    <document series="1333" number="112233" type="PASSPORT" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void convertXmlWithoutBirthDateElement() {
        String xml = """
                <person>
                    <name>Тест</name>
                    <surname>Тестов</surname>
                    <patronymic>Тестович</patronymic>
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
                <person name="Тест" surname="Тестов" patronymic="Тестович" birthDate="" gender="MAN">
                    <document series="1333" number="112233" type="PASSPORT" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void convertXmlWithoutGenderElement() {
        String xml = """
                <person>
                    <name>Тест</name>
                    <surname>Тестов</surname>
                    <patronymic>Тестович</patronymic>
                    <birthDate>1990-01-01</birthDate>
                    <document>
                        <series>1333</series>
                        <number>112233</number>
                        <type>PASSPORT</type>
                        <issueDate>2020-01-01</issueDate>
                    </document>
                </person>
                """;

        String expXslt = """
                <person name="Тест" surname="Тестов" patronymic="Тестович" birthDate="01.01.1990" gender="">
                    <document series="1333" number="112233" type="PASSPORT" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void convertXmlWithoutDocumentSeriesElement() {
        String xml = """
                <person>
                    <name>Тест</name>
                    <surname>Тестов</surname>
                    <patronymic>Тестович</patronymic>
                    <birthDate>1990-01-01</birthDate>
                    <gender>MAN</gender>
                    <document>
                        <number>112233</number>
                        <type>PASSPORT</type>
                        <issueDate>2020-01-01</issueDate>
                    </document>
                </person>
                """;

        String expXslt = """
                <person name="Тест" surname="Тестов" patronymic="Тестович" birthDate="01.01.1990" gender="MAN">
                    <document series="" number="112233" type="PASSPORT" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void convertXmlWithoutDocumentNumberElement() {
        String xml = """
                <person>
                    <name>Тест</name>
                    <surname>Тестов</surname>
                    <patronymic>Тестович</patronymic>
                    <birthDate>1990-01-01</birthDate>
                    <gender>MAN</gender>
                    <document>
                        <series>1333</series>
                        <type>PASSPORT</type>
                        <issueDate>2020-01-01</issueDate>
                    </document>
                </person>
                """;

        String expXslt = """
                <person name="Тест" surname="Тестов" patronymic="Тестович" birthDate="01.01.1990" gender="MAN">
                    <document series="1333" number="" type="PASSPORT" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void convertXmlWithoutDocumentTypeElement() {
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
                        <type></type>
                        <issueDate>2020-01-01</issueDate>
                    </document>
                </person>
                """;

        String expXslt = """
                <person name="Тест" surname="Тестов" patronymic="Тестович" birthDate="01.01.1990" gender="MAN">
                    <document series="1333" number="112233" type="" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void convertXmlWithoutIssueDateElement() {
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
                    </document>
                </person>
                """;

        String expXslt = """
                <person name="Тест" surname="Тестов" patronymic="Тестович" birthDate="01.01.1990" gender="MAN">
                    <document series="1333" number="112233" type="PASSPORT" issueDate=""/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    // Не знаю, как обрабатывать такой случай с точки зрения требуемой бизнес-логики,
    // поэтому буду считать, что все ключи должны остаться, а значения просто быть пустыми
    public void convertXmlWithoutDocumentElement() {
        String xml = """
                <person>
                    <name>Тест</name>
                    <surname>Тестов</surname>
                    <patronymic>Тестович</patronymic>
                    <birthDate>1990-01-01</birthDate>
                    <gender>MAN</gender>
                </person>
                """;

        String expXslt = """
                <person name="Тест" surname="Тестов" patronymic="Тестович" birthDate="01.01.1990" gender="MAN">
                    <document series="" number="" type="" issueDate=""/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void severalElementsNotSet() {
        String xml = """
                <person>
                    <surname>Тестов</surname>
                    <patronymic>Тестович</patronymic>
                    <birthDate>1990-01-01</birthDate>
                    <document>
                        <series>1333</series>
                        <type>PASSPORT</type>
                        <issueDate>2020-01-01</issueDate>
                    </document>
                </person>
                """;

        String expXslt = """
                <person name="" surname="Тестов" patronymic="Тестович" birthDate="01.01.1990" gender="">
                    <document series="1333" number="" type="PASSPORT" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void allElementsNotSetExcludeRootPersonTag() {
        String xml = """
                <person>
                </person>
                """;

        String expXslt = """
                <person name="" surname="" patronymic="" birthDate="" gender="">
                    <document series="" number="" type="" issueDate=""/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void allElementsNotSet() {
        String xml = """
                """;

        IncorrectXmlException incorrectXmlException = assertThrows(
                IncorrectXmlException.class,
                () -> {
                    soapService.convertXmlToXslt(xml);
                }
        );
    }

    @Test
    public void emptyStringElement() {
        String xml = """
                <person>
                    <name></name>
                    <surname>Тестов</surname>
                    <patronymic></patronymic>
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
                <person name="" surname="Тестов" patronymic="" birthDate="01.01.1990" gender="MAN">
                    <document series="1333" number="112233" type="PASSPORT" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }

    @Test
    public void emptyDateElement() {
        String xml = """
                <person>
                    <name>Тест</name>
                    <surname>Тестов</surname>
                    <patronymic>Тестович</patronymic>
                    <birthDate></birthDate>
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
                <person name="Тест" surname="Тестов" patronymic="Тестович" birthDate="" gender="MAN">
                    <document series="1333" number="112233" type="PASSPORT" issueDate="01.01.2020"/>
                </person>
                """;

        String actualXslt = soapService.convertXmlToXslt(xml);

        assertEquals(expXslt, actualXslt);
    }
}
