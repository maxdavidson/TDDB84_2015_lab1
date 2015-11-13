package interpreter.lisp.expressions.syntactic;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.ExpressionBase;
import interpreter.lisp.visitors.Visitor;

import java.util.List;


public final class CompoundExpression extends ExpressionBase {

    public final List<? extends Expression> elements;

    public CompoundExpression(List<? extends Expression> elements) {
        this.elements = elements;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
