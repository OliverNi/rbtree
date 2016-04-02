import java.util.Scanner;

/**
 * Created by ubuntu on 2016-04-01.
 */
public class Main {
    public static void main(String args[]) {
        RedBlackTree tree = new RedBlackTree();
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (!input.equals("end")) {
            System.out.println("Input yo stuff");
            input = scanner.nextLine();
            tree.insert(Integer.parseInt(input));
            tree.printTree();
        }
    }
}
