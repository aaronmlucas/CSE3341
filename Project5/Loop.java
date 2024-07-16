public class Loop{
    private final Parser parser;
    private Cond cond;
    private StmtSeq ss;

    Loop(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form 'while <cond> do <stmt-seq> end'
        parser.expectedToken(Core.WHILE); // Redundancy.
        parser.scanner.nextToken();
        cond = new Cond(parser);
        cond.parse();
        parser.expectedToken(Core.DO);
        parser.scanner.nextToken();
        ss = new StmtSeq(parser);
        ss.parse();
        parser.expectedToken(Core.END);
        parser.scanner.nextToken();
    }

    public void print(){
        System.out.print("while ");
        cond.print();
        System.out.println(" do");
        ss.print();
        System.out.println("end");
    }

    public void execute(){ 
        while (cond.execute()){
            // Enter new scope.
            parser.stack.newBlock();
            // Execute code in scope.
            ss.execute();
            // Return to previous scope.
            parser.stack.removeBlock();
        }
    }
}