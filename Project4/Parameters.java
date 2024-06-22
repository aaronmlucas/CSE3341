import java.util.List;

public class Parameters{
    private final Parser parser;
    private List<String> paramNames;
    
    public Parameters(Parser parser){
        this.parser = parser;
    }

    public void parse(){
        while (parser.scanner.currentToken() == Core.ID){
            // Checking for expected token would be redundant here.
            paramNames.add(parser.scanner.getId());
            parser.scanner.nextToken();
            // Skip over any commas
            if (parser.scanner.currentToken() == Core.COMMA){
                parser.scanner.nextToken();
                parser.expectedToken(Core.ID); // Only another ID may follow a comma.
            }   
        }
    }

    public void print(){
        // There is likely a much better way to do this.
        if (!paramNames.isEmpty()){
            // Since paramNames is not empty, we print every parameter except for the last one with an appended comma.
            for (int i = 0; i < paramNames.size()-1; i++){
                System.out.print(paramNames.get(i) + ", ");
            }
            // Print out the last paramName in the array. This time, without a comma appended to it.
            System.out.print(paramNames.get(paramNames.size()-1));
        }
    }

    public void execute(){

    }
}