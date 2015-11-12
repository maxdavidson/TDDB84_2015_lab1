package visitor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ExpressionTest {

    public static Expression expression =
            new Sum(new Minus(new Number(1), new Variable("x", 3)),
                    new Sum(new Number(5), new Variable("y", 7)));

    @Test
    public void printing() {
        assertEquals("(1 - x) + (5 + y)", expression.toString());
    }
}
