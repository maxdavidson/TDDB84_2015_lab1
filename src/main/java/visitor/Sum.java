package visitor;

public final class Sum extends CompoundExpression {

    public Sum(Expression left, Expression right) {
        super(left, right, "+");
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
