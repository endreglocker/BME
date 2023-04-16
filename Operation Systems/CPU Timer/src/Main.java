import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System. in);
        String inputString;

        while (scanner.hasNextLine()) {
            try {
                inputString = scanner.nextLine();
                System.out.println(inputString);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
}

