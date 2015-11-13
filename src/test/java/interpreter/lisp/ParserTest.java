package interpreter.lisp;

import interpreter.lisp.expressions.syntactic.CompoundExpression;
import interpreter.lisp.expressions.syntactic.Num;
import interpreter.lisp.expressions.syntactic.Symbol;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ParserTest {

    @Test
    public void readFunctionDefinition() {
        String functionDefinition = "(def fac (x) (if (= x 0) 1 (* x (fac (- x 1)))))";
        assertEquals(CompoundExpression.class, Parser.read(functionDefinition).getClass());
    }

    @Test
    public void readSimpleExpression() {
        assertEquals(new Num(1), Parser.read("1"));
    }

    @Test
    public void readVariable() {
        assertEquals(Symbol.of("x"), Parser.read("x"));
    }
}
