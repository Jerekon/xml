import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static File textFileMain = new File("basket.txt");
    public static String[] products = {"Milk", "Bred", "Rise"};
    public static int[] prices = {30, 14, 40};

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Task 1\n");
        Basket basket;
        if (textFileMain.exists()) {
            System.out.println("we hve basket:");
            basket = Basket.loadFromTxtFile(textFileMain);
            basket.printCart();
        } else {
            System.out.print("Basket empty. ");
            basket = new Basket(products, prices);
        }
        groceryList(basket);
        while (true) {
            System.out.println("\nchose item " +
                    "or enter \"end\" for exit:");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println(String.format("need to print 2 numbers \"number and count\"" +
                        " use space'%s'", input));
                continue;
            }
            try {
                if (Integer.parseInt(parts[0]) < 0 || Integer.parseInt(parts[0]) > products.length) {
                    System.out.println(String.format("need to  write number '1' до '%s'",
                            (products.length)));
                } else if (Integer.parseInt(parts[1]) >= 0) {
                    int productNumber = Integer.parseInt(parts[0]) - 1;
                    int productCount = Integer.parseInt(parts[1]);
                    basket.addToCart(productNumber, productCount);
                    basket.saveTxt(textFileMain);
                } else
                    System.out.println(String.format("number of items cane be minus" +
                            " '%s'", Integer.parseInt(parts[1])));
            } catch (NumberFormatException e) {
                System.out.println(String.format("wrong enter:" +
                        " '%s'", input));
            }
        }
        System.out.println("tour basket:");
        basket.printCart();
    }

    private static void groceryList(Basket basket) {
        System.out.println("list:");
        for (int i = 0; i < products.length; i++) {
            System.out.printf("%d. %s %d rub/ps \n", i + 1, products[i], prices[i]);
        }
    }
}