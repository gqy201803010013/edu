package com.edu.eduservice.controller;

import java.util.HashMap;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();

        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0, -1);
        int count = 0;
        int max = 0;

        for (int i = 0; i < str.length() ; i++) {
            if(str.charAt(i) == 'A'){
                count++;
            }
            else{
                count--;
            }

            if(map.containsKey(count)){
                max = Math.max(max,i-map.get(count));
            }
            else {
                map.put(count,i);
            }
        }
        System.out.println(max);
    }
}
