/**
 * Created by ubuntu on 2016-04-01.
 */
public class RedBlackTree<T extends Comparable> {
    private Node NIL;
    private Node root;
    private int height = 4;
    private int nrOfNodes = 0;

    public Node getRoot() {
        return root;
    }

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
        else {
            x.getParent().setRight(y);
        }


    public void insert(T element){
        Node y = NIL;
        Node x = root;
        while (x != NIL){
            y = x;
            x.incSize();
            if (element.compareTo(x.getElement()) < 0){ //element - x.getElement() < 0 -> if (element < x.getElement)
                x = x.getLeft();
            }
            else {
                x = x.getRight();
            }
        }
        Node z = new Node(y, NIL, NIL, element);
        if (y == NIL){
            root = z;
            z.setSize(1);
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
        y.setSize(x.getSize());
        x.setSize(x.getLeft().getSize() + x.getRight().getSize() + 1);
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
        y.setRight(x);
        x.setParent(y);
        y.setSize(x.getSize());
        x.setSize(x.getLeft().getSize() + x.getRight().getSize() + 1);
    }

    /**
     * Return the value of the tree stored at the specified rank starting from the root
     * @param rank the element's rank in the tree
     * @return the element
     */
    public T osSelect(int rank){
        return osSelect(root, rank);
    }

    /**
     * Return the value of the tree stored at the specified rank starting from node x
     * @param x starting node (root of subtree)
     * @param rank the element's rank in the tree
     * @return the element
     */
    public T osSelect(Node x, int rank){
        int r = x.getLeft().getSize();
        if (rank == r){
            return x.getElement();
        }
        else if (rank < r){
            return osSelect(x.getLeft(), rank);
        }
        else {
            return osSelect(x.getRight(), rank - r);
        }
    }

    public int osRank(Node x){
        int r = x.getLeft().getSize() + 1;
        Node y = x;
        while (y != x){
            if (y == y.getParent().getRight()){
                r = r + y.getLeft().getSize() + 1;
            }
            y = y.getParent();
        }
        return r;
    }

    public int osKeyRank(Node x, T elem){
        if (x == NIL){
            return -1;
        }
        else if (elem.compareTo(x.getElement()) < 0){ //element - x.getElement() < 0 -> if (element < x.getElement)
            int rank = osKeyRank(x.getLeft(), elem);
            if (rank != -1){
                return rank;
            }
            else {
                return -1;
            }
        }
        else if (elem.compareTo(x.getElement()) > 0){
            int rank = osKeyRank(x.getRight(), elem);
            if (rank != -1){
                return rank + x.getLeft().getSize() + 1;
            }
            else {
                return -1;
            }
        }
        else {
            return x.getLeft().getSize() + 1;
        }
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
                System.out.println("|-------\033[31m" + root.getElement() + "\033[37m-" + root.getSize());
            }
            else {
                System.out.println("|-------" + root.getElement() + "-" + root.getSize());
            }
        }
        else
            System.out.println(root.getElement() + "-" + root.getSize());
        printBinaryTree(root.getLeft(), level+1);
    }
    public class Node {
        private Node parent;
        private Node left;
        private Node right;
        private boolean isBlack;
        private T element;
        private int size;

        public Node() {
            parent = NIL;
            left = NIL;
            right = NIL;
            isBlack = true;
            element = null;
            size = 0;
        }

        public Node(Node parent, Node left, Node right, T element) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.isBlack = false;
            this.element = element;
            this.size = 1;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void incSize(){
            size++;
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
