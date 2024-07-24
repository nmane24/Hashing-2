
// Time Complexity : O(N)
// Space Complexity : O(N), n is the lenght of the array and map can store those n entires
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

/*
Leetcode : https://leetcode.com/problems/subarray-sum-equals-k/

Given an array of integers nums and an integer k, 
return the total number of subarrays whose sum equals to k.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2


Brute Force  - n^3 : get all the sub arrays and compare their sum with k 
n^2 for calculating all the sub arrays. For every subarray we would have to calculate the sum which would be n times
So it becomes n^2 * n = n^3.
*/

/*
Refer class video explaination.
We need to reduce nested irteration and hence use the 'Running Sum method'
'Running Sum method' is used for this problem.
In map we store the runningSum and frequency the rsum occurs. 
We would be actually checking if x - z = y which is runningSum - k = y . We check if y exists in map, that is runningSum.

The counter/frequency which we increment in hashmap is actually we check if the number of subarrays whose sum will be equal to k
We keep on adding the runningSum values in hashmap as well

Example : 
             [3,  4,  7,  2,  -3, 1,  4,  2,  0,  1]
Running Sum   3   7  14   16  13  14  18  20  20   21    

We do  x = y + z, we are given k i.e we are given the value of z. So lets say x = 21, so that means y = z - x = 21 - 7 = 14
We have to look do we have this sum 14,  if we have that means sub array z is equal to 7. So here whatever running sum we are getting
has happened or not, if it has happened, then how many times that running Sum  has happened.

In hashmap we store runningSum and Frequency
HashMap : {rSum , Frequency
3 : 1
7: 1
14: 2
16: 1
13: 2
18: 1
20: 2
}


I am at index 1, the running Sum is 7, I do x - z which is runningSum - k : 7 - 7 = 0, Is 0 ( y) happened  in hashMap . Also add the 
current runnning Sum in the map which is 7 : 1. Now I am at runningSum 14, so 14 -7 = 7, has it happened in hashMap?, yes it has happened
and how many times ? 1 time. We keep the count variable that will be counting the number of subarrays whose sume is equal to k which is 7 here 
 and increment the variable count = 1. So if y is not there in hashmap we will not increase the variable count
 At runningSum = 16, we see that 16-7 = 9, 9 has not occurred in hashmap so we just add the entry 16 : 1 in hashmap and move forward.
At index 5, we get runningSum as 14, so 14 -7 = 7, has 7 happened before, it has , how many times 1 time, then the count now increment by 1
and update 7 : 2 in hashmap

WE missed the base case with above which is index 0 and index 1 add default entry -> 0 : 1, runningSum 0 has happened 1 time is the default entry.

*/


public class subarray_sum {
    public int calculate_sub_array(int[] nums, int k){
        HashMap <Integer, Integer>hMap = new HashMap<>();
        hMap.put(0,1);
        int rSum = 0;
        int count = 0;

        for(int i =0; i<nums.length; i++){
            rSum += nums[i]; // calculate running Sum
            int y = rSum - k; // calculate k
            if(hMap.containsKey(y)){   // check y is present at rSum or not
                count += hMap.get(y); // add the frequency value to the count;
            }

            // now check if rSum exists or not
            if(!hMap.containsKey(rSum)){  // if it does not exists
                hMap.put(rSum, 1); // add the entry
            }
            else{
                hMap.put(rSum, hMap.get(rSum) +1); // if it exists get the previoud frequency value and increment  by 1
            }

            // Lines 87 to 92 cna be written like below
            //hMap.put(rSum, hMap.getOrDefault(rSum, 0)+1);

        }
        return count;
    }
}
