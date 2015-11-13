package interpreter.lisp.expressions.semantic;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.ExpressionBase;
import interpreter.lisp.expressions.syntactic.Symbol;
import interpreter.lisp.visitors.Visitor;


public abstract class VariableDefinition extends ExpressionBase {

    public abstract Symbol getName();

    public abstract Expression getValue();

    @Override
    public final <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
