package com.noisyapple;

import org.fusesource.jansi.AnsiConsole;

public class App {
  public static void main(String[] args) {
    AnsiConsole.systemInstall();

    final int BAR_WIDTH = 7;
    final int NUMBER_AMOUNT = 10;
    final int VALUE_PER_ROW = 5;
    final int MAX = 100;
    final int SCALE_REPEATS = 10;

    final String ANSI_RESET = "\u001B[0m";

    final String ANSI_BLACK = "\u001B[90m";
    final String ANSI_RED = "\u001B[91m";
    final String ANSI_GREEN = "\u001B[92m";
    final String ANSI_YELLOW = "\u001B[93m";
    final String ANSI_BLUE = "\u001B[94m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[96m";
    final String ANSI_WHITE = "\u001B[37m";

    final String ANSI_RED_D = "\u001B[31m";

    final String[] ANSI_COLORS = { ANSI_BLACK, ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN,
        ANSI_WHITE };

    int[] numbers = new int[NUMBER_AMOUNT];
    int[] scaledNumbers = new int[NUMBER_AMOUNT];

    int graphMaxValue = MAX / VALUE_PER_ROW;
    int scaleValue = MAX;
    int scaleLength = Integer.toString(scaleValue).length();
    int sum = 0;

    for (int i = 0; i < NUMBER_AMOUNT; i++) {

      numbers[i] = (int) Math.floor(Math.random() * MAX);
      scaledNumbers[i] = (int) numbers[i] / VALUE_PER_ROW;

      sum += numbers[i];
    }

    float average = ((float) sum) / NUMBER_AMOUNT;

    System.out.println();

    while (graphMaxValue > 0) {

      // Left scale values.
      if (graphMaxValue % (MAX / VALUE_PER_ROW / SCALE_REPEATS) == 0) {

        int scaleLengthCorrection = scaleLength - Integer.toString(scaleValue).length();

        for (int i = 0; i < scaleLengthCorrection; i++)
          System.out.print(" ");

        System.out.print(scaleValue + "┤ ");
        scaleValue -= (MAX / SCALE_REPEATS);

      } else {

        for (int i = 0; i < scaleLength; i++)
          System.out.print(" ");

        System.out.print("│ ");
      }

      if (graphMaxValue == (int) (average / VALUE_PER_ROW)) {

        System.out.print(ANSI_RED_D);

        String str = "■";

        for (int i = 0; i < scaledNumbers.length; i++) {

          for (int j = 0; j < BAR_WIDTH; j++)
            System.out.print(str);

          System.out.print(str);
        }

        System.out.print(ANSI_RESET + " [" + average + "]");

      } else {

        // Bars.
        for (int i = 0; i < scaledNumbers.length; i++) {
          String str = " ";

          if (scaledNumbers[i] >= graphMaxValue)
            str = ANSI_COLORS[i % ANSI_COLORS.length] + "█" + ANSI_RESET;

          for (int j = 0; j < BAR_WIDTH; j++)
            System.out.print(str);

          System.out.print(' ');
        }

      }

      System.out.println();

      graphMaxValue--;
    }

    // Bottom Scale Values.
    for (int i = 0; i < 2; i++) {

      for (int j = 0; j < scaleLength; j++)
        System.out.print(" ");

      System.out.print((i == 0) ? "└─" : "  ");

      for (int n : numbers) {

        String barBase = "";

        for (int j = 0; j < BAR_WIDTH; j++)
          barBase += (i == 0) ? "─" : " ";

        int middleIndex = (int) Math.floor(BAR_WIDTH / 2);

        if (i == 0) {
          barBase = barBase.substring(0, middleIndex) + "┬" + barBase.substring(middleIndex + 1);
        } else {
          int nLength = Integer.toString(n).length();
          int startIndex = middleIndex - ((int) Math.floor(nLength / 2));
          barBase = barBase.substring(0, startIndex) + n + barBase.substring(startIndex + nLength);
        }

        // barBase += (j == Math.floor(BAR_WIDTH / 2)) ? "┬" : "─";

        System.out.print(barBase + ((i == 0) ? "─" : " "));
      }

      System.out.println();

    }

  }

}
