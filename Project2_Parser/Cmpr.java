public class Cmpr{
    private Parser parser;
    private Expr expr1;
    private Expr expr2;
    private Core comparator;

    Cmpr(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form: <expr> == <expr> | <expr> < <expr>
        Expr expr1 = new Expr(parser);
        expr1.parse();
        Core token = parser.scanner.currentToken();
        comparator = token;
        if (token == Core.EQUAL){
            // <expr> == <expr>
            parser.scanner.nextToken();
            expr2 = new Expr(parser);
            expr2.parse();
        } else if (token == Core.LESS){
            // <expr> < <expr>
            parser.scanner.nextToken();
            expr2 = new Expr(parser);
            expr2.parse();
        }
    }

    public void print(){
        expr1.print();
        System.out.print(" " + comparator + " ");
        expr2.print();
    }
}