/*
    class: SetUp
    author: Asmita Hari, Anmol Jaising

    This class is used to SetUp the grid for the player and create their ships.
    It takes the x and y coordinates to create the ship.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SetUp {

    String[][] grid = new String[10][10];
    Scanner scanner = new Scanner(System.in);
    String name;
    int length;
    int x, y;
    String direction;
    List<Integer> arrayList = new ArrayList<>();

    // This method is used to create the grid.
    
    public String[][] createGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = "-";
            }
        }
        return grid;
    }
    
    // This method is used to print the grid.

    public void printGrid(String tempGrid[][]) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(tempGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // This method is used to get the name of the Person.
    
    public String getName() {
        System.out.println("Provide your name");
        name = scanner.nextLine();

        while (name.length() == 0) {
            System.out.println("Provide a valid name");
            name = scanner.nextLine();
        }
        return name;
    }
    
    // This method is used to get the length of the ship.

    public int getLength() {
        System.out.println("Provide the length of the ship in terms of 2/4");
        int shipLegnth = scanner.nextInt();

//        System.out.println(legnth);
        while (shipLegnth != 4 && shipLegnth != 2) {
            System.out.println("Invalid Length");
            shipLegnth = scanner.nextInt();
        }

        length = shipLegnth;

        return shipLegnth;
    }
    
    
    // This method is used to get the x coordinate.

    public int getXCoordianate() {

        System.out.println("Provide the x coordinate of the ship");
        int x_coordinate = scanner.nextInt();

        while (x_coordinate < 0 || x_coordinate >= 10) {
            System.out.println("Provide a valid coordinate");
            x_coordinate = scanner.nextInt();
        }
        return x_coordinate;
    }
    
    // This method is used to get the y coordinate.

    public int getYCoordianate() {

        System.out.println("Provide the y coordinate of the ship");
        int y_coordinate = scanner.nextInt();
        while (y_coordinate < 0 || y_coordinate >= 10) {
            System.out.println("Provide a valid coordinate");
            y_coordinate = scanner.nextInt();
        }
        return y_coordinate;
    }
    
    // This method is used to get the direction of the ship

    public String getDirection() {

        System.out.println("Provide the direction of the ship in terms of H/V");
        direction = String.valueOf(scanner.next().charAt(0));
        System.out.println("1234");

        while (!direction.equals("H") && !direction.equals("V")) {
            System.out.println("Provide a valid direction");
            direction = scanner.nextLine();
        }
//        String directionString = "H";
        return direction;
    }

    // This method is used to create the ship.
    
    public void createShip(int length) {
        this.length = length;
        x = getXCoordianate();
        y = getYCoordianate();

        direction = getDirection();

        try {
            if (direction.equals("V")) {
                for (int i = 0; i < length; i++) {
                    grid[x + i][y] = "S";
                }
            } else if (direction.equals("H")) {
                for (int i = 0; i < length; i++) {
                    grid[x][y + i] = "S";
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Invalid ship position. Input co-ordinates again");
            createGrid();
            createShip(length);
        }

    }

    public static void main(String[] args) {

//        up.printShip();
    }
}
