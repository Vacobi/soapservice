package axiomatika.converter.soapservice.exception;

public class IncorrectXmlException extends RuntimeException {
    public IncorrectXmlException(String xml) {
        super("Entered xml: \n" + xml + "\nIs incorrect");
    }
}
