import java.util.HashMap;
class CoreObject extends CoreVariable<HashMap<String, Integer>>{
    private final HashMap<String, Integer> pairs = new HashMap<>();
    private String name = null;
    public String defaultKey;

    CoreObject(String name){
        super(name, Core.OBJECT, 0);
        this.name = name;
    }
    // For "id = new object(id, <expr>);" assignments
    CoreObject(String name, String defaultKey, int defaultValue){
        super(name, Core.OBJECT, defaultValue);
        this.name = name;
        this.defaultKey = defaultKey;
        pairs.put(defaultKey, defaultValue);
    }

    public CoreObject(String name, Core type, int value) {
        super(name, type, value);
    }

    public CoreObject(String defaultKey, String name, Core type, int value) {
        super(name, type, value);
        this.defaultKey = defaultKey;
    }
    
    @Override
    public void setValue(String key, int value){
        pairs.put(key, value);
    }

    @Override
    public int getValue(String key){
        if (!pairs.containsKey(key)){
            System.out.println("ERROR: Key " + key + " not found in object " + this.name);
            System.exit(0);
        }
        return pairs.get(key);
    }

    // Grabs the default value when no key is specified.
    @Override
    public int getValue(){
        return pairs.get(defaultKey);
    }
}