package interpreter;

import java.util.Map;

public class Div implements Expression {

    private Expression right;
    private Expression left;

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Map<String, Expression> variables) {
        return left.interpret(variables) / right.interpret(variables);
    }

}
