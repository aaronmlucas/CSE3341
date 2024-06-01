public class Expr {
    private Parser parser;
    public String strRep = ""; // The string representation of the expression. Used for printing.
    Expr(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form <term> | <term> + <expr> | <term> - <expr>
        Term term = new Term(parser);
        term.parse();
        strRep += term.strRep;
        Core token = parser.scanner.currentToken();
        
        if (token == Core.ADD){
            // <term> + <expr>
            parser.scanner.nextToken();
            Expr expr = new Expr(parser);
            expr.parse();
            strRep += " + " + expr.strRep;
        } else if (token == Core.SUBTRACT){
            // <term> - <expr>
            parser.scanner.nextToken();
            Expr expr = new Expr(parser);
            expr.parse();
            strRep += " - " + expr.strRep;
        }
    }
    public void print(){
        System.out.print(strRep);
    }
}
