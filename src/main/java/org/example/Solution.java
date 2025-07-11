package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int i = 0, j = 0;
        while (i < word1.length() && j < word2.length()){
            result.append(word1.charAt(i));
            result.append(word2.charAt(j));
            i++;
            j++;
        }

        while (i < word1.length()){
            result.append(word1.charAt(i));
            i++;
        }

        while (j < word2.length()){
            result.append(word2.charAt(j));
            j++;
        }

        return result.toString();
    }

    public String gcdOfStrings(String str1, String str2) {
        if(!(str1+str2).equals(str2+str1))
            return "";

        return str1.substring(GCD(str1.length(), str2.length()));
    }

    private int GCD(int a, int b){
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();

        int max = -1;

        for(int candyCount : candies){
            if(candyCount >= max){
                max = candyCount;
            }
        }

        for (int candyCount : candies) {
            if (candyCount + extraCandies >= max)
                result.add(true);
            else
                result.add(false);
        }
        return result;
    }
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if(flowerbed[i] == 0){
                if(i == 0){
                    if(flowerbed.length == 1){
                        return n == 1;
                    }
                    else {
                        if(flowerbed[1] == 0){
                            flowerbed[0] = 1;
                            n--;
                        }
                    }
                }
                else if(i == flowerbed.length-1){
                    if(flowerbed[i-1] == 0){
                        flowerbed[i] = 1;
                        n--;
                    }
                }
                else {
                    if(flowerbed[i-1] == 0 && flowerbed[i+1] == 0){
                        flowerbed[i] = 1;
                        n--;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(flowerbed));
        return n <= 0;
    }

    public String reverseVowels(String s) {
        Set<Character> vowels = Set.of('a','e','i','o','u','A','E','I','O','U');

        char[] chars = new char[s.length()];

        int low = 0, high = s.length()-1;

        while (low <= high){
            if(vowels.contains(s.charAt(low)) && vowels.contains(s.charAt(high))){
                chars[high] = s.charAt(low);
                chars[low] = s.charAt(high);
                low++;
                high--;
            }
            else if(vowels.contains(s.charAt(low)) && !vowels.contains(s.charAt(high))){
                chars[high] = s.charAt(high);
                high--;
            }
            else if(!vowels.contains(s.charAt(low)) && vowels.contains(s.charAt(high))){
                chars[low] = s.charAt(low);
                low++;
            }
            else {
                chars[low] = s.charAt(low);
                chars[high] = s.charAt(high);
                low++;
                high--;
            }
        }
        return new String(chars);
    }

    public String reverseWords(String s) {
        String[] s1 = s.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i = s1.length-1; i >= 0; i--) {
            result.append(s1[i]).append(" ");
        }
        return result.toString().trim();
    }


//    1 2 6 24
//    5 20 60 120

//    -1 1 0 -3 3


//    -1 -1 0 0
//    3 -9 0 0

//    0 0 9 0 0

    // 1 0


    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] mults = new int[n * 2 - 2];

        int prefixProd = 1;
        for (int i = 0; i < n - 1; i++) {
            prefixProd *= nums[i];
            mults[i] = prefixProd;
        }

        int suffixProd = 1;
        for (int i = n - 1, j = n - 1; i > 0; i--, j++) {
            suffixProd *= nums[i];
            mults[j] = suffixProd;
        }

        System.out.println(Arrays.toString(mults));

        int[] result = new int[nums.length];

        result[0] = mults[mults.length-1];
        result[result.length-1] = mults[result.length-2];

        int low = 0, high = mults.length-2;

        for (int i = 1; i < result.length-1; i++){
            System.out.println(mults[low] +" at "+ low + " : " + mults[high]);
            result[i] = mults[low]* mults[high];
            low++;
            high--;
        }

        return result;
    }

    public int[] productExceptSelf1(int[] nums) {
        // todo
        int[] result = new int[nums.length];

        Arrays.fill(result,1);


        int left = 1;
        for (int i = 0; i < result.length; i++) {
            result[i] *= left;
            left *= result[i];
        }

        int right = 1;
        for (int i = result.length-1; i >= 0; i--) {

        }


        return null;
    }

