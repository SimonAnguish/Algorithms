import java.util.*;

class Knapsack {
//	static int ITEM_COUNT = 5;
	public static int TENSILE_STRENGTH = 11;
	
	public static Sack ks = new Sack();
	public static House h = new House();
	public static int m[][] = new int[6][TENSILE_STRENGTH+1];
	
	public static void main(String[] args) {
		// Generate the Items from the example in class
		Item i1 = new Item(1, 1);
		Item i2 = new Item(2, 6);
		Item i3 = new Item(5, 18);
		Item i4 = new Item(6, 22);
		Item i5 = new Item(7, 28);
		
		// Add all the items to the 'house'
		h.add(i1);
		h.add(i2);
		h.add(i3);
		h.add(i4);
		h.add(i5);
		
		// Call the algorithm
		Knapsack_A();
		
		for (int i[]: m) {
			for (int j: i) {
				System.out.printf("%d, ", j);
			}
			System.out.printf("\n");
		}
		
		System.out.printf("Takeaway Value: %d\n", Highest_Value());
		Get_Objects_For_Knapsack();
		System.out.printf("Contents: %s\n", ks);
	}
	
	public static void Get_Objects_For_Knapsack() {
		int i = m.length-1;
		int j = m[0].length-1;
		while (j > 0) {
			if (h.get(i-1).weight > j) {
				i--;
			} else {
				int v1 = m[i-1][j];
				int v2 = h.get(i-1).value + m[i-1][j-h.get(i-1).weight];
				if (v1 > v2) i--;
				else {
					// Add the object to the knapsack
					ks.add(h.get(i-1));
					j = j - h.get(i-1).weight;
					i--;
				}
			}
		}
	}
	
	public static int Highest_Value() {
		return m[m.length-1][m[0].length-1];
	}
	
	/*
		Calculates with items we should steal
		@return - int: Value of the knapsack after stealing
	*/
	public static void Knapsack_A() {
		for (int i=0;i<TENSILE_STRENGTH+1;i++){
			m[0][i] = 0;
		}
		
		for (int i=1;i<m.length;i++) {
			for (int j=0;j<TENSILE_STRENGTH+1;j++) {
				if (h.get(i-1).weight > j) {
					m[i][j] = m[i-1][j];
				} else {
					int v1 = m[i-1][j];
					int v2 = h.get(i-1).value + m[i-1][j-h.get(i-1).weight];
					if (v1 > v2) m[i][j] = v1;
					else m[i][j] = v2;
				}
			}
		}
	}
}

class Sack extends ArrayList<Item> {
	public Sack() {}
	
	public String toString() {
		String rs = "";
		for (Item i: this) {
			rs += i + ", ";
		}
		
		return rs;
	}
}

class House extends ArrayList<Item> {
	public House() {}
}

class Item {
	public int weight;
	public int value;
	public int index;
	
	public Item() {
		this.weight = 0;
		this.value = 0;
	}
	
	public Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}
	
	public String toString() {
		return "Weight: " + this.weight + "\tValue: " + this.value;
	}
}