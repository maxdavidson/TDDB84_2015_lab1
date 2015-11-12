package visitor;

public final class Minus extends CompoundExpression {

    public Minus(Expression left, Expression right) {
        super(left, right, "-");
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
