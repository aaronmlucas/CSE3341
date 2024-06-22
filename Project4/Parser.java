public class Parser {
    Scanner scanner;
    Scanner fileInput;
    Procedure mainProcedure;
    ProgramStack stack = new ProgramStack();

    Parser(Scanner scanner, Scanner fileInput) {
        this.scanner = scanner;
        this.fileInput = fileInput;
    }
    
    public void parse() {
        mainProcedure = new Procedure(this);
        mainProcedure.parse();
    }

    public void expectedToken(Core token) {
        if (this.scanner.currentToken() != token) {
            System.out.println("ERROR: expected " + token +" instead have " + this.scanner.currentToken());
            System.exit(0);
           }
    }
}
