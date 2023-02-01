package main;

import esercitazione5.Lexer;
import esercitazione5.Parser;
import esercitazione5.symboltable.SymbolTable;
import esercitazione5.syntax.ProgramOP;
import esercitazione5.visitor.ScopeVisitor;
import esercitazione5.visitor.TranslatorVisitor;
import esercitazione5.visitor.TypeCheckVisitor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Tester {

    public static void main(String args[]) throws Exception {
        FileReader reader = null;
        try {
            reader = new FileReader("test_files/test_case_prof/valid1.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Lexer lexer = new Lexer(reader);

        Parser p = new Parser(lexer);

        ScopeVisitor scopeVisitor = new ScopeVisitor(lexer.getStringTable());
        TypeCheckVisitor checkVisitor = new TypeCheckVisitor(lexer.getStringTable());


        ProgramOP programOP = (ProgramOP) p.parse().value;
        ProgramOP resultScope = (ProgramOP) scopeVisitor.visit(programOP);
        ProgramOP resultCheck = (ProgramOP) checkVisitor.visit(resultScope);

        SymbolTable.clearScope();
        TranslatorVisitor translatorVisitor = new TranslatorVisitor("test_files/temp.c", lexer.getStringTable());
        translatorVisitor.visit(resultCheck);
    }

}
