package main;

import esercitazione5.Lexer;
import esercitazione5.ParserSym;
import java_cup.runtime.Symbol;
import java.io.FileReader;
import java.io.IOException;

public class TesterLexer {

    public static void main (String args[]) throws IOException {
        FileReader reader = new FileReader("test_files/test_case_prof/valid6.txt");
        Lexer lexer = new Lexer(reader);

        Symbol s;
        while((s = lexer.next_token()) != null){
            if (s.value == null)
                System.out.println(ParserSym.terminalNames[s.sym]);
            else
                System.out.println(ParserSym.terminalNames[s.sym] + " - " + s.value);
        }

    }

}
