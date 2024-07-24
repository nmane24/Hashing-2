/*

// (Apporach 1)
// Time Complexity : O(N) + O(k) ~ O(N) + O(1) ~ O(N)
//                    N is number of characters in string
//                    K is number of entries in map as we are iterating over map
//                    K can be maximum 52 (26 lower and 26 upper character); therefore it can be considered as O(1)
// Space Complexity : O(K) ~ O(1)   (As per above explaination for K)

// (Apporach 2)
// Time Complexity : O(N)
// Space Complexity : O(K) ... K is elements in set

// (Apporach 3)
// Time Complexity : O(N)
// Space Complexity : O(58) ... K is size of boolean array
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


Leetcode : https://leetcode.com/problems/longest-palindrome
Given a string s which consists of lowercase or uppercase letters, 
return the length of the longest palindrome that can be built with those letters.
Letters are case sensitive, for example, "Aa" is not considered a palindrome here.

Example 1:

Input: s = "abccccdd"
Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
Example 2:

Input: s = "a"
Output: 1
Explanation: The longest palindrome that can be built is "a", whose length is 1.

*/

/*

Approach 1:Using Hashset

// Time Complexity : O(N)
// Space Complexity : O(K) ... K is elements in set

Hashset is used to store the freqency data for each character.
Iterate through each character of string and check for number of occurances of character in set. 
each pair is considered and added to the count eg :  if a is occured 3 times then count = 2
If character found , increase the count by 2 and remove from set. 
At last we check if set is not empty then just increase the count by 1 this is for
 If we have at least one odd number of occurance then count is increased by one at the end.



Approach 1 : Using HashMap 
Hashmap is used to store the freqency data for each character.
Array is iterated one character by character and number of occurances of character
is stored as value and character is used as a key.
Once the frequncy map is created, all elements of map are iterated.
each pair is considered and added to the count.  (count += no. of occurances / 2* 2)
e.g. if a is occured 3 times then count = 3 / 2 * 2 = 2 (out of 3 only 2 are considered in palindrom)
    if b is occured 4 times then count = 4 / 2 * 2 = 2

If we have at least one odd number of occurance then count is increased by one at the end.
*/

import java.util.HashSet;

public class longest_palindrome {
    public int palindrome(String s){
        int count = 0;
        HashSet<Character> hSet = new HashSet<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(hSet.contains(c)){
                count += 2;
                hSet.remove(c);
            }
            else{
                hSet.add(c);
            }

            if(!hSet.isEmpty()){
                count++;
            }
        }
        return count;
    }
}
