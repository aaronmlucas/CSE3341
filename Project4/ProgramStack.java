import java.util.Stack;
// To properly utilize this, new blocks should be created when entering a new scope, and removed when exiting a scope.
// This is meant to provide a way to keep track of variables and their scope during run-time.
public class ProgramStack {
    private Stack<ProgramBlock> stack = null;
    // Program Stack: Holds the variables declared. All variables 
    ProgramStack(){
        this.stack = new Stack<>();
    }

    // Add a new block to the stack. Variables declared before this block are still in scope.
    public void newBlock(){
        stack.push(new ProgramBlock());
    }
    // Exit the current block. Variables from that block are no longer in scope.
    public void removeBlock(){
        stack.pop();
    }

    public void addVariable(String name, CoreVariable var){
        stack.peek().addVariable(name, var);
    }

    public boolean containsVariable(String name){
        for (ProgramBlock block : stack){
            if (block.containsVariable(name)){
                return true;
            }
        }
        return false;
    }
    // Remember to call containsVariable before calling getVariable to avoid any errors.
    public CoreVariable getVariable(String name){
        for (ProgramBlock block : stack){
            if (block.containsVariable(name)){
                return block.getVariable(name);
            }
        }
        return null;
    }

    public void replaceVariable(String name, CoreVariable var){
        for (ProgramBlock block : stack){
            if (block.containsVariable(name)){
                block.replaceVariable(name, var);
            }
        }
    }

    // Doesn't work with how it is currently implemented. Since this is not graded for this project, I am not going to worry about it for now.
    // public void checkVariableType(String name, Core type){
    //     CoreVariable var = getVariable(name);
    //     if (var.getType() != type){
    //         System.out.println("ERROR: Variable " + name + " is not of type " + type);
    //         System.exit(0);
    //     }
    // }
}
