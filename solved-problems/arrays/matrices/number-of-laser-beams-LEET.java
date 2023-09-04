// https://leetcode.com/problems/number-of-laser-beams-in-a-bank

class Solution {
    /*
    TC: O(M * N)
    SC: O(1)
    */
    public int numberOfBeams(String[] bank) {
        // Initially no laser beams present.
        int numOfLaserBeams = 0;

        int currentRowDevices = 0;
        int prevRowDevices = 0;

        for(int rowIdx = 0; rowIdx < bank.length; rowIdx++) {
            
            currentRowDevices = 0;
            
            for(int colIdx = 0; colIdx < bank[0].length(); colIdx++) {
                if(bank[rowIdx].charAt(colIdx) == '1') {
                    currentRowDevices++;
                }
            }

            // There are devices in the current row.
            if(currentRowDevices != 0) {
                /* 
                There are laser beams from every device in the 
                previous row to every device in the current row.

                For the first row with devices, there were no
                devices in the previous row due to absence 
                of a previous row, so appropriately 0 laser beams added
                */
                numOfLaserBeams += currentRowDevices * prevRowDevices;
                
                /* 
                Setting count of devices in current row 
                AS 
                count of devices in previous row.
                */
                prevRowDevices = currentRowDevices;
            }
        }

        return numOfLaserBeams;
    }
    
    /*
    TC: O(M * N)

    Slightly improved runtime since we deal with char arrays and not strings.

    But SC is O(M * N), instead of O(1)
    */
    // public int numberOfBeams(String[] bank) {
    //     char[][] cells = new char[bank.length][bank[0].length()];

    //     for(int rowIdx = 0; rowIdx < bank.length; rowIdx++) {
    //         cells[rowIdx] = bank[rowIdx].toCharArray();
    //     }

    //     // Initially no laser beams present.
    //     int numOfLaserBeams = 0;

    //     int currentRowDevices = 0;
    //     int prevRowDevices = 0;

    //     for(int rowIdx = 0; rowIdx < cells.length; rowIdx++) {
            
    //         currentRowDevices = 0;
            
    //         // Counting the number of devices in the current row.
    //         for(int colIdx = 0; colIdx < cells[0].length; colIdx++) {
    //             if(cells[rowIdx][colIdx] == '1') {
    //                 currentRowDevices++;
    //             }
    //         }

    //         // There are devices in the current row.
    //         if(currentRowDevices != 0) {
    //             /* 
    //             There are laser beams from every device in the 
    //             previous row to every device in the current row.

    //             For the first row with devices, there were no
    //             devices in the previous row due to absence 
    //             of a previous row, so appropriately 0 laser beam added
    //             */
    //             numOfLaserBeams += currentRowDevices * prevRowDevices;
                
    //             /* 
    //             Setting count of devices in current row 
    //             AS 
    //             count of devices in previous row.
    //             */
    //             prevRowDevices = currentRowDevices;
    //         }
    //     }

    //     return numOfLaserBeams;
    // }
}