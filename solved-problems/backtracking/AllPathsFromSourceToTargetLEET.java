// https://leetcode.com/problems/all-paths-from-source-to-target/

class Return {
    int[] topSortPositions;
    int firstElementPosition;
    int lastElementPosition;

    Return(int[] _topSortPositions, int _firstElementPosition, int _lastElementPosition) {
        topSortPositions = _topSortPositions;
        firstElementPosition = _firstElementPosition;
        lastElementPosition = _lastElementPosition;
    }
}

class Solution {
    public Return getTopSort(int[][] graph) {
        int n = graph.length;

        int[] degrees = new int[n];

        for(int from = 0; from < n; from++) {
            for(int to = 0; to < graph[from].length; to++) {
                degrees[graph[from][to]] += 1;
            }
        }

        int positionValue = 0;
        int[] topSortPositions = new int[n];
        int firstElementPosition = -1;
        int lastElementPosition = -1;

        
        while(positionValue < n) {
            for(int degIdx = 0; degIdx < n; degIdx++) {
                if(degrees[degIdx] == 0) {
                    if(degIdx == 0) {
                        firstElementPosition = positionValue;
                    }
                    else if(degIdx == n - 1) {
                        lastElementPosition = positionValue;
                    }

                    topSortPositions[degIdx] = positionValue++;

                    degrees[degIdx] = -1;
                    for(int graphIdx = 0; graphIdx < graph[degIdx].length; graphIdx++) {
                        degrees[graph[degIdx][graphIdx]]--;
                    }
                }
            }
        }

        Return returnVal = new Return(topSortPositions, firstElementPosition, lastElementPosition);

        return returnVal;
    }

    static ArrayList<ArrayList<Integer>> paths;

    static ArrayList<Integer> currentPath = new ArrayList<Integer>();

    public static void findPaths(int currentVertex, int[][] graph, Return result) {
        if(currentVertex == graph.length - 1) {
            currentPath.add(currentVertex);
            paths.add(new ArrayList<Integer>(currentPath));
            currentPath.remove(currentPath.size() - 1);
            return;
        }

        if(result.topSortPositions[currentVertex] > result.lastElementPosition) {
            return;
        }

        currentPath.add(currentVertex);
        for(int edgeTo: graph[currentVertex]) {
            findPaths(edgeTo, graph, result);
        }
        currentPath.remove(currentPath.size() - 1);;
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // Since it is a directed acyclic graph, we can obtain a top sort.
        Return returnVal = getTopSort(graph);

        int[] topSortPositions = returnVal.topSortPositions;
        int firstElementPosition = returnVal.firstElementPosition;
        int lastElementPosition = returnVal.lastElementPosition;

        paths = new ArrayList<ArrayList<Integer>>();

        findPaths(0, graph, returnVal);

        return (List)paths;

    }
}