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
    }

    public void print(){
        System.out.println("procedure " + functionName + "(");
        parameters.print();
        System.out.print(") is");
        ss.print();
        System.out.println("end");
    }

    public void execute(){

    }
}