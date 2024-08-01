package com.example.estudosDro;

import java.util.List;

public class HelloWorld {
    public static void main (String args[]){
        List<Integer> arr = List.of(-4, 3, -9, 0, 4, 1);
        int positives = 0;
        int negatives = 0;
        int total = arr.size();
        int zero = 0;
        for(int j: arr){
            if(j > 0){
                positives++;
            }
            else if(j < 0){
                negatives ++;
            }
            else{
                zero++;
            }
        }
        System.out.println((double)positives/total);
        System.out.println((double)negatives/total);
        System.out.println((double)zero/total);
    }
}

