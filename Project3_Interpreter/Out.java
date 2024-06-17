public class Out{
    private final Parser parser;
    private Expr expr;

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
    }

    public void print(){
        System.out.print("out (");
        expr.print();
        System.out.println(");");
    }
    public void execute(){
        System.out.println(expr.execute());
    }
}