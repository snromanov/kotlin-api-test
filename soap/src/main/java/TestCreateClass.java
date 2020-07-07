import org.tempuri.Calculator;

import java.net.MalformedURLException;
import java.net.URI;

class TestCreateClass {
    public static void main(String[] args) throws MalformedURLException {
        final var calculatorSoap12 = new Calculator(
                URI.create("http://www.dneonline.com/calculator.asmx?wsdl").toURL()
        ).getCalculatorSoap12();

        System.out.println("Res: " + calculatorSoap12.add(1, 2));
    }
}
