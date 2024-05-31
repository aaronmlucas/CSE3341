import java.util.List;
import java.util.ArrayList;

public class StmtSeq {
    private List<Stmt> stmts;
    private Parser parser;
    public String strRep = ""; // The string representation of the statement sequence. Used for printing.

    StmtSeq(Parser parser){
        this.parser = parser;
        stmts = new ArrayList<Stmt>();
    }
    
    public void parse(){
        Stmt newStmt;
        // Can be assignment, if statement, loop statement, out(put?), or declaration
        while (parser.scanner.currentToken() != Core.END && parser.scanner.currentToken() != Core.ELSE){
            newStmt = new Stmt(parser);
            newStmt.parse();
            stmts.add(newStmt);
            strRep += "\t" + newStmt.strRep + "\n";
        }
        strRep = strRep.substring(0, strRep.length() - 1); // Remove the last newline character
    }

    public void print(){
        System.out.println(strRep);
    }

}