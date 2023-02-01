package main;

import esercitazione5.syntax.ProgramOP;
import esercitazione5.Lexer;
import esercitazione5.Parser;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TesterParser {

    public static void main(String args[]){
        FileReader reader = null;
        try {
            reader = new FileReader("test_files/program.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Lexer lexer = new Lexer(reader);

        Parser p = new Parser(lexer);

        try {
            ProgramOP result = (ProgramOP) p.parse().value;
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
