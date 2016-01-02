package Keys;


public abstract class KeyGenerator {
	protected abstract int generateKey(int maxKey);
	
	public KeyM1 createKeys(int num, int maxKey) {
		Integer [] arr = new Integer[num];
		for(int i=0;i<num;i++) {
			arr[i] = new Integer(generateKey(maxKey));
		}
		KeyM1 keys = new KeyM1(arr);
		return keys;
	}
	public Key1 createSingleKey(int maxKey) {
		return new Key1(generateKey(maxKey));
	}
}
