import java.io.BufferedReader;
import java.io.FileReader;
public class Scanner {

    public BufferedReader reader;
    private final char EOF = (char) -1;
    private String token = "";
    private String symbols = "+-*/=<>:;.,()[]" + EOF;
    // Initialize the scanner
    public Scanner(String filename) {
        try {
            // A buffered reader is chosen here to reduce the number of reads from the secondary storage medium. 
            // Memory access is faster than disk access.
            reader = new BufferedReader(new FileReader(filename));
            nextToken(); // Read the first token
        } catch (Exception IOException) {
            System.out.println("ERROR: Could not open" + filename + ".");
        }
    }

    // Advance to the next token
    public void nextToken() {
        assert (reader != null);
        assert (reader.markSupported()); // These asserts should realistically always pass.
        // Reset the token
        token = "";
        try{
            char currentChar = (char) reader.read();
            
            // Ignore preceding whitespace characters
            while (Character.isWhitespace(currentChar)) {
                currentChar = (char) reader.read();
            }

            // Check if the current character is a symbol.
            if (symbols.indexOf(currentChar) != -1){
                token += currentChar;
                // Check if the current character is an ASSIGN operator then peak to see if the token should be an EQUAL operator.
                if (currentChar == '=') {
                    reader.mark(2);
                    if (currentChar == (char) reader.read()) {
                        // currentChar is an EQUAL operator
                        token += currentChar;
                    } else {
                        // currentChar is an ASSIGN operator; reset the reader back to the previous character
                        reader.reset();
                    }  
                }
                return;
            }
            
            // Begin reading and adding characters to the token. Note: This is a really janky solution, but it works.
            boolean isConst = Character.isDigit(currentChar);
            while(currentChar != EOF && ((!isConst && Character.isLetter(currentChar)) || Character.isDigit(currentChar))){
                token += currentChar;
                reader.mark(2);
                currentChar = (char) reader.read();

                // Reset reader back to the previous character if the next character is not a letter or digit.
                if (!Character.isLetterOrDigit(currentChar)){
                    reader.reset();
                }
            }
            // This is for cases such as EOF or unrecognized symbols.
            if (token.length() == 0){
                token += currentChar;
            }
        } catch (Exception IOException) {
            System.out.println("ERROR: Could not read from file.");
        }
    }

    // Return the current token
    public Core currentToken() {
        // Disgusting switch statement for all possible tokens.
        switch(token){
            // Keywords
            case "and":
                return Core.AND;
            case "begin":
                return Core.BEGIN;
            case "do":
                return Core.DO;
            case "end":
                return Core.END;
            case "else":
                return Core.ELSE;
            case "is":
                return Core.IS;
            case "if":
                return Core.IF;
            case "in": 
                return Core.IN;
            case "integer":
                return Core.INTEGER;
            case "new":
                return Core.NEW;
            case "not":
                return Core.NOT;
            case "or":
                return Core.OR;
            case "out":
                return Core.OUT;
            case "object":
                return Core.OBJECT;
            case "procedure":
                return Core.PROCEDURE;
            case "return":
                return Core.RETURN;
            case "then":
                return Core.THEN;
            case "while":
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
                // We can determine what the token is by the first character.
                char firstChar = token.charAt(0);
                if (token.length() == 0 || firstChar == EOF){
                    return Core.EOS;
                } else if (Character.isDigit(firstChar) && Integer.parseInt(token) <= 99991) {
                    return Core.CONST;
                } else if (Character.isLetter(firstChar)) {
                    return Core.ID;
                } else{
                    System.out.println("ERROR: Invalid token: " + token);
                    return Core.ERROR;
                }
        }
    }

	// Return the identifier string
    public String getId() {
        assert token != null; // Realistically this should never be null, but just for good practice.
        return token;
    }

	// Return the constant value
    public int getConst() {
        assert token != null; // Same with this.
        return Integer.parseInt(token);
    }

}
