import java.util.List;
import java.util.ArrayList;

public class DeclSeq {
    private List<Decl> decls;
    private Parser parser;
    public String strRep = ""; // The string representation of the declaration sequence. Used for printing.

    DeclSeq(Parser parser){
        this.parser = parser;
        decls = new ArrayList<Decl>();
    }

    public void parse(){
        Decl newDecl;
        while (parser.scanner.currentToken() == Core.INTEGER || parser.scanner.currentToken() == Core.OBJECT){
            newDecl = new Decl(parser);
            newDecl.parse();
            decls.add(newDecl);
            strRep += "\t" + newDecl.strRep + "\n";
        }
        strRep = strRep.substring(0, strRep.length() - 1); // Remove the last newline character
    }

    public void print(){
        System.out.println(strRep);
    }
}