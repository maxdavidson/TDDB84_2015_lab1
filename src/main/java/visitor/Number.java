package visitor;

public final class Number extends SimpleExpression {

    private final int value;

    public Number(int value) {
        super();
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
