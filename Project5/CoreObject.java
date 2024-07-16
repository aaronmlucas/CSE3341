import java.util.HashMap;
class CoreObject extends CoreVariable<HashMap<String, Integer>>{
    private HashMap<String, Integer> pairs;
    public String defaultKey;
    private int referenceCount; // Tracks number of references to this object

    // For "object id;" assignments
    CoreObject(String name){
        super(name, Core.OBJECT);
        this.referenceCount = 0; // There is currently no object to refer to.
        //System.out.println("Creating object " + name + " with reference count " + referenceCount);
    }
    // For "id = new object(id, <expr>);" assignments
    CoreObject(String name, String defaultKey, int defaultValue){
        super(name, Core.OBJECT);
        this.defaultKey = defaultKey;
        super.defaultKey = defaultKey;
        this.pairs = new HashMap<>();
        pairs.put(defaultKey, defaultValue);
        this.referenceCount = 1;
        GarbageCollector.incrementNumAccessibleObjects();
    }
    
    @Override
    public void setValue(String key, int value){
        pairs.put(key, value);
    }

    @Override
    public int getValue(String key){
        if (!pairs.containsKey(key)){
            System.out.println("ERROR: Key " + key + " not found in object.");
            System.exit(0);
        }
        return pairs.get(key);
    }

    // Grabs the default value when no key is specified.
    @Override
    public int getValue(){
        return pairs.get(defaultKey);
    }

    public HashMap<String, Integer> getPairs(){
        return pairs;
    }

    // Specifically for copying via a "id : id" assignment.
    public void setPairs(HashMap<String, Integer> pairs){
        this.pairs = pairs;
    }

    // Project 5 changes; added reference counting

    public int getReferenceCount(){
        return referenceCount;
    }

    public void incrementReferenceCount(){
        referenceCount++;
        //System.out.println("Incrementing reference count of " + getName() + ". Now: " + getReferenceCount());
    }

    public void decrementReferenceCount(){
        if (referenceCount > 0){ // If there is no reference, nothing we can decrement.
            referenceCount--;
            if (referenceCount <= 0){
                GarbageCollector.decrementNumAccessibleObjects();
                //System.out.println("Decrementing reference count of " + getName() + ". Now: " + getReferenceCount());
            }
        }
    }
}