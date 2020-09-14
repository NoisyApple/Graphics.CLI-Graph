package com.noisyapple;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        final int BAR_WIDTH = 8;
        final int NUMBER_AMOUNT = 10;
        final int MAX = 20;

        int[] numbers = new int[NUMBER_AMOUNT];

        for (int i = 0; i < NUMBER_AMOUNT; i++)
            numbers[i] = (int) Math.floor(Math.random() * MAX);

        System.out.println(Arrays.toString(numbers));

        int biggest = 0;
        for (int n : numbers)
            biggest = (n > biggest) ? n : biggest;

        System.out.println(biggest);

        while (biggest > 0) {
            System.out.print("│ ");
            for (int n : numbers) {
                char c = ' ';

                if (n >= biggest)
                    c = '█';

                for (int i = 0; i < BAR_WIDTH; i++)
                    System.out.print(c);

                System.out.print(' ');
            }

            System.out.println();

            biggest--;
        }

        System.out.print("└─");

        for (int n : numbers)
            System.out.print("─────────");

    }

}
