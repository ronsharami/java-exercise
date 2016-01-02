package DirectoryProcessor_pac;

import java.io.File;
import java.io.IOException;

import Assignment2.Encryptor;
import Keys.InvalidEncryptionKeyException;
import Keys.Key;

public class EncryptFile_Run implements Runnable{

	private File f;
	private Key k;
	private Encryptor myEncryptor;
	private EncryptionMode m;
	private String outfilepath;
	
	
	public EncryptFile_Run(File f, Key k, Encryptor myEncryptor,
			EncryptionMode m,String outfilepath) {
		super();
		this.f = f;
		this.k = k;
		this.myEncryptor = myEncryptor;
		this.m = m;
		this.outfilepath = outfilepath;
	}
	
	public void run() {
		// TODO Auto-generated method stub
			long startSingle;
			long endSingle;
			double dur;
			startSingle = System.currentTimeMillis();
			
		if(this.m.equals(EncryptionMode.Encryption)) {
			//enc
			try {
				this.myEncryptor.encryptFile
					(f.getAbsolutePath(), this.outfilepath, k);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidEncryptionKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(this.m.equals(EncryptionMode.Decryption)) {
			//dec
			try {
				this.myEncryptor.decryptFile
					(f.getAbsolutePath(), this.outfilepath, k);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidEncryptionKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		endSingle = System.currentTimeMillis();
		dur = (endSingle - startSingle)/1000.0;
		//System.out.println("The "+m.getName()+" of "+f.getName() +" end. its took "
		//		+dur+" second");
	}

}
