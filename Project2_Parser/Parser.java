public class Parser {
    Scanner scanner;
    Procedure procedure;

    Parser(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public void parse() {
        procedure = new Procedure(this);
        procedure.parse();
    }

    public void expectedToken(Core token) {
        if (this.scanner.currentToken() != token) {
            System.out.println("ERROR: expected " + token +" instead have " + this.scanner.currentToken());
            System.exit(0);
           }
    }
}
