package interpreter.lisp.expressions.syntactic;

import interpreter.lisp.Context;
import interpreter.lisp.expressions.Expression;
import interpreter.lisp.visitors.Evaluator;
import interpreter.lisp.visitors.Visitor;


public enum Constant implements Expression {

    TRUE, FALSE, NIL;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Expression evaluate(Context context) {
        return accept(new Evaluator(context));
    }
}
