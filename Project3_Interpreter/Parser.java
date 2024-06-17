public class Parser {
    Scanner scanner;
    Scanner fileInput;
    Procedure procedure;
    ProgramStack stack = new ProgramStack();

    Parser(Scanner scanner, Scanner fileInput) {
        this.scanner = scanner;
        this.fileInput = fileInput;
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
