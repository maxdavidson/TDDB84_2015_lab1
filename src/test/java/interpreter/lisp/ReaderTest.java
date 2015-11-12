package interpreter.lisp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ReaderTest {

    @Test
    public void functionDefinition() {
        String functionDefinition = "(def fac (x) (if (= x 0) 1 (* x (fac (- x 1)))))";
        assertEquals(CompoundExpression.class, Reader.read(functionDefinition).getClass());
    }

    @Test
    public void readSimpleExpression() {
        assertEquals(new Num(1), Reader.read("1"));
    }

    @Test
    public void readVariable() {
        assertEquals(new Symbol("x"), Reader.read("x"));
    }


}
