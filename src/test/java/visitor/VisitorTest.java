package visitor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class VisitorTest {

    public static Expression expression =
            new Sum(new Minus(new Number(1), new Variable("x", 3)),
                    new Sum(new Number(5), new Variable("y", 7)));

    @Test
    public void evaluate() {
        EvaluationVisitor evaluator = new EvaluationVisitor();

        expression.accept(evaluator);

        assertEquals(10, evaluator.getResult());
    }

    @Test
    public void printing() {
        PrintVisitor printer = new PrintVisitor();

        expression.accept(printer);

        assertEquals(expression.toString(), printer.toString());
    }

    @Test
    public void counting() {
        DepthFirstVisitorWrapper<CountingVariablesVisitor> wrapper =
                new DepthFirstVisitorWrapper<>(new CountingVariablesVisitor());

        expression.accept(wrapper);

        assertEquals(2, wrapper.delegate.getCount());
    }
}
