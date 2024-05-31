public class Procedure {
    private String procedureName;
    private DeclSeq ds;
    private StmtSeq ss;
    private Parser parser;

    public Procedure(Parser parser) {
        this.parser = parser;
    }

    public void parse(){
        // Of the form  procedure ID is <decl-seq> begin <stmt-seq> end | procedure ID is begin <stmt-seq> end
        parser.expectedToken(Core.PROCEDURE);
        parser.scanner.nextToken();

        parser.expectedToken(Core.ID);
        procedureName = parser.scanner.getId();
        parser.scanner.nextToken();

        parser.expectedToken(Core.IS);
        parser.scanner.nextToken();

        if (parser.scanner.currentToken() != Core.BEGIN) {
            ds = new DeclSeq(parser);
            ds.parse();
        }

        parser.expectedToken(Core.BEGIN);
        parser.scanner.nextToken();

        ss = new StmtSeq(parser);
        ss.parse();

        parser.expectedToken(Core.END);
        parser.scanner.nextToken();  // Move past the END token
        parser.expectedToken(Core.EOS);
    }

    public void print() {
        System.out.println("procedure " + procedureName + " is ");
        if (ds != null) {
            ds.print();
        }
        System.out.println("begin");
        ss.print();
        System.out.println("end");
    }
}
