public class Factor {
    private final Parser parser;
    public String terminal = ""; // The string representation of the terminals. Used for printing.
    public Expr expr;
    private String id1;
    private String id2;
    Factor(Parser parser){
        this.parser = parser;
    }
    public void parse(){
        // Of the id | id [id] | const | ( <expr> ) | in ()
        Core token = parser.scanner.currentToken();
        switch (token){
            case ID -> {
                // id | id [id]
                id1 = parser.scanner.getId();
                terminal += parser.scanner.getId();
                parser.scanner.nextToken();
                if (parser.scanner.currentToken() == Core.LBRACE){
                    parser.scanner.nextToken();
                    parser.expectedToken(Core.ID);
                    id2 = parser.scanner.getId();
                    terminal += "[" + parser.scanner.getId() + "]";
                    parser.scanner.nextToken();
                    parser.expectedToken(Core.RBRACE);
                    parser.scanner.nextToken();
                }
            }
            case CONST -> {
                terminal += parser.scanner.getConst();
                parser.scanner.nextToken();
            }
            case LPAREN -> {
                // ( <expr> )
                parser.scanner.nextToken();
                expr = new Expr(parser);
                expr.parse();
                parser.expectedToken(Core.RPAREN);
                parser.scanner.nextToken();
            }
            case IN -> {
                // in ()
                parser.scanner.nextToken();
                parser.expectedToken(Core.LPAREN);
                parser.scanner.nextToken();
                parser.expectedToken(Core.RPAREN);
                terminal += "in()";
                parser.scanner.nextToken();
            }
            default -> {
                // Error
                System.out.println("ERROR: Invalid factoring token: " + token);
                System.exit(0);
            }
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

    public int execute(){
        if (expr == null){
            if (terminal.contains("in()")){
                if (parser.fileInput.currentToken() == Core.EOS){
                    System.out.println("ERROR: End of input file reached.");
                    System.exit(0);
                }
                //Note: We are assuming that the input file is in the form specified in the assignment.
                int token = parser.fileInput.getConst();
                parser.fileInput.nextToken();
                return token;
            
            } else if (terminal.contains("[")){
                if (!parser.stack.containsVariable(id1)){
                    System.out.println("ERROR: Variable " + id1 + " not found.");
                    System.exit(0);
                }
                return parser.stack.getVariable(id1).getValue(id2);
            } else{
                // Either id or const
                if (terminal.matches("[0-9]+")){ // If terminal is a number, it is a constant
                    return Integer.parseInt(terminal);
                } else { // otherwise, it is an id
                    return parser.stack.getVariable(id1).getValue();
                }
            }
        } else {
            return expr.execute();
        }
    }
}
