#include <iostream>

// Each node contains a value and an address pointing to the next node
class term {
    friend std::ostream& operator << (std::ostream& out, term* polynomial);
    friend term* addPolynomial(term* poly1, term* poly2);    
private:
    int coeff;
    int pow;
    term* nextTerm = NULL; // This default value doesn't affect the work/non-working code in any way
public:
    // Default Constructor
    term() {
        coeff = 0;
        pow = 0;
        nextTerm = NULL;
    };

    // Parameterized Constructor
    term(int elementCt) {
        printf("Please enter the terms with descending powers of 'x'. Failing to do this will trigger a prompt to enter the power again.\n");

        term* head = this; 
        term* tempPtr;
        tempPtr = head;
        int max = -10000; 
        for(int index = 0; index < elementCt; index++) {
            printf("Enter coefficient of term %d : ", index + 1);
            scanf("%d", &(tempPtr -> coeff));
            
            printf("Enter power of term %d : ", index + 1);
            scanf("%d", &(tempPtr -> pow));
            
            if (max == -10000) {
                max = (tempPtr -> pow);
            }
            else {
                while ( max <= (tempPtr -> pow) ) {
                    printf("The power of this term isn't less than the last one.\nKindly enter the power again for term %d : ", index + 1);
                    scanf("%d", &(tempPtr -> pow));
                };
            };

            if (index == (elementCt - 1)) { // Since index starts from 0 and elements start from 1, this is the last element of the linked list
                tempPtr -> nextTerm = NULL; //the next pointer of the last element will point to NULL
            }
            else {
                //* Working code, where we first allocate memory in next, then change tempPtr
                // This is because we need to allocate space fo
                
                (tempPtr -> nextTerm) = (term*)(malloc(sizeof(term))); 
                tempPtr = tempPtr -> nextTerm;          
            };

        };

        printf("\n");
    };
};

std::ostream& operator << (std::ostream& out, term* polynomial) { 
    term* tempPtr = polynomial;

    while (tempPtr != NULL) { // this condition helps us stop printing when the tempPtr points to the NULL terminator of the linked list.
        if ( (tempPtr != polynomial) && ( (tempPtr -> coeff) != 0 ) ) { // For the first term, we need not a + binary operator sign.
            out << (" + ");
        };
        
        if ( (tempPtr -> coeff) != 0 ) {
            out << (tempPtr -> coeff) <<  " * (x^" << (tempPtr -> pow) << ")";
        };
                     
        tempPtr = tempPtr -> nextTerm; // this moves the tempPtr to the next term.
    };

    out << "\n";

    return out;
}

term* addPolynomial(term* poly1, term* poly2) {
    term* result = (term*)(malloc(sizeof(term)));
    term* tempPtr = result;

    while( (poly1 != NULL) && (poly2 != NULL) ) {

        if( (poly1 -> pow) > (poly2 -> pow) ) {
            tempPtr -> coeff = poly1 -> coeff;
            tempPtr -> pow = poly1 -> pow;
            poly1 = poly1 -> nextTerm;
        }
        else if( (poly1 -> pow) < (poly2 -> pow) ) {
            tempPtr -> coeff = poly2 -> coeff;
            tempPtr -> pow = poly2 -> pow;
            poly2 = poly2 -> nextTerm;
        }
        else {
            tempPtr -> coeff = (poly1 -> coeff) + (poly2 -> coeff);
            tempPtr -> pow = (poly1 -> pow);
            poly1 = poly1 -> nextTerm;
            poly2 = poly2 -> nextTerm;
        };
        
        if( (poly1 == NULL) && (poly2 == NULL) ) {
            tempPtr -> nextTerm = NULL;
        }
        else {
            tempPtr -> nextTerm = (term*)(malloc(sizeof(term)));
            tempPtr = tempPtr -> nextTerm;
        };
    }

    while(poly1 != NULL) {
        tempPtr -> coeff = poly1 -> coeff;
        tempPtr -> pow = poly1 -> pow;
        poly1 = poly1 -> nextTerm;

        if(poly1 == NULL) {
            tempPtr -> nextTerm = NULL;
        }
        else {
            tempPtr -> nextTerm = (term*)(malloc(sizeof(term)));
            tempPtr = tempPtr -> nextTerm;
        };
    };

    while(poly2 != NULL) {
        tempPtr -> coeff = poly2 -> coeff;
        tempPtr -> pow = poly2 -> pow;
        poly2 = poly2 -> nextTerm;

        if(poly2 == NULL) {
            tempPtr -> nextTerm = NULL;
        }
        else {
            tempPtr -> nextTerm = (term*)(malloc(sizeof(term)));
            tempPtr = tempPtr -> nextTerm;
        };
    };

    return result;
}



int main() {
    term* poly1 = new term(3);
    term* poly2 = new term(3);

    std::cout << "poly1  : " << poly1;
    std::cout << "poly2  : " << poly2;

    term* result = addPolynomial(poly1, poly2);

    std::cout << "Sum is : " << result << "\n";

    // term* poly2 = createPolynomial(3);
}

