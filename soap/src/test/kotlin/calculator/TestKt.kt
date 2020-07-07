package calculator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.tempuri.Calculator
import java.net.URI

class TestKt {

    @Test
    fun `check add`() {
        Calculator(
            URI.create("http://www.dneonline.com/calculator.asmx?wsdl").toURL()
        ).calculatorSoap12.also {

            it.add(1, 3).shouldBe(4)
        }
    }
}
