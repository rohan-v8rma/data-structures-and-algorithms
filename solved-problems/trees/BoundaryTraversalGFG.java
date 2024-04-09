// https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1

// class Node  
// { 
//     int data; 
//     Node left, right; 
   
//     public Node(int d)  
//     { 
//         data = d; 
//         left = right = null; 
//     } 
// }

class Solution
{
    /*____Boundary Traversal using 3 recursive functions, and a SET to keep track of visited____*/
    
//     static void getLeftBoundary(Node node) {
//         if(node == null) return;
//         if(node.left == null && node.right == null) return;
        
//         traversed.add(node);
//         boundaryTrav.add(node.data);
        
//         getLeftBoundary(node.left != null ? node.left : node.right);
//     }
    
//     static void getLeaves(Node node) {
//         if(node == null) return;
//         if(node.left == null && node.right == null) {
//             traversed.add(node);
//             boundaryTrav.add(node.data);
//             return;
//         }
        
//         getLeaves(node.left);
//         getLeaves(node.right);
//     }
    
//     static void getRightBoundary(Node node) {
//         if(node == null) return;
//         if(node.left == null && node.right == null) return;
        
//         getRightBoundary(node.right != null ? node.right : node.left);
        
//         if(!traversed.contains(node)) {
//             boundaryTrav.add(node.data);
//         }
//     }
    
//     static HashSet<Node> traversed;
//     static ArrayList<Integer> boundaryTrav;
    
// 	ArrayList <Integer> boundary(Node node)
// 	{
// 	    traversed = new HashSet<>();
// 	    boundaryTrav = new ArrayList<>();
	    
// 	    traversed.add(node);
// 	    boundaryTrav.add(node.data);
	    
// 	    // Left boundary starts from left of root node, else there is no left boundary.
// 	    getLeftBoundary(node.left);
	    
// 	    if(node.left != null || node.right != null) {
// 	        getLeaves(node);
// 	    }
	    
// 	    // Right boundary starts from right of root node, else there is no right boundary.
//         getRightBoundary(node.right);
	    
// 	    return boundaryTrav;
// 	}

    /*____Boundary Traversal using 3 recursive functions____*/

//     static void getLeftBoundary(Node node) {
//         if(node == null) return;
//         if(node.left == null && node.right == null) return;
        
//         boundaryTrav.add(node.data);
        
//         getLeftBoundary(node.left != null ? node.left : node.right);
//     }
    
//     static void getLeaves(Node node) {
//         if(node == null) return;
//         if(node.left == null && node.right == null) {
//             boundaryTrav.add(node.data);
//             return;
//         }
        
//         getLeaves(node.left);
//         getLeaves(node.right);
//     }
    
//     static void getRightBoundary(Node node) {
//         if(node == null) return;
//         if(node.left == null && node.right == null) return;
        
//         getRightBoundary(node.right != null ? node.right : node.left);
        
//         boundaryTrav.add(node.data);
//     }
    
//     static ArrayList<Integer> boundaryTrav;
    
// 	ArrayList<Integer> boundary(Node node)
// 	{
// 	    boundaryTrav = new ArrayList<>();
	    
// 	    /*
// 	    We don't need a set to keep track of visited nodes, 
// 	    since nodes in left and right boundary cannot be same 
// 	    since they start from two different children of root
// 	    */
	    
// 	    boundaryTrav.add(node.data);
	    
// 	    // Left boundary starts from left of root node, else there is no left boundary.
// 	    getLeftBoundary(node.left);
	    
// 	    if(node.left != null || node.right != null) {
// 	        getLeaves(node);
// 	    }
	    
// 	    // Right boundary starts from right of root node, else there is no right boundary.
//         getRightBoundary(node.right);
	    
// 	    return boundaryTrav;
// 	}

    /*____Single recursive function for getting boundary traversal_____*/

    void traverse(Node node, boolean isLeft, boolean isRight) {
        if(node == null) return;
        
        if(node.left == null && node.right == null) {
            boundaryTrav.add(node.data);
            return;
        }
        
        if(isLeft && isRight) {
            /*
            The node is neither on right boundary, nor on left boundary,
            but we still traverse its children, to capture leaves.
            
            First left, then right to get leaves from left to right.
            */
            traverse(node.left, true, true);
            traverse(node.right, true, true);
        }
        else if(isLeft) {
            boundaryTrav.add(node.data);
            
            // Call for getting left boundary
            traverse(node.left, true, false);
                
            // Call for getting leaves / left boundary depending on whether node.left is null
            traverse(node.right, true, node.left != null);
        }
        else {
            // Call for getting leaves / right boundary depending on whether node.right is null
            traverse(node.left, node.right != null, true);
            
            // Call for getting right boundary
            traverse(node.right, false, true);
            
            boundaryTrav.add(node.data);
        }
    }

    ArrayList<Integer> boundaryTrav;
	
	ArrayList<Integer> boundary(Node node)
	{
	    boundaryTrav = new ArrayList<>();
	    
	    boundaryTrav.add(node.data);
	    
        traverse(node.left, true, false);
        traverse(node.right, false, true);
	    
	    
	    return boundaryTrav;
	}
}
