public class Term {
    private Parser parser;
    public Factor factor;
    public Term term;
    public char sign;
    Term(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form <factor> | <factor> * <term> | <factor> / <term>
        factor = new Factor(parser);
        factor.parse();
        Core token = parser.scanner.currentToken();

        if (token == Core.MULTIPLY){
            // <factor> * <term>
            sign = '*';
            parser.scanner.nextToken();
            term = new Term(parser);
            term.parse();
        } else if (token == Core.DIVIDE){
            // <factor> / <term>
            sign = '/';
            parser.scanner.nextToken();
            term = new Term(parser);
            term.parse();
        }
    }
    public void print(){
        if (term != null){
            // Print <factor> * <term> | <factor> / <term>
            factor.print();
            System.out.print(" " + sign + " ");
            term.print();
        } else {
            // Print <factor>
            factor.print();
        }
    }
}
