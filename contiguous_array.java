/*

// Time Complexity : O(n) as we are iterating on the whole array
// Space Complexity : O(n) ... n number of different sum values are possible which 
//                             needs to be stored in map
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
 *
Leetcode : https://leetcode.com/problems/contiguous-array/

Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
Example 1:
Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.


 *  Code Explanation : 
 * 
 * We use running sum pattern to optimize the code
 * If the counts of sum are equal, the sub array between them will be balanced. To get the max lenght we would have to look 
 * for the farthest distance. 
 * We make use hashmap to store the sum that we have and index of that sum which happened for the first time as the value.
 * 
 * Example 
 * Index   0 1 2 3 4 5 6 7 8 9 10 11  12  13  14  15
 *        [1,0,1,0,1,1,1,0,0,1,1, 0,  1,  0,  1,  1]
 * Sum     1 0 1 0 1 2 3 2 1 2 3  2    3  2   3   4 
 * 
*
    HashMap {Sum : Index}
    {1:0, 
     0:1,
     2:5, 
     3:6,
    }
*
 * Dry Run . I am at index 2 and see the sum as 1, has sum 1 occured before . In hashmap it shows as it has happened at index 0
 * So (index - index): 2-0 = 2 ( max length). Again I am at index 4 and the sume here is 1. Has 1 happened, yes it has which 
 * is at index 0. So I do 4-0 = 4 Sub array is 0,1,0,1.
 * Now I am at index 8 and see again the sum as 1, so 8 - 0 = 8, i update the length as 8
 * At index 9, sum is 2, 2 has happened at index 5 ; so 9 - 5 = 4, but I do not update the lenght as 4 as it is not better thant previous lenght of 8
 * 
 * Edge Case when  array is : [1, 0, 1, 0] , we assume that at -1 index sum is always 0
 * so we create default entry in map that count 0 is happening at index -1
 * 
 * So now above
 * {sum : index}
 * { 0 : -1
 *   1 : 0
 *   
 * }
 * So now at index 1, has 0 occured yes, at index -1 so now we do : 1 - (-1) = 1+1 = 2 : max length.
 * 
 *At index 3, has 0 happened before , yes it has happened at -1 so it happens 3 - (-1) = 4: max length.
 We create a dummy entry of sum 0 at index -1.
 * 
 * 
 * ****************************************
 * /* ( explanation)
'Running Sum method' is used for this problem.
rSum varible is used to store the addition. When element is 1, this sum value is incremented by 1
and for 0, it is decremented by 1.
For each unique sum value map is created to store the first index where this sum value was occured.
Distance between same sum values gives us balalnced substring of 0 and 1.

example :
sum value at -1 location is assumed to be 0.
Index      0  1  2  3  4  5  6  7  8  9  10  11  12
          [1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0,  0,  1]
sum =  -1  0  -1 0  -1 0  1  0  1  2  3  2   1   2
           [-----------------] longest balanced array of size 6
                          [------------------] another longest balanced array of size 6


Also the bruteforce way gave us nested iterations and time complexity came to be n^2. So to optimize we need to reduce 
the nested iteration , we have three technique, hashing : running sum pattern, two pointers and sliding window. 
*/


public class contiguous_array {
    public int  findMaxLength(int [] nums){
        HashMap <Integer, Integer> hMap = new HashMap<>();
        hMap.put(0,-1); // Add a default value at index -1 
        int max =  0;
        int rSum = 0;
        for (int i = 0; i< nums.length; i++){
            if(nums[i] == 0){
                rSum --;
            }
            else{
                rSum ++;
            }
            if(hMap.containsKey(rSum)){
                max = Math.max(max, i - hMap.get(rSum));
            }
            else{
                hMap.put(i,rSum);
            }
        }
        return  max;
    }
}
