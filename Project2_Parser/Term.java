public class Term {
    private Parser parser;
    public String strRep = ""; // The string representation of the term. Used for printing.
    Term(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form <factor> | <factor> * <term> | <factor> / <term>
        Factor factor = new Factor(parser);
        factor.parse();
        strRep += factor.strRep;
        Core token = parser.scanner.currentToken();

        if (token == Core.MULTIPLY){
            // <factor> * <term>
            parser.scanner.nextToken();
            Term term = new Term(parser);
            term.parse();
            strRep += " * " + term.strRep;
        } else if (token == Core.DIVIDE){
            // <factor> / <term>
            parser.scanner.nextToken();
            Term term = new Term(parser);
            term.parse();
            strRep += " / " + term.strRep;
        }
    }
    public void print(){
        System.out.print(strRep);
    }
}
