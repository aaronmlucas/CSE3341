import java.util.List;
import java.util.ArrayList;

public class DeclSeq {
    private List<Decl> decls;
    private Parser parser;

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
        }
    }

    public void print(){
        for (Decl decl : decls){
            decl.print();
        }
    }
}