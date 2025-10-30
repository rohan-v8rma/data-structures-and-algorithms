// https://leetcode.com/problems/clone-graph

/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> neighbors;
    Node() {
        val = 0;
        neighbors = vector<Node*>();
    }
    Node(int _val) {
        val = _val;
        neighbors = vector<Node*>();
    }
    Node(int _val, vector<Node*> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/

class Solution {
public:

    Node* recursiveClone(Node* node, map<int, Node*>& valToNode) {
        if(valToNode.find(node -> val) != valToNode.end()) {
            return valToNode[node -> val];
        }

        valToNode[node -> val] = new Node(node -> val, node -> neighbors);

        Node* returnNode = valToNode[node -> val];
        

        int n = node -> neighbors.size();

        for(int i = 0; i < n; i++) {
            returnNode -> neighbors[i] = recursiveClone(returnNode -> neighbors[i], valToNode);
        }

        return returnNode;
    }

    Node* cloneGraph(Node* node) {
        if(node == NULL) return NULL;

        map<int, Node*> valToNode;

        return recursiveClone(node, valToNode);
    }
};
