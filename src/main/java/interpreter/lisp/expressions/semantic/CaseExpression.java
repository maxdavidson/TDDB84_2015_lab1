package interpreter.lisp.expressions.semantic;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.ExpressionBase;
import interpreter.lisp.visitors.Visitor;

import java.util.Map;


public abstract class CaseExpression extends ExpressionBase {

    public abstract Expression getCondition();

    public abstract Map<Expression, Expression> getCases();

    @Override
    public final <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
