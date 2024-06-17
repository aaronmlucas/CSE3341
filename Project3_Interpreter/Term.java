public class Term {
    private final Parser parser;
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
    public int execute(){
        if (term != null){
            if (sign == '*'){
                return factor.execute() * term.execute();
            } else {
                int divisor = term.execute();
                if (term.execute() == 0){
                    System.out.println("ERROR: Division by zero");
                    System.exit(0);
                }
                return factor.execute() / term.execute();
            }
        } else {
            return factor.execute();
        }
    }
}
