/**
 * The sample object in the sequence to sort
 */

public class Obj {

    private SequenceType sequenceType;
    private int id;
    private int attribute;

    public Obj(SequenceType sequenceType, int id, int n){
        this.sequenceType = sequenceType;
        this.id = id;
        this.attribute = n;
    }

    public SequenceType getSequenceType() {
        return this.sequenceType;
    }

    public int getId(){
        return this.id;
    }

    public int getAttribute(){
        return this.attribute;
    }

}
