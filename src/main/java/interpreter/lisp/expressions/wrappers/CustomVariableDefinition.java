package interpreter.lisp.expressions.wrappers;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.VariableDefinition;
import interpreter.lisp.expressions.syntactic.Symbol;


public final class CustomVariableDefinition extends VariableDefinition {

    private final Symbol name;
    private final Expression value;

    public CustomVariableDefinition(Symbol name, Expression value) {
        this.name = name;
        this.value = value;
    }

    public CustomVariableDefinition(String name, Expression value) {
        this(Symbol.of(name), value);
    }

    @Override
    public Symbol getName() {
        return name;
    }

    @Override
    public Expression getValue() {
        return value;
    }
}
