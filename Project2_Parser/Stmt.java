public class Stmt {
    private Parser parser;
    public String strRep = ""; // The string representation of the statement. Used for printing.
    Stmt(Parser parser){
        this.parser = parser;
    }
    public void parse(){
        Core token = parser.scanner.currentToken();
        switch(token){
            case Core.ID:
                parseAssignment();
                break;
            case Core.IF:
                parseIf();
                break;
            case Core.WHILE:
                parseLoop();
                break;
            case Core.OUT:                   
                parseOut();
                break;
            case Core.INTEGER:
                // Declaration of an integer variable
                parseDecl();
                break;
            case Core.OBJECT:
                // Declaration of an object variable
                parseDecl();
                break;
            default: 
                // Error
                System.out.println("ERROR: Invalid statement token: " + token);
                System.exit(0);
        }
    }
    public void print(){
        System.out.println(strRep);
    }

    // ------------------- Helper Methods ------------------- //
    // These may be better in their own separate classes but ¯\_(ツ)_/¯
    private void parseAssignment(){
        // Of the form 'id = <expr> ;' | 'id [ id ] = <expr> ;' | 'id = new object( id, <expr> );' | 'id : id ;'
        parser.expectedToken(Core.ID); // Redundancy.
        strRep += parser.scanner.getId();
        parser.scanner.nextToken();
        Core token = parser.scanner.currentToken();
        switch(token){
            case Core.ASSIGN:
                parser.scanner.nextToken();
                if (parser.scanner.currentToken() == Core.NEW){
                    // Of the form 'id = new object( id, <expr> );'
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
                    Expr expr = new Expr(parser);
                    expr.parse();
                    parser.expectedToken(Core.RPAREN);
                    parser.scanner.nextToken();  
                    strRep += ", " + expr.strRep + ")";     
                } else {
                    // Of the form 'id = <expr> ;'
                    Expr expr = new Expr(parser);
                    expr.parse();
                    strRep += "=" + expr.strRep;
                }
                parser.expectedToken(Core.SEMICOLON);
                parser.scanner.nextToken();
                strRep += ";";
                break;
            case Core.LBRACE:
                // Of the form 'id [ id ] = <expr> ;'
                parser.scanner.nextToken();
                parser.expectedToken(Core.ID);
                strRep += "[" + parser.scanner.getId();
                parser.scanner.nextToken();
                parser.expectedToken(Core.RBRACE);
                parser.scanner.nextToken();
                parser.expectedToken(Core.ASSIGN);
                parser.scanner.nextToken();
                Expr expr = new Expr(parser);
                expr.parse();
                parser.expectedToken(Core.SEMICOLON);
                parser.scanner.nextToken();
                strRep += "] = " + expr.strRep + ";";
                break;
            case Core.COLON:
                // Of the form 'id : id ;'
                parser.scanner.nextToken();
                parser.expectedToken(Core.ID);
                strRep += " : " + parser.scanner.getId();
                parser.scanner.nextToken();
                parser.expectedToken(Core.SEMICOLON);
                parser.scanner.nextToken();
                strRep += ";";
                break;
            default:
                // Error
                System.out.println("ERROR: Invalid assignment token: " + token);
                System.exit(0);
        }

    }
    private void parseIf(){
        // Of the form 'if <cond> then <stmt-seq> end' | 'if <cond> then <stmt-seq> else <stmt-seq> end'
        parser.expectedToken(Core.IF); // Redundancy.
        parser.scanner.nextToken();
        Cond cond = new Cond(parser);
        cond.parse();
        parser.expectedToken(Core.THEN);
        parser.scanner.nextToken();
        StmtSeq stmtSeq = new StmtSeq(parser);
        stmtSeq.parse();
        strRep += "if " + cond.strRep + " then\n" + stmtSeq.strRep;
        Core token = parser.scanner.currentToken();
        if (token == Core.ELSE){
            // else <stmt-seq>
            parser.scanner.nextToken();
            stmtSeq = new StmtSeq(parser);
            stmtSeq.parse();
            strRep += " else " + stmtSeq.strRep;
        }
        parser.expectedToken(Core.END);
        parser.scanner.nextToken();
        strRep += "\n\tend";
    }
    private void parseLoop(){
        // Of the form 'while <cond> do <stmt-seq> end'
        parser.expectedToken(Core.WHILE); // Redundancy.
        parser.scanner.nextToken();
        Cond cond = new Cond(parser);
        cond.parse();
        parser.expectedToken(Core.DO);
        parser.scanner.nextToken();
        StmtSeq stmtSeq = new StmtSeq(parser);
        stmtSeq.parse();
        parser.expectedToken(Core.END);
        parser.scanner.nextToken();
        strRep += "while " + cond.strRep + " do\n" + stmtSeq.strRep + "\n\tend";
    }
    private void parseOut(){
        // Of the form 'out ( <expr> ) ;'
        parser.expectedToken(Core.OUT); // Redundancy.
        parser.scanner.nextToken();
        parser.expectedToken(Core.LPAREN);
        parser.scanner.nextToken();
        Expr expr = new Expr(parser);
        expr.parse();
        parser.expectedToken(Core.RPAREN);
        parser.scanner.nextToken();
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
        strRep += "out(" + expr.strRep + ");";
    }
    private void parseDecl(){
        Decl newDecl = new Decl(parser);
        newDecl.parse();
        strRep += newDecl.strRep;
    }
}