public class Out(){
    private Parser parser;
    private Expr expr;
    public String strRep = ""; // The string representation of the out statement. Used for printing.

    Out(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form 'out ( <expr> ) ;'
        parser.expectedToken(Core.OUT); // Redundancy.
        parser.scanner.nextToken();
        parser.expectedToken(Core.LPAREN);
        parser.scanner.nextToken();
        expr = new Expr(parser);
        expr.parse();
        parser.expectedToken(Core.RPAREN);
        parser.scanner.nextToken();
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
        strRep += "out(" + expr.strRep + ");";
    }

    public void print(){
        System.out.println(strRep);
    }
}