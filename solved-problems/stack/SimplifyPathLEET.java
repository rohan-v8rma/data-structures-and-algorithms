// https://leetcode.com/problems/simplify-path/
// TODO: Make notes

class Solution {
    public String simplifyPath(String path) {
        ArrayDeque<String> st = new ArrayDeque<>();

        String[] dirs = path.split("/");

        for(String dir: dirs) {
            if(!dir.equals("") && !dir.equals(".")) {
                if(dir.equals("..")) {
                    if(!st.isEmpty()) {
                        st.pop();
                    }
                }
                else {
                    st.push(dir);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append("/");
            sb.append(st.pollLast());
        }
        
        String result = sb.toString();

        return result.length() == 0 ? "/" : result;
    }
}