/* 
Algo for binary search

fn binsearch(arr details, start, end, target) 
    middle = (start + end) /2
    
    if( details[middle] < target ) 
        binsearch(details, middle + 1, end, target)
    else if( details[middle] > target )
        binsearch(details, start, middle - 1, target)
    else 
        return middle
    
    return -1

*/


/*
ALgo for quick sort

fn quicksort(arr details, start, end)
    pivot = (start + end) / 2
    pivotnum = details[pivot]
    
    num left = start
    num right = end
    
    while(left <= right)
        while(details[left] < pivot)
            left++
        while(details[right > pivot])
            right--
        
        if(left < right) 
            temp = details[left]
            details[left] = details[right]
            details[right] = temp
    
    quicksort(details, start, right)
    quicksort(details, left+1, end)

*/


#include <iostream>
using namespace::std;

struct studrec {	 	  	 	 		     	      	      	  	  	 	
    string name;
    int regno;
    float cgpa;
};

// if 0 is returned, strings are equal
// if 1 is returned, 1st string is lesser
// if -1 is returned, 2nd string is lesser
int strCompare(string str1, string str2) { 
    int len1 = str1.length();
    int len2 = str2.length();
    
    int comparLen = (len1 < len2)? len1 : len2;
    
    for(int index = 0; index < comparLen; index++) {
        if(str1[index] < str2[index]) {
            return 1;
        }
        else if(str2[index] < str1[index]) {
            return -1;
        }
    }
    
    if(len1 == len2) {
        return 0;
    }
    else if(len1 < len2) {
        return 1;
    }
    else if(len2 < len1) {
        return -1;
    }
    
    return 0;
}

int binSearch(studrec* list, int start, int end, string target) {	 	  	 	 		     	      	      	  	  	 	
    if(start > end) { // Element was not found
        return -1;
    }
    
    int middle = (start + end) / 2;
    
    if( strCompare(list[middle].name, target) == 1 ) {
        start = middle + 1;
    }
    else if( strCompare(list[middle].name, target) == -1 ) {
        end = middle - 1; 
    }
    else if( strCompare(list[middle].name, target) == 0) {
        return  middle;
    }
    
    return binSearch(list, start, end, target);
}

// end is inclusive
void quickSort(studrec* list, int start, int end) { 
    int pivot = (start + end) / 2;
    
    string pivotString = (list[pivot]).name;
    
    studrec temp;
    
    if(end - start <= 0) {
        return;
    }
    
    int left = start;
    int right = end;

    // Swapping pivot to end
    temp = list[pivot];
    list[pivot] = list[right];
    list[right] = temp;
    right--;

    while(left <= right) {	 	  	 	 		     	      	      	  	  	 	
        while( (strCompare( (list[left]).name, pivotString ) == 1) && (left <= end)) {
            // left is less than pivot, then this executes
            left++;    
        };
        while( (strCompare(pivotString, (list[right]).name) == 1) && (right >= start) && (left <= right)) {
            // right is more than pivot, then this executes
            right--;    
        };
        
        if(left < right) {
            temp = list[left];
            list[left] = list[right];
            list[right] = temp;
        };
    }
    
    temp = list[end];
    list[end] = list[left];
    list[left] = temp;

    
    quickSort(list, start, right);
    quickSort(list, ++left, end);
}

int main() {
    studrec listsorted[5] = {
        {"a", 4, 6.9},
        {"b", 2, 8.9},
        {"c", 1, 9.9},
        {"d", 3, 7.9},
        {"e", 5, 5.9}
    };
    
    // calling binary search
    cout << "b present at index : " << binSearch(listsorted, 0, 4, "b") << endl;

    studrec list[5] = {
        {"d", 4, 6.9},
        {"b", 2, 8.9},
        {"a", 1, 9.9},
        {"c", 3, 7.9},
        {"e", 5, 5.9}
    };
    
    // Calling quick sort
    quickSort(list, 0, 4);
    
    // for(int index = 0; index < 5; index++) {
    //     cout << list[index].name << endl;
    // } 
    return 0;
}