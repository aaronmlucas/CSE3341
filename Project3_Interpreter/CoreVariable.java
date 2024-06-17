public abstract class CoreVariable<T> {
    private final String name;
    private final Core type;
    public String defaultKey; // THIS SHOULD NOT EXIST *but im too lazy to fix it*
    
    CoreVariable(String name, Core type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public Core getType(){
        return type;
    }

    // These suck. I need to find a better way to do this. Maybe this should be an interface instead?
    int getValue(){return -1;}
    int getValue(String id){return -1;}
    void setValue(int value){};
    void setValue(String key, int value){};
}
