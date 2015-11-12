package visitor;

public final class Variable extends SimpleExpression {

    private final String name;
    private final int value;

    public Variable(String name, int value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
