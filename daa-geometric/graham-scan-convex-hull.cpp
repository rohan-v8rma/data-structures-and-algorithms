#include <iostream>
#include <stack>
#include <vector>
#include <cmath>
// #include <bits/stdc++.h>
using namespace::std;

// In this algorithm, we have not handled for duplicate points.

struct Point {
    int x, y;
};


void bringLowestPointToStart(Point* vertices, int numOfVertices) {
    int lowestPointIndex = 0;

    for(int index = 0; index < numOfVertices; index++) {
        if (
            (// Lower point found
                vertices[lowestPointIndex].y > vertices[index].y
            ) 
        ||
            (// Y coordinate same but x co-ordinate is lesser
                (vertices[lowestPointIndex].y == vertices[index].y) 
                &&
                (vertices[lowestPointIndex].x > vertices[index].x)
            )
        ) 
        {
            lowestPointIndex = index;
        }
    }

    // Bringing the lowest vertex to the start of the array.
    swap(vertices[0], vertices[lowestPointIndex]);
}

double turnAngle(Point* vertices, int from, int to) {
    double angleValInRadian = atan( (double)(vertices[to].y - vertices[from].y) / (vertices[to].x - vertices[from].x) );
    double angleValInDegrees = (180 / M_PI) * angleValInRadian;
    return angleValInDegrees;
}


int orientation(Point origin, Point dest1, Point dest2) {

    int areaVector = ((dest2.y - origin.y) * (dest1.x - origin.x)) - ((dest1.y - origin.y) * (dest2.x - origin.x));
    
    // Turning direction is inwards (towards left)
    if(areaVector > 0) { 
        return 2;
    }
    // The points are collinear
    else if(areaVector == 0) {
        return 1;
    }
    // Turning direction is outwards (towards right)
    else {
        return 0;
    }
    
}

int getDistance(Point* vertices, int from, int to) {
    int x2 = (vertices[from].x - vertices[to].x) ^ 2;
    int y2 = (vertices[from].y - vertices[to].y) ^ 2;
    return sqrt(x2 + y2);
}

int getCloserPoint(Point* vertices, int index1, int index2) {
    int distOfPoint1 = getDistance(vertices, 0, index1);
    int distOfPoint2 = getDistance(vertices, 0, index2);

    return ( distOfPoint1 > distOfPoint2 ? index1 : index2);
}

// Less indices have less anti-clockwise angle and vice versa
int sortByAntiClockwiseAngle(Point* vertices, int numOfVertices) {
    // We do -2 because the we want to keep the first element in place
    int lengthOfModifiedArray = numOfVertices;

    int* markedForRemoval = new int[numOfVertices];

    for(int index = 0; index < numOfVertices; index++) {
        markedForRemoval[index] = 0;    
    }

    int modifiedArrayLength = numOfVertices;

    for(int pass = 1; pass <= (numOfVertices - 2); pass++) {
        // printf("Pass %d:\n", pass);
        for(int index = 1; index < (numOfVertices - pass); index++) {
            printf("First index: %d, Second index: %d;\n", index, index+1);

            if( turnAngle(vertices, 0, index) > turnAngle(vertices, 0, index + 1) ) {
                swap(vertices[index], vertices[index + 1]);
                swap(markedForRemoval[index], markedForRemoval[index + 1]);
            }
            else if( turnAngle(vertices, 0, index) == turnAngle(vertices, 0, index + 1) ) {
                markedForRemoval[getCloserPoint(vertices, index, index + 1)] = -1;
                modifiedArrayLength--;
            }
        }
    }

    cout << "hello";
    // *numOfVertices = modifiedArrayLength;

    Point* newVertices = new Point[modifiedArrayLength];
    int newIndex = 0;

    for(int index = 0; index < numOfVertices; index++) {
        if(markedForRemoval[index] != -1) {
            newVertices[newIndex++] = vertices[index];
        }
    }
    

    return modifiedArrayLength;
}

// This has a time complexity of O(n^3) due to the 3 for-loops
void grahamScanConvexHull(Point* vertices, int numOfVertices) {
    cout << "hello" << endl;
    bringLowestPointToStart(vertices, numOfVertices);

    int modifiedLength = sortByAntiClockwiseAngle(vertices, numOfVertices);
    
    


    for(int index = 1; index < numOfVertices; index++) {
        printf("%d, %d\n", vertices[index].x, vertices[index].y);
        cout << turnAngle(vertices, 0, index) << endl;
    }

    stack<Point> hullVertices;

    hullVertices.push(vertices[0]);
    hullVertices.push(vertices[1]);
    hullVertices.push(vertices[2]);

    // for(int index = 3; index < numOfVertices; index++) {
    //     while((turnDirection()) && hullVertices.size() >= 3) {

    //         hullVertices.pop();
    //     }

    //     hullVertices.push(vertices[index]);
    // }
    
    vector<Point> antiClockWiseConvexHull;

    while(!hullVertices.empty()) {
        antiClockWiseConvexHull.push_back(hullVertices.top());
        hullVertices.pop();
    }

    cout << "Vertices on the Convex Hull in Anti-clockwise direction.\n";

    // rbegin gives us the reverse iterator
    for(auto it = antiClockWiseConvexHull.rbegin(); it != antiClockWiseConvexHull.rend(); it++) {
        printf("(%d, %d)\n", it->x, it->y);
    }
  
}


int main() {
    Point* vertices;

    int numOfVertices = 8;
    cout << "hello" << endl;

    // vertices[0].x = 732;
    // vertices[0].y = 590;

    // vertices[1].x = 415;
    // vertices[1].y = 360;
    
    // vertices[2].x = 276;
    // vertices[2].y = 276;

    // vertices[3].x = 229;
    // vertices[3].y = 544;

    // vertices[4].x = 299;
    // vertices[4].y = 95;
    // Ans is (732, 590) (229, 544) (299, 95)

    vertices[0].x = 0;
    vertices[0].y = 3;

    vertices[1].x = 1;
    vertices[1].y = 1;
    
    vertices[2].x = 2;
    vertices[2].y = 2;

    vertices[3].x = 4;
    vertices[3].y = 4;

    vertices[4].x = 0;
    vertices[4].y = 0;

    vertices[5].x = 1;
    vertices[5].y = 2;

    vertices[6].x = 3;
    vertices[6].y = 1;

    vertices[7].x = 3;
    vertices[7].y = 3;


    grahamScanConvexHull(vertices, numOfVertices);
}