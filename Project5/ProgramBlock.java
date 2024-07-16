import java.util.HashMap;
public class ProgramBlock{
    private HashMap<String, CoreVariable> variables = null;
    private HashMap<String, CoreVariable> globalVariables = null;
    ProgramBlock(){
        this.variables = new HashMap<>();
    }

    ProgramBlock(HashMap<String, CoreVariable> variables){
        this.variables = new HashMap<>(variables); // Local copy
        this.globalVariables = variables; // Global copy
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
        CoreVariable oldVar = getVariable(name);
        if (oldVar.getType() == Core.OBJECT){
            // Decrement the reference count of the old object and increment the new one
            if (((CoreObject) oldVar).getPairs() != null){
                // oldVar had a reference to another object.
                ((CoreObject) oldVar).decrementReferenceCount();
            }
        }
        variables.replace(name, var);
    }

    public void leaveBlock(){
        //System.out.println("Reference variables in block: " + variables.size());
        for (CoreVariable var : variables.values()){
            // We wish to decrement any reference counts of objects local to this block.
            // We do not wish to decrement the reference count of global variables.
            if (var.getType() == Core.OBJECT && !globalVariables.containsValue(var)){
                ((CoreObject) var).decrementReferenceCount();
            }
        }
        
    }
}