package interpreter.lisp.expressions.wrappers;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.CaseExpression;
import interpreter.lisp.expressions.syntactic.CompoundExpression;

import java.util.Map;
import java.util.WeakHashMap;


public final class CaseExpressionWrapper extends CaseExpression {

    public final CompoundExpression delegate;

    public CaseExpressionWrapper(CompoundExpression delegate) {
        this.delegate = delegate;
    }

    @Override
    public Expression getCondition() {
        return delegate.elements.get(1);
    }

    @Override
    public Map<Expression, Expression> getCases() {
        Map<Expression, Expression> cases = new WeakHashMap<>();

        for (int i = 2, length = delegate.elements.size(); i < length; ++i) {
            CompoundExpression row = (CompoundExpression) delegate.elements.get(i);
            cases.put(row.elements.get(0), row.elements.get(1));
        }

        return cases;
    }
}
