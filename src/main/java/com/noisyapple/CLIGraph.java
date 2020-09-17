package com.noisyapple;

// CLIGraph class.
public class CLIGraph {

  // Graph configuration.
  static final int BAR_WIDTH = 7;
  static final int NUMBER_AMOUNT = 10;
  static final int VALUE_PER_ROW = 5;
  static final int MAX = 100;
  static final int SCALE_REPEATS = 10;

  // Color constants.
  static final String ANSI_RESET = "\u001B[0m";

  static final String ANSI_BLACK = "\u001B[90m";
  static final String ANSI_RED = "\u001B[91m";
  static final String ANSI_GREEN = "\u001B[92m";
  static final String ANSI_YELLOW = "\u001B[93m";
  static final String ANSI_BLUE = "\u001B[94m";
  static final String ANSI_PURPLE = "\u001B[35m";
  static final String ANSI_CYAN = "\u001B[96m";
  static final String ANSI_WHITE = "\u001B[37m";
  static final String ANSI_RED_D = "\u001B[31m";

  // Array of colors.
  static final String[] ANSI_COLORS = { ANSI_BLACK, ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE,
      ANSI_CYAN, ANSI_WHITE };

  // Static method for graphic generation.
  public static void generateGraphic() {

    int[] numbers = new int[NUMBER_AMOUNT]; // Stores the real values.
    int[] scaledNumbers = new int[NUMBER_AMOUNT]; // Stores scaled values for CLI display.

    int actualLine = MAX / VALUE_PER_ROW; // Number of CLI lines to write (excluding bottom scale).
    int scaleValue = MAX; // First value of left scale.
    int scaleLength = Integer.toString(scaleValue).length(); // Character length of max value.
    int sum = 0; // Numbers sum.

    for (int i = 0; i < NUMBER_AMOUNT; i++) {
      numbers[i] = (int) Math.floor(Math.random() * MAX); // numbers array gets filled.
      scaledNumbers[i] = (int) numbers[i] / VALUE_PER_ROW; // scaledNumbers array gets filled.

      sum += numbers[i]; // Generated values gets added.
    }

    float average = ((float) sum) / NUMBER_AMOUNT; // Average.

    System.out.println();

    while (actualLine > 0) { // Each loop represents a line before reaching bottom scale.

      // Left scale values.
      if (actualLine % (MAX / VALUE_PER_ROW / SCALE_REPEATS) == 0) { // Only when modulus returns 0.

        // Calculates how many spaces to write before the scale value.
        int scaleLengthCorrection = scaleLength - Integer.toString(scaleValue).length();

        for (int i = 0; i < scaleLengthCorrection; i++)
          System.out.print(" "); // Spaces are printed.

        System.out.print(scaleValue + "┤ "); // Scale edge when a value is printed.
        scaleValue -= (MAX / SCALE_REPEATS); // Next scale value is calculated.

      } else { // Otherwise a simple scale edge is printed.

        for (int i = 0; i < scaleLength; i++)
          System.out.print(" "); // Spaces before edge.

        System.out.print("│ "); // Scale edge.
      }

      // Checks if actual line range corresponds with average value.
      if (actualLine == (int) (average / VALUE_PER_ROW)) {

        System.out.print(ANSI_RED_D); // Turns to red color.

        String str = "■"; // Average line character.

        // Prints the entire average line.
        for (int i = 0; i < NUMBER_AMOUNT; i++) {

          for (int j = 0; j < BAR_WIDTH; j++)
            System.out.print(str);

          System.out.print(str);
        }

        // Resets color and prints average value.
        System.out.print(ANSI_RESET + " [" + average + "]");

      } else { // Otherwise bars are printed.

        // Bars.
        for (int i = 0; i < NUMBER_AMOUNT; i++) {
          String str = " "; // Default value to print if no bar is detected.

          if (scaledNumbers[i] >= actualLine) // Checks if a bar needs to be printed.
            str = ANSI_COLORS[i % ANSI_COLORS.length] + "█" + ANSI_RESET; // String is redefined.

          // Bar section is filled.
          for (int j = 0; j < BAR_WIDTH; j++)
            System.out.print(str);

          System.out.print(' '); // Space between bars.
        }

      }

      System.out.println(); // Line break/

      actualLine--; // Actual line value decreases.
    }

    // Bottom Scale Values.
    for (int i = 0; i < 2; i++) { // First iteration for edges, second for values.

      for (int j = 0; j < scaleLength; j++)
        System.out.print(" "); // Space before bottom scale.

      System.out.print((i == 0) ? "└─" : "  "); // Scale corner.

      // Iterates trough real values.
      for (int n : numbers) {

        String segment = ""; // A segment of the scale.

        for (int j = 0; j < BAR_WIDTH; j++)
          segment += (i == 0) ? "─" : " "; // Scale edges or scale values.

        int middleIndex = (int) Math.floor(BAR_WIDTH / 2); // Calculates middle index of segment.

        if (i == 0) { // If on scale edge, middle index is replaced to "┬".
          segment = segment.substring(0, middleIndex) + "┬" + segment.substring(middleIndex + 1);
        } else { // Otherwise calculated index are replaced with the value of the segment.
          int nLength = Integer.toString(n).length(); // Lenfth of segment value.
          int startIndex = middleIndex - ((int) Math.floor(nLength / 2)); // Index where to start to replace.
          segment = segment.substring(0, startIndex) + n + segment.substring(startIndex + nLength);
        }

        System.out.print(segment + ((i == 0) ? "─" : " ")); // Segment is printed with te correspondent character
                                                            // between.
      }

      System.out.println(); // Final line break.

    }
  }

}
