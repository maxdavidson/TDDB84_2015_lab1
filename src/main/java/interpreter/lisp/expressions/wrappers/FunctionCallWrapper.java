package interpreter.lisp.expressions.wrappers;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.FunctionCall;
import interpreter.lisp.expressions.semantic.FunctionExpression;
import interpreter.lisp.expressions.syntactic.CompoundExpression;

import java.util.List;


/**
 * Wraps a compound expression and treats it as a function call.
 * Syntax: (name ...arguments)
 */
public final class FunctionCallWrapper extends FunctionCall {

    private final FunctionExpression function;
    private final List<? extends Expression> arguments;

    public FunctionCallWrapper(FunctionExpression function, CompoundExpression expr) {
        this.function = function;
        this.arguments = expr.elements.subList(1, expr.elements.size());
    }

    @Override
    public FunctionExpression getFunction() {
        return function;
    }

    @Override
    public List<? extends Expression> getArguments() {
        return arguments;
    }
}
