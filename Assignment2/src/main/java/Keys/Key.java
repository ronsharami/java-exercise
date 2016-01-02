package Keys;

import java.util.List;


//import Shift.Performer;

public abstract class Key <T> {
	protected T keyValue;
	protected int numOfKeys;

	public abstract Integer exe(Integer t,Performer p);
	public abstract Integer arc(Integer t,Performer p);
	
	/*public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}*/


	public Key() {
		super();
	}
	
	public T getValue() {
		return keyValue;
	}
	
	public int getNumOfKeys() {
		return numOfKeys;
	}
	public abstract String toFileFormat();
	public abstract boolean keyOutOfRange(Integer maxKey) ;
	protected abstract List<Integer> getIterableValue();
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return toFileFormat();
	}
}
