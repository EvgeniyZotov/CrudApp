package kz.runtime.catalog.jpa;

import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    static Operations operations = new Operations();

    public static void main(String[] args) {
            System.out.println("""
                        - Create [1]
                        - Read [2]
                        - Update [3]
                        - Delete [4]
                        Select an action:
                        """);
            String actionNumScanner = scanner.nextLine();
            switch (actionNumScanner) {
                case "1" -> operations.createEntity();
                case "2" -> operations.readEntity();
                case "3" -> operations.updateEntity();
                case "4" -> operations.deleteEntity();
                default -> System.out.println("Selected action doesn't exists");
            }
    }
}
