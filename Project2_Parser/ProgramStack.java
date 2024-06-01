import java.util.Stack;
import java.util.HashMap;
public class ProgramStack {
    Stack<HashMap<String, CoreVariable>> stack = new Stack<HashMap<String, CoreVariable>>();
    ProgramStack(){
    }
    public void push(String name, Core type){
        CoreVariable var = new CoreVariable(name, type);
        HashMap<String, CoreVariable> varMap = new HashMap<String, CoreVariable>();
        varMap.put(name, var);
        stack.push(varMap);
    }
    public void push(String name, Core type, String value){
        CoreVariable var = new CoreVariable(name, type, value);
        HashMap<String, CoreVariable> varMap = new HashMap<String, CoreVariable>();
        varMap.put(name, var);
        stack.push(varMap);
    }

    public Object pop(){
        return stack.pop();
    }

    public Object peek(){
        return stack.peek();
    }

    public void containsId(String id){
        if (indexOfVariable(id) == -1){
            System.out.println("ERROR: " + id + " has not been declared yet.");
            System.exit(0);
        }
    }

    public void doesNotContainId(String id){
        if (indexOfVariable(id) != -1){
            System.out.println("ERROR: " + id + " has already been declared.");
            System.exit(0);
        }
    }

    public int indexOfVariable(String id){
        for (HashMap<String, CoreVariable> varMap : stack){
            if (varMap.containsKey(id)){
                return stack.indexOf(varMap); 
            }
        }
        return -1;
    }

    public void checkVariableType(String name, Core type){
        if (getVariableType(name) != type){
            System.out.println("ERROR: " + name + " is not of type " + type);
            System.exit(0);
        }
    }

    // ------------------- Helper Methods ------------------- //

    private Core getVariableType(String name){
        HashMap<String, CoreVariable> varMap = stack.get(indexOfVariable(name));
        return varMap.get(name).getType();
    }


}
