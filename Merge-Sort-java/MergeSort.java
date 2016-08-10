import java.util.Random;
import javax.xml.transform.*;

class MergeSort {
	final static int ARRAY_LIST = 100;
	final static int MAX_RAND = 100;
		
	public static void main(String[] args) {
		Random rand = new Random();
		
		int[] to_merge = new int[ARRAY_LIST];
		
		for (int i=0;i<ARRAY_LIST;i++) {
			to_merge[i] = rand.nextInt(MAX_RAND);
		}
		printArray(to_merge);
		int[] sorted = mergesort(to_merge);
		printArray(sorted);
	}
	
	private static int[] mergesort(int[] m) {
		if (m.length <= 1) {
			return m;
		} else {
			int middle = m.length / 2;
			int[] left = new int[middle], right = new int[m.length - middle], result = new int[m.length];
			
			for (int i=0;i<middle;i++) {
				left[i] = m[i];
			}
			
			for (int i=middle;i<m.length;i++) {
				right[i-middle] = m[i];
			}
			
			left = mergesort(left);
			right = mergesort(right);
			if (left[middle-1] <= right[0]) {
				for (int i=0;i<middle;i++) {
					result[i] = left[i];
				}
				
				for (int i=middle;i<m.length;i++) {
					result[i] = right[i-middle];
				}
				
				return result;
			}
			
			result = merge(left, right);
			return result;
		}
	}
	
	private static int[] merge(int[] left, int[] right) {
		int[] 	result = new int[left.length + right.length],
				new_left, new_right;
		int count = 0;
		while (left.length > 0 && right.length > 0) {
			if (left[0] <= right[0]) {
				result[count] = left[0];
				
				new_left = new int[left.length-1];
				for (int i=1;i<left.length;i++) {
					new_left[i-1] = left[i];
				}
				left = new_left;
				count++;
			} else {
				result[count] = right[0];
				
				new_right = new int[right.length-1];
				for (int i=1;i<right.length;i++) {
					new_right[i-1] = right[i];
				}
				
				right = new_right;
				count++;
			}
		}
		
		if (left.length > 0) {
			for (int l : left) {
				result[count] = l;
				count++;
			}
		}
		
		if (right.length > 0) {
			for (int r : right) {
				result[count] = r;
				count++;
			}
		}
		
		return result;
	}
	
	private static void printArray(int[] m) {
		for (int i : m) {
			System.out.printf("%s, ", i);
		}
		System.out.printf("\n\n");
	}
}