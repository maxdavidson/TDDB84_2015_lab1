package visitor;


public interface Visitor {

    default void visit(Number exp) {
        visit((SimpleExpression) exp);
    }

    default void visit(Variable exp) {
        visit((SimpleExpression) exp);
    }

    default void visit(Sum exp) {
        visit((CompoundExpression) exp);
    }

    default void visit(Minus exp) {
        visit((CompoundExpression) exp);
    }

    default void visit(SimpleExpression exp) {
        visit((Expression) exp);
    }

    default void visit(CompoundExpression exp) {
        visit((Expression) exp);
    }

    default void visit(Expression exp) {
        // Do nothing by default
    }
}
