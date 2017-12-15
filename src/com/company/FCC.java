package com.company;

import java.util.*;

/**
 * Created by _kbluue_ on 9/20/2017.
 */
public class FCC {

    static Scanner read(){
        return new Scanner(System.in);
    }

    String reverse(String in){
        String out = "";
        for (int i = in.length()-1; i >= 0; i--) {
            out += in.charAt(i);
        }
        return out;
    }

    long factorial(int num){
        if (num < 0) return -1;
        long out = 1;
        for (int i = 1; i <= num; i++) {
            out *= i;
        }
        return out;
    }

    long fac(int num){
        if (num < 0) return -1;
        long out = 1;
        while (num > 0) {
            out *= num;
            num--;
        }
        return out;
    }

    boolean isPalindrome(String in){
        for (int i = 0; i < in.length() / 2; i++) {
            if (in.charAt(i) != in.charAt(in.length()-1-i)) return false;
        }
        return true;
    }

    int lenOfLongest(String in){
        int out = 0;
        String []inS = in.split(" ");
        for (int i = 0; i < inS.length; i++) {
            out = (inS[i].length() > out) ? inS[i].length() : out;
        }
        return out;
    }

    String sentenceCase(String in){
        String out = "";
        String inS[] = in.toLowerCase().split(" ");
        for (int i = 0; i < inS.length; i++) {
            out += (inS[i].substring(0,1).toUpperCase() + inS[i].substring(1));
            if (i < inS.length - 1) out += " ";
        }
        return out;
    }

    int[] largestInArray(int[] in[]){
        int len = in.length;
        int[] out = new int[len];
        for (int i = 0; i < len; i++) {
            int max = in[i][0];
            for (int j = 1; j < in[i].length; j++) {
                max = (max > in[i][j]) ? max : in[i][j];
            }
            out[i] = max;
        }
        return out;
    }

    void printArray(Object[] in){
        System.out.print("[");
        for (int i = 0; i < in.length; i++) {
            System.out.print(in[i]);
            if (i < in.length-1) System.out.print(", ");
        }
        System.out.print("]\n");
    }

    boolean endswith(String sentence,String end){
        String sub = sentence.substring(sentence.length() - end.length());
        return sub.equals(end);
    }

    String repeatString(String str,int times){
        if (times < 1) return "";
        String out = "";
        for (int i = 0; i < times; i++) {
            out += str;
        }
        return out;
    }

    boolean mutation(String[] in){
        for (int i = 0; i < in[1].length(); i++) {
            if (!in[0].contains(in[1].substring(i, i+1))) return false;
        }
        return true;
    }

    String truncate(String str,int size){
        if (size >= str.length()) return str;
        int end = (size > 3) ? size - 3: size;
        return str.substring(0, end) + "...";
    }

    Object[][] chunked(Object[] in,int size){
        int last = (in.length/size)%size == 0 ? in.length/size - 1: in.length/size;
        Object out[][] = new Object[last + 1][size];
        for (int i = 0; i < in.length; i++) out[i / size][i % size] = in[i];
        if ((in.length/size)%size == 0) return out;
        for (int i = 0; i < out[last].length; i++) {
            if (out[last][i] == null) {
                out[last] = trim(out[last], i);
                break;
            }
        }
        return out;
    }

    Object[] trim(Object[] in,int size){
        Object[] out = new Object[size];
        for (int i = 0; i < size; i++) out[i] = in[i];
        return out;
    }

    Object[] slasher(Object[] in,int index){
        if (index >= in.length) return new Object[]{};
        Object out[] = new Object[in.length - index];
        for (int i = 0; i < out.length; i++) {
            out[i] = in[i + index];
        }
        return out;
    }

    String cipher(String str){
        String host = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        str = str.toUpperCase();
        String out = "";
        for (int i = 0; i < str.length(); i++) {
            if (!host.contains(str.substring(i, i+1))) out += str.charAt(i);
            else {
                int index = host.indexOf(str.charAt(i));
                out += (index > 12) ? host.charAt(index - 13) : host.charAt(index + 13);
            }
        }
        return out;
    }

    Object[] trim(Object[] host,Object[] virus){
        Object[] out = new Object[host.length];
        List<Object> v = Arrays.asList(virus);
        int index = 0;
        for (Object a : host) {
            if (!v.contains(a)){
                out[index] = a;
                index++;
            }
        }
        return trim(out, index);
    }

    Object[] trim(Object[] host,Object virus){
        return trim(host, new Object[]{virus});
    }

        public static void main(String args[]){
////        int[][] y = {{4, 5, 1, 3}, {13, 27, 18, 26}, {32, 35, 37, 39}, {1000, 1001, 857, 1}};
//        String[] z = {"hello", "loo"};
//        int[] x = {1, 2, 3, 4, 7, 8, 9, 10, 11, 12, 13};
//        Object[] in = new Object[]{1, 2, 3, 4, 7, 8, 9, 10, 11, 12, 13};
//        System.out.println(new FCC().cipher("the quick brown dog jumped over the lazy fox"));
        new FCC().printArray(new FCC().trim(new Object[]{1,2,3,2}, new Object[]{2}));
    }
}
