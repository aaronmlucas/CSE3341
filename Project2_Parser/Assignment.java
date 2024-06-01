public class Assignment(){
    public Parser parser;
    public Expr expr;
    Assignment(Parser parser){
        this.parser = parser;
    }

    private void parse(){
        // Of the form 'id = <expr> ;' | 'id [ id ] = <expr> ;' | 'id = new object( id, <expr> );' | 'id : id ;'
        parser.expectedToken(Core.ID); // Redundancy.
        String assignee = parser.scanner.getId();
        parser.stack.containsId(assignee);
        strRep += parser.scanner.getId();
        parser.scanner.nextToken();
        Core token = parser.scanner.currentToken();
        switch(token){
            case Core.ASSIGN:
                parser.scanner.nextToken();
                parser.scanner.currentToken() == Core.NEW ? parseNewObject() : parseStandardVariable();
                break;
            case Core.LBRACE:
                parseArray();
                break;
            case Core.COLON:
                parseColon();
                break;
            default:
                // Error
                System.out.println("ERROR: Invalid assignment token: " + token);
                System.exit(0);
        }

    }

    public void print(){
        System.out.println(strRep);
    }

    // ------------------- Helper Methods ------------------- //

    private void parseStandardVariable(){
        // Of the form 'id = <expr> ;'
        expr = new Expr(parser);
        expr.parse();
        strRep += "=" + expr.strRep;
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
        strRep += ";";
    }
    private void parseNewObject(){
        // Of the form 'new object( id, <expr> );'
        parser.stack.checkVariableType(assignee, Core.OBJECT); // Assignee variable MUST be of type OBJECT
        parser.scanner.nextToken();
        parser.expectedToken(Core.OBJECT);
        parser.scanner.nextToken();
        parser.expectedToken(Core.LPAREN);
        parser.scanner.nextToken();
        parser.expectedToken(Core.ID);
        strRep += "=new object(" + parser.scanner.getId();
        parser.scanner.nextToken();
        parser.expectedToken(Core.COMMA);
        parser.scanner.nextToken();
        expr = new Expr(parser);
        expr.parse();
        parser.expectedToken(Core.RPAREN);
        parser.scanner.nextToken();  
        strRep += ", " + expr.strRep + ")";
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
        strRep += ";";
    }
    private void parseArray(){
        // Of the form '[ id ] = <expr> ;'
        parser.stack.checkVariableType(assignee, Core.OBJECT); // Assignee variable MUST be of type OBJECT
        parser.scanner.nextToken();
        parser.expectedToken(Core.ID);
        strRep += "[" + parser.scanner.getId();
        parser.scanner.nextToken();
        parser.expectedToken(Core.RBRACE);
        parser.scanner.nextToken();
        parser.expectedToken(Core.ASSIGN);
        parser.scanner.nextToken();
        expr = new Expr(parser);
        expr.parse();
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
        strRep += "] = " + expr.strRep + ";";
    }
    private void parseColon(){
        // Of the form ': id ;'
        parser.stack.checkVariableType(assignee, Core.OBJECT); // Assignee variable MUST be of type OBJECT
        parser.scanner.nextToken();
        parser.expectedToken(Core.ID);
        String assigned = parser.scanner.getId();
        strRep += " : " + assigned;
        parser.stack.checkVariableType(assigned, Core.OBJECT); // Assigned variable MUST be of type OBJECT
        parser.scanner.nextToken();
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
        strRep += ";";
    }
}