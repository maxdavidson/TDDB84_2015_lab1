package interpreter;

import java.util.Map;

public class Mult implements Expression {

    private Expression left;
    private Expression right;

    public Mult(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Map<String, Expression> variables) {
        return left.interpret(variables) * right.interpret(variables);
    }

}
