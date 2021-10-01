import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FindPhone {

    private String name;
    public static int option;

    static Scanner input = new Scanner(System.in);

    public static int startMenu() {
        System.out.println("Welcome to Phone Finder!");
        System.out.println();
        System.out.println("↓Select an option:↓");
        System.out.println("1. Find a phone");
        System.out.println("2. Add new phone");

        return (Integer.parseInt(input.nextLine()));
    }

    public static int phoneModel() {
        System.out.println("Select your phone model:");

        return (Integer.parseInt(input.nextLine()));
    }

    public static void addPhone() {
        System.out.println("What's the phone make? (e.g. Samsung, Google, Apple)");
        String phone_make = input.nextLine();
        String phone_ = input.nextLine();

        try {
            File f = new File("phones.txt");

            if (f.createNewFile()) {
                System.out.println("Phone added");
            } else {
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        option = startMenu();
        System.out.println(option);

        if (option == 1) {
            option = phoneModel();

        } else {
            
        }
        input.close();
    }
}
