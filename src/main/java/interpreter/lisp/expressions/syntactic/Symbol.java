package interpreter.lisp.expressions.syntactic;

import interpreter.lisp.expressions.ExpressionBase;
import interpreter.lisp.visitors.Visitor;

import java.util.HashMap;
import java.util.Map;


public final class Symbol extends ExpressionBase {

    private static Map<String, Symbol> symbolTable = new HashMap<>();

    public final String name;

    private Symbol(String name) {
        this.name = name;
    }

    public static Symbol of(String name) {
        return symbolTable.computeIfAbsent(name, Symbol::new);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
