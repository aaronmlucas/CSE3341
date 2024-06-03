public class Stmt {
    private Parser parser;
    public Object child;
    public Core stmtType;
    Stmt(Parser parser){
        this.parser = parser;
    }
    public void parse(){
        stmtType = parser.scanner.currentToken();
        switch(stmtType){
            case ID:
                child = new Assignment(parser);
                ((Assignment)child).parse();
                break;
            case IF:
                child = new IfStmt(parser);
                ((IfStmt)child).parse();
                break;
            case WHILE:
                child = new Loop(parser);
                ((Loop)child).parse();
                break;
            case OUT:                   
                child = new Out(parser);
                ((Out)child).parse();
                break;
            case INTEGER:
                // Declaration of an integer variable
            case OBJECT:
                // Declaration of an object variable
                child = new Decl(parser);
                ((Decl)child).parse();
                break;
            default: 
                // Error
                System.out.println("ERROR: Invalid statement token: " + stmtType);
                System.exit(0);
        }
    }
    public void print(){
        switch(stmtType){
            case ID:
                ((Assignment)child).print();
                break;
            case IF:
                ((IfStmt)child).print();
                break;
            case WHILE:
                ((Loop)child).print();
                break;
            case OUT:
                ((Out)child).print();
                break;
            case INTEGER:
            case OBJECT:
                ((Decl)child).print();
                break;
            default:
                // Error. I sure hope this never happens.
                System.out.println("ERROR: Printing Error. Invalid statement token: " + stmtType);
                System.exit(0);
        }
    }

}