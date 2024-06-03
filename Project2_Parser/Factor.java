public class Factor {
    private Parser parser;
    public String terminal = ""; // The string representation of the terminals. Used for printing.
    public Expr expr;
    Factor(Parser parser){
        this.parser = parser;
    }
    public void parse(){
        // Of the id | id [id] | const | ( <expr> ) | in ()
        Core token = parser.scanner.currentToken();
        switch (token){
            case ID:
                // id | id [id]
                terminal += parser.scanner.getId();
                parser.stack.containsId(parser.scanner.getId()); // Check if the variable has been declared
                parser.scanner.nextToken();
                if (parser.scanner.currentToken() == Core.LBRACE){
                    parser.scanner.nextToken();
                    parser.expectedToken(Core.ID);
                    terminal += "[" + parser.scanner.getId() + "]";
                    parser.scanner.nextToken();
                    parser.expectedToken(Core.RBRACE);
                    parser.scanner.nextToken();
                }
                break;
            case CONST:
                terminal += parser.scanner.getConst();
                parser.scanner.nextToken();
                break;
            case LPAREN:
                // ( <expr> )
                parser.scanner.nextToken();
                expr = new Expr(parser);
                expr.parse();
                parser.expectedToken(Core.RPAREN);
                parser.scanner.nextToken();
                break;
            case IN:
                // in ()
                parser.scanner.nextToken();
                parser.expectedToken(Core.LPAREN);
                parser.scanner.nextToken();
                parser.expectedToken(Core.RPAREN);
                terminal += "in()";
                parser.scanner.nextToken();
                break;
            default:
                // Error
                System.out.println("ERROR: Invalid factoring token: " + token);
                System.exit(0);
        }
    }
    public void print(){
        if (expr != null){
            System.out.print("(");
            expr.print();
            System.out.print(")");
        } else {
            System.out.print(terminal);
        }
    }
}
