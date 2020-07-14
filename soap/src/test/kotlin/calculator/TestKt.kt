package calculator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.tempuri.Calculator
import java.net.URI
import java.util.stream.Stream

class TestKt {
    private val calc = Calculator(URI.create("http://www.dneonline.com/calculator.asmx?wsdl").toURL()).calculatorSoap12

    @Test
    fun `check add`() {
        calc.add(1, 3).shouldBe(4)
    }

    @ParameterizedTest
    @MethodSource("intProvider")
    fun `Test with custom arguments provider`(argument: Int) {
        calc.add(argument, argument).shouldBe(Math.addExact(argument, argument))
    }

    companion object {
        @JvmStatic
        fun intProvider(): Stream<Int> = Stream.of(3, 5, 6, 7)
    }
}
