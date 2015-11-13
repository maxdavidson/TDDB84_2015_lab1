package interpreter.lisp;

import interpreter.lisp.expressions.Expression;
import interpreter.lisp.visitors.Evaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class REPL {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Evaluator evaluator = new Evaluator();
        String line;
        Expression result;

        try {
            System.out.print("> ");
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    result = Parser.read(line).accept(evaluator);
                    System.out.println(result);
                }
                System.out.print("> ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
