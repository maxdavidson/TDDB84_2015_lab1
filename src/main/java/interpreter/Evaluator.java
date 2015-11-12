package interpreter;

import java.util.Map;
import java.util.Stack;

/**
 * Adapted from http://en.wikipedia.org/wiki/Interpreter_pattern
 *
 * @author olale
 */
class Evaluator implements Expression {
    private Expression syntaxTree;

    public Evaluator(Stack<Expression> expressionStack) {
        syntaxTree = expressionStack.pop();
    }

    public static Stack<Expression> read(String expression) {
        Stack<Expression> expressionStack = new Stack<Expression>();
        for (String token : expression.split(" ")) {
            if (token.equals("+")) {
                // it's necessary remove first the left & right operands from
                // the stack
                Expression subExpression = new Plus(expressionStack.pop(),
                        expressionStack.pop());
                expressionStack.push(subExpression);
            } else if (token.equals("-")) {
                Expression subExpression = new Minus(expressionStack.pop(),
                        expressionStack.pop());
                expressionStack.push(subExpression);
            } else if (token.equals("*")) {
                Expression subExpression = new Mult(expressionStack.pop(),
                        expressionStack.pop());
                expressionStack.push(subExpression);
            } else if (token.equals("/")) {
                Expression subExpression = new Div(expressionStack.pop(),
                        expressionStack.pop());
                expressionStack.push(subExpression);
            } else if (token.matches("-?\\d+")) {
                int num = Integer.parseInt(token);
                expressionStack.push(new Number(num));
            } else {
                // Variable
                expressionStack.push(new Variable(token));
            }
        }
        return expressionStack;
    }

    public int interpret(Map<String, Expression> context) {
        return syntaxTree.interpret(context);
    }
}
