public class Function{
    private final Parser parser;
    private String functionName;
    private Parameters parameters;
    private StmtSeq ss;

    Function(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        parser.expectedToken(Core.PROCEDURE);
        parser.scanner.nextToken();

        parser.expectedToken(Core.ID);
        functionName = parser.scanner.getId();
        parser.scanner.nextToken();

        parser.expectedToken(Core.LPAREN);
        parser.scanner.nextToken();
        parameters = new Parameters(parser);
        parameters.parse();
        parser.expectedToken(Core.RPAREN);
        parser.scanner.nextToken();

        parser.expectedToken(Core.IS);
        parser.scanner.nextToken();

        ss = new StmtSeq(parser);
        ss.parse();

        parser.expectedToken(Core.END);
        parser.scanner.nextToken();

        if (parser.stack.functions.containsKey(functionName)){
            System.out.println("ERROR: Function " + functionName + " already exists.");
            System.exit(0);
        }
        parser.stack.functions.put(functionName, this);
    }

    public void print(){
        System.out.println("procedure " + functionName + "(");
        parameters.print();
        System.out.print(") is");
        ss.print();
        System.out.println("end");
    }

    public void execute(Parameters passedValues){
        parameters.loadValues(passedValues); // Set the parameters to the values passed by the caller
        ss.execute(); // Execute the function's statements
    }
}