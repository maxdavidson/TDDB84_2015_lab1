package interpreter.lisp;

import interpreter.lisp.visitors.Printer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PrinterTest {

    private static final Printer printer = new Printer();

    private static String parseAndPrint(String source) {
        return Parser.read(source).accept(printer);
    }

    @Test
    public void test1() {
        String source = "(def apa (x) (+ x 2))";
        assertEquals(source, parseAndPrint(source));
    }

    @Test
    public void test2() {
        String source = "(def fac (x) (if (= x 0) 1 (* x (fac (- x 1)))))";;
        assertEquals(source, parseAndPrint(source));
    }

    @Test
    public void test3() {
        String source = "3456.123";
        assertEquals(source, parseAndPrint(source));
    }

    @Test
    public void test4() {
        String source = "'(34.23 13 94 hello)";
        assertEquals(source, parseAndPrint(source));
    }
}
