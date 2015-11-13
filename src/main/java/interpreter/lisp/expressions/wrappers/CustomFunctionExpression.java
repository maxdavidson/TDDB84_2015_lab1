package interpreter.lisp.expressions.wrappers;

import interpreter.lisp.Context;
import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.FunctionExpression;
import interpreter.lisp.expressions.syntactic.Symbol;

import java.util.Collections;
import java.util.List;


public class CustomFunctionExpression extends FunctionExpression {

    private final List<Symbol> parameters;
    private final List<? extends Expression> body;
    private final Context context;

    public CustomFunctionExpression(List<Symbol> parameters, List<? extends Expression> body, Context context) {
        this.parameters = parameters;
        this.body = body;
        this.context = context;
    }

    public CustomFunctionExpression(List<Symbol> parameters, Expression body, Context context) {
        this(parameters, Collections.singletonList(body), context);
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public List<Symbol> getParameters() {
        return parameters;
    }

    @Override
    public List<? extends Expression> getBody() {
        return body;
    }
}
