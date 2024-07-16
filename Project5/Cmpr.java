public class Cmpr{
    private final Parser parser;
    private Expr expr1;
    private Expr expr2;
    private String comparator;

    Cmpr(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form: <expr> == <expr> | <expr> < <expr>
        expr1 = new Expr(parser);
        expr1.parse();
        Core token = parser.scanner.currentToken();
        if (token == Core.EQUAL){
            // <expr> == <expr>
            comparator = "==";
            parser.scanner.nextToken();
            expr2 = new Expr(parser);
            expr2.parse();
        } else if (token == Core.LESS){
            // <expr> < <expr>
            comparator = "<";
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

    public boolean execute(){
        if (comparator.equals("==")){
            return (expr1.execute() == expr2.execute());
        } else if (comparator.equals("<")){
            return (expr1.execute() < expr2.execute());
        }
        return false; // This should never happen. >:(
    }
}