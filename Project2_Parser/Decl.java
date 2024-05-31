public class Decl {
    private Parser parser;
    private Core varType;
    private String varName;
    public String strRep = ""; // The string representation of the declaration. Used for printing.
    
    Decl(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        if (parser.scanner.currentToken() == Core.INTEGER){
            parseIntegerDecl();
        } else if (parser.scanner.currentToken() == Core.OBJECT){
            parseObjectDecl();
        } else {
            System.out.println("ERROR: expected INTEGER or OBJECT instead have " + parser.scanner.currentToken());
            System.exit(0);
        }
        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
        strRep = varType.toString().toLowerCase() + " " + varName + ";";

    }

    public void print(){
        System.out.println(strRep);
    }

    // ------------------- Helper Methods ------------------- //

    // Of the form: Core.INTEGER Core.ID
    private void parseIntegerDecl(){
        parser.expectedToken(Core.INTEGER);
        this.varType = Core.INTEGER;
        parser.scanner.nextToken();

        parser.expectedToken(Core.ID);
        this.varName = parser.scanner.getId();
        parser.scanner.nextToken();
        }

    // Of the form: Core.OBJECT Core.ID
    private void parseObjectDecl(){
        parser.expectedToken(Core.OBJECT);
        this.varType = Core.OBJECT;
        parser.scanner.nextToken();

        parser.expectedToken(Core.ID);
        this.varName = parser.scanner.getId();
        parser.scanner.nextToken();
    }
}