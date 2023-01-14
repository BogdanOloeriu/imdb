package org.example;

import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    public static Integer displayGeneralMenu() {
        Boolean userInput = false;
        String userWrite = null;
        while (userInput) {
            System.out.println("Choose one option: ");
            System.out.println(" 1 -> Genre ");
            System.out.println(" 2 -> Movie ");
            System.out.println(" 3 -> Review ");
            userWrite = scanner.nextLine();
            switch (userWrite) {
                case ("1"):
                    userInput = true;
                    break;
                case ("2"):
                    userInput = true;
                    break;
                case ("3"):
                    userInput = true;
                    break;
                default:
                    System.err.println("Invalid option choosed!");
            }
        }
        return Integer.valueOf(userWrite);
    }

    public static Integer displaySpecificMenu() {

        String userInput = null;
        Boolean userInputIsValid = false;
        while (userInputIsValid) {
            System.out.println(" Choose one option:");
            System.out.println(" 1 -> create ");
            System.out.println(" 2 -> show ");
            System.out.println(" 3 -> change ");
            System.out.println(" 4 -> remove ");
            userInput = scanner.nextLine();
            switch (userInput) {
                case ("1"):
                    userInputIsValid = true;
                    break;
                case ("2"):
                    userInputIsValid = true;
                    break;
                case ("3"):
                    userInputIsValid = true;
                    break;
                case ("4"):
                    userInputIsValid = true;
                    break;
                default:
                    System.err.println("Invalid Option Choosed!");
            }
        }
        return Integer.valueOf(userInput);
    }
}
