package com.metanit;

import java.io.FileReader;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Stack;


public class Main {

    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("C:\\Users\\honey\\IdeaProjects\\MP_NFA\\src\\com\\metanit\\text.txt");
        Scanner scan = new Scanner(reader);

        String str = new String(scan.nextLine());
        System.out.println(str);
        char array[] = str.toCharArray();

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(7);
        nf.setGroupingUsed(false);

        Stack st = new Stack();
        st.push("Z");

        int q = 0, count0 = 0;
        int countN = 0, incForM = 0, countBAfter = 0, countBUntil = 0, flag = 0;
        int countB = 0;

        for (int i = 0; i < str.length(); i++) {
            if ((q == 0) && (st.peek() == "Z") && (array[i] == 'a')) {
                q = 1;
                incForM++;
            } else if ((q == 1) && (st.peek() == "Z") && (array[i] == 'a')) {
                q = 2;
                st.push("a");
                incForM++;
            } else if ((q == 1) && (st.peek() == "a") && (array[i] == 'a')) {
                q = 2;
                incForM++;
            } else if ((q == 2) && (st.peek() == "a") && (array[i] == '0') && (incForM % 2 == 0)) {
                q = 3;
            } else if ((q == 2) && (st.peek() == "a") && (array[i] == 'a')) {
                q = 1;
                incForM++;
            } else if ((q == 3) && (st.peek() == "a" && (array[i] == 'b'))) {
                q = 4;
                if (flag == 0) {
                    countBUntil++;
                } else {
                    countBAfter++;
                }
            } else if ((q == 3) && (st.peek() == "a") && (array[i] == '0') && (incForM == 2)) {
                count0++;
                st.pop();
                st.push("0");
            } else if ((q == 3) && (st.peek() == "0") && (array[i] == '0')) {
                count0++;
                if ((count0 > 3) && (i < (str.length() + 1))) {
                    System.out.println("Первое несоответствие на " + (i + 1) + " символе");
                    break;
                }
            } else if ((q == 3) && (st.peek() == "0") && (array[i] == 'a' && (count0 == 4))) {
                System.out.println("Цепочка принадлежит данному языку");
                break;
            } else if ((q == 4) && (st.peek() == "a") && (array[i] == 'b') && (countBAfter < countBUntil)) {
                q = 4;
                if (flag == 0) {
                    countBUntil++;
                } else {
                    countBAfter++;
                }
            } else if ((q == 4) && (st.peek() == "a") && (array[i] == '1')) {
                q = 3;
                flag = 1;
            } else if ((q == 4) && (st.peek() == "a") && (array[i] == '0') && (countBAfter == countBUntil)) {
                q = 5;
            } else if ((q == 5) && (st.peek() == "a") && (array[i] == 'a')) {
                q = 6;
                countN++;
                if (((incForM == (countN * 2)) && (i == str.length() - 1))) {
                    System.out.println("Цепочка принадлежит данному языку");
                    break;
                }
            } else if ((q == 6) && ((incForM >= (countN * 2)))) {
                countN++;
                if (i == (str.length() - 1)) {
                    if (incForM == (countN * 2)) {
                        System.out.println("Цепочка принадлежит данному языку");
                        break;
                    } else {
                        System.out.println("Первое несоответствие на " + (i + 1) + " символе");
                    }
                }
            } else if ((q == 0) && (st.peek() == "Z") && (array[i] == '0')) {
                q = 8;
            } else if ((q == 8) && (st.peek() == "Z") && (array[i] == '1')) {
                q = 9;
            } else if ((q == 9) && (st.peek() == "Z") && (array[i] == '0') && (i == (str.length() - 1))) {
                System.out.println("Цепочка принадлежит данному языку");
            } else if ((q == 8) && (st.peek() == "Z") && (array[i] == 'b')) {
                q = 3;
                st.pop();
                st.push("b");
            } else if ((q == 3) && (st.peek() == "b") && (array[i] == 'b')) {
                q = 4;
                countB++;
            } else if ((q == 4) && (st.peek() == "b") && (array[i] == 1) && (countB == 1)) {
                q = 3;
                countB = 0;
            } else if ((q == 4) && (st.peek() == "b") && (array[i] == '0')) {
                q = 5;
            } else if ((q == 5) && (st.peek() == "b") && (array[i] == 'a') && (i == (str.length() + 1))) {
                System.out.println("Цепочка принадлежит данному языку");
            } else {
                if ((incForM % 2 != 0) && (i <= incForM)) {
                    System.out.println("Первое несоответствие на " + (i) + "символе");
                } else {
                    System.out.println("Первое несоответствие на " + (i + 1) + " символе");
                }
                break;
            }
        }
    }
}
