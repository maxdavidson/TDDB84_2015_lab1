package interpreter.lisp.expressions.syntactic;

import interpreter.lisp.expressions.ExpressionBase;
import interpreter.lisp.visitors.Visitor;


public final class Num extends ExpressionBase {

    public final double value;

    public Num(double value) {
        this.value = value;
    }

    @Override
    public <U> U accept(Visitor<U> visitor) {
        return visitor.visit(this);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public boolean equals(Object other) {
        return (other.getClass() == Num.class) && ((Num) other).value == value;
    }
}
