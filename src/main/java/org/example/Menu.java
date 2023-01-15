package org.example;

import org.example.service.GenericsService;
import org.example.service.GenreService;
import org.example.service.MovieService;
import org.example.service.ReviewService;
import org.example.service.impl.GenreServiceConsoleImpl;
import org.example.service.impl.MovieServiceConsoleImpl;

import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    public static void enterGeneralMenu(GenreService genreService, MovieService movieService, ReviewService reviewService) {
        Boolean exitCommand = false;
        String userWrite = null;
        while (!exitCommand) {
            System.out.println();
            System.out.println("=======MENU PRINCIPAL=======");
            System.out.println("Choose one option or Insert exit to quit the menu! ");
            System.out.println(" 1 -> Genre ");
            System.out.println(" 2 -> Movie ");
            System.out.println(" 3 -> Review ");
            userWrite = scanner.nextLine();
            switch (userWrite) {
                case ("1"):
                    System.out.println("=======GENRES=======");
//                    exitCommand = true;
                    displaySpecificMenu(genreService);
                    break;
                case ("2"):
                    System.out.println("=======MOVIES=======");
//                    exitCommand = true;
                    displaySpecificMenu(movieService);
                    break;
                case ("3"):
                    System.out.println("=======REVIEWS=======");
                    displaySpecificMenu(reviewService);
//                    exitCommand = true;
                    break;
                case ("exit"):
                    exitCommand = true;
                    break;
                default:
                    System.err.println("Invalid option choosed! Insert exit to quit MENU!");
            }
        }
    }

    public static Integer displaySpecificMenu(GenericsService service ) {

        String userInput = null;
        Boolean userInputIsValid = false;
        while (!userInputIsValid) {
            System.out.println(" Choose one option:");
            System.out.println(" 1 -> create ");
            System.out.println(" 2 -> show ");
            System.out.println(" 3 -> change ");
            System.out.println(" 4 -> remove ");
            userInput = scanner.nextLine();
            switch (userInput) {
                case ("1"):
                    userInputIsValid = true;
                    service.add();
                    break;
                case ("2"):
                    userInputIsValid = true;
                    service.displayAll();
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
