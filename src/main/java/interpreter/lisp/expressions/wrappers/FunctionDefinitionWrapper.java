package interpreter.lisp.expressions.wrappers;

import interpreter.lisp.Context;
import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.FunctionExpression;
import interpreter.lisp.expressions.semantic.VariableDefinition;
import interpreter.lisp.expressions.syntactic.CompoundExpression;
import interpreter.lisp.expressions.syntactic.Symbol;

import java.util.List;


/**
 * Wraps a compound expression and treats it as a function definition,
 * i.e. a variable definition of a function expression.
 * Syntax: (def name (...parameters) body)
 */
public final class FunctionDefinitionWrapper extends VariableDefinition {

    public final CompoundExpression delegate;
    private final Context context;

    public FunctionDefinitionWrapper(CompoundExpression delegate, Context context) {
        this.delegate = delegate;
        this.context = context;
    }

    @Override
    public Symbol getName() {
        return (Symbol) delegate.elements.get(1);
    }

    @Override
    public FunctionExpression getValue() {
        return new CustomFunctionExpression(getParameters(), getBody(), context);
    }

    @SuppressWarnings("unchecked")
    public List<Symbol> getParameters() {
        return (List<Symbol>) ((CompoundExpression) delegate.elements.get(2)).elements;
    }

    public List<? extends Expression> getBody() {
        return delegate.elements.subList(3, delegate.elements.size());
    }
}
