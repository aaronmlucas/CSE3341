public class Stmt {
    private final Parser parser;
    public Object child;
    public Core stmtType;
    Stmt(Parser parser){
        this.parser = parser;
    }
    public void parse(){
        stmtType = parser.scanner.currentToken();
        switch(stmtType){
            case ID -> {
                child = new Assignment(parser);
                ((Assignment)child).parse();
            }
            case IF -> {
                child = new IfStmt(parser);
                ((IfStmt)child).parse();
            }
            case WHILE -> {
                child = new Loop(parser);
                ((Loop)child).parse();
            }
            case OUT -> {                   
                child = new Out(parser);
                ((Out)child).parse();
            }
            case INTEGER, OBJECT -> {
                // Declaration of an object variable
                child = new Decl(parser);
                ((Decl)child).parse();
            }
            case BEGIN -> {
                child = new Call(parser);
                ((Call)child).parse();
            }
            default -> { 
                // Error
                System.out.println("ERROR: Invalid statement token: " + stmtType);
                System.exit(0);
            }
        }
        // Declaration of an integer variable
            }
    public void print(){
        switch(stmtType){
            case ID -> ((Assignment)child).print();
            case IF -> ((IfStmt)child).print();
            case WHILE -> ((Loop)child).print();
            case OUT -> ((Out)child).print();
            case INTEGER, OBJECT -> ((Decl)child).print();
            default -> {
                // Error. I sure hope this never happens.
                System.out.println("ERROR: Printing Error. Invalid statement token: " + stmtType);
                System.exit(0);
            }
        }
    }
    public void execute(){
        switch(stmtType){
            case ID -> ((Assignment)child).execute();
            case IF -> ((IfStmt)child).execute();
            case WHILE -> ((Loop)child).execute();
            case OUT -> ((Out)child).execute();
            case INTEGER, OBJECT -> ((Decl)child).execute();
            default -> {
                // Error. I sure hope this also never happens.
                System.out.println("ERROR: Execution Error. Invalid statement token: " + stmtType);
                System.exit(0);
            }
        }
    }
}