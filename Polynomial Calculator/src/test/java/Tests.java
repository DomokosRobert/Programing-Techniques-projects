import com.company.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @Test
    void testAddition() {
        Polynomial A,B;
        A=Polynomial.read("4x^2+3x^1+3x^0");
        B=Polynomial.read("3x^2+2x^1");
        assertEquals("+7x^2+5x^1+3x^0",Polynomial.Addition(A,B).toString());
    }
    @Test
    void testSubstraction() {
        Polynomial A,B;
        A=Polynomial.read("4x^2+3x^1");
        B=Polynomial.read("3x^2+2x^1");
        assertEquals("+1x^2+1x^1",Polynomial.Substract(A,B).toString());
    }
    @Test
    void testMultiplication() {
        Polynomial A,B;
        A=Polynomial.read("4x^2+3x^1");
        B=Polynomial.read("3x^2+2x^1");
        assertEquals("+12x^4+17x^3+6x^2",Polynomial.Multiplication(A,B).toString());
    }
    @Test
    void testDerivate() {
        Polynomial A;
        A=Polynomial.read("4x^2+3x^1");
        assertEquals("+8x^1+3x^0",Polynomial.Derivate(A).toString());
    }
    @Test
    void testIntegration() {
        Polynomial A;
        A=Polynomial.read("3x^2+4x^1");
        assertEquals("+1x^3+2x^2",Polynomial.Integrate(A).toString());
    }
    @Test
    void testDivision() {
        Polynomial A,B;
        A=Polynomial.read("4x^2+3x^1");
        B=Polynomial.read("4x^2+3x^1");
        assertEquals("",Polynomial.Division(A,B).toString());
    }

}
