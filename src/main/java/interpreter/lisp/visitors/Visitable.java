package interpreter.lisp.visitors;


public interface Visitable {
    default <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
