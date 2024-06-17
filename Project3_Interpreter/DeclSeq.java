import java.util.ArrayList;
import java.util.List;

public class DeclSeq {
    private List<Decl> decls = null;
    private final Parser parser;

    DeclSeq(Parser parser){
        this.parser = parser;
        decls = new ArrayList<>();
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

    public void execute(){
        parser.stack.newBlock();
        for (Decl decl : decls){
            CoreVariable var = decl.execute();
            // Adds the variable as a pair (<String name -> CoreVariable var>) to the new block in the stack
            parser.stack.addVariable(var.getName(), var); 
        }
    }
}