// https://leetcode.com/problems/online-stock-span/

/*____BRUTEFORCE solution (N^2)____*/
// class StockSpanner {

//     ArrayList<Integer> prevPrices;

//     public StockSpanner() {
//         prevPrices = new ArrayList<>();
//     }
    
//     public int next(int price) {
//         int span = 1;
//         for(int idx = prevPrices.size() - 1; idx >= 0; idx--) {
//             if(prevPrices.get(idx) <= price) {
//                 span++;
//             }
//             else {
//                 break;
//             }
//         }

//         prevPrices.add(price);

//         return span;
//     }
// }

/*___OPTIMAL : O(N) in worst case___*/

class StockSpanner {

    ArrayDeque<Integer> prevPrices;
    ArrayDeque<Integer> prevSpans;

    public StockSpanner() {
        prevPrices = new ArrayDeque<>();
        prevSpans = new ArrayDeque<>();
    }
    
    public int next(int price) {
        int span = 1;
        
        while(!prevPrices.isEmpty()) {
            if(prevPrices.peek() > price) {
                break;
            }

            prevPrices.pop();
            span += prevSpans.pop();
        }

        prevPrices.push(price);
        prevSpans.push(span);

        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */