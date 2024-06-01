public class Cond {
    public Parser parser;
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
            case NOT:
                // not <cond>
                modifier = Core.NOT;
                parser.scanner.nextToken();
                cond = new Cond(parser);
                cond.parse();
                break;
            case LBRACE:
                // [ <cond> ]
                parser.scanner.nextToken();
                cond = new Cond(parser);
                cond.parse();
                parser.scanner.nextToken();
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
                } else if (token == Core.AND){
                    modifier = Core.AND;
                    parser.scanner.nextToken();
                    cond = new Cond(parser);
                    cond.parse();
                }
        }
    }

    public void print(){
        if (cond != null){
            if (modifier == Core.NOT){
                // not <cond>
                System.out.print("not ");
                cond.print();
            } else if (modifier == Core.OR){
                // <cmpr> or <cond>
                cmpr.print();
                System.out.print(" or ");
                cond.print();
            } else if (modifier == Core.AND){
                // <cmpr> and <cond>
                cmpr.print();
                System.out.print(" and ");
                cond.print();
            } else { // If we wanted to be extra safe, this could also be modifier == null
                // '[ <cond> ]' is the only case with a condition and no modifier.
                System.out.print("[");
                cond.print();
                System.out.print("]");
            }

        } else{
            // <cmpr> is the only case without a conditional statement.
            cmpr.print();
        }
    }
}