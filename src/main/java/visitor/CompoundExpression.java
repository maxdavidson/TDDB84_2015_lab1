package visitor;

import java.text.MessageFormat;


public abstract class CompoundExpression implements Expression {

    public final Expression left;
    public final Expression right;
    public final String operator;

    protected CompoundExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                ((left instanceof CompoundExpression) ? "({0})" : "{0}")
                + " {1} " +
                ((right instanceof CompoundExpression) ? "({2})" : "{2}"),
                left, operator, right);
    }
}
