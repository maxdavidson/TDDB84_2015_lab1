package interpreter.lisp;

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
        Expr definition = Reader.read(fac).evaluate(Context.getTopLevelContext());
        assertThat(definition, is(instanceOf(CompoundExpression.class)));
    }

    @Test
    public void evaluateApplyFac() {
        Reader.read(fac).evaluate(Context.getTopLevelContext());
        Expr value = Reader.read(applyFac).evaluate(Context.getTopLevelContext());
        assertEquals(new Num(120), value);
    }

}
