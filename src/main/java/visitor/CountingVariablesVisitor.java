package visitor;


public final class CountingVariablesVisitor implements Visitor {

    private int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public void visit(Variable exp) {
        count++;
    }
}
