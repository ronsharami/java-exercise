package Keys;

import java.util.Random;

public class SimpleKeyGenerator extends KeyGenerator {
	Random rand = new Random();
	protected int generateKey(int maxKey) {	
		int key = this.rand.nextInt(maxKey) + 1;
		return key;
	}

	public SimpleKeyGenerator() {
	}
}