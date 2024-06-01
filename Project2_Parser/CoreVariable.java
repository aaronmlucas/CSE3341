
public class CoreVariable {
    private String name;
    private Core type;
    private String value;
    
    CoreVariable(String name, Core type){
        this.name = name;
        this.type = type;
        this.value = null;
    }

    CoreVariable(String name, Core type, String value){
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public Core getType(){
        return type;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }
}
