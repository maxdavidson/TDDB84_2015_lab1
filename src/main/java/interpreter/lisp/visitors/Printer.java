package interpreter.lisp.visitors;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.*;
import interpreter.lisp.expressions.syntactic.CompoundExpression;
import interpreter.lisp.expressions.syntactic.Constant;
import interpreter.lisp.expressions.syntactic.Num;
import interpreter.lisp.expressions.syntactic.Symbol;

import java.text.MessageFormat;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class Printer implements Visitor<String> {

    @Override
    public String visit(Expression expr) {
        return "";
    }

    @Override
    public String visit(CompoundExpression expr) {
        return expr.elements
                .stream()
                .map(e -> e.accept(this))
                .collect(Collectors.joining(" ", "(", ")"));
    }

    @Override
    public String visit(FunctionCall expr) {
        return MessageFormat.format("({0} {1})",
                expr.getFunction().accept(this),
                expr.getArguments()
                        .stream()
                        .map(e -> e.accept(this))
                        .collect(Collectors.joining(" ")));
    }

    @Override
    public String visit(FunctionExpression expr) {
        return MessageFormat.format("(lambda {0} {1})",
                expr.getParameters()
                        .stream()
                        .map(e -> e.accept(this))
                        .collect(Collectors.joining(" ", "(", ")")),
                expr.getBody().stream()
                        .map(e -> e.accept(this))
                        .collect(Collectors.joining(" ")));
    }

    @Override
    public String visit(VariableDefinition expr) {
        return MessageFormat.format("(set {0} {1})",
                expr.getName().accept(this),
                expr.getValue().accept(this));
    }

    @Override
    public String visit(ConditionalExpression expr) {
        return MessageFormat.format("(if {0} {1} {2})",
                expr.getCondition().accept(this),
                expr.getConsequent().accept(this),
                expr.getAntecedent().accept(this));
    }

    @Override
    public String visit(ListExpression expr) {
        return StreamSupport.stream(expr.spliterator(), false)
                .map(e -> e.accept(this))
                .collect(Collectors.joining(" ", "'(", ")"));
    }

    @Override
    public String visit(CaseExpression expr) {
        return null;
    }

    @Override
    public String visit(Constant expr) {
        return expr.toString();
    }

    @Override
    public String visit(Symbol expr) {
        return expr.name;
    }

    @Override
    public String visit(Evaluable expr) {
        return "[Native function]";
    }

    @Override
    public String visit(Num expr) {
        double d = expr.value;
        return (d == (long) d)
                ? String.format("%d", (long) d)
                : String.format("%s", d);
    }

    @Override
    public String visit(Visitable visitable) {
        return "";
    }
}
