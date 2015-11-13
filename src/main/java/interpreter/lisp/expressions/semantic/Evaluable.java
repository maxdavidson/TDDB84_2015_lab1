package interpreter.lisp.expressions.semantic;

import interpreter.lisp.Context;
import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.ExpressionBase;
import interpreter.lisp.visitors.Visitor;

/**
 * This class wraps an custom evaluator function that the visitor
 * evaluator delegates usage to.
 */
public abstract class Evaluable extends ExpressionBase {

    public abstract Expression customEvaluate(Context context);

    @Override
    public final <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
