// https://leetcode.com/problems/find-the-minimum-amount-of-time-to-brew-potions

typedef long long l;

class Solution {
public:
    l setEndTimes(vector<int>& skill, vector<int>& mana, int wiz, int potion, vector<vector<l>>& dp) {
        if(wiz == skill.size()) return dp[wiz - 1][potion];

        // Finish time of wiz for last potion, meaning when he can start next potion
        l minStartTime = 0;

        if(potion != 0) {
            minStartTime = max(minStartTime, dp[wiz][potion - 1]); 
            // cout << "prev potion, same wizard, minStartTime[" << wiz << "]" << "[" << potion << "] = " << dp[wiz][potion - 1] << endl;
        }
        
        if(wiz != 0) {
            // Finish time of last wiz for current potion meaning after what time the potion is available to brew for current wizard
            minStartTime = max(minStartTime, dp[wiz - 1][potion]);
            // cout << "same potion, prev wizard, minStartTime[" << wiz << "]" << "[" << potion << "] = " << dp[wiz - 1][potion] << endl;
        }

        // cout << "minStartTime[" << wiz << "]" << "[" << potion << "] = " << minStartTime << endl;

        l timeTakenByWiz = 1ll * skill[wiz] * mana[potion];

        // cout << "timeTakenByWiz[" << wiz << "]" << "[" << potion << "] = " << timeTakenByWiz << endl;

        dp[wiz][potion] = minStartTime + timeTakenByWiz;

        // cout << "dp[" << wiz << "]" << "[" << potion << "] = " << dp[wiz][potion] << endl;

        l nextWizStartTime = setEndTimes(skill, mana, wiz + 1, potion, dp);

        // cout << "nextWizStartTime[" << wiz << "]" << "[" << potion << "] = " << nextWizStartTime << endl;

        // Current wiz brewing will have to end when next wiz starts.
        dp[wiz][potion] = nextWizStartTime;

        // Returning start time of current wiz.
        return dp[wiz][potion] - timeTakenByWiz;
    }

    long long minTime(vector<int>& skill, vector<int>& mana) {
        int wizCt = skill.size();
        int potionCt = mana.size();

        vector<vector<l>> dp = vector(wizCt, vector(potionCt, 0ll));

        for(int potion = 0; potion < potionCt; potion++) {
            setEndTimes(skill, mana, 0, potion, dp);

            // for(int wiz = 0; wiz < wizCt; wiz++) {
            //     cout << dp[wiz][potion] << ", ";
            // }

            // cout << endl;
        }

        // for(int i = 0; i < wizCt; i++) {
        //     for(int j = 0; j < potionCt; j++) {
        //         cout << dp[i][j] << ", ";
        //     }
        //     cout << endl;
        // }

        return dp[wizCt - 1][potionCt - 1];
    }
};
