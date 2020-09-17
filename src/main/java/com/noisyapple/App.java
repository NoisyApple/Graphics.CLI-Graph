package com.noisyapple;

import java.util.Scanner;

import org.fusesource.jansi.AnsiConsole;

// App class.
public class App {

  // Main method.
  public static void main(String[] args) {

    // ANSI support.
    AnsiConsole.systemInstall();

    // New scanner to detect keyboard input.
    Scanner scanner = new Scanner(System.in);

    // Generate a graph until "0" is entered,
    do {
      CLIGraph.generateGraphic();
      System.out.println("\nEnter any key to generate a new graph or \"0\" to exit.\n");
    } while (!scanner.nextLine().equals("0"));

    scanner.close(); // Data leak prevention.

  }

}
