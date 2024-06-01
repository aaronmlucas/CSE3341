public class Cmpr(){
    private Parser parser;
    private Expr expr1;
    private Expr expr2;
    private Core comparator;
    public String strRep = ""; // The string representation of the comparison. Used for printing.

    Cmpr(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form: <expr> == <expr> | <expr> < <expr>
        Expr expr1 = new Expr(parser);
        expr1.parse();
        strRep += expr.strRep;
        Core token = parser.scanner.currentToken();
        comparator = token;
        if (token == Core.EQUAL){
            // <expr> == <expr>
            parser.scanner.nextToken();
            expr2 = new Expr(parser);
            expr2.parse();
            strRep += " == " + expr2.strRep;
        } else if (token == Core.LESS){
            // <expr> < <expr>
            parser.scanner.nextToken();
            expr2 = new Expr(parser);
            expr2.parse();
            strRep += " < " + expr2.strRep;
        }
    }

    public void print(){
        System.out.println(strRep);
    }
}