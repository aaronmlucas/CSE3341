public class Loop(){
    private Parser parser;
    private Cond cond;
    private StmtSeq ss;
    public String strRep = ""; // The string representation of the loop statement. Used for printing.

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
        strRep += "while " + cond.strRep + " do\n" + ss.strRep + "\n\tend";
    }

    public void print(){
        System.out.println(strRep);
    }
}