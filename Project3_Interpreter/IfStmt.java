public class IfStmt{
    private final Parser parser;
    private Cond cond;
    private StmtSeq ss1;
    private StmtSeq ss2;

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
        Core token = parser.scanner.currentToken();
        // Check if there is an else statement
        if (token == Core.ELSE){
            // else <stmt-seq>
            parser.scanner.nextToken();
            ss2 = new StmtSeq(parser);
            ss2.parse();
        }
        parser.expectedToken(Core.END);
        parser.scanner.nextToken();
    }

    public void print(){
        if (ss2 != null){
            // Print with an else statement.
            System.out.print("if ");
            cond.print();
            System.out.println(" then");
            ss1.print();
            System.out.println("else");
            ss2.print();
            System.out.println("end");
        } else {
            // Print without an else statement.
            System.out.print("if ");
            cond.print();
            System.out.println(" then");
            ss1.print();
            System.out.println("end");
        }
    }

    public void execute(){
        if (cond.execute()){
            // Enter new scope.
            parser.stack.newBlock();
            // Execute code in scope.
            ss1.execute();
            // Return to previous scope.
            parser.stack.removeBlock();
        }else {
            if (ss2 != null){
                // Enter new scope.
                parser.stack.newBlock();
                // Execute code in scope.
                ss2.execute();
                // Return to previous scope.
                parser.stack.removeBlock();
            }
        }
    }
}