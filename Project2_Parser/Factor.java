public class Factor {
    private Parser parser;
    public String strRep = ""; // The string representation of the factor. Used for printing.
    Factor(Parser parser){
        this.parser = parser;
    }
    public void parse(){
        // Of the id | id [id] | const | ( <expr> ) | in ()
        Core token = parser.scanner.currentToken();
        switch (token){
            case Core.ID:
                // id | id [id]
                strRep += parser.scanner.getId();
                parser.stack.containsId(parser.scanner.getId()); // Check if the variable has been declared
                parser.scanner.nextToken();
                if (parser.scanner.currentToken() == Core.LBRACE){
                    parser.scanner.nextToken();
                    parser.expectedToken(Core.ID);
                    strRep += "[" + parser.scanner.getId() + "]";
                    parser.scanner.nextToken();
                    parser.expectedToken(Core.RBRACE);
                    parser.scanner.nextToken();
                }
                break;
            case Core.CONST:
                strRep += parser.scanner.getConst();
                parser.scanner.nextToken();
                break;
            case Core.LPAREN:
                // ( <expr> )
                parser.scanner.nextToken();
                Expr expr = new Expr(parser);
                expr.parse();
                parser.expectedToken(Core.RPAREN);
                parser.scanner.nextToken();
                strRep += "(" + expr.strRep + ")";
                break;
            case Core.IN:
                // in ()
                parser.scanner.nextToken();
                parser.expectedToken(Core.LPAREN);
                parser.scanner.nextToken();
                parser.expectedToken(Core.RPAREN);
                strRep += "in()";
                parser.scanner.nextToken();
                break;
            default:
                // Error
                System.out.println("ERROR: Invalid factoring token: " + token);
                System.exit(0);
        }
    }
    public void print(){
        System.out.print(strRep);
    }
}
