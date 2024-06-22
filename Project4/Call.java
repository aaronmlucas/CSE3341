public class Call{
    private final Parser parser;
    private String functionName;
    private Parameters parameters;

    Call(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        parser.expectedToken(Core.BEGIN);
        parser.scanner.nextToken();

        parser.expectedToken(Core.ID);
        functionName = parser.scanner.getId();

        parser.expectedToken(Core.LPAREN);
        parser.scanner.nextToken();
        parameters = new Parameters(parser);
        parameters.parse();
        parser.expectedToken(Core.RPAREN);
        parser.scanner.nextToken();

        parser.expectedToken(Core.SEMICOLON);
        parser.scanner.nextToken();
    }

    public void print(){
        System.out.println("begin " + functionName + "(");
        parameters.print();
        System.out.print(");");
    }

    public void execute(){

    }
}