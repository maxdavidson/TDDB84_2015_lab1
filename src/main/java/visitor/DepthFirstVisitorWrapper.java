package visitor;


public class DepthFirstVisitorWrapper<T extends Visitor> implements Visitor {

    public final T delegate;

    public DepthFirstVisitorWrapper(T delegate) {
        this.delegate = delegate;
    }

    @Override
    public void visit(SimpleExpression exp) {
        exp.accept(delegate);
    }

    @Override
    public void visit(CompoundExpression exp) {
        exp.left.accept(this);
        exp.right.accept(this);
        exp.accept(delegate);
    }
}
