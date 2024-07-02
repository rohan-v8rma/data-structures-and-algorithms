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
    /*
    ____ATTEMPT 1____

    Tried using arrays to denote variants of trees.

    But the problem comes in cloning the array, since other
    unintentional parts of that array are also cloned.

    Won't work.
    */
    // void addToTree(int num, int idx, ArrayList<Integer> tree) {
    //     while(idx >= tree.size()) {
    //         tree.add(0);
    //     }

    //     tree.set(idx, num);
    // }

    // void removeFromTree(int num, )

    // TreeNode recurse(int n, int min, int max, int treeIdx, int nodeIdx) {
    //     int newTreeIdx = treeIdx;

    //     for(int k = 0; k < n; k++) {
    //         if(k != 0) {
    //             newTreeIdx = potentialTrees.size();
    //             potentialTrees.add(potentialsTrees.get(treeIdx).clone());
    //             addToTree(min, nodeIdx, potentialTrees.get(treeIdx));

                
    //         }
    //         else {
    //             int 
                

    //             addToTree(min + k, nodeIdx, potentialTrees.get(newTreeIdx));

    //             recurse(
    //                 k, 
    //                 min, 
    //                 min + k, 
    //                 newTreeIdx, 
    //                 2 * nodeIdx + 1
    //             );

    //             recurse(
    //                 n - k - 1, 
    //                 min + k + 1, 
    //                 max, 
    //                 newTreeIdx, 
    //                 2 * nodeIdx + 2
    //             );
    //         }
    //     }
    // }

    // TreeNode createTree(ArrayList<Integer> tree, int idx) {
    //     if(idx >= tree.size() || tree.get(idx) == 0) {
    //         return null;
    //     }

    //     TreeNode root = new TreeNode(tree.get(idx));

    //     root.left = createTree(tree, 2 * idx + 1);
    //     root.right = createTree(tree, 2 * idx + 2);

    //     return root;
    // }

    // ArrayList<ArrayList<Integer>> potentialTrees;

    // ArrayList<TreeNode> trees;

    // public List<TreeNode> generateTrees(int n) {
    //     trees = new ArrayList<>();

    //     recurse(n, 1, n + 1, 0, 0);

    //     for(ArrayList<Integer> tree: potentialTrees) {
    //         trees.add(createTree(tree, 0));
    //     }

    //     return (List)trees;
    // }

    /*
    ____OPTIMAL____

    min is inclusive
    max is exclusive
    */
    List<TreeNode> getTrees(int min, int max) {
        List<TreeNode> trees = new ArrayList<>();

        int n = max - min;

        if(max - min == 1) {
            trees.add(new TreeNode(min));
        }
        else if(max == min) {
            trees.add(null);
        }
        else {
            for(int k = 0; k < n; k++) {
                int root = min + k;

                List<TreeNode> leftSubtrees = getTrees(min, root);
                List<TreeNode> rightSubtrees = getTrees(root + 1, max);

                for(TreeNode leftSubtree: leftSubtrees) {
                    for(TreeNode rightSubtree: rightSubtrees) {
                        /*
                        We are saving on memory by multiple roots
                        pointing to the leftSubtree or rightSubtree.
                        */
                        trees.add(new TreeNode(root, leftSubtree, rightSubtree));
                    }
                }
            }
        }
        

        return trees;
    }

    public List<TreeNode> generateTrees(int n) {
        return getTrees(1, n + 1);
    }
}