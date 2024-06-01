public class IfStmt(){
    private Parser parser;
    private Cond cond;
    private StmtSeq ss1;
    private StmtSeq ss2;
    public String strRep = ""; // The string representation of the if statement. Used for printing.

    IfStmt(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form 'if <cond> then <stmt-seq> end' | 'if <cond> then <stmt-seq> else <stmt-seq> end'
        parser.expectedToken(Core.IF); // Redundancy.
        parser.scanner.nextToken();
        cond = new Cond(parser);
        cond.parse();
        parser.expectedToken(Core.THEN);
        parser.scanner.nextToken();
        ss1 = new StmtSeq(parser);
        ss1.parse();
        strRep += "if " + cond.strRep + " then\n" + ss1.strRep;
        Core token = parser.scanner.currentToken();
        // Check if there is an else statement
        if (token == Core.ELSE){
            // else <stmt-seq>
            parser.scanner.nextToken();
            ss2 = new StmtSeq(parser);
            ss2.parse();
            strRep += " else " + ss2.strRep;
        }
        parser.expectedToken(Core.END);
        parser.scanner.nextToken();
        strRep += "\n\tend";
    }

    public void print(){
        System.out.println(strRep);
    }

}