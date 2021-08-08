package com.satvik.bookexchange.compRandom;

import java.util.*;

public class MissingString {
    public static final int mod = (int) 1e9+7;
    public static void main(String args[]){
        String s = "s??k";
        int n = s.length();
        List<Integer> missing = new ArrayList<>();
        char[] arr = s.toCharArray();
        for(int i=1;i<n-1;i++){
            if(String.valueOf(arr[i]).equals("?")){
                missing.add(i);
            }
        }

        Long total = 1l;

        // handle case when both 1st and last char is '?'
        if((s.charAt(0) == '?' && s.charAt(n-1) == '?')){
            // ???????
        }

        int currentWays = 0;
        int[] ways = new int[missing.size()];
        for(int i=0;i<missing.size();i++) ways[i] = 1;

        for(int i=0;i<missing.size();i++) {
            int place = missing.get(i);
            if(place > 0 && place < n){
                if(s.charAt(place-1)!='?' && s.charAt(place+1) != '?'){
                    if(s.charAt(place-1) == s.charAt(place+1)) ways[i] = 25;
                    else ways[i] = 24;
                } else ways[i] = 25;
            }
            System.out.println("at "+place+": "+currentWays);
            total = (total%mod * currentWays) % mod;
        }
        System.out.println(total);
        System.out.println(ways.toString());
    }
}
