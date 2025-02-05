package com.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class MediaApp {

    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3,4,5,7,8};
        int i=0,j=i+1;
        int count=1;
        int max=1;
        for(i=0;i<nums.length-1 && j<nums.length;)
        {
            if(nums[i]+1==nums[j])
            {
                count++;
i++;
            }
            else{
                count=1;
                i=j;

            }
            j++;
            max=Math.max(count,max);
        }
        System.out.println("max->"+max);


/*      //  SpringApplication.run(MediaApp.class, args);
        System.out.println("Animal Listing Application is running...");
String result="radar";
int[] re= new int[26];
for(int i=0;i<result.length();i++)
{
    re[result.charAt(i)-'a']++;
}
String an="radar";
        for(int i=0;i<an.length();i++)
        {
            re[an.charAt(i)-'a']--;
        }

   *//*     for (int count : re) {
            if (count != 0) {
                return false;
            }
        }*//*
        String reversed = new StringBuilder(result).reverse().toString();
       result.compareTo(reversed.toString());
        List<Character> paln=reversed.chars().mapToObj(c->(char)c).collect(Collectors.toList());
         IntStream.range(0,reversed.length()/2).allMatch(i->reversed.charAt(i)==reversed.charAt(args.length-i-1));
  *//*      Node n=new Node(1);
        n.next=new Node(2);
        n.next.next=new Node(3);
        Node node=new Node();
        Node cre=node.reverseList(n);
        System.out.println("Reversed List:");*//*
        System.out.println("oaln ->"+result.equals(result));*/
       // node.printList(cre);

    }
}
