public class Cond {
    public Parser parser;
    public String strRep = ""; // The string representation of the conditional. Used for printing.
    Cond(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form:  <cmpr> | not <cond> | [ <cond> ] | <cmpr> or <cond> | <cmpr> and <cond>
        Core token = parser.scanner.currentToken();
        Cond cond;
        switch(token){
            case Core.NOT:
                parser.scanner.nextToken();
                cond = new Cond(parser);
                cond.parse();
                strRep += "not " + cond.strRep;
                break;
            case Core.LBRACE:
                parser.scanner.nextToken();
                cond = new Cond(parser);
                cond.parse();
                parser.scanner.nextToken();
                strRep += "[" + cond.strRep + "]";
                break;
            default:
                // <cmpr> | <cmpr> or <cond> | <cmpr> and <cond>
                parseComparison();
                token = parser.scanner.currentToken();
                if (token == Core.OR){
                    parser.scanner.nextToken();
                    cond = new Cond(parser);
                    cond.parse();
                    strRep += " or " + cond.strRep;
                } else if (token == Core.AND){
                    parser.scanner.nextToken();
                    cond = new Cond(parser);
                    cond.parse();
                    strRep += " and " + cond.strRep;
                }
        }
    }

    // ------------------- Helper Methods ------------------- //
    private void parseComparison(){
        // Of the form: <expr> == <expr> | <expr> < <expr>
        Expr expr = new Expr(parser);
        expr.parse();
        strRep += expr.strRep;
        Core token = parser.scanner.currentToken();
        if (token == Core.EQUAL){
            parser.scanner.nextToken();
            expr = new Expr(parser);
            expr.parse();
            strRep += " == " + expr.strRep;
        } else if (token == Core.LESS){
            parser.scanner.nextToken();
            expr = new Expr(parser);
            expr.parse();
            strRep += " < " + expr.strRep;
        }
    }
}