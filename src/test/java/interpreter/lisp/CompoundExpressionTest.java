package interpreter.lisp;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.FunctionExpression;
import interpreter.lisp.expressions.syntactic.Num;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class CompoundExpressionTest {

    private static final String fac = "(def fac (x) (if (= x 0) 1 (* x (fac (- x 1)))))";
    private static final String applyFac = "(fac 5)";

    @Test
    public void evaluateFac() {
        Expression definition = Parser.read(fac).evaluate(Context.GLOBAL);

        assertThat(definition, is(instanceOf(FunctionExpression.class)));
    }

    @Test
    public void evaluateApplyFac() {
        Parser.read(fac).evaluate(Context.GLOBAL);

        Expression value = Parser.read(applyFac).evaluate(Context.GLOBAL);

        assertEquals(new Num(120), value);
    }
}
