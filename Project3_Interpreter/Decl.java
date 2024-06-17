public class Decl {
    private final Parser parser;
    private Core varType;
    private String varName;
    
    Decl(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        if (null == parser.scanner.currentToken()){
            System.out.println("ERROR: expected INTEGER or OBJECT instead have " + parser.scanner.currentToken());
            System.exit(0);
        } else switch (parser.scanner.currentToken()) {
            case INTEGER -> // Declaration of an integer variable
                parseIntegerDecl();
            case OBJECT -> // Declaration of an object variable
                parseObjectDecl();
            default -> {
                System.out.println("ERROR: expected INTEGER or OBJECT instead have " + parser.scanner.currentToken());
                System.exit(0);
            }
        }
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
    }

    public void print(){
        System.out.println(varType.toString().toLowerCase() + " " + varName + ";");
    }

    // ------------------- Helper Methods ------------------- //

    // Of the form: integer id
    private void parseIntegerDecl(){
        parser.expectedToken(Core.INTEGER);
        this.varType = Core.INTEGER;
        parser.scanner.nextToken();

        parser.expectedToken(Core.ID);
        this.varName = parser.scanner.getId();
        parser.scanner.nextToken();
        }

    // Of the form: object id
    private void parseObjectDecl(){
        parser.expectedToken(Core.OBJECT);
        this.varType = Core.OBJECT;
        parser.scanner.nextToken();

        parser.expectedToken(Core.ID);
        this.varName = parser.scanner.getId();
        parser.scanner.nextToken();
    }

    // Returns the CoreVariable object that represents the variable declared
    public CoreVariable execute(){
        if (parser.stack.containsVariable(varName)){
            System.out.println("ERROR: variable " + varName + " has already been declared.");
            System.exit(0);
        }else{
            if (varType == Core.INTEGER){
                return new CoreInteger(varName);
            } else if (varType == Core.OBJECT){
                return new CoreObject(varName);
            }
        }
        return null;
    }
}