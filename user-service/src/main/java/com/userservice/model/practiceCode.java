package com.userservice.model;

import java.util.*;

public class practiceCode {
    public static void main(String args[])
    {
practiceCode practiceCode=new practiceCode();
System.out.println("output:"+practiceCode.containsDuplicateUsingBrutForce(new int[]{1,2,2,4,5}));
    }
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> finalSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if( finalSet.contains(nums[i]))
            {
                return true;
            }

            finalSet.add(nums[i]);
        }
        return false;
    }
    public boolean containsDuplicateUsingSort(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
     if(nums[i]==nums[i-1])
     {
         return  true;
     }
        }
        return false;
    }
    public boolean containsDuplicateUsingBrutForce(int[] nums) {


        for (int i = 0; i < nums.length; i++) {
          for(int j=i+1;j< nums.length;j++)
          {
              if(nums[i]==nums[j])
              {
                  return true;
              }
          }
        }
        return false;
    }
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())
        {
            return false;
        }
        char[] fisrts=s.toCharArray();
        char[] st=t.toCharArray();
        Arrays.sort(fisrts);
        Arrays.sort(st);


        return Arrays.equals(fisrts, st);
    }
    public boolean isAnagramHashMap(String s, String t) {
        if(s.length()!=t.length())
        {
            return false;
        }
Map<Character,Integer> smap=new HashMap<>();
        Map<String,Integer> tmap=new HashMap<>();
        for(int i=0;i<s.length();i++)
        {
            smap.put(s.charAt(i),smap.getOrDefault(s.charAt(i),0)+1);
            smap.put(t.charAt(i),smap.getOrDefault(t.charAt(i),0)+1);
        }
        return smap.equals(tmap);
    }
    public boolean isAnagramUsing26(String s, String t) {
        if(s.length()!=t.length())
        {
            return false;
        }
        int count[]=new int[26];
        for(int i=0;i<s.length();i++) {
        count[s.charAt(i)-'a']++;
        count[t.charAt(i)-'a']--;
        }
        for(int c:count)
        {
            if(c!=0)
            {
                return  false;
            }
        }
        return true;

    }
    public int[] twoSum(int[] nums, int target) {

      Map<Integer,Integer> checkMap=new HashMap<>();
      for(int i=0;i<nums.length;i++)
      { int diff=target-nums[i];
          if(checkMap.containsKey(diff) ) {
          return new int[]{checkMap.get(diff),i};
          }
          checkMap.put(nums[i],i);

      }
        return   new int[]{0,0};
    }
}
