package visitor;


public final class PrintVisitor implements Visitor {

    private final boolean hasParent;
    private final StringBuilder builder = new StringBuilder();

    public PrintVisitor() {
        this.hasParent = false;
    }

    private PrintVisitor(boolean hasParent) {
        this.hasParent = hasParent;
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    @Override
    public void visit(Number exp) {
        builder.append(exp.getValue());
    }

    @Override
    public void visit(Variable exp) {
        builder.append(exp.getName());
    }

    @Override
    public void visit(Sum exp) {
        visit(exp, "+");
    }

    @Override
    public void visit(Minus exp) {
        visit(exp, "-");
    }

    private void visit(CompoundExpression exp, String operator) {
        if (hasParent) {
            builder.append("(");
        }

        PrintVisitor v1 = new PrintVisitor(true);
        exp.left.accept(v1);
        builder.append(v1.toString());

        builder.append(" ");
        builder.append(operator);
        builder.append(" ");

        PrintVisitor v2 = new PrintVisitor(true);
        exp.right.accept(v2);
        builder.append(v2.toString());

        if (hasParent) {
            builder.append(")");
        }
    }
}
