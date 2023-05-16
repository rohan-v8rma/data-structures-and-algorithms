#include <iostream>
#include <queue>
#include <set>
using namespace std;


class Node {

public:
    int count;
    string leafCharacter;
    Node* leftChildPointer;
    Node* rightChildPointer;

    Node(char c, int count) {
        this->leafCharacter = c;
        this->count = count;
        this->leftChildPointer = NULL;
        this->rightChildPointer = NULL;
    }   

    Node(Node* left, Node* right) {
        this->leafCharacter = "";
        this->leftChildPointer = left;
        this->rightChildPointer = right;
        this->count = leftChildPointer->count + rightChildPointer->count;
    }   
};


struct NodeComparator {
    bool operator()(const Node* lhs, const Node* rhs) const {
        return lhs->count > rhs->count;
    }
};

void doEncoding(Node* parent, string currentEncoding) {

    // cout << parent->leafCharacter << "lol" << endl;
    if(parent->leftChildPointer != NULL) {
        doEncoding(parent->leftChildPointer, currentEncoding + "0");
    }
    
    if(parent->rightChildPointer != NULL) {
        doEncoding(parent->rightChildPointer, currentEncoding + "1");
    }

    if(parent->leafCharacter != "") {
        cout << "Character `" << parent->leafCharacter << "`: " << currentEncoding << endl;
    }
}

void huffmanCoding(string plainString) {
    priority_queue<Node*, vector<Node*>, NodeComparator> pq;

    set<char> parsedCharacters;
    
    for(int index = 0; index < plainString.length(); index++) {
        // cout << "hello";
        char leafCharacter = plainString[index];
        if(parsedCharacters.find(leafCharacter) != parsedCharacters.end()) {
            continue;
        }

        int ct = 0;
        parsedCharacters.insert(leafCharacter);
        for(int comparisonIndex = 0; comparisonIndex < plainString.length(); comparisonIndex++) {
            if(leafCharacter == plainString[comparisonIndex]) {
                ct++;
            }
        }   
        
        pq.push(new Node(leafCharacter, ct));
    }


    //* Testing the comparator    
    // while(!pq.empty()) {
    //     // cout << "hello";
    //     Node* ptr = pq.top();
    //     cout << (ptr -> leafCharacter) << endl;
    //     pq.pop();
    // }

    while(pq.size() >= 2) {
        Node* firstNode = pq.top();
        pq.pop();
        Node* secondNode = pq.top();
        pq.pop();

        Node* parentNode = new Node(firstNode, secondNode);
        pq.push(parentNode);
    }

    Node* rootNode = pq.top();
    
    doEncoding(rootNode, "");
}

int main() {
    huffmanCoding("afffeedddd");
}