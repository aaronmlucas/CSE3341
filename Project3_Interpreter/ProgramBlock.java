import java.util.HashMap;
public class ProgramBlock{
    private HashMap<String, CoreVariable> variables = null;
    ProgramBlock(){
        this.variables = new HashMap<>();
    }

    public void addVariable(String name, CoreVariable var){
        variables.put(name, var);
    }

    public boolean containsVariable(String name){
        return variables.containsKey(name);
    }

    public CoreVariable getVariable(String name){
        return variables.get(name);
    }

    // Specifically for changing a variable's reference value
    public void replaceVariable(String name, CoreVariable var){
        variables.replace(name, var);
    }
}