package visitor;


public final class EvaluationVisitor implements Visitor {

    private int result;

    public int getResult() {
        return result;
    }

    @Override
    public void visit(Number exp) {
        result = exp.getValue();
    }

    @Override
    public void visit(Variable exp) {
        result = exp.getValue();
    }

    @Override
    public void visit(Sum exp) {
        EvaluationVisitor v1 = new EvaluationVisitor();
        EvaluationVisitor v2 = new EvaluationVisitor();

        exp.left.accept(v1);
        exp.right.accept(v2);

        result = v1.getResult() + v2.getResult();
    }

    @Override
    public void visit(Minus exp) {
        EvaluationVisitor v1 = new EvaluationVisitor();
        EvaluationVisitor v2 = new EvaluationVisitor();

        exp.left.accept(v1);
        exp.right.accept(v2);

        result = v1.getResult() - v2.getResult();
    }
}
