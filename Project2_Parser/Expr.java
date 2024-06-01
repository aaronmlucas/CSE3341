public class Expr {
    private Parser parser;
    public Term term;
    public Expr expr;
    public char sign;
    Expr(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form <term> | <term> + <expr> | <term> - <expr>
        term = new Term(parser);
        term.parse();
        Core token = parser.scanner.currentToken();
        
        if (token == Core.ADD){
            // <term> + <expr>
            sign = '+';
            parser.scanner.nextToken();
            expr = new Expr(parser);
            expr.parse();
        } else if (token == Core.SUBTRACT){
            // <term> - <expr>
            sign = '-';
            parser.scanner.nextToken();
            expr = new Expr(parser);
            expr.parse();
        }
    }
    public void print(){
        if (expr != null){
            // Print <term> + <expr> | <term> - <expr>
            term.print();
            System.out.print(" " + sign + " ");
            expr.print();
        } else {
            // Print <term>
            term.print();
        }
    }
}
