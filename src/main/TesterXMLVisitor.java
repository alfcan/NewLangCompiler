package main;

import esercitazione5.Lexer;
import esercitazione5.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import esercitazione5.syntax.ProgramOP;
import esercitazione5.visitor.XMLVisitor;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class TesterXMLVisitor {

    public static final String filePath = "output_files/output.xml";

    public static void main(String[] args){
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            XMLVisitor xmlVisitor = new XMLVisitor(document);

            FileReader reader = new FileReader("test_files/program.txt");
            Lexer lexer = new Lexer(reader);
            Parser p = new Parser(lexer);
            ProgramOP result = (ProgramOP) p.parse().value;

            Element root = (Element) xmlVisitor.visit(result);
            document.appendChild(root);

            System.out.println("TABELLA DELLE STRINGHE");
            for (int i=0; i<lexer.getSizeStringTable(); i++)
                System.out.println("Pos " + i + ": " + lexer.getElementOfStringTable(i));

            // write dom document to a file
            try (FileOutputStream output = new FileOutputStream(filePath)) {
                writeXml(document, output);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\n\n\nL'albero sintattico è stato costruito nel file output.xml");

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // write doc to output stream
    private static void writeXml(Document doc, OutputStream output) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        StreamResult result = new StreamResult(output);
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
    }
}