//    public boolean increasingTriplet(int[] nums) {
//
//    }

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> nums2set = new HashSet<>();
        List<Integer> list = new ArrayList<>();

        for(int i : nums2){
            nums2set.add(i);
        }
        for(int i : nums1){
            if(!nums2set.contains(i) && !list.contains(i)){
                list.add(i);
            }
        }

        result.add(new ArrayList<>(list));
        nums2set = new HashSet<>();
        list = new ArrayList<>();

        for(int i : nums1){
            nums2set.add(i);
        }
        for(int i : nums2){
            if(!nums2set.contains(i) && !list.contains(i)){
                list.add(i);
            }
        }
        result.add(new ArrayList<>(list));
        return result;
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int element : arr){
            map.put(element, map.getOrDefault(element,0)+1);
        }

        return map.size() == new HashSet<>(map.values()).size();
    }

    public int compress(char[] chars) {
        if(chars.length == 1)
            return 1;

        int readIndex = 0;
        int writeIndex = 0;
        int charCount = 0;

        while (readIndex < chars.length){
            if(readIndex == 0){
                charCount++;
            }
            else if(readIndex == chars.length-1){
                if(chars[readIndex-1] == chars[readIndex]){
                    charCount++;
                    writeIndex = write(chars, writeIndex, chars[readIndex-1], charCount);
                }
                else {
                    writeIndex = write(chars, writeIndex, chars[readIndex-1], charCount);
                    charCount = 1;
                    writeIndex = write(chars, writeIndex, chars[readIndex], charCount);

                }
            }
            else {
                if(chars[readIndex-1] == chars[readIndex]){
                    charCount++;
                }
                else {
                    // write
                    writeIndex = write(chars, writeIndex, chars[readIndex-1], charCount);
                    charCount = 1;
                }
            }
            readIndex++;
        }

//        System.out.println(Arrays.toString(chars));
        return writeIndex;
    }

    private int write(char[] chars, int writeIndex, char gamotneuliChar, int charCount){
//        System.out.println("write "+gamotneuliChar + " at "+writeIndex);

        chars[writeIndex] = gamotneuliChar;
        writeIndex++;
        if(charCount == 1){
            return writeIndex;
        }
//        System.out.println("in write");
//        System.out.println("char count: " + charCount);
        List<Character> charList = new ArrayList<>();

        while (charCount > 0){
            char currentChar = (char) (charCount % 10 + '0');
            charList.addFirst(currentChar);
            charCount /= 10;
        }

        for (Character character : charList) {
            chars[writeIndex] = character;
            writeIndex++;
        }
        return writeIndex;
    }


    public boolean isSubsequence(String s, String t) {
        int low = 0;
        int high = 0;
        while (low < s.length() && high < t.length()){
            if(s.charAt(low) == t.charAt(high)){
                low++;
            }
            high++;
        }
        return low == s.length();
    }

    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        int low = 0;
        int high = height.length-1;

        while (low < high){
            int minHeight = Math.min(height[low], height[high]);
            int length = high - low;
            int waterAmount = minHeight * length;

            if(waterAmount > max){
                max = waterAmount;
            }

            if(height[low] > height[high]){
                high--;
            }
            else {
                low++;
            }
        }
        return max;
    }

//    public int maxOperations(int[] nums, int k) {
//        int result = 0;
//        int[] checked = new int[nums.length];
//        Arrays.fill(checked,0);
//
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i+1; j < nums.length; j++) {
//                if(nums[i] + nums[j] == k && (checked[i] == 0 && checked[j] == 0)){
//                    result+=1;
//                    checked[i] = 1;
//                    checked[j] = 1;
//                }
//            }
//        }
//        return result;
//    }

    // 5 4 3 2 1

    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
        int medium = Integer.MAX_VALUE;

        for (int number : nums){
            if(number <= min){
                min = number;
            }
            else if(number <= medium){
                medium = number;
            }
            else {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(s.maxOperations(new int[]{3, 1, 3, 4, 3}, 6));
    }
}
