public class Stmt {
    private Parser parser;
    public String strRep = ""; // The string representation of the statement. Used for printing.
    public Object child;
    Stmt(Parser parser){
        this.parser = parser;
    }
    public void parse(){
        Core token = parser.scanner.currentToken();
        switch(token){
            case Core.ID:
                child = new Assignment(parser);
                ((Assignment)child).parse();
                strRep += ((Assignment)child).strRep;
                break;
            case Core.IF:
                child = new IfStmt(parser);
                ((IfStmt)child).parse();
                strRep += ((IfStmt)child).strRep;
                break;
            case Core.WHILE:
                child = new Loop(parser);
                ((Loop)child).parse();
                strRep += ((Loop)child).strRep;
                break;
            case Core.OUT:                   
                child = new Out(parser);
                ((Out)child).parse();
                strRep += ((Out)child).strRep;
                break;
            case Core.INTEGER:
                // Declaration of an integer variable
                child = new Decl(parser);
                ((Decl)child).parse();
                strRep += ((Decl)child).strRep;
                break;
            case Core.OBJECT:
                // Declaration of an object variable
                child = new Decl(parser);
                ((Decl)child).parse();
                strRep += ((Decl)child).strRep;
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

}