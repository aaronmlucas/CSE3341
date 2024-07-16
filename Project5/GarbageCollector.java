public class GarbageCollector {
    private final Parser parser;
    private static int numAccessibleObjects;
    
    public GarbageCollector(Parser parser){
        this.parser = parser;
        numAccessibleObjects = 0;
    }

    public static int getNumAccessibleObjects(){
        return numAccessibleObjects;
    }

    public static void incrementNumAccessibleObjects(){
        numAccessibleObjects++;
        print();
    }

    public static void decrementNumAccessibleObjects(){
        numAccessibleObjects--;
        print();
    }

    public static void print(){
        System.out.println("gc:" + numAccessibleObjects);
    }
}
