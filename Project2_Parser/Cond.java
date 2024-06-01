public class Cond {
    public Parser parser;
    public String strRep = ""; // The string representation of the conditional. Used for printing.
    public Cmpr cmpr;
    public Cond cond;
    public Core modifier; // Maybe not the best name, but it determines the form the conditonal statement will take. IE 'not' | 'or' | 'and'
    Cond(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        // Of the form:  <cmpr> | not <cond> | [ <cond> ] | <cmpr> or <cond> | <cmpr> and <cond>
        Core token = parser.scanner.currentToken();
        switch(token){
            case Core.NOT:
                // not <cond>
                modifier = Core.NOT;
                parser.scanner.nextToken();
                cond = new Cond(parser);
                cond.parse();
                strRep += "not " + cond.strRep;
                break;
            case Core.LBRACE:
                // [ <cond> ]
                parser.scanner.nextToken();
                cond = new Cond(parser);
                cond.parse();
                parser.scanner.nextToken();
                strRep += "[" + cond.strRep + "]";
                break;
            default:
                // <cmpr> | <cmpr> or <cond> | <cmpr> and <cond>
                cmpr = new Cmpr(parser);
                cmpr.parse();
                token = parser.scanner.currentToken();
                if (token == Core.OR){
                    modifier = Core.OR;
                    parser.scanner.nextToken();
                    cond = new Cond(parser);
                    cond.parse();
                    strRep += " or " + cond.strRep;
                } else if (token == Core.AND){
                    modifier = Core.AND;
                    parser.scanner.nextToken();
                    cond = new Cond(parser);
                    cond.parse();
                    strRep += " and " + cond.strRep;
                }
        }
    }

    public class print(){
        
    }
}