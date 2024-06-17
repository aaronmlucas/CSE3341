public class Assignment{
    public Parser parser;
    public Expr expr;
    public String id1;
    public String id2;
    public boolean isObjectAccess = false;
    Assignment(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form 'id = <expr> ;' | 'id [ id ] = <expr> ;' | 'id = new object( id, <expr> );' | 'id : id ;'
        parser.expectedToken(Core.ID); // Redundancy.
        id1 = parser.scanner.getId();
        parser.scanner.nextToken();
        Core token = parser.scanner.currentToken();
        switch(token){
            case ASSIGN -> {
                parser.scanner.nextToken();
                if (parser.scanner.currentToken() == Core.NEW){
                    // id = new object ( id , <expr> ) ;
                    parseNewObject();
                } else {
                    // id = <expr> ;
                    parseStandardVariable();
                }
            }
            case LBRACE -> {
                isObjectAccess = true;
                parseArray();
            }
            case COLON -> parseColon();
            default -> {
                // Error
                System.out.println("ERROR: Invalid assignment token: " + token);
                System.exit(0);
            }
        }

    }

    public void print(){
        if (expr != null){
            if (id2 != null){
                if (isObjectAccess){
                    // 'id [ id ] = <expr> ;' is accessing an object. 
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

    public void execute(){
        if (parser.stack.containsVariable(id1)){
            if (expr != null){
                CoreVariable assignee = parser.stack.getVariable(id1);
                if (id2 != null){
                    if (isObjectAccess){
                        // 'id [ id ] = <expr> ;' is accessing an object. 
                        assignee.setValue(id2, expr.execute());
                    } else{
                        // 'id = new object( id, <expr> );' is the only case left.
                        CoreVariable var = new CoreObject(id1, id2, expr.execute());
                        parser.stack.replaceVariable(id1, var);
                    }
                } else{
                    // 'id = <expr> ;' is the only case with without an id2.
                    if (assignee.getType() == Core.INTEGER){
                        // Changing the integer's value
                        assignee.setValue(expr.execute());
                    } else if (assignee.getType() == Core.OBJECT){
                        // Changing the default key's value
                        int val = expr.execute();
                        assignee.setValue(assignee.defaultKey, val);
                    }
                }   
            } else{
                // 'id : id ;' is the only case without an expression.
                parser.stack.replaceVariable(id1, parser.stack.getVariable(id2));
            }
        } else {
            System.out.println(expr.execute());
            System.out.println("ERROR: variable " + id1 + " has not been declared.");
            System.exit(0);
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
        //parser.stack.checkVariableType(id1, Core.OBJECT); // Assignee variable MUST be of type OBJECT
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
    private void parseArray(){ // Wrong function name. Too lazy to come up with a better one.
        // Of the form '[ id ] = <expr> ;'
        //parser.stack.checkVariableType(id1, Core.OBJECT); // Assignee variable MUST be of type OBJECT
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
        //parser.stack.checkVariableType(id1, Core.OBJECT); // Assignee variable MUST be of type OBJECT
        parser.scanner.nextToken();
        parser.expectedToken(Core.ID);
        id2 = parser.scanner.getId();
        //parser.stack.checkVariableType(id2, Core.OBJECT); // Assigned variable MUST be of type OBJECT
        parser.scanner.nextToken();
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
    }
}