// https://leetcode.com/problems/accounts-merge

class Solution {
    /* 
    ____SELF DEVELOPED ALGO, that uses Disjoint Sets____
    */
    static void unionBySize(int U, int V) {
        
        int parentU = getParent(U);
        int parentV = getParent(V);

        if(parentU == parentV) {
            return;
        }

        if(size[parentU] >= size[parentV]) {
            parent[parentV] = parentU;
            size[parentU] += size[parentV];
        }
        else {
            parent[parentU] = parentV;
            size[parentV] += size[parentU];
        }
    }

    static int getParent(int node) {
        if(node == parent[node]) {
            return node;
        }

        return parent[node] = getParent(parent[node]);
    }

    static int[] parent;
    static int[] size;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int numOfAcc = accounts.size();

        parent = new int[numOfAcc];
        Arrays.setAll(parent, idx -> idx);

        size = new int[numOfAcc];
        Arrays.setAll(size, idx -> accounts.get(idx).size() - 1);

        /* 
        This map is for keeping a track of which account had a particular email,
        so that we know which account we need to merge with.
        */
        HashMap<String, Integer> seenEmails = new HashMap<>();

        for(int accIdx = 0; accIdx < numOfAcc; accIdx++) {
            
            List<String> currAccDetails = accounts.get(accIdx);
            
            for(int detailIdx = 1; detailIdx < currAccDetails.size(); detailIdx++) {
                
                String currentDetail = currAccDetails.get(detailIdx);
                
                int occurIdx = seenEmails.getOrDefault(currentDetail, -1);

                /* 
                The email has never been seen before, so we add the information
                that the email is present in the current account INTO hashmap.
                */
                if(occurIdx == -1) {
                    seenEmails.put(currentDetail, accIdx);
                }
                /* 
                It has been seen, so we merge the current account with the account
                in which the email was first seen.
                */
                else {
                    unionBySize(occurIdx, accIdx);
                }
            }
        }

        // This is for merging the details, and ensuring they stay sorted.
        HashMap<Integer, TreeSet<String>> mergedAccountsMap = new HashMap<>();
        
        /* 
        We keep the name separately so that we can separately 
        add it to beginning of merged details.
        */
        HashMap<Integer, String> accountName = new HashMap<>();
        
        for(int node = 0; node < numOfAcc; node++) {
            int parOfNode = getParent(node);

            List<String> details = accounts.get(node); 

            // If the map doesn't contain a new merged account with this key, we add it.
            if(!mergedAccountsMap.containsKey(parOfNode)) {
                
                mergedAccountsMap.put(parOfNode, new TreeSet<>());
                
                /* 
                Putting the account name separately, so that we can add it to beginning 
                of the list we make, using the set.

                The list will be made once all insertions into the set have been done.
                */
                accountName.put(parOfNode, details.get(0));
            }

            TreeSet<String> detailSet = mergedAccountsMap.get(parOfNode);

            // Adding details, other than the name, to the detailSet.
            for(int detailIdx = 1; detailIdx < details.size(); detailIdx++) {
                detailSet.add(details.get(detailIdx));
            }
        }

        // We will be converting the sorted sets of merged account details to lists.
        List<List<String>> mergedAccounts = new ArrayList<>(mergedAccountsMap.size());

        for(
            Map.Entry<Integer, TreeSet<String>> mergedAccountEntry: 
            mergedAccountsMap.entrySet()
        ) {
            int parentNode = mergedAccountEntry.getKey();
        
            List<String> mergedAccDetails = new ArrayList<>();
            
            // Adding the name separately, as the first element in the account details.
            mergedAccDetails.add(accountName.get(parentNode));
            
            // Adding the rest of the details, that are in sorted order in TreeSet.
            mergedAccDetails.addAll(mergedAccountEntry.getValue());
            
            mergedAccounts.add(mergedAccDetails);
        }
        
        return mergedAccounts;
    }
}