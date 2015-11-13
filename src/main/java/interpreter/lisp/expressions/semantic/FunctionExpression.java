package interpreter.lisp.expressions.semantic;

import interpreter.lisp.Context;
import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.ExpressionBase;
import interpreter.lisp.expressions.syntactic.Num;
import interpreter.lisp.expressions.syntactic.Symbol;
import interpreter.lisp.expressions.wrappers.CustomArityFunctionExpression;
import interpreter.lisp.expressions.wrappers.CustomEvaluable;
import interpreter.lisp.visitors.Visitor;

import java.util.List;
import java.util.function.*;


/**
 * Represents a callable function expression.
 */
public abstract class FunctionExpression extends ExpressionBase {

    // Unary custom function
    @SuppressWarnings("unchecked")
    public static <T extends Expression, R extends Expression>
    FunctionExpression fromFunction(Function<T, R> body) {
        return new CustomArityFunctionExpression(1,
                new CustomEvaluable(ctx -> {
                    Symbol p0 = CustomArityFunctionExpression.parameters.get(0);
                    return Expression.from(body.apply((T) ctx.get(p0)));
                }), Context.GLOBAL);
    }

    // Binary custom function
    @SuppressWarnings("unchecked")
    public static <T extends Expression, U extends Expression, R extends Expression>
    FunctionExpression fromFunction(BiFunction<T, U, R> body) {
        return new CustomArityFunctionExpression(2,
                new CustomEvaluable(ctx -> {
                    Symbol p0 = CustomArityFunctionExpression.parameters.get(0);
                    Symbol p1 = CustomArityFunctionExpression.parameters.get(1);
                    return Expression.from(body.apply((T) ctx.get(p0), (U) ctx.get(p1)));
                }), Context.GLOBAL);
    }

    public static FunctionExpression fromNumericFunction(DoubleUnaryOperator body) {
        return fromFunction((Num a) -> Expression.from(body.applyAsDouble(a.value)));
    }

    public static FunctionExpression fromNumericFunction(DoubleBinaryOperator body) {
        return fromFunction((Num a, Num b) -> Expression.from(body.applyAsDouble(a.value, b.value)));
    }

    public static <T extends Expression> FunctionExpression fromPredicate(Predicate<T> tester) {
        return fromFunction((T a) -> Expression.from(tester.test(a)));
    }

    public static <T extends Expression> FunctionExpression fromPredicate(BiPredicate<T, T> tester) {
        return fromFunction((T a, T b) -> Expression.from(tester.test(a, b)));
    }

    public static FunctionExpression fromNumericPredicate(Predicate<Double> tester) {
        return fromPredicate((Num a) -> tester.test(a.value));
    }

    public static FunctionExpression fromNumericPredicate(BiPredicate<Double, Double> tester) {
        return fromPredicate((Num a, Num b) -> tester.test(a.value, b.value));
    }

    // A function expression is a closure, so it has lexical scope,
    // i.e. it captures the context where it is defined.
    public abstract Context getContext();

    public abstract List<Symbol> getParameters();

    // A function body may consist of multiple expressions,
    // which are all evaluated, and the last is returned
    public abstract List<? extends Expression> getBody();

    @Override
    public final <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
