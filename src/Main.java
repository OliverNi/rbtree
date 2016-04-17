import java.io.IOException;
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
            System.out.print("Input an operation and argument:");
            input = scanner.nextLine();
            String op[] = input.split(" ");
            if (op[0].equals("in")){
                tree.insert(Integer.parseInt(op[1]));
                tree.printTree();
            } else if (op[0].equals("rankk")){
                System.out.println("rank: " + tree.osKeyRank(tree.getRoot(), Integer.parseInt(op[1])) + "\n");
                tree.printTree();
            }
        }
    }
}
