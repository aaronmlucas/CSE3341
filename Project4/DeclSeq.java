import java.util.ArrayList;
import java.util.List;

public class DeclSeq {
    private List<Decl> decls = null;
    private List<Function> funcs = null;
    private final Parser parser;

    DeclSeq(Parser parser){
        this.parser = parser;
        decls = new ArrayList<>();
        funcs = new ArrayList<>();
    }

    public void parse(){
        while (parser.scanner.currentToken() == Core.INTEGER 
        || parser.scanner.currentToken() == Core.OBJECT
        || parser.scanner.currentToken() == Core.PROCEDURE){

            if (parser.scanner.currentToken() == Core.INTEGER 
            || parser.scanner.currentToken() == Core.OBJECT){
                // Add a new declaration to the declarations list.
                Decl newDecl = new Decl(parser);
                newDecl.parse();
                decls.add(newDecl);
            } else {
                // Add a new function to the functions list.
                Function newFunc = new Function(parser);
                newFunc.parse();
                funcs.add(newFunc);
            }
        }
    }

    public void print(){
        // NOTE: This does not produce the exact same output as the input! 
        // Instead, all declarations and all functions are grouped together.
        for (Decl decl : decls){
            decl.print();
        }
        for (Function func : funcs){
            func.print();
        }
    }

    public void execute(){
        parser.stack.newBlock();
        for (Decl decl : decls) decl.execute(); // Just found out you could do this in Java. Pretty nifty.
    }
}