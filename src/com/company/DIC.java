package com.company;

import ipNX.HB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by _kbluue_ on 1/7/2018.
 */
public class DIC {

    public static void main(String[] args){

        bcorpDIC();
    }

    static void PAPV(word word){

        int num = 0, fnum = 0;

        try {
            System.setOut(new PrintStream(new File("console.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String initial = word.toString();
        System.out.println(initial);
        word.plus1();
        while (!initial.equals(word.toString())){

            if (num > 100000){
                num = 0;
                fnum++;
                try {
                    System.setOut(new PrintStream(new File("console.txt" + fnum)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(word.toString());
            word.plus1();
            num++;
        }
    }

    static void bcorpDIC(){
        word bcorp = new word(
                new digit(" ", "_", "`", "~", "%", "^", "!", "-", ""),
                new digit(" ", "_", "`", "~", "%", "^", "!", "-"),
                bothCases("b"),
                bothCases("l"),
                bothCases("u"),
                bothCases("e", "3"),
                bothCases("c"),
                bothCases("o", "0"),
                bothCases("r"),
                bothCases("p"),
                bothCases("o", "0"),
                bothCases("o", "0"),
                new digit(digit.NUMERALS),
                new digit(" ", "_", "`", "~", "%", "^", "!", "-"),
                new digit(" ", "_", "`", "~", "%", "^", "!", "-", "")
                );
        PAPV(bcorp);
    }

    static digit bothCases(String... chr){
        return new digit(chr).bothCases();
    }

    static class word{

        digit[] digits;
        int length;

        word(digit... digits){
            this.digits = digits;
            length = digits.length;
        }

        @Override
        public String toString() {
            String out = "";
            for (digit digit : digits) out += digit.currentValue;
            return out;
        }

        void plus1(){
            digits[length - 1].plus1();
            for (int i = length - 1; i > 0; i--) {
                if (digits[i].cycle) {
                    digits[i - 1].plus1();
                    digits[i].cycle = false;
                }
            }
        }

    }

    static class digit{

        String values[], currentValue;
        boolean cycle;
        int currentIndex, length;
        final static String NUMERALS[] = getNumerals(),
        UPPERALPHA[] = getUpperAlpha(),
        LOWERALPHA[] = getLowerAlpha();

        digit(String... values){
            this.values = removeDuplicates(values);
            length = values.length;
            cycle = false;
            currentIndex = 0;
            currentValue = getCurrentValue();
        }

        digit bothCases(){
            ArrayList<String> altAplha = new ArrayList<>();
            List<String> upper = Arrays.asList(UPPERALPHA);
            List<String> lower = Arrays.asList(LOWERALPHA);
            for (String s : values) {
                if (upper.contains(s)) altAplha.add(s.toLowerCase());
                else if (lower.contains(s)) altAplha.add(s.toUpperCase());
            }
            this.values = removeDuplicates(merge(values, altAplha.toArray(new String[altAplha.size()])));
            this.length = values.length;
            return this;
        }

        digit(digit digit){
            this.values = digit.values;
            length = values.length;
            cycle = false;
            currentIndex = 0;
            currentValue = getCurrentValue();
        }

        @Override
        public String toString() {
            return getCurrentValue();
        }

        @Override
        public digit clone(){
            return new digit(this);
        }

        static String[] getNumerals(){
            String out[] = new String[10];
            for (int i = 0; i < 10; i++) {
                out[i] = String.valueOf(i);
            }
            return out;
        }

        static String[] getUpperAlpha(){
            String out[] = new String[26];
            for (int i = 0; i < 26; i++) {
                int charValue = 65+i;
                out[i] = String.valueOf((char) charValue);
            }
            return out;
        }

        static String[] getLowerAlpha(){
            String out[] = new String[26];
            for (int i = 0; i < 26; i++) {
                int charValue = 97+i;
                out[i] = String.valueOf((char) charValue);
            }
            return out;
        }

        private String getCurrentValue() {
            return values[currentIndex];
        }

        public int getValueSize(){
            return values.length;
        }

        void  plus1(){
            currentIndex++;
            if (currentIndex == length){
                cycle = true;
                currentIndex = 0;
            } else if (currentIndex == 1) cycle = false;
            currentValue = getCurrentValue();
        }

        String[] removeDuplicates(String[] array){
            ArrayList<String> out = new ArrayList<>();
            for (String s : array) {
                if (!out.contains(s)) out.add(s);
            }
            return out.toArray(new String[out.size()]);
        }

        String[] merge(String[] first, String[] second) {
            String[] result = Arrays.copyOf(first, first.length + second.length);
            System.arraycopy(second, 0, result, first.length, second.length);
            return result;
        }
    }
}
