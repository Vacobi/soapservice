package axiomatika.converter.soapservice.controller;

import axiomatika.converter.soapservice.dto.XlstResponseDto;
import axiomatika.converter.soapservice.dto.XsltRequestDto;
import axiomatika.converter.soapservice.service.SoapService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SoapEndpoint {

    private final static String NAMESPACE_URI = "http://localhost:8081/ws/server";

    private final SoapService soapService;

    public SoapEndpoint(SoapService soapService) {
        this.soapService = soapService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getXsltRequest")
    @ResponsePayload
    public XlstResponseDto getXslt(@RequestPayload XsltRequestDto request) {
        System.out.println("\n\n\n\n\nGet XSLT Request\n\n\n\n\n"); // Не выводится, значит не заходит сюда
        return new XlstResponseDto(soapService.convertXmlToXslt(request.getXmlData()));
    }
}
