package interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapted from http://en.wikipedia.org/wiki/Interpreter_pattern
 *
 * @author olale
 */
public class InterpreterExample {
    public static void main(String[] args) {
        String expression = "w 20 z + /"; // Postfix notation: operands first, operations after
        Evaluator sentence = new Evaluator(Evaluator.read(expression));
        Map<String, Expression> variables = new HashMap<String, Expression>();
        variables.put("w", new Number(5));
        variables.put("x", new Number(10));
        variables.put("z", new Number(42));
        // The variables are our simple evaluation context
        int result = sentence.interpret(variables);
        System.out.println(result);
    }
}
