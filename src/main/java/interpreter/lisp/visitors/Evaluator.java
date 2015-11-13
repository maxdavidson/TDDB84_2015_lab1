package interpreter.lisp.visitors;

import interpreter.lisp.Context;
import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.*;
import interpreter.lisp.expressions.syntactic.CompoundExpression;
import interpreter.lisp.expressions.syntactic.Constant;
import interpreter.lisp.expressions.syntactic.Num;
import interpreter.lisp.expressions.syntactic.Symbol;
import interpreter.lisp.expressions.wrappers.*;

import java.util.List;
import java.util.Map;


public class Evaluator implements Visitor<Expression> {

    private final Context context;

    public Evaluator() {
        this.context = new Context();
    }

    public Evaluator(Context context) {
        this.context = context;
    }

    @Override
    public Expression visit(CompoundExpression expr) {
        if (expr.elements.isEmpty()) {
            return Constant.NIL;
        }

        // The first expression in the list dictates the behavior
        Expression first = expr.elements.get(0);

        if (first instanceof Symbol) {
            Symbol symbol = (Symbol) first;

            switch (symbol.name) {
                case "def":
                    return new FunctionDefinitionWrapper(expr, context).accept(this);
                case "set":
                    return new VariableDefinitionWrapper(expr).accept(this);
                case "if":
                    return new ConditionalWrapper(expr).accept(this);
                case "case":
                    return new CaseExpressionWrapper(expr).accept(this);
                case "list":
                    return ListExpression.from(expr.elements.subList(1, expr.elements.size())).accept(this);
                case "lambda":
                    return new FunctionExpressionWrapper(expr, context).accept(this);
            }
        }

        // Evaluate the first symbol and check if the result is a function expression
        Expression result = first.accept(this);
        if (result instanceof FunctionExpression) {
            return new FunctionCallWrapper((FunctionExpression) result, expr).accept(this);
        }

        // No match, return nil
        return Constant.NIL;
    }

    @Override
    public Expression visit(FunctionCall expr) {
        FunctionExpression function = expr.getFunction();

        List<Symbol> parameters = function.getParameters();
        List<? extends Expression> arguments = expr.getArguments();

        // Create a new stack frame for the function call
        Context localContext = new Context(function.getContext());

        // For all arguments, accept the expressions and store
        // the result at the parameter symbol in the new context.
        for (int i = 0, length = arguments.size(); i < length; ++i) {
            Symbol parameter = parameters.get(i);
            Expression argument = arguments.get(i).accept(this);

            localContext.put(parameter, argument);
        }

        Evaluator localEvaluator = new Evaluator(localContext);
        Expression result = Constant.NIL;

        // Evaluate all expression in the body, and return the last one
        for (Expression bodyPart : function.getBody()) {
            result = bodyPart.accept(localEvaluator);
        }

        return result;
    }

    @Override
    public Expression visit(FunctionExpression expr) {
        return expr;
    }

    @Override
    public Expression visit(VariableDefinition expr) {
        Expression result = expr.getValue().accept(this);
        context.put(expr.getName(), result);
        return result;
    }

    @Override
    public Expression visit(ConditionalExpression expr) {
        return expr.getCondition().accept(this) == Constant.TRUE
                ? expr.getConsequent().accept(this)
                : expr.getAntecedent().accept(this);
    }

    @Override
    public Expression visit(ListExpression expr) {
        return expr;
    }

    @Override
    public Expression visit(CaseExpression expr) {
        Expression condition = expr.getCondition().accept(this);

        Map<Expression, Expression> cases = expr.getCases();

        // Need to evaluate keys
        for (Expression key : cases.keySet()) {
            if (key.accept(this).equals(condition)) {
                return cases.get(key).accept(this);
            }
        }

        return Constant.FALSE;
    }

    @Override
    public Expression visit(Constant expr) {
        return expr;
    }

    @Override
    public Expression visit(Symbol expr) {
        return context.get(expr);
    }

    @Override
    public Expression visit(Evaluable expr) {
        return expr.customEvaluate(context);
    }

    @Override
    public Expression visit(Num expr) {
        return expr;
    }

    @Override
    public Expression visit(Expression expr) {
        return expr;
    }

    @Override
    public Expression visit(Visitable expr) {
        return Constant.NIL;
    }
}
