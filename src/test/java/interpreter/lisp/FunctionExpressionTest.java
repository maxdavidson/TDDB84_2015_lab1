package interpreter.lisp;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.FunctionExpression;
import interpreter.lisp.expressions.syntactic.Constant;
import interpreter.lisp.expressions.syntactic.Num;
import interpreter.lisp.visitors.Evaluator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class FunctionExpressionTest {

    private Evaluator evaluator;

    @Before
    public void setUp() {
        evaluator = new Evaluator(new Context());
    }

    private Expression eval(String source) {
        return Parser.read(source).accept(evaluator);
    }

    @Test
    public void functionDeclaration() {
        eval("(def twice (x) (* 2 x))");

        assertThat(eval("twice"), is(instanceOf(FunctionExpression.class)));
    }

    @Test
    public void callable0() {
        eval("(def one () 1)");

        assertEquals(new Num(1), eval("(one)"));
    }

    @Test
    public void callable1() {
        eval("(def id (x) x)");

        assertEquals(new Num(5), eval("(id 5)"));
    }

    @Test
    public void callable2() {
        eval("(def plus (a b) (+ a b))");

        assertEquals(new Num(13), eval("(plus 6 7)"));
    }

    @Test
    public void callable3() {
        eval("(def sum (a b c) (+ a (+ b c)))");

        assertEquals(new Num(6), eval("(sum 1 2 3)"));
    }

    @Test
    public void isLambdaExpression() {
        eval("(set twice (lambda (x) (* 2 x)))");

        assertThat(eval("twice"), is(instanceOf(FunctionExpression.class)));
    }

    @Test
    public void hasLexicalScope() {
        eval("(def add (a) (lambda (b) (+ a b)))");

        assertEquals(new Num(16), eval("((add 5) 11)"));
    }

    @Test
    public void hasLocalScope() {
        eval("(set x 1)");
        eval("(def set-x () (set x 2))");

        // Should modify the local variable "x" inside the function scope
        eval("(set-x)");

        assertEquals(new Num(1), eval("x"));
    }

    @Test
    public void supportsMultiExpressionBody() {
        eval("(def doStuff () (set x 2) (set y 4) (+ x y))");

        assertEquals(new Num(6), eval("(doStuff)"));
        assertEquals(Constant.NIL, eval("x"));
        assertEquals(Constant.NIL, eval("y"));
    }
}
