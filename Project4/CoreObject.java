import java.util.HashMap;
class CoreObject extends CoreVariable<HashMap<String, Integer>>{
    private final HashMap<String, Integer> pairs = new HashMap<>();
    private String name = null;
    public String defaultKey;

    CoreObject(String name){
        super(name, Core.OBJECT);
        this.name = name;
    }
    // For "id = new object(id, <expr>);" assignments
    CoreObject(String name, String defaultKey, int defaultValue){
        super(name, Core.OBJECT);
        this.name = name;
        this.defaultKey = defaultKey;
        super.defaultKey = defaultKey;
        pairs.put(defaultKey, defaultValue);
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