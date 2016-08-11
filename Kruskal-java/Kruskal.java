import java.util.*;

class Kruskal {
	public static void main(String[] args) {
		int graph[][] = makeGraph();
		Kruskals(graph);
	}
	
	
	public static int[][] makeGraph() {
		int n1[] = {1,2,1,1,6,6,6,7,5,7,5,5,4,4,3};
		int n2[] = {2,3,6,7,3,7,5,5,3,8,4,8,8,3,8};
		int e[] = {9,24,14,15,18,5,30,20,2,44,11,16,7,6,19};
		
		int graph[][] = new int[n1.length][3];
		
		for (int i=0;i<n1.length;i++) {
			graph[i][0] = n1[i];
			graph[i][1] = e[i];
			graph[i][2] = n2[i];
		}
		
		return graph;
	}
	
	public static void Kruskals(int graph[][]) {
		// Sort edges in increasing order of weight
		graph = ArraySort(graph, 1);
		
		int[][] sets = PopulateSets(graph);
		int i1;
		int i2;
		int max_node=0;
		int min_node=0;
		int count = 0;
		for (int[] edge: graph) {
			System.out.printf("%d: ", ++count);
			i1 = FindEdge(sets, edge[0]);
			System.out.printf("Find(%d) returns %d\t", edge[0], i1);
			i2 = FindEdge(sets, edge[2]);
			System.out.printf("Find(%d) returns %d\t", edge[2], i2);
			
			if (i1 != i2) {
				if (i1 > i2){max_node = i1;min_node = i2;}
				if (i1 < i2){max_node = i2;min_node = i1;}
				sets[max_node] = UnionSets(sets[max_node], sets[min_node]);
				System.out.printf("Union(%d, %d) done\n", i1, i2);
			} else {
				System.out.printf("Union(%d, %d) not done. Same set\n", i1, i2);
			}
			ShowForest(sets);
		}
//		sets[0] = UnionSets(sets[0], sets[1]);
	}
	
	private static void ShowForest(int sets[][]) {
		for (int[] tree: sets) {
			System.out.printf("{");
			for (int node: tree) {
				if (node != 0)
					System.out.printf("%d, ", node);
			}
			System.out.printf("}, ");
		}
		System.out.printf("\n\n");
	}
	
	private static int[][] PopulateSets(int graph[][]) {
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		
		for (int i=0;i<graph.length;i++) {
			if (!nodes.contains(graph[i][0]))
				nodes.add(graph[i][0]);
			if (!nodes.contains(graph[i][2]))
				nodes.add(graph[i][2]);
		}
		
		int sets[][] = new int[nodes.size()][nodes.size()];
		Collections.sort(nodes);
		
		for (int i=0;i<nodes.size();i++) {
			sets[i][0] = nodes.get(i);
		}
		
		return sets;
	}

	private static int[][] ArraySort(int graph[][], int index) {
		Arrays.sort(graph, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[index], b[index]);
			}
		});
		
		return graph;
	}
	
	private static int FindEdge(int sets[][], int e) {
		for (int i=0;i<sets.length;i++) {
			for (int j=0;j<sets[i].length;j++) {
				if (e == sets[i][j]) {
					return i;
				}
			}
		}
		
		// Default case should never happen, since e will be in one of the sets
		return -1;
	}
	
	private static int[] UnionSets(int[] s1, int[] s2) {
		int new_set[] = new int[s1.length];
		int k=0;
		for (int i=0;i<s1.length;i++) {
			if (s1[i] != 0) {
				new_set[k] = s1[i];
				k++;
			}
		}
		
		for (int i=0;i<s2.length;i++) {
			if (s2[i] != 0) {
				new_set[k] = s2[i];
				s2[i] = 0;
				k++;
			}
		}
		
		return new_set;
		
	}
}