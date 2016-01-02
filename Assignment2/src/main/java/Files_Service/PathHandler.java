package Files_Service;

public class PathHandler {
	public static  String pathToDir(String sourceFilePath) {
		return sourceFilePath.substring(
				0,sourceFilePath.lastIndexOf('\\'));
	}
	public static  String pathToFileName(String sourceFilePath) {
		return sourceFilePath.substring(sourceFilePath.lastIndexOf('\\'));
	}
	public   String encryptionPath(String sourceFilePath) {
		String outPath;
		String fileName = sourceFilePath.substring(
  				sourceFilePath.lastIndexOf('\\')+1);
		outPath = pathToDir(sourceFilePath);
		
		outPath = outPath + "\\" + fileName.substring
				(0,fileName.lastIndexOf('.')) +"_encrypted";
		outPath = outPath +fileName.
				substring(fileName.lastIndexOf('.'));
		return outPath;
	}
	public  String decryptionPath(String sourceFilePath) {
		String outPath;
		String fileName = sourceFilePath.substring(
  				sourceFilePath.lastIndexOf('\\')+1);
		outPath = pathToDir(sourceFilePath);
		
		outPath = outPath + "\\" + fileName.substring
				(0,fileName.lastIndexOf('.')) +"_decrypted";
		outPath = outPath +fileName.
				substring(fileName.lastIndexOf('.'));
		return outPath;
	}
	public static String keyPathFile(String sourceFilePath) {
		String keysPath;
		keysPath = PathHandler.pathToDir(sourceFilePath)+"\\key.txt";
		return keysPath;
	}
	public static String keyPathDir(String sourceDirPath) {
		String keysPath;
		keysPath = sourceDirPath+"\\key.txt";
		return keysPath;
	}
}
