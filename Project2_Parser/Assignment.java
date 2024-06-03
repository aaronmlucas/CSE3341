public class Assignment{
    public Parser parser;
    public Expr expr;
    public String id1;
    public String id2;
    public boolean isArray = false;
    Assignment(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form 'id = <expr> ;' | 'id [ id ] = <expr> ;' | 'id = new object( id, <expr> );' | 'id : id ;'
        parser.expectedToken(Core.ID); // Redundancy.
        id1 = parser.scanner.getId();
        parser.stack.containsId(id1);
        parser.scanner.nextToken();
        Core token = parser.scanner.currentToken();
        switch(token){
            case ASSIGN:
                parser.scanner.nextToken();
                if (parser.scanner.currentToken() == Core.NEW){
                    // id = new object ( id , <expr> ) ;
                    parseNewObject();
                } else {
                    // id = <expr> ;
                    parseStandardVariable();
                }
                break;
            case LBRACE:
                isArray = true;
                parseArray();
                break;
            case COLON:
                parseColon();
                break;
            default:
                // Error
                System.out.println("ERROR: Invalid assignment token: " + token);
                System.exit(0);
        }

    }

    public void print(){
        if (expr != null){
            if (id2 != null){
                if (isArray){
                    // 'id [ id ] = <expr> ;' is an array assignment. Noted by the isArray flag.
                    System.out.print(id1 + "[" + id2 + "] = ");
                    expr.print();
                    System.out.println(";");
                } else{
                    // 'id = new object( id, <expr> );' is the only case left.
                    System.out.print(id1 + " = new object( " + id2 + ", ");
                    expr.print();
                    System.out.println(");");
                }
            } else{
                // 'id = <expr> ;' is the only case with without an id2.
                System.out.print(id1 + " = ");
                expr.print();
                System.out.println(";");
            }   
        } else{
            // 'id : id ;' is the only case without an expression.
            System.out.println(id1 + " : " + id2 + " ;");
        }
    }

    // ------------------- Helper Methods ------------------- //

    private void parseStandardVariable(){
        // Of the form 'id = <expr> ;'
        expr = new Expr(parser);
        expr.parse();
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
    }
    private void parseNewObject(){
        // Of the form 'new object( id, <expr> );'
        parser.stack.checkVariableType(id1, Core.OBJECT); // Assignee variable MUST be of type OBJECT
        parser.scanner.nextToken();
        parser.expectedToken(Core.OBJECT);
        parser.scanner.nextToken();
        parser.expectedToken(Core.LPAREN);
        parser.scanner.nextToken();
        parser.expectedToken(Core.ID);
        id2 = parser.scanner.getId();
        parser.scanner.nextToken();
        parser.expectedToken(Core.COMMA);
        parser.scanner.nextToken();
        expr = new Expr(parser);
        expr.parse();
        parser.expectedToken(Core.RPAREN);
        parser.scanner.nextToken();  
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
    }
    private void parseArray(){
        // Of the form '[ id ] = <expr> ;'
        parser.stack.checkVariableType(id1, Core.OBJECT); // Assignee variable MUST be of type OBJECT
        parser.scanner.nextToken();
        parser.expectedToken(Core.ID);
        id2 = parser.scanner.getId();
        parser.scanner.nextToken();
        parser.expectedToken(Core.RBRACE);
        parser.scanner.nextToken();
        parser.expectedToken(Core.ASSIGN);
        parser.scanner.nextToken();
        expr = new Expr(parser);
        expr.parse();
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
    }
    private void parseColon(){
        // Of the form ': id ;'
        parser.stack.checkVariableType(id1, Core.OBJECT); // Assignee variable MUST be of type OBJECT
        parser.scanner.nextToken();
        parser.expectedToken(Core.ID);
        id2 = parser.scanner.getId();
        parser.stack.checkVariableType(id2, Core.OBJECT); // Assigned variable MUST be of type OBJECT
        parser.scanner.nextToken();
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
    }
}