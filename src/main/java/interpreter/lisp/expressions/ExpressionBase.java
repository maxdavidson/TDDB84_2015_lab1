package interpreter.lisp.expressions;

import interpreter.lisp.Context;
import interpreter.lisp.visitors.Evaluator;
import interpreter.lisp.visitors.Printer;

import java.util.Map;
import java.util.WeakHashMap;


/**
 * This abstract base class is used to delegate default behavior to visitors.
 */
public abstract class ExpressionBase implements Expression {

    private static Map<Context, Evaluator> cache = new WeakHashMap<>();
    private static Printer printer = new Printer();

    @Override
    public final String toString() {
        return accept(printer);
    }

    @Override
    public final Expression evaluate(Context context) {
        return accept(cache.computeIfAbsent(context, Evaluator::new));
    }
}
