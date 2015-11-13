package interpreter.lisp.expressions.wrappers;

import interpreter.lisp.Context;
import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.Evaluable;

import java.util.function.Function;


public final class CustomEvaluable extends Evaluable {

    public final Function<Context, Expression> evaluator;

    public CustomEvaluable(Function<Context, Expression> evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public Expression customEvaluate(Context context) {
        return evaluator.apply(context);
    }
}
