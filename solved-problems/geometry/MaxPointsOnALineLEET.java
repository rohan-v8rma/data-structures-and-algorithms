// https://leetcode.com/problems/max-points-on-a-line/
// TODO: Make notes

class Solution {
    int gcd(int a, int b) {
        if(a < b) return gcd(b, a);
        if(b == 0) return a;

        return gcd(b, a % b);
    }

    ArrayList<Integer> getKey(int[] pt1, int[] pt2) {
        int ydiff = pt2[1] - pt1[1];
        int xdiff = pt2[0] - pt1[0];

        ArrayList<Integer> key = new ArrayList<>();

        if(ydiff == 0) {
            key.add(0);
            key.add(1);
        }
        else if(xdiff == 0) {
            key.add(1);
            key.add(0);
        }
        else {
            int div = gcd((int)Math.abs(ydiff), (int)Math.abs(xdiff));
    
            ydiff /= div;
            xdiff /= div;

            if(xdiff < 0) {
                key.add(-ydiff);
                key.add(-xdiff);
            }
            else {
                key.add(ydiff);
                key.add(xdiff);
            }
        }

        return key;
    }

    public int maxPoints(int[][] points) {
        int maxOnLine = 1;

        for(int pt = 0; pt < points.length; pt++) {
            HashMap<ArrayList<Integer>, Integer> slopes = new HashMap<>();

            /* 
            We get slopes of a point with all points after it, 
            because if a previous point had been a part of the current point's line group, 
            it would have been accounted for when the previous point's iteration was going on.
            */
            for(int toPt = pt + 1; toPt < points.length; toPt++) {
                ArrayList<Integer> key = getKey(points[toPt], points[pt]);

                int slopeCt = slopes.getOrDefault(key, 0) + 1;

                maxOnLine = Math.max(maxOnLine, 1 + slopeCt);

                slopes.put(key, slopeCt);
            }
        }

        return maxOnLine;
    }
}