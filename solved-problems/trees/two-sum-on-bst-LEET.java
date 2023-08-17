// https://leetcode.com/problems/two-sum-iv-input-is-a-bst

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
	static Set<Integer> seenElements;
	static int target;

	/* 
	BRUTEFORCE: Implement this one since it has lesser number of lines.

	Keep travelling the nodes using DFS, and add the value of the node
	reached into set. 

	Check if (target - current node value) is in set. If yes, return 
	true because target sum is possible.

	Time complexity : O(N)
	Space complexity : O(N) -> At max, all nodes in set.
	*/
	static boolean seeThisNode(TreeNode node) {
		if(node == null) {
			return false;
		}

		if(seenElements.contains(target - node.val)) {
			return true;
		}	

		seenElements.add(node.val);

		/* 
		Executing DFS on the children of the root node.

		The target sum has to be found in only one of these branches
		for true to be returned.
		*/
		return (seeThisNode(node.left) || seeThisNode(node.right));
	}

	public boolean findTarget(TreeNode root, int k) {
		seenElements = new HashSet<>();

		target = k;

		return seeThisNode(root);
	}

	/* 
	OPTIMAL

	We know that BST the property that its in-order traversal is sorted.
	
	So, we can use the methodology of two sum on a sorted array.

	This can be done by running one in-order and one reverse in-order traversal.

	Time complexity is still O(N)
	But, space complexity reduced to O(H), where H is the height of the tree.
	*/
	// public void pushIn(TreeNode root, Stack<TreeNode> inOrder) {
	// 	while(root != null) {
	// 		inOrder.push(root);
	// 		root = root.left;
	// 	}
	// }

	// public void pushInReverse(TreeNode root, Stack<TreeNode> inOrderRev) {
	// 	while(root != null) {
	// 		inOrderRev.push(root);
	// 		root = root.right;
	// 	}
	// }

	// public boolean findTarget(TreeNode root, int k) {
	// 	// The case when only 1 node is present in tree.
	// 	if(root.left == null && root.right == null) {
	// 		return false;
	// 	}

	// 	Stack<TreeNode> inOrder = new Stack<>();
	// 	Stack<TreeNode> inOrderReverse = new Stack<>();

	// 	// Fixing the initial nodes, for the two pointer approach.

	// 	TreeNode leftNode = root;
	// 	// After this leftNode will be pointed to the node with LEAST VALUE
	// 	while(leftNode.left != null) {
	// 		inOrder.push(leftNode);
	// 		leftNode = leftNode.left;
	// 	}
	// 	/* 
	// 	If the node has a right child, we add it to stack, so that it is chosen
	// 	next for traversal.
	// 	*/
	// 	pushIn(leftNode.right, inOrder);
		
	// 	TreeNode rightNode = root;
	// 	// After this rightNode will be pointed to the node with HIGHEST VALUE
	// 	while(rightNode.right != null) {
	// 		inOrderReverse.push(rightNode);
	// 		rightNode = rightNode.right;	
	// 	}
	// 	/*
	// 	If the node has a left child, we add it to stack, so that it is chosen 
	// 	next for traversal.
	// 	*/
	// 	pushInReverse(rightNode.left, inOrderReverse);

	// 	/* 
	// 	AND, Since we eliminated the case where only node is in tree;
	// 	there is no chance of the the two pointers pointing to the same node.
	// 	*/

	// 	/* 
	// 	As long as the 2 pointers don't cross, we still have a chance of finding
	// 	a solution
	// 	*/
	// 	while(leftNode.val < rightNode.val) {
	// 		if(leftNode.val + rightNode.val == k) {
	// 			return true;
	// 		}
	// 		else if(leftNode.val + rightNode.val < k) {
	// 			leftNode = inOrder.pop();
	// 			/* 
	// 			This function pushes leftNode.right, and any left children it has 
	// 			(since this is in-order traversal; where left children are printed first)	
	// 			onto the stack
	// 			*/
	// 			pushIn(leftNode.right, inOrder);
	// 		}
	// 		else {
	// 			rightNode = inOrderReverse.pop();
	// 			/* 
	// 			This function pushes rightNode.left, and any right children it has 
	// 			(since this is reverse in-order traversal; where right children are ]
	// 			printed first) onto the stack
	// 			*/
	// 			pushInReverse(rightNode.left, inOrderReverse);
	// 		}
	// 	}

	// 	return false;

	// }
}