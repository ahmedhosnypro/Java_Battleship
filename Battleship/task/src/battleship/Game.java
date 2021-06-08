package battleship;

import java.util.Scanner;

public class Game {
    static private final Scanner scanner;

    static  {
        scanner = new Scanner(System.in);
    }

    static void startGame(GameField field){
        System.out.println();
        System.out.println("The game starts!");
        System.out.println();
        field.printHiddenField();
        System.out.println();
        System.out.println("Take a shot!");
        System.out.println();
        while (shot(field));
    }

    static private String checkInput(String input) {
        String check = "";
        input = input.replaceAll("10", ":");
        if (input.length() != 2) {
            check = "wrong coordinate";
            return check;
        } else {
            if (!(input.charAt(0) >= 65 && input.charAt(0) <= 74)) {
                check = input.charAt(0) + " first option on the coordinate must be [A - J]";
                return check;
            }
            else if (!(input.charAt(1) >= 48 && input.charAt(1) <= 58)) {
                check = input.charAt(1) + " second option on the coordinate must be a number";
                return check;
            } else {
                check = "valid";
                return check;
            }
        }
    }

    static boolean shot(GameField field){
        boolean isContinue = true;
        String input = scanner.nextLine().trim();
        input = input.replaceAll("10", ":");
        System.out.println();
        if (!checkInput(input).equals("valid")) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
        }
        else {
            int row = (input.charAt(0) - 64);
            int clm = (input.charAt(1) - 48);

            switch (field.getGameField()[row][clm]) {
                case "O":
                case "X":
                    field.getGameField()[row][clm] = "X";
                    field.getHiddenField()[row][clm] = "X";
//                    field.getVisualField()[row][clm] = "X";
                    field.printHiddenField();
                    System.out.println();
                    if (isAllSank(field)) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        isContinue = false;
                    } else if (isSank(field, row, clm)) {
                        System.out.println("You sank a ship! Specify a new target:");
                    } else {
                        System.out.println("You hit a ship! Try again:");
                    }
                    break;
                case "~":
                    field.getGameField()[row][clm] = "M";
                    field.getHiddenField()[row][clm] = "M";
//                    field.getVisualField()[row][clm] = "M";
                    field.printHiddenField();
                    System.out.println();
                    System.out.println("You missed. Try again:");
                    break;
//                case "X":
//                    System.out.println("already shot");
//                    break;
                default:
                    break;
            }
        }

        System.out.println();
        return isContinue;
    }

    private static boolean isSank(GameField field, int row, int clm){
        int[][] cords = new int[2][2];
        switch (field.getVisualField()[row][clm]){
            case "A":
                cords = field.getAircraftCarrier();
                break;
            case "B":
                cords = field.getBattleship();
                break;
            case "s":
                cords = field.getSubmarine();
                break;
            case "C":
                cords = field.getCruiser();
                break;
            case "D":
                cords = field.getDestroyer();
                break;
            default:
                break;
        }
        boolean isSank = true;
        for (int i = cords[0][0]; i <= cords[1][0]; i++){
            for (int j = cords[0][1]; j <= cords[1][1]; j++){
                if (!field.getGameField()[i][j].equals("X")){
                    isSank = false;
                    break;
                }
            }
        }

        return isSank;
    }
    private static boolean isAllSank(GameField field){
        return isAircraftCarrierSank(field) && isBattleshipSank(field) && isSubmarineSank(field) && isCruiserSank(field)
                && isDestroyerSank(field);
    }
    private static boolean isAircraftCarrierSank(GameField field){
        boolean isSank = true;
        int[][] cords = field.getAircraftCarrier();

        for (int i = cords[0][0]; i <= cords[1][0]; i++){
            for (int j = cords[0][1]; j <= cords[1][1]; j++){
                if (!field.getGameField()[i][j].equals("X")){
                    isSank = false;
                    break;
                }
            }
        }

        return isSank;

    }
    private static boolean isBattleshipSank(GameField field){
        boolean isSank = true;
        int[][] cords = field.getBattleship();

        for (int i = cords[0][0]; i <= cords[1][0]; i++){
            for (int j = cords[0][1]; j <= cords[1][1]; j++){
                if (!field.getGameField()[i][j].equals("X")){
                    isSank = false;
                    break;
                }
            }
        }

        return isSank;

    }
    private static boolean isSubmarineSank(GameField field){
        boolean isSank = true;
        int[][] cords = field.getSubmarine();

        for (int i = cords[0][0]; i <= cords[1][0]; i++){
            for (int j = cords[0][1]; j <= cords[1][1]; j++){
                if (!field.getGameField()[i][j].equals("X")){
                    isSank = false;
                    break;
                }
            }
        }

        return isSank;

    }
    private static boolean isCruiserSank(GameField field){
        boolean isSank = true;
        int[][] cords = field.getCruiser();

        for (int i = cords[0][0]; i <= cords[1][0]; i++){
            for (int j = cords[0][1]; j <= cords[1][1]; j++){
                if (!field.getGameField()[i][j].equals("X")){
                    isSank = false;
                    break;
                }
            }
        }

        return isSank;

    }
    private static boolean isDestroyerSank(GameField field){
        boolean isSank = true;
        int[][] cords = field.getDestroyer();

        for (int i = cords[0][0]; i <= cords[1][0]; i++){
            for (int j = cords[0][1]; j <= cords[1][1]; j++){
                if (!field.getGameField()[i][j].equals("X")){
                    isSank = false;
                    break;
                }
            }
        }

        return isSank;

    }
}
