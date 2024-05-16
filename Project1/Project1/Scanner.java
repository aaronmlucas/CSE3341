import java.io.BufferedReader;
import java.io.FileReader;
class Scanner {

    public BufferedReader reader;
    private String token = "";
    private String symbols = "+-*/=<>:;.,()[]";
    // Initialize the scanner
    Scanner(String filename) {
        try {
            // A buffered reader is chosen here to reduce the number of reads from the secondary storage medium. 
            // Memory access is faster than disk access.
            reader = new BufferedReader(new FileReader(filename));
            nextToken(); // Read the first token

        } catch (Exception IOException) {
            System.out.println("Error: Could not open" + filename + ".");
        }
    }

    // Advance to the next token
    public void nextToken() {
        // Reset the token
        token = "";
        try{
            char currentChar = (char) reader.read();

            // Ignore preceding whitespace characters
            while (Character.isWhitespace(currentChar)) {
                currentChar = (char) reader.read();
            }
            token += currentChar;

            // Check if the current character is a symbol.
            if (symbols.indexOf(currentChar) != -1){
                // Check if the current character is an ASSIGN operator then peak to see if the token should be an EQUAL operator.
                if (currentChar == '=') {
                    reader.mark(2);
                    if (currentChar == (char) reader.read()) {
                        token += currentChar;
                    } else {
                        // Reset the reader back to the previous character
                        reader.reset();
                    }  
                }
                return;
            }
            
            // Begin reading and adding characters to the token
            boolean isConst = Character.isDigit(currentChar);
            while(currentChar != (char) -1 && (!isConst && Character.isLetterOrDigit(currentChar)) || (isConst && Character.isDigit(currentChar))){
                reader.mark(2);
                currentChar = (char) reader.read();
                token += currentChar;
                
            }
            reader.reset();

        } catch (Exception IOException) {
            System.out.println("Error: Could not read from file.");
        }
        
    }

    // Return the current token
    public Core currentToken() {
        // Disgusting switch statement for all possible tokens.
        switch(token){
            // Keywords
            case "AND":
                return Core.AND;
            case "BEGIN":
                return Core.BEGIN;
            case "DO":
                return Core.DO;
            case "END":
                return Core.END;
            case "ELSE":
                return Core.ELSE;
            case "IS":
                return Core.IS;
            case "IF":
                return Core.IF;
            case "IN": 
                return Core.IN;
            case "INTEGER":
                return Core.INTEGER;
            case "NEW":
                return Core.NEW;
            case "NOT":
                return Core.NOT;
            case "OR":
                return Core.OR;
            case "OUT":
                return Core.OUT;
            case "OBJECT":
                return Core.OBJECT;
            case "PROCEDURE":
                return Core.PROCEDURE;
            case "RETURN":
                return Core.RETURN;
            case "THEN":
                return Core.THEN;
            case "WHILE":
                return Core.WHILE;
            // Symbols
            case "+":
                return Core.ADD;
            case "-":
                return Core.SUBTRACT;
            case "*":
                return Core.MULTIPLY;
            case "/":
                return Core.DIVIDE;
            case "=":
                return Core.ASSIGN;
            case "==":
                return Core.EQUAL;
            case "<":
                return Core.LESS;
            case ":":
                return Core.COLON;
            case ";":
             return Core.SEMICOLON;
            case ".":
                return Core.PERIOD;
            case ",":
                return Core.COMMA;
            case "(":
                return Core.LPAREN;
            case ")":
                return Core.RPAREN;
            case "[":
                return Core.LBRACE;
            case "]":
                return Core.RBRACE;
            // Others
            default:
                if (Character.isDigit(token.charAt(0))) {
                    return Core.CONST;

                } else if (Character.isLetter(token.charAt(0))) {
                    return Core.ID;

                } else if (token.charAt(0) == (char) -1 ) {
                    return Core.EOS;

                } else{
                    System.out.println("ERROR: Invalid token: " + token);
                    return Core.ERROR;
                }
        }
    }

	// Return the identifier string
    public String getId() {
        return token;
    }

	// Return the constant value
    public int getConst() {
        return Integer.parseInt(token);
    }

}
