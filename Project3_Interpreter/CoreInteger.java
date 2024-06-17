public class CoreInteger extends CoreVariable<Integer>{
    private int value = 0;

    CoreInteger(String name){
        super(name, Core.INTEGER);
    }

    @Override
    public void setValue(int value){
        this.value = value;
    }

    @Override
    public int getValue(){
        return this.value;
    }

}