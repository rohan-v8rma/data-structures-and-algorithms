// https://practice.geeksforgeeks.org/contest/gfg-weekly-coding-contest-150/problems
// TODO: Make notes (In move based problems section)


class Solution {
    public static boolean AreEqual(String a, String b) {
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        int n = aArr.length;

        // Finding a 1 from the beginning.
        boolean[] extra1 = new boolean[2];

        for (int i = 0; i < n; i++) {
            if (aArr[i] != bArr[i]) {
                if (aArr[i] == '1')
                    extra1[0] = true;
                else
                    extra1[1] = true;
            }

            // If we find a 0, we use the starting 0 and this 0, to convert all extra 1s to
            // 0s.
            if (aArr[i] == '0')
                extra1[0] = false;
            if (bArr[i] == '0')
                extra1[1] = false;

            /*
             * We have an opportunity to convert all digits till end to 1s.
             */
            if (aArr[i] == bArr[i] && aArr[i] == '1') {
                /*
                 * We just have to validate that there is NO extra 1 in either of the string,
                 * which would mean all characters before the current index were pre-existing
                 * or converted 0s.
                 * 
                 * This would also mean the strings are the same before the current index,
                 * and can be made same for the indices from the current to the end
                 * (since we found 1s at the same index.)
                 */
                if (!extra1[0] && !extra1[1]) {
                    return true;
                }

                /*
                 * If there previously have been extra 1s in either of them,
                 * we know that the strings are not the same yet,
                 * in the portion before the current index
                 * 
                 * Example:
                 * 0101001
                 * 0011001
                 * ^
                 * |
                 * |
                 * This is the current index. So, there are extra1s in both strings.
                 * Even if we make last 4 places into 1s, we don't have any way of making
                 * the initial portions of the string equal.
                 */
                extra1[0] = true;
                extra1[1] = true;
            }
        }

        return !extra1[0] && !extra1[1];
    }
}
