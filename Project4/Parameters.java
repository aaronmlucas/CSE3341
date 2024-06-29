import java.util.List;

public class Parameters{
    private final Parser parser;
    public final List<String> paramNames;
    
    public Parameters(Parser parser){
        this.parser = parser;
        this.paramNames = new java.util.ArrayList<>();
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

    // Arguments are parameter values passed by the caller. This acts as Parameters' 'execute' method.
    public void loadValues(Parameters passedValues){
        if (passedValues.paramNames.size() != paramNames.size()){
            System.out.println("ERROR: Number of parameters passed does not match number of parameters expected.");
            System.exit(0);
        }
        for (int i = 0; i < passedValues.paramNames.size(); i++) {
            CoreVariable var = parser.stack.getVariable(passedValues.paramNames.get(i)); // Find the value of the passed in variable.
            parser.stack.addVariable(paramNames.get(i), var);  
        }

    }
}