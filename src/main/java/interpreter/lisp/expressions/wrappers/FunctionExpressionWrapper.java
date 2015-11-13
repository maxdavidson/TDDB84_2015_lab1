package interpreter.lisp.expressions.wrappers;

import interpreter.lisp.Context;
import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.FunctionExpression;
import interpreter.lisp.expressions.syntactic.CompoundExpression;
import interpreter.lisp.expressions.syntactic.Symbol;

import java.util.List;

/**
 * Wraps a compound expression and treats it as a function expression.
 * Syntax: (lambda (...parameters) body)
 */
public class FunctionExpressionWrapper extends FunctionExpression {

    public final CompoundExpression delegate;
    private final Context context;

    public FunctionExpressionWrapper(CompoundExpression delegate, Context context) {
        this.delegate = delegate;
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Symbol> getParameters() {
        return (List<Symbol>) ((CompoundExpression) delegate.elements.get(1)).elements;
    }

    @Override
    public List<? extends Expression> getBody() {
        return delegate.elements.subList(2, delegate.elements.size());
    }
}
