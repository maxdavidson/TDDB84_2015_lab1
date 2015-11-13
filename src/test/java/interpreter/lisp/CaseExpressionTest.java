package interpreter.lisp;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.syntactic.Constant;
import interpreter.lisp.expressions.syntactic.Num;
import interpreter.lisp.visitors.Evaluator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CaseExpressionTest {

    private Evaluator evaluator;

    @Before
    public void setUp() {
        evaluator = new Evaluator(new Context());
    }

    private Expression eval(String source) {
        return Parser.read(source).accept(evaluator);
    }

    @Test
    public void example1() {
        assertEquals(new Num(1), eval("(case 1 (2 0) ((* 1 1) 1))"));
    }

    @Test
    public void example2() {
        Expression result = eval("(case (* 3 (+ 1 2)) ((+ 6 2) 0) (14 2) ((+ 6 3) (- 2 1)))");

        assertEquals(new Num(1), result);
    }

    @Test
    public void example3() {
        eval("(def f (x) (case x (1 0)))");

        assertEquals(Constant.FALSE, eval("(f 0)"));
    }
}
