// https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period

class Solution {
    static int getTime(String time) {
        return 
        Integer.parseInt(time.substring(0, 2)) * 60 
        + 
        Integer.parseInt(time.substring(3, 5));
    }

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        List<String> empToAlert = new ArrayList<>();

        /* 
        A map needs to be used since it is not necessary all accesses of a person
        are given together.
        */
        Map<String, List<Integer>> allEmpTimes = new HashMap<>();

        for(int i = 0; i < keyTime.length; i++) {
            allEmpTimes.putIfAbsent(keyName[i], new ArrayList<>());
            allEmpTimes.get(keyName[i]).add(getTime(keyTime[i]));
        }

        for(Map.Entry<String, List<Integer>> entry: allEmpTimes.entrySet()) {
            List<Integer> times = entry.getValue();
            
            /* 
            If perfectly uniform distribution of accesses is assumed
            1 access is there every 20 minutes, so definitely 3 or more accesses in 1 hour.

            If non-uniform distribution, it goes without saying; 3 or more accesses.
            */
            if(times.size() >= 72) {
                empToAlert.add(entry.getKey());
                continue;
            }
            
            // Sorting the access times.
            Collections.sort(times);
            
            for(int idx = 2; idx < times.size(); idx++) {
                /* 
                Checking if the first and last time in the sorted triplet lie 
                within span of 1 hour.
                */
                if(times.get(idx) <= 60 + times.get(idx - 2)) {
                    empToAlert.add(entry.getKey());
                    break;
                }
            }   
        }

        Collections.sort(empToAlert);
        return empToAlert;
    }
}