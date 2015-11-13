package interpreter.lisp.expressions.wrappers;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.VariableDefinition;
import interpreter.lisp.expressions.syntactic.CompoundExpression;
import interpreter.lisp.expressions.syntactic.Symbol;

/**
 * Wraps a compound expression and treats it as a variable definition.
 * Syntax: (set name value)
 */
public class VariableDefinitionWrapper extends VariableDefinition {

    public final CompoundExpression delegate;

    public VariableDefinitionWrapper(CompoundExpression delegate) {
        this.delegate = delegate;
    }

    @Override
    public Symbol getName() {
        return (Symbol) delegate.elements.get(1);
    }

    @Override
    public Expression getValue() {
        return delegate.elements.get(2);
    }
}
