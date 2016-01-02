package Assignment2;

import java.util.Comparator;

public class AlgorithmComparator implements Comparator<Algorithm> {



	public int compare(Algorithm o1, Algorithm o2) {
		// TODO Auto-generated method stub
		return Integer.compare(o1.getKeyStrength(), o2.getKeyStrength());
	}

}
