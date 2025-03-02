package axiomatika.converter.soapservice.controller;

import axiomatika.converter.soapservice.service.SoapService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SoapEndpoint {

    private final static String NAMESPACE_URI = "http://localhost:8081/";

    private final SoapService soapService;

    public SoapEndpoint(SoapService soapService) {
        this.soapService = soapService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "string")
    @ResponsePayload
    public String getXslt(@RequestPayload String request) {
        return soapService.convertXmlToXslt(request);
    }
}
