#include <iostream>
#include <set>
using namespace::std;

bool comparePoints(int** vertices, int index1, int index2) {
    if(vertices[index1][0] != vertices[index2][0]) {
        return false;
    }
    if(vertices[index1][1] != vertices[index2][1]) {
        return false;
    }

    return true;
}

int isLineParallelToY(int** vertices, int from, int to) {
    int y2_minus_y1 = vertices[to][1] - vertices[from][1];
    int x2_minus_x1 = vertices[to][0] - vertices[from][0];

    if(x2_minus_x1 == 0) {
        return 1;
    }
    
    return 0;
}

double calculateSlope(int** vertices, int from, int to) {
    int y2_minus_y1 = vertices[to][1] - vertices[from][1];
    int x2_minus_x1 = vertices[to][0] - vertices[from][0];

    double slope;

    if(isLineParallelToY(vertices, from, to)) {
        slope = 0;
    }
    else {
        slope = ((double)(y2_minus_y1) / x2_minus_x1);
    }
    

    return slope;
}

double calculateIntercept(int** vertices, int from, int to) {
    double slope = calculateSlope(vertices, from, to);

    double intercept;


    if(isLineParallelToY(vertices, from, to)) {
        intercept = vertices[from][0];
    }
    else {
        intercept = (vertices[from][1] - (slope * vertices[from][0]));
    }

    return intercept;
}

// This has a time complexity of O(n^3) due to the 3 for-loops
void naiveConvexHull(int** vertices, int numOfVertices) {

   set<int*> convexHullPoints;

    for(int from = 0; from < numOfVertices; from++) {
        for(int to = 0; to < numOfVertices; to++) {
            if(comparePoints(vertices, from, to)) {
                continue;    
            }

            bool valid = true;
            
            bool isFirstPointOnLeft = false;

            int isLinellToY = isLineParallelToY(vertices, from, to);
            double slope = calculateSlope(vertices, from, to);
            double intercept = calculateIntercept(vertices, from, to);

            int determiningPoint = 0;

            for(int index = 0; index < numOfVertices; index++) {
                if(comparePoints(vertices, from, index) || comparePoints(vertices, to, index)) {
                    
                    if(determiningPoint == index) {
                        determiningPoint++;
                    }
                    continue;
                }

                int givenCoord = vertices[index][isLinellToY];

                double extendedCoord = slope * givenCoord + intercept;

                // First point that is being checked
                if(index == determiningPoint) {
                    if(extendedCoord > vertices[index][1 - isLinellToY]) {
                        isFirstPointOnLeft = false;
                    }
                    else if(extendedCoord < vertices[index][1 - isLinellToY]) {
                        isFirstPointOnLeft = true;
                    }
                    else { 
                        // The point being checked lies on the Line so it can't be used to determine whether points lie on the left or right
                        determiningPoint++;
                    }
                }
                else if(
                    (isFirstPointOnLeft && (extendedCoord > vertices[index][1 - isLinellToY]))
                    ||
                    (!isFirstPointOnLeft && (extendedCoord < vertices[index][1 - isLinellToY]))
                ) {
                    printf("determining point: (%d, %d)\n", vertices[determiningPoint][0], vertices[determiningPoint][1]);
                    printf("breaking point: (%d, %d)\n", vertices[index][0], vertices[index][1]);
                    printf("from: (%d, %d); to: (%d, %d)\n\n", vertices[from][0], vertices[from][1], vertices[to][0], vertices[to][1]);
                    valid = false;
                    break;
                }
            }
            
            if(valid) {
                printf("Valid - from: (%d, %d); to: (%d, %d)\n\n", vertices[from][0], vertices[from][1], vertices[to][0], vertices[to][1]);
                convexHullPoints.insert(vertices[from]);
                convexHullPoints.insert(vertices[to]);
            }
        }        
    }    

    for(auto& it: convexHullPoints) {
        printf("%d, %d\n", it[0], it[1]);
    }
}


int main() {
    int** vertices;

    int numOfVertices = 8;

    for(int index = 0; index < numOfVertices; index++) {
        vertices[index] = new int[2];
    }

    // vertices[0][0] = 732;
    // vertices[0][1] = 590;

    // vertices[1][0] = 415;
    // vertices[1][1] = 360;
    
    // vertices[2][0] = 276;
    // vertices[2][1] = 276;

    // vertices[3][0] = 229;
    // vertices[3][1] = 544;

    // vertices[4][0] = 299;
    // vertices[4][1] = 95;
    // Ans is (732, 590) (229, 544) (299, 95)

    vertices[0][0] = 0;
    vertices[0][1] = 3;

    vertices[1][0] = 1;
    vertices[1][1] = 1;
    
    vertices[2][0] = 2;
    vertices[2][1] = 2;

    vertices[3][0] = 4;
    vertices[3][1] = 4;

    vertices[4][0] = 0;
    vertices[4][1] = 0;

    vertices[5][0] = 1;
    vertices[5][1] = 2;

    vertices[6][0] = 3;
    vertices[6][1] = 1;

    vertices[7][0] = 3;
    vertices[7][1] = 3;

    naiveConvexHull(vertices, numOfVertices);
}