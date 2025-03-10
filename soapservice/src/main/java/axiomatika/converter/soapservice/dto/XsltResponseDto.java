package axiomatika.converter.soapservice.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://localhost:8081/ws/server", name = "getXsltResponse")
public class XsltResponseDto {
    private String xsltResult;

    public XsltResponseDto() {
        ;
    }

    public XsltResponseDto(String xsltResult) {
        this.xsltResult = xsltResult;
    }

    @XmlElement(namespace = "http://localhost:8081/ws/server", name = "xsltResult")
    public String getXsltResult() {
        return xsltResult;
    }

    public void setXsltResult(String xsltResult) {
        this.xsltResult = xsltResult;
    }
}
