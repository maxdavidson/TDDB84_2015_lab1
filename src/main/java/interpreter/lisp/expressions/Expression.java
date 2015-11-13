package interpreter.lisp.expressions;

import interpreter.lisp.Context;
import interpreter.lisp.expressions.syntactic.Constant;
import interpreter.lisp.expressions.syntactic.Num;
import interpreter.lisp.visitors.Visitable;


public interface Expression extends Visitable {

    static Constant from(boolean bool) {
        return bool ? Constant.TRUE : Constant.FALSE;
    }

    static Num from(double num) {
        return new Num(num);
    }

    static Expression from(Expression obj) {
        return (obj == null) ? Constant.NIL : obj;
    }

    Expression evaluate(Context context);
}
