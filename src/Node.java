import java.util.*;

public class Node {

   private String name;
   private Node firstChild;
   private Node nextSibling;

   Node (String n, Node d, Node r) {
      name = n;
      firstChild = d;
      nextSibling = r;
   }

   public static Node parsePostfix (String s) {
      Stack<Node> stack = new Stack<Node>();
      for (int i = s.length() - 1; i >= 0; i--) { //starting from end
         char c = s.charAt(i);
         if (c == ')') {
            stack.push(null); // null represents that we are done dealing with siblings of current node
         } else if (c == ',') {
            continue;
         } else { // if its anything except "," and ")" it means there is node
            Node child = stack.pop();
            Node sibling = stack.pop();
            // we take two last elements of stack
            if (sibling != null) { // in this case we are dealing with root because it has no siblings
               sibling.nextSibling = child;
            }
            stack.push(new Node(String.valueOf(sibling.name), child, sibling.nextSibling));
         }
      }
      return stack.pop(); //last element is obviously root
   }

   public String leftParentheticRepresentation() {
      StringBuilder sb = new StringBuilder();
      sb.append(name);
      if (firstChild != null) {
         sb.append("(");
         sb.append(firstChild.leftParentheticRepresentation());
         sb.append(")");
      }
      if (nextSibling != null) {
         sb.append(nextSibling.leftParentheticRepresentation());
      }
      return sb.toString();
   }

   public static void main (String[] param) {
      // after I tried it for 2 hours, saving siblings and children in
      // variables, I asked chatGPT how to deal with it
      // he told me that stack is a possible solution

      // code itself is done by me though
   }
}