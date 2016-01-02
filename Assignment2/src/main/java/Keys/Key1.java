package Keys;

import java.util.ArrayList;
import java.util.List;


public class Key1 extends Key<Integer> {

	public Key1(Integer value) {
		this.keyValue = value;
		this.numOfKeys = 1;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Integer exe(Integer t, Performer p) {
		return p.exe(this.keyValue, t);
	}

	@Override
	public Integer arc(Integer t, Performer p) {
		return p.arc(this.keyValue, t);
	}

	@Override
	public boolean keyOutOfRange(Integer maxKey) {
		// TODO Auto-generated method stub
		return this.keyValue >= maxKey;
	}
	@Override
	public List<Integer> getIterableValue() {
		List<Integer> l = new ArrayList<Integer>();
		l.add(this.keyValue);
		return l;
	}

	@Override
	public String toFileFormat() {
		return this.keyValue.toString();
	}
	






}
