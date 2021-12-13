import java.util.Scanner;
import java.util.LinkedList;
import java.lang.*;

/*
 * Create a binary search tree from nodes chosen by the user with the ability to search for nodes
 *
 * @author Casandra Torres Rebollar
 * @version 06/05/2021
 */
public class BinarySearchTree {

   public static int key = 0;

   public static class Node {
      int key;
      Node left;
      Node right;
      Node parent;

      public Node(int keyIn){
         key = keyIn;
         left = null;
         right = null;
         parent = null;
      }

      void setKey(int keyIn){
         key = keyIn;
      }

      int getKey(){
         return key;
      }

      void setParent(Node nodeIn){
         parent = nodeIn;
      }

      Node getParent(){
         return parent;
      }

      void setLeft(Node leftIn){
         left = leftIn;
      }

      Node getLeft(){
         return left;
      }

      void setRight(Node rightIn){
         right = rightIn;
      }

      Node getRight(){
         return right;
      }
   }


   // *** tree code begins here ***
   public static Node root; //Creates the root of the BST

   /**
    * Constructor of the Binary Search Tree
    */
   BinarySearchTree(Node rootIn)
   {
      root = rootIn;
   }

   public void treeInsert(BinarySearchTree tree, Node z) {
      Node y =null;
      Node x = tree.root;

      while(x != null){
         y = x;
         if(z.getKey() < x.getKey()){
            x = x.getLeft();
         }
         else{
            x = x.getRight();
         }
      }
      z.setParent(y);
      if(y == null){
         tree.root = z; // if tree T was empty
      }
      else if(z.getKey() < y.getKey()){
         y.setLeft(z);
      }
      else{
         y.setRight(z);
      }
   }

    /** 
     * Searches the BST for the position of a key
     */
   public int search(Node nodeIn, int key) {
      if (nodeIn == null || key == nodeIn.getKey()) { // the node is null or the key is at the root, base case
         if(nodeIn == null){
            return -1;
         }
         return nodeIn.getKey();
      }

      if (key < nodeIn.getKey()) {
         return search(nodeIn.getLeft(), key); // search towards the left if the key is less than the node
      }

      else {
         return search(nodeIn.getRight(), key); // search towards the right if the key is greater than the node
      }
   }

   /**
    * In order traversal of the BST
    */
   public void inorderRec(Node root)
   {
      if (root != null) {
         inorderRec(root.left);
         System.out.println(root.key);
         inorderRec(root.right);
      }
   }
   void inOrder(){
      inorderRec(root); // calls inorder recusive method to print out tree
   }

   /**
    * here we write our main program
    * this will allow the user to interact with our program 
    */
   public static void main(String[] args) {
      // declare our variables here
      LinkedList<Node> nodes = new LinkedList<>();
      int count = 0;
      int valueEntered;
      Scanner userInput = new Scanner(System.in);


      // for the root input
      System.out.println("Enter an integer not already in the binary search tree or -1 to end: ");
      valueEntered = userInput.nextInt();
      nodes.add(new Node(valueEntered)); // adds the first node
      BinarySearchTree tree = new BinarySearchTree(nodes.get(count));
      System.out.println("The value you have entered is the root. The parent of the root is NIL."); // this will only run once in the beginning



      // create a loop that will proceed as long as the user does not enter -1
      // loop must detect duplicates
      // loop states whether the new node is the left or right child
      while(valueEntered != -1) {
         System.out.println("Enter an integer not already in the binary search tree or -1 to end: ");
         valueEntered = userInput.nextInt();
         if(valueEntered == -1){
            break; // here we make sure that if -1 is detected we can exit the loop and not continue the program
         }
         // this will check to make sure there are no duplicates
         if(tree.search(nodes.get(0), valueEntered) != valueEntered) {
            count++; // add to the count that we will use for our height
            nodes.add(new Node(valueEntered)); // add node
            tree.treeInsert(tree, nodes.get(count)); // here we call our insert method
            System.out.println("The parent is " + nodes.get(count).getParent().getKey());

            if (nodes.get(count).getParent().getKey() > nodes.get(count).getKey()) {
               // if the child is less than the parent we will go to the left
               System.out.println((nodes.get(count).getKey()) + " is the left child");
            }
            else if (nodes.get(count).getParent().getKey() < nodes.get(count).getKey()) {
              // if the child is less than the parent we will go to the right
               System.out.println((nodes.get(count).getKey()) + " is the right child");
            }
         }
            else{
               // this catches duplicates
               System.out.println("The value you entered already exists in the tree. Please enter another value to continue, or -1 to end the program.");
            }
      }
      // once we make it out of the while loop, we have ended our program
      // so we will now print the height
      // first create a class that calculates with log 2
      // this works with log2(count) because we know that log(n) can be used to get the height in a BST
      // the if statement makes sure we are getting a valid value
      if(count > 1) {
         System.out.println("The height of the tree is " + log2(count));
      }
      // we also want our tree to print out in order so we call the inOrder method to do this
      tree.inOrder();
   }
   /**
    * here we created an extra public parameter that will help calculate the tree height
    * the default log value is 10, but we are interested in log base 2
    */
   public static int log2 (int N){
      int result = (int) (Math.log(N) / Math.log(2));
      return result;
   }

}


