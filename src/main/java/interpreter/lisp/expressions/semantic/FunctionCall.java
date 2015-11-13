package interpreter.lisp.expressions.semantic;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.ExpressionBase;
import interpreter.lisp.visitors.Visitor;

import java.util.List;


public abstract class FunctionCall extends ExpressionBase {

    public abstract FunctionExpression getFunction();

    public abstract List<? extends Expression> getArguments();

    @Override
    public final <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
