import java.util.ArrayList;
import java.util.List;

public class StmtSeq {
    private List<Stmt> stmts = null;
    private final Parser parser;

    StmtSeq(Parser parser){
        this.parser = parser;
        stmts = new ArrayList<>();
    }
    
    public void parse(){
        Stmt newStmt;
        // Can be assignment, if statement, loop statement, output, or declaration
        while (parser.scanner.currentToken() != Core.END && parser.scanner.currentToken() != Core.ELSE){
            newStmt = new Stmt(parser);
            newStmt.parse();
            stmts.add(newStmt);
        }
    }

    public void print(){
        for (Stmt stmt : stmts){
            stmt.print();
        }
    }

    public void execute(){
        parser.stack.newBlock();
        for (Stmt stmt : stmts){
            stmt.execute();
        }
    }
}