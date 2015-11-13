package interpreter.lisp.expressions.wrappers;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.ConditionalExpression;
import interpreter.lisp.expressions.syntactic.CompoundExpression;


/**
 * Wraps a compound expression and treats it as a conditional.
 */
public final class ConditionalWrapper extends ConditionalExpression {

    public final CompoundExpression delegate;

    public ConditionalWrapper(CompoundExpression delegate) {
        this.delegate = delegate;
    }

    @Override
    public Expression getCondition() {
        return delegate.elements.get(1);
    }

    @Override
    public Expression getConsequent() {
        return delegate.elements.get(2);
    }

    @Override
    public Expression getAntecedent() {
        return delegate.elements.get(3);
    }
}
