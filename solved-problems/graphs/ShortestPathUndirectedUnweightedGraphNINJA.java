// https://www.codingninjas.com/studio/problems/shortest-path-in-an-unweighted-graph_981297

import java.util.*;

class Solution {
	/*
	____TLE: Modified version of DFS, that has complexity greater than O(V + E)____
	*/
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

	// 	dfs(s, t, currentPath);

	// 	return path;
	// }
	
	// static LinkedList<Integer> path = null;
	// static ArrayList<ArrayList<Integer>> adjList;
	// static boolean[] visited;
	
	// static void dfs(int currentNode, int destination, LinkedList<Integer> currentPath) {
	// 	visited[currentNode] = true;
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
	// 				dfs(adjNode, destination, currentPath);
					
	// 			}
	// 		}
	// 	}

	// 	currentPath.removeLast();
	// 	visited[currentNode] = false;
	// }

	/*
	____TLE: Modified version of DFS, that has complexity greater than O(V + E)____

	Changing to parent array doesn't really help because problem is in O(V + E) part
	*/
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
		
	// 	parent = new int[n + 1];

	// 	pathLen = Integer.MAX_VALUE;

	// 	parent[s] = -1;

	// 	dfs(s, t, -1);

	// 	return path;
	// }
	
	// static int[] parent;
	// static LinkedList<Integer> path = null;
	// static int pathLen;
	// static ArrayList<ArrayList<Integer>> adjList;
	// static boolean[] visited;

	// static void getPath(int destination) {
	// 	LinkedList<Integer> newPath = new LinkedList<>();

	// 	while(destination != -1) {
	// 		newPath.addFirst(destination);
	// 		destination = parent[destination];
	// 	}
		
	// 	path = newPath;
	// }
	
	// static void dfs(
	// 	int currentNode, 
	// 	int destination, 
	// 	int currPathLen
	// ) {
	// 	currPathLen++;
	// 	visited[currentNode] = true;

	// 	if(currentNode == destination && currPathLen < pathLen) {
	// 		pathLen = currPathLen;
	// 		getPath(destination);
	// 	}
	// 	else {	
	// 		for(int adjNode: adjList.get(currentNode)) {
	// 			if(!visited[adjNode]) {
	// 				int oldParent = parent[adjNode];
	// 				parent[adjNode] = currentNode;
	// 				dfs(adjNode, destination, currPathLen);
	// 				parent[adjNode] = oldParent;
	// 			}
	// 		}
	// 	}

	// 	visited[currentNode] = false;
	// }

	/*
	____BFS appproach____
	*/
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