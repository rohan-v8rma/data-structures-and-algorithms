// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
// TODO: Make notes

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    static TreeMap<Integer, TreeMap<Integer, ArrayList<Integer>>> vertical;

    void dfs(TreeNode root, int col, int lvl) {
        if(root == null) return;

        vertical.putIfAbsent(col, new TreeMap<>());

        TreeMap<Integer, ArrayList<Integer>> columnIndexMap = vertical.get(col);

        columnIndexMap.putIfAbsent(lvl, new ArrayList<>());

        columnIndexMap.get(lvl).add(root.val);


        dfs(root.left, col - 1, lvl + 1);
        dfs(root.right, col + 1, lvl + 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        vertical = new TreeMap<>();

        dfs(root, 0, 0);

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for(TreeMap<Integer, ArrayList<Integer>> columnIndices: vertical.values()) {
            ArrayList<Integer> colArr = new ArrayList<>();

            for(ArrayList<Integer> columnIndex: columnIndices.values()) {
                Collections.sort(columnIndex);
                colArr.addAll(columnIndex);
            }

            result.add(colArr);
        }

        return (List)result;
    }

}