package com.view;

public class PrintDelay {
    public static void print(String a){
        int delay = 15;
        for (char ch : a.toCharArray()){
            System.out.print(ch);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
    }
}
