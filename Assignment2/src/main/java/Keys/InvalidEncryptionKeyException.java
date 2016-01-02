package Keys;

public class InvalidEncryptionKeyException extends Exception {
	int code;
	public InvalidEncryptionKeyException(int c) {
		this.code = c;
	}
	public String getErrorMessage() {
		String result = "";
		switch (this.code) {
		case 0:
			result = "Not enough keys";
			break;
		case 1:
			result = "Non numeric key";
			break;
		case 2:
			result = "Too bigger key";
			break;
		}
		return result;
	}
}
