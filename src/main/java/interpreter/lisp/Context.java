package interpreter.lisp;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.FunctionExpression;
import interpreter.lisp.expressions.semantic.ListExpression;
import interpreter.lisp.expressions.syntactic.Constant;
import interpreter.lisp.expressions.syntactic.Symbol;

import java.util.HashMap;
import java.util.Map;


public class Context {

    public static final Context GLOBAL = new Context(null);

    static {
        // Default bindings:

        // Log
        bind("=", FunctionExpression.fromPredicate(Expression::equals));
        bind("or", FunctionExpression.fromPredicate((a, b) -> (a == Constant.TRUE) || (b == Constant.TRUE)));
        bind("and", FunctionExpression.fromPredicate((a, b) -> (a == Constant.TRUE) && (b == Constant.TRUE)));
        bind("not", FunctionExpression.fromPredicate(a -> a == Constant.FALSE || a == Constant.NIL));

        // Comparison operators
        bind("<", FunctionExpression.fromNumericPredicate((a, b) -> a < b));
        bind(">", FunctionExpression.fromNumericPredicate((a, b) -> a > b));
        bind("<=", FunctionExpression.fromNumericPredicate((a, b) -> a <= b));
        bind(">=", FunctionExpression.fromNumericPredicate((a, b) -> a >= b));
        // Numeric operators
        bind("+", FunctionExpression.fromNumericFunction((a, b) -> a + b));
        bind("-", FunctionExpression.fromNumericFunction((a, b) -> a - b));
        bind("*", FunctionExpression.fromNumericFunction((a, b) -> a * b));
        bind("/", FunctionExpression.fromNumericFunction((a, b) -> a / b));
        bind("/", FunctionExpression.fromNumericFunction((a, b) -> a / b));

        bind("mod", FunctionExpression.fromNumericFunction((a, b) -> a % b));

        // Constants
        bind("true", Constant.TRUE);
        bind("false", Constant.FALSE);
        bind("nil", Constant.NIL);

        // List operations
        bind("cons", FunctionExpression.<Expression, ListExpression, ListExpression>fromFunction(ListExpression::cons));
        bind("car", FunctionExpression.fromFunction(ListExpression::car));
        bind("cdr", FunctionExpression.fromFunction(ListExpression::cdr));

        // Some functional stuff
        eval("(def id (x) x");
        eval("(def foldr (f z xs) (if (= '() xs) z (f (foldr f z (cdr xs)) (car xs)))");
        eval("(def foldl (f z xs) (if (= '() xs) z (foldl f (f z (car xs)) (cdr xs)))");
        eval("(def map (f xs) (foldr (lambda (z x) (cons (f x) z)) '() xs)");
        eval("(def filter (f xs) (foldr (lambda (zs x) (if (f x) (cons x zs) zs)) '() xs)");
        eval("(def length (xs) (foldr (lambda (z x) (+ 1 z)) 0 xs))");
        eval("(def range (a b step) (if (>= a b) '() (cons a (range (+ step a) b step))))");
        eval("(def sum (xs) (foldr + 0 xs))");
        eval("(def product (xs) (foldr * 1 xs))");
    }

    private final Map<Symbol, Expression> bindings = new HashMap<>();

    private final Context parentContext;

    public Context() {
        this.parentContext = GLOBAL;
    }
    public Context(Context parentContext) {
        this.parentContext = parentContext;
    }

    private static Expression eval(String str) {
        return Parser.read(str).evaluate(GLOBAL);
    }

    private static void bind(String sym, Expression expr) {
        GLOBAL.put(Symbol.of(sym), expr);
    }

    public Expression get(Symbol symbol) {
        Expression expr = bindings.get(symbol);
        if (expr == null && parentContext != null) {
            expr = parentContext.get(symbol);
        }
        return (expr == null) ? Constant.NIL : expr;
    }

    public void put(Symbol key, Expression value) {
        bindings.put(key, value);
    }

    @Override
    public String toString() {
        return bindings.toString();
    }
}
