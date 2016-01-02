package Keys;
import java.util.*;

public class KeyM1 extends Key<ArrayList<Integer>> {

	public KeyM1(Integer... arr) {
		this.keyValue =  (ArrayList<Integer>) arrayToList(arr);
		this.numOfKeys = this.keyValue.size();
	}
	public KeyM1(ArrayList<Integer> l) {
		this.keyValue = l;
		this.numOfKeys = this.keyValue.size();
	}
	static <T> List<T> arrayToList(final T[] array) {
		  final List<T> l = new ArrayList<T>(array.length);
		  for (final T s : array) {
		    l.add(s);
		  }
		  return (l);
		}
	@Override
	public Integer exe(Integer t, Performer p) {
		int a = t;
		for (int n:this.keyValue) {
			a = p.exe(a, n);
		}
		return a;
	}

	@Override
	public Integer arc(Integer t, Performer p) {
		int a = t;
		for (int n:this.keyValue) {
			a = p.arc(a, n);
		}
		return a;
	}
	@Override
	public boolean keyOutOfRange(Integer maxKey) {
		for(int n:this.keyValue) {
			if(n > maxKey) {
				return true;
			}
		}
		return false;
	}
	@Override
	public List<Integer> getIterableValue() {
		return this.keyValue;
	}
	@Override
	public String toFileFormat() {
		int i = 1;
		StringBuilder sb = new StringBuilder();
		for(Integer n:this.keyValue) { 
			sb.append(n.toString());
			if(i < this.keyValue.size()) {
				sb.append(System.lineSeparator());
			}
			i++;
		}
		return sb.toString();
	}

	
	//List<Integer>;
}
