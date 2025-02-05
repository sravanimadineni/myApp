package com.userservice;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> result = new HashMap<>();
        for (String s : strs) {

            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            String key = Arrays.toString(count);
            System.out.println("key" + key);
            System.out.println("string" + s);
            result.putIfAbsent(key, new ArrayList<>());
            result.get(key).add(s);
        }
        //result.entrySet().stream().limit(2).sorted((e1,e2)->e1.getValue()-e2.getValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1,e2)->e1),LinkedHashMap::new));
        for (Map.Entry<String, List<String>> resultout : result.entrySet()) {
            {
                System.out.println("Key: " + resultout.getKey());
                System.out.println("Values: " + resultout.getValue());
            }


        }
        return new ArrayList<>(result.values());
    }
    public List<List<String>> groupAnagramsusingHashMap(String[] strs) {
        Map<String, List<String>> result = new HashMap<>();
  for(String s:strs)
  {
      ; char[] charArray = s.toCharArray();
      Arrays.sort(charArray);
      String sortedString = new String(charArray);
      result.putIfAbsent(sortedString,new ArrayList<>());
      result.get(sortedString).add(s);
  }
        for (Map.Entry<String, List<String>> resultout : result.entrySet()) {
            {
                System.out.println("Key: " + resultout.getKey());
                System.out.println("Values: " + resultout.getValue());
            }


        }
        return new ArrayList<>(result.values());
    }
    public  static  void main(String []args)
    {
String[] input =new String[] {"act","pots","tops","cat","stop","hat"};
Solution s=new Solution();
s.groupAnagramsusingHashMap(input);
    }
}
