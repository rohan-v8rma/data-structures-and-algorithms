// https://www.codingninjas.com/studio/problems/shortest-path-in-an-unweighted-graph_981297

public class Solution {

	// TLE: Modified version of DFS, that has complexity greater than O(V + E)
	// public static LinkedList<Integer> shortestPath(int[][] edges, int n, int m, int s, int t) {
	// 	// Write your code here.
	// 	adjList = new ArrayList<>();

	// 	for(int house = 0; house <= n; house++) {
	// 		adjList.add(new ArrayList<>());
	// 	}

	// 	for(int[] edge: edges) {
	// 		adjList.get(edge[0]).add(edge[1]);
	// 		adjList.get(edge[1]).add(edge[0]);
	// 	}

	// 	visited = new boolean[n + 1];

	// 	path = null;
		
	// 	LinkedList<Integer> currentPath = new LinkedList<>();

	// 	visited[s] = true;
	// 	dfs(s, t, currentPath);

	// 	return path;
	// }
	
	// static LinkedList<Integer> path = null;
	// static ArrayList<ArrayList<Integer>> adjList;
	// static boolean[] visited;
	
	// static void dfs(int currentNode, int destination, LinkedList<Integer> currentPath) {
	// 	currentPath.add(currentNode);

	// 	if(currentNode == destination) {
	// 		if(
	// 			path == null
	// 			||
	// 			currentPath.size() < path.size()
	// 		) {
	// 			path = new LinkedList<>(currentPath);
	// 		}

	// 	}
	// 	else if(path != null && currentPath.size() >= path.size()) {}
	// 	else {
			
	// 		for(int adjNode: adjList.get(currentNode)) {
	// 			if(!visited[adjNode]) {
	// 				visited[adjNode] = true;
	// 				dfs(adjNode, destination, currentPath);
	// 				visited[adjNode] = false;
	// 			}
	// 		}
	// 	}

	// 	currentPath.removeLast();
	// }

	// BFS appproach
	public static LinkedList<Integer> shortestPath(int[][] edges, int n, int m, int s, int t) {
		
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

		for(int house = 0; house <= n; house++) {
			adjList.add(new ArrayList<>());
		}

		for(int[] edge: edges) {
			adjList.get(edge[0]).add(edge[1]);
			adjList.get(edge[1]).add(edge[0]);
		}

		boolean[] visited = new boolean[n + 1];
		int[] parent = new int[n + 1];

		
		Queue<Integer> bfsQueue = new LinkedList<>();
		bfsQueue.offer(s);
		visited[s] = true;

		/* 
		Since we go in a BFS manner, 
		we are bound to reach the destination in shortest possible distance.
		*/
		while(bfsQueue != null) {
			int currentNode = bfsQueue.poll();

			for(int adjNode: adjList.get(currentNode)) {
				if(!visited[adjNode]) {
					/* 
					We update the parent of the adjacent node, only if it hasn't been visited.

					If it is already visited, it means a node closer to it got to it first,
					which is what we want so that destination is reached in shortest distance.
					*/
					parent[adjNode] = currentNode;

					if(adjNode == t) {
						bfsQueue = null;
						break;
					}

					visited[adjNode] = true;
					bfsQueue.offer(adjNode);
				}
			}
		}
		
		int currentNode = t;
		LinkedList<Integer> path = new LinkedList<>();

		while(currentNode != 0) {
			path.addFirst(currentNode);
			currentNode = parent[currentNode];
		}
	
		return path;
	}
}