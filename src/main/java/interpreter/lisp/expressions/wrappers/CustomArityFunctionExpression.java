package interpreter.lisp.expressions.wrappers;

import interpreter.lisp.Context;
import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.FunctionExpression;
import interpreter.lisp.expressions.syntactic.Symbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class CustomArityFunctionExpression extends FunctionExpression {

    // Stores parameter symbols
    public static final List<Symbol> parameters = new ArrayList<>();

    private final int arity;
    private final List<? extends Expression> body;
    private final Context context;

    public CustomArityFunctionExpression(int arity, List<? extends Expression> body, Context context) {
        this.context = context;
        this.arity = arity;
        this.body = body;

        // Increase size of the static parameter list if needed
        for (int i = parameters.size(); i < arity; ++i) {
            parameters.add(i, Symbol.of("param" + i));
        }
    }

    public CustomArityFunctionExpression(int arity, Expression body, Context context) {
        this(arity, Collections.singletonList(body), context);
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public List<Symbol> getParameters() {
        return parameters.subList(0, arity);
    }

    @Override
    public List<? extends Expression> getBody() {
        return body;
    }
}
