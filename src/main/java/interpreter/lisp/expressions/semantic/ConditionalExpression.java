package interpreter.lisp.expressions.semantic;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.ExpressionBase;
import interpreter.lisp.visitors.Visitor;


public abstract class ConditionalExpression extends ExpressionBase {

    public abstract Expression getCondition();

    public abstract Expression getConsequent();

    public abstract Expression getAntecedent();

    @Override
    public final <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
