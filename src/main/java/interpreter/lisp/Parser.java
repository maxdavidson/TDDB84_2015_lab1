package interpreter.lisp;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.expressions.semantic.ListExpression;
import interpreter.lisp.expressions.syntactic.CompoundExpression;
import interpreter.lisp.expressions.syntactic.Num;
import interpreter.lisp.expressions.syntactic.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public final class Parser {

    /**
     * All expressions are either atomic (numbers, variables) or compound
     * expressions (function definitions, conditional if-expressions, function
     * calls). This method creates an Expr object fromFunction such a string.
     *
     * @param expression
     * @return
     */
    public static Expression read(String expression) {
        return parse(tokenize(expression));
    }

    private static List<String> tokenize(String expression) {
        return Arrays.stream(expression
                .replaceAll("\\(", "( ")
                .replaceAll("\\)", " )")
                .split("\\s+"))
                .filter(str -> !str.equals(""))
                .collect(Collectors.toList());
    }

    /**
     * Create an abstract expression, either a compound term or an atom, fromFunction a
     * list of tokens
     *
     * @param tokens
     * @return
     */
    private static Expression parse(List<String> tokens) {
        String token = tokens.remove(0);
        if (token.equals("(")) {
            List<Expression> elements = new ArrayList<>();

            while (!tokens.isEmpty() && !tokens.get(0).equals(")")) {
                elements.add(parse(tokens));
            }

            if (!tokens.isEmpty()) {
                tokens.remove(0);
            }

            return new CompoundExpression(elements);
        } else if (token.equals("'(")) {
            List<Expression> elements = new ArrayList<>();

            while (!tokens.isEmpty() && !tokens.get(0).equals(")")) {
                elements.add(parse(tokens));
            }

            if (!tokens.isEmpty()) {
                tokens.remove(0);
            }

            return ListExpression.from(elements);
        } else if (token.matches("\\d*(\\.\\d+)*")) {
            return new Num(Double.parseDouble(token));
        } else {
            return Symbol.of(token);
        }
    }

}
