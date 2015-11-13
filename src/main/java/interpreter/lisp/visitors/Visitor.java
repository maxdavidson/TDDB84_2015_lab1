package interpreter.lisp.visitors;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.*;
import interpreter.lisp.expressions.syntactic.CompoundExpression;
import interpreter.lisp.expressions.syntactic.Constant;
import interpreter.lisp.expressions.syntactic.Num;
import interpreter.lisp.expressions.syntactic.Symbol;


public interface Visitor<T> {
    T visit(CompoundExpression expr);

    T visit(FunctionCall expr);

    T visit(FunctionExpression expr);

    T visit(VariableDefinition expr);

    T visit(ConditionalExpression expr);

    T visit(ListExpression expr);

    T visit(CaseExpression expr);

    T visit(Constant expr);

    T visit(Symbol expr);

    T visit(Evaluable expr);

    T visit(Num expr);

    T visit(Expression expr);

    T visit(Visitable expr);
}

