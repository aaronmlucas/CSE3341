public class Call{
    private final Parser parser;
    private String functionName;
    private Parameters callParameters;

    Call(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        parser.expectedToken(Core.BEGIN);
        parser.scanner.nextToken();

        parser.expectedToken(Core.ID);
        functionName = parser.scanner.getId();
        parser.scanner.nextToken();

        parser.expectedToken(Core.LPAREN);
        parser.scanner.nextToken();
        callParameters = new Parameters(parser);
        callParameters.parse();
        parser.expectedToken(Core.RPAREN);
        parser.scanner.nextToken();

        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
    }

    public void print(){
        System.out.println("begin " + functionName + "(");
        callParameters.print();
        System.out.print(");");
    }

    public void execute(){
        parser.stack.newBlock(); // Create a new block for the function call
        if (parser.stack.functions.get(functionName) == null){
            System.out.println("ERROR: Function " + functionName + " does not exist.");
            System.exit(0);
        }
        parser.stack.functions.get(functionName).execute(callParameters); // Execute the function
        parser.stack.removeBlock(); // Remove the block after the function call
    }
}