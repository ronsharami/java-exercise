package Logger;

import Assignment2.EncryptionAlgorithm;
import DirectoryProcessor_pac.EncryptionMode;

public class EncryptionLogEventArgs {



	private boolean enc;
	private String fileF;
	private EncryptionAlgorithm algorithmY;
	private String fileX;
	private boolean start;
	private double dur = 0.0;
	private boolean isFile = true;
	private boolean syns = false;

	public EncryptionLogEventArgs(String originalFilePath,
			EncryptionAlgorithm alg,String outFilePath,boolean isEnc,
			boolean isStart,double dur) {
		super();
		this.enc = isEnc;
		this.fileF = outFilePath;
		this.algorithmY = alg;
		this.fileX = originalFilePath;
		this.start = isStart;
		this.dur = dur;
	}


	public EncryptionLogEventArgs(String originalFilePath, 
			EncryptionAlgorithm alg,String outFilePath,boolean isEnc, 
			boolean isStart,double dur, boolean isFile, boolean syns) {
		super();
		this.enc = isEnc;
		this.fileF = outFilePath;
		this.algorithmY = alg;
		this.fileX = originalFilePath;
		this.start = isStart;
		this.dur = dur;
		this.isFile = isFile;
		this.syns = syns;
	}


	public double getDur() {
		return dur;
	}

	public boolean isEnc() {
		return enc;
	}

	public String getFileF() {
		return fileF;
	}

	public EncryptionAlgorithm getAlgorithmY() {
		return algorithmY;
	}

	public String getFileX() {
		return fileX;
	}

	public boolean isStart() {
		return start;
	}
	public EncryptionLogEventArgs(String originalFilePath, 
			EncryptionAlgorithm alg,String outFilePath,
			boolean isEnc, boolean isStart) {
		this.fileX = originalFilePath;
		this.algorithmY = alg;
		this.fileF = outFilePath;
		this.enc = isEnc;
		this.start = isStart;
	}
	public EncryptionLogEventArgs(String originalFilePath, 
			EncryptionAlgorithm alg,String outFilePath,
			boolean isEnc, boolean isStart,boolean isFile, boolean syns) {
		this.fileX = originalFilePath;
		this.algorithmY = alg;
		this.fileF = outFilePath;
		this.enc = isEnc;
		this.start = isStart;
		this.isFile = isFile;
		this.syns = syns;
	}


	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EncryptionLogEventArgs) {
			return false;
		}
		EncryptionLogEventArgs obj2 = (EncryptionLogEventArgs)obj;
		if (!this.fileX.equals(obj2.fileX)) {
			return false;
		}
		if (!this.algorithmY.equals(obj2.algorithmY)) {
			return false;
		}
		if (!this.fileF.equals(obj2.fileF)) {
			return false;
		}
		if (this.enc != obj2.enc) {
			return false;
		}
		if (this.start != obj2.start) {
			return false;
		}

		return true;
	}
	public String infoMessage()
	{
		String s;
		if(this.enc){
			s = "encryption";
		}
		else
			s = "decryption";
		
		if(isFile) {
			

			return "the "+ s +" for file "+this.getFileX() +
					" with algorithm "+ this.getAlgorithmY().getName() +
					" took "+this.getDur() +" milisecondes. The encrypted file "+ 
					"is located in file " + this.getFileF(); 
		}
		else {
			String sy;
			if(this.syns){
				sy = "Synchronous";
			}
			else
				sy = "Asynchronous";
			return "the "+ s +" for Directory "+sy+" "+this.getFileX() +
					" with algorithm "+ this.getAlgorithmY().getName() +
					" took "+this.getDur()+" milisecondes. The encrypted "+ 
					"Directory is located in  " + this.getFileF(); 
		}
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int result = 17;
		result = 31 * result + this.fileX.hashCode();
		result = 31 * result + this.algorithmY.hashCode();
		result = 31 * result + this.fileF.hashCode();
		return result;

	}

}
