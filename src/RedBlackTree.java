/**
 * Created by ubuntu on 2016-04-01.
 */
public class RedBlackTree<T extends Comparable> {
    private Node NIL;
    private Node root;
    private int height = 4;
    private int nrOfNodes = 0;


    public RedBlackTree() {
        NIL = new Node();
        NIL.setParent(NIL);
        NIL.setBlack(true);
        NIL.setLeft(NIL);
        NIL.setRight(NIL);
        root = NIL;
    }

    public RedBlackTree(Node root, int height, int nrOfNodes) {
        this.root = root;
        this.height = height;
        this.nrOfNodes = nrOfNodes;
    }

    public void insert(T element){
        Node y = NIL;
        Node x = root;
        while (x != NIL){
            y = x;
            if (element.compareTo(x.getElement()) < 0){
                x = x.getLeft();
            }
            else {
                x = x.getRight();
            }
        }
        Node z = new Node(y, NIL, NIL, element);
        if (y == NIL){
            root = z;
        }
        else if (z.getElement().compareTo(y.getElement()) < 0){
            y.setLeft(z);
        }
        else {
            y.setRight(z);
        }

        z.setLeft(NIL);
        z.setRight(NIL);
        z.setBlack(false);
        fixup(z);
        nrOfNodes++;
    }

    private void fixup(Node z){
        while (!z.getParent().isBlack()){
            if (z.getParent() == z.getParent().getParent().getLeft()){
                Node y = z.getParent().getParent().getRight();
                if (!y.isBlack()){
                    z.getParent().setBlack(true);
                    y.setBlack(true);
                    z.getParent().getParent().setBlack(false);
                    z = z.getParent().getParent();
                }
                else if (z == z.getParent().getRight()){
                    z = z.getParent();
                    leftRotate(z);
                }
                else {
                    z.getParent().setBlack(true);
                    z.getParent().getParent().setBlack(false);
                    rightRotate(z.getParent().getParent());
                }
            }
            else {
                Node y = z.getParent().getParent().getLeft();
                if (!y.isBlack()){
                    z.getParent().setBlack(true);
                    y.setBlack(true);
                    z.getParent().getParent().setBlack(false);
                    z = z.getParent().getParent();
                }
                else if (z == z.getParent().getLeft()){
                    z = z.getParent();
                    rightRotate(z);
                }
                else {
                    z.getParent().setBlack(true);
                    z.getParent().getParent().setBlack(false);
                    leftRotate(z.getParent().getParent());
                }
            }
        }
        root.setBlack(true);
    }

    private void leftRotate(Node x){
        Node y = x.getRight();
        x.setRight(y.getLeft());

        if (y.getLeft() != NIL){
            y.getLeft().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == NIL){
            root = y;
        }
        else if (x == x.getParent().getLeft()){
            x.getParent().setLeft(y);
        }
        else {
            x.getParent().setRight(y);
        }

        y.setLeft(x);
        x.setParent(y);
    }

    private void rightRotate(Node x){
        Node y = x.getLeft();
        x.setLeft(y.getRight());

        if (y.getRight() != NIL){
            y.getRight().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == NIL){
            root = y;
        }
        else if (x == x.getParent().getLeft()){
            x.getParent().setLeft(y);
        }
        else {
            x.getParent().setRight(y);
        }

        y.setRight(x);
        x.setParent(y);
    }

    public void printTree(){
        System.out.println("Tree:\n");
        printBinaryTree(root, 0);
        System.out.println("\n");
    }

    public void printBinaryTree(Node root, int level){
        if(root==NIL)
            return;
        printBinaryTree(root.getRight(), level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t\t");
            if (!root.isBlack) {
                System.out.println("|-------\033[31m" + root.getElement() + "\033[37m");
            }
            else {
                System.out.println("|-------" + root.getElement());
            }
        }
        else
            System.out.println(root.getElement());
        printBinaryTree(root.getLeft(), level+1);
    }
    public class Node {
        private Node parent;
        private Node left;
        private Node right;
        private boolean isBlack;
        private T element;

        public Node() {
            parent = NIL;
            left = NIL;
            right = NIL;
            isBlack = true;
            element = null;
        }

        public Node(Node parent, Node left, Node right, T element) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.isBlack = false;
            this.element = element;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public boolean isBlack() {
            return isBlack;
        }

        public void setBlack(boolean black) {
            isBlack = black;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }
    }
}
