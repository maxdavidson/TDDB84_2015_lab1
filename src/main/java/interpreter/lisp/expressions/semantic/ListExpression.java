package interpreter.lisp.expressions.semantic;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.ExpressionBase;
import interpreter.lisp.visitors.Visitor;

import java.util.*;


/**
 * Where the compound expression is a syntactical measure,
 * a list expression is a singly linked, immutable list.
 */
public final class ListExpression extends ExpressionBase implements Iterable<Expression> {

    public static final ListExpression EMPTY = new ListExpression(null, null);
    private final Expression value;
    private final ListExpression next;

    private ListExpression(Expression value, ListExpression next) {
        this.value = value;
        this.next = next;
    }

    public static ListExpression from(Collection<? extends Expression> values) {
        ListExpression list = EMPTY;
        List<? extends Expression> moreValues = new ArrayList<>(values);
        Collections.reverse(moreValues);

        for (Expression value : moreValues) {
            list = new ListExpression(value, list);
        }

        return list;
    }

    public static ListExpression of(Expression... values) {
        ListExpression list = null;

        int i = values.length;
        while ((i--) != 0) {
            list = new ListExpression(values[i], list);
        }

        return list;
    }

    public static ListExpression cons(Expression value, ListExpression list) {
        return new ListExpression(value, list);
    }

    public ListExpression cons(Expression value) {
        return cons(value, this);
    }

    public Expression car() {
        return value;
    }

    public ListExpression cdr() {
        return next;
    }

    @Override
    public <U> U accept(Visitor<U> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Iterator<Expression> iterator() {
        return new ListExpressionIterator(this);
    }

    private static class ListExpressionIterator implements Iterator<Expression> {

        private ListExpression current;

        public ListExpressionIterator(ListExpression expr) {
            this.current = expr;
        }

        @Override
        public boolean hasNext() {
            return current != EMPTY;
        }

        @Override
        public Expression next() {
            Expression value = current.car();
            current = current.cdr();
            return value;
        }
    }
}
