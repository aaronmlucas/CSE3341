public abstract class CoreVariable<T> {
    private final String name;
    private final Core type;
    private final int value;
    public String defaultKey; // THIS SHOULD NOT EXIST *but im too lazy to fix it*
    
    CoreVariable(String name, Core type, int value){
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

    public int getValue(){
        return value;
    }
    public int getValue(String id){return 0;}
    public void setValue(int value){}
    public void setValue(String key, int value){}
}
