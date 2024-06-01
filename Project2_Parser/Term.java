public class Term {
    private Parser parser;
    public String strRep = ""; // The string representation of the term. Used for printing.
    public Factor factor;
    public Term term;
    public Core sign;
    Term(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form <factor> | <factor> * <term> | <factor> / <term>
        factor = new Factor(parser);
        factor.parse();
        strRep += factor.strRep;
        Core token = parser.scanner.currentToken();

        if (token == Core.MULTIPLY){
            // <factor> * <term>
            sign = token;
            parser.scanner.nextToken();
            term = new Term(parser);
            term.parse();
            strRep += " * " + term.strRep;
        } else if (token == Core.DIVIDE){
            // <factor> / <term>
            sign = token;
            parser.scanner.nextToken();
            term = new Term(parser);
            term.parse();
            strRep += " / " + term.strRep;
        }
    }
    public void print(){
        System.out.print(strRep);
    }
}
