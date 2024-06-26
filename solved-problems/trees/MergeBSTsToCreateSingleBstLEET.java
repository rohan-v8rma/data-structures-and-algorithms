// https://leetcode.com/problems/merge-bsts-to-create-single-bst/

// TODO: See the optimal and compact solution for this

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
    ____SELF DEVELOPED (but messy)____
    */
    static int[] def = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};

    boolean merge(TreeNode upperTree, TreeNode lowerTree) {
        // Checking if the value is the left leaf.
        if(upperTree.left != null && upperTree.left.val == lowerTree.val) {
            /* 
            If the value is in the left leaf of the tree,
            right node, if any of the current tree should be less than
            the root node of upperTree.

            Simply compare max of the current tree.
            */
            if(minMax.get(lowerTree)[1] < upperTree.val) {
                if(limits.getOrDefault(upperTree, def)[0] >= minMax.get(lowerTree)[0]) return false;

                upperTree.left = lowerTree;
                
                roots.remove(lowerTree);
                notRoots.add(lowerTree.val);

                minMax.get(upperTree)[0] = Math.min(
                    minMax.get(upperTree)[0],
                    minMax.get(lowerTree)[0]
                );

                limits.putIfAbsent(lowerTree, new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE});
                limits.get(lowerTree)[1] = Math.min(limits.get(lowerTree)[1], upperTree.val);
            }
            else {
                return false;
            }
        }
        // So the value is definitely in the right leaf.
        else {
            if(minMax.get(lowerTree)[0] > upperTree.val) {
                if(limits.getOrDefault(upperTree, def)[1] <= minMax.get(lowerTree)[1]) return false;

                upperTree.right = lowerTree;
                roots.remove(lowerTree);
                notRoots.add(lowerTree.val);

                minMax.get(upperTree)[1] = Math.min(
                    minMax.get(upperTree)[1],
                    minMax.get(lowerTree)[1]
                );

                limits.putIfAbsent(lowerTree, new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE});
                limits.get(lowerTree)[0] = Math.max(limits.get(lowerTree)[0], upperTree.val);
            }
            else {
                return false;
            }
        }

        return true;
    }

    static HashMap<Integer, TreeNode> treeWhereNumPresent;
    static HashMap<TreeNode, int[]> minMax;
    static HashMap<TreeNode, int[]> limits;
    static HashSet<TreeNode> roots;
    static HashSet<Integer> notRoots;

    public TreeNode canMerge(List<TreeNode> trees) {
        treeWhereNumPresent = new HashMap<>();
        minMax = new HashMap<>();
        limits = new HashMap<>();
        roots = new HashSet<>();
        notRoots = new HashSet<>();
        
        int op = 0;

        for(int i = 0; i < trees.size(); i++) {
            TreeNode tree = trees.get(i);

            minMax.put(
                tree, 
                new int[]{
                    tree.left != null ? tree.left.val : tree.val,
                    tree.right != null ? tree.right.val : tree.val
                }
            );

            /* 
            This means, the root of this tree is present in the leaf of 
            some other tree since no 2 trees have same root.
            */
            if(treeWhereNumPresent.containsKey(tree.val)) {
                TreeNode upperTree = treeWhereNumPresent.get(tree.val);

                if(!merge(upperTree, tree)) {
                    return null;
                }
                else {
                    op++;
                }
            }
            else {
                treeWhereNumPresent.put(tree.val, tree);
                roots.add(tree);
            }

            if(tree.left != null) {
                // So in this case, this tree is the upper tree.
                if(treeWhereNumPresent.containsKey(tree.left.val)) {
                    TreeNode lowerTree = treeWhereNumPresent.get(tree.left.val);

                    if(notRoots.contains(tree.left.val)) return null;

                    if(!merge(tree, lowerTree)) {
                        return null;
                    }
                    else {
                        op++;
                    }
                }
                else {
                    treeWhereNumPresent.put(tree.left.val, tree);
                }

                notRoots.add(tree.left.val);
            }

            if(tree.right != null) {
                // So in this case, this tree is the upper tree.
                if(treeWhereNumPresent.containsKey(tree.right.val)) {
                    TreeNode lowerTree = treeWhereNumPresent.get(tree.right.val);

                    if(notRoots.contains(tree.right.val)) return null;

                    if(!merge(tree, lowerTree)) {
                        return null;
                    }
                    else {
                        op++;
                    }
                }
                else {
                    treeWhereNumPresent.put(tree.right.val, tree);
                }

                notRoots.add(tree.right.val);
            }            
        }

        if(op < trees.size() - 1) {
            return null;
        }

        return roots.iterator().next();
    }
}