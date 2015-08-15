import java.util.*;

class Node {

	int key;
	Node leftChild;
	Node rightChild;
	Node parent;
	
	public Node(int data) {
		this.key = data;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}
	
	public int getKey() {
		return this.key;
	}
	
    // set data held by node
    public void setNodeKey(int data) {
        this.key = data;
    }
    
    // return left child node
    public Node getLeftChild() {
        return this.leftChild;
    }
    
    // set left child node
    public void setLeftChild(Node left) {
        this.leftChild = left;
    }
    
    // return right child node
    public Node getRightChild() {
        return this.rightChild;
    }
    
    // set right child node
    public void setRightChild(Node right) {
        this.rightChild = right;
    }
    
    public Node getParent() {
    	return parent;
    }
    
    public void setParent(Node parent) {
    	this.parent = parent;
    }
}

public class BST {
	static Node root = null;
	
	private int[] getUserInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the initial sequence of values:");
		
		//gets line of numbers and splits tokens
		String[] inputStrings = sc.nextLine().split("\\s");
		
		// create and initialize array of input numbers
		int[] inputNumbers = new int[inputStrings.length];
		
		// parse each string into integer and add to array of ints
		for(int i = 0; i < inputStrings.length; i++) {
			inputNumbers[i] = Integer.parseInt(inputStrings[i]);
		}
		return inputNumbers;
	}
	
	public static boolean insertNode(int key) {
		root = insertNode(root, new Node(key));
		return true;
	}
	
	private static Node insertNode(Node currentParent, Node newNode) {

	    if (currentParent == null) 
	        return newNode;         

	    else if (newNode.key > currentParent.key) 
	        currentParent.rightChild = insertNode(currentParent.rightChild, newNode);

	    else if (newNode.key < currentParent.key)
	        currentParent.leftChild = insertNode(currentParent.leftChild, newNode);

	    return currentParent;
	}
	
	public void addNode(int key) {

		// Create a new Node and initialize it
		Node newNode = new Node(key);
		// If there is no root this becomes root
		if (root == null) {
			root = newNode;
		} else {

			// Set root as the Node we will start
			// with as we traverse the tree
			Node focusNode = root;
			// Future parent for our new Node
			Node parent;
			while (true) {

				// root is the top parent so we start there
				parent = focusNode;

				// Check if the new node should go on
				// the left side of the parent node
				if (key < focusNode.key) {

					// Switch focus to the left child
					focusNode = focusNode.leftChild;
					// If the left child has no children
					if (focusNode == null) {
						// then place the new node on the left of it
						parent.leftChild = newNode;
						return; // All Done
					}
				} else { // If we get here put the node on the right
					focusNode = focusNode.rightChild;
					// If the right child has no children
					if (focusNode == null) {
						// then place the new node on the right of it
						parent.rightChild = newNode;
						return; 
					}
				}
			}
		}
	}

	public static void inOrderTraverseTree(Node node) {
			
		if (node != null) {

			inOrderTraverseTree(node.getLeftChild());
			System.out.print(node.getKey() + " ");
			inOrderTraverseTree(node.getRightChild());
		}
	}
	
	public static void preOrderTraverseTree(Node node) {
		
		if (node != null) {
				
			System.out.print(node.getKey() + " ");
			preOrderTraverseTree(node.getLeftChild());
			preOrderTraverseTree(node.getRightChild());
		}
	}
		
	public static void postOrderTraverseTree(Node node) {
		
		if (node != null) {

			postOrderTraverseTree(node.getLeftChild());
			postOrderTraverseTree(node.getRightChild());
			System.out.print(node.getKey() + " ");
		}
	}

	public static boolean findNode(int key) {

		// Start at the top of the tree
		Node focusNode = root;

		// While we haven't found the Node
		// keep looking
		while (focusNode.key != key) {

			// If we should search to the left
			if (key < focusNode.key) {

				// Shift the focus Node to the left child
				focusNode = focusNode.leftChild;

			} else {

				// Shift the focus Node to the right child
				focusNode = focusNode.rightChild;
			}
			// The node wasn't found
			if (focusNode == null)
				return false;
		}
		return true;
	}
	
	public static Node findMinimum(Node root)
	{
	    if (root == null) {
	    	return null;
	    }
	    if (root.getLeftChild() != null) {
	    	return findMinimum(root.getLeftChild());
	    }
	    return root;
	}
	
	public static Node findMaximum(Node root)
	{
	    if (root == null) {
	    	return null;
	    }
	    if (root.getRightChild() != null) {
	        return findMaximum(root.getRightChild());
	    }
	    return root;
	}

	public static Node search(int key) {
	    Node node = root;
	    while (node != null && key != node.key) {

	        if(key < node.key) {
	            node = node.leftChild;
	        } else if (key > node.key) {
	            node = node.rightChild;
	        }
	    }
	    return node;
	}
	
    
	public static Node successor(Node node) {
	    if (node == null)
	        return node;
	    if (node.rightChild != null) {
	        return findMinimum(node.rightChild);
	    }
	    while (null != node.parent /*while we are not root*/ 
	            && node.parent.rightChild == node) /* and while we are "right" node */ {
	        node = node.parent; // go one level up
	    }
	    return node.parent;
	}

	/** Returns Successor of given value **/
	public static int getSuccessor(int val) {
	    Node node; 
	    if (null == (node = search(val))|| (null == (node = successor(node)))) {
	        // either val is not in BST, or it is the last value-> no successor
	        System.out.println("No successor to " + val);
	    	return val; // -1, for instance;
	    }
	    return node.key;
	}

	public static Node predecessor(Node node) {
	    if (node.leftChild != null) {
	        return findMaximum(node.leftChild);
	    }

	    while (null != node.parent /*while we are not root*/ 
	            && node.parent.leftChild == node) /* and while we are "left" node */ {

	        node = node.parent; // go one level up
	    }

	    return node.parent;
	}
	
		
	public static int getPredecessor(int val) {
	    Node node; 
	    if (null == (node = search(val))
	            || (null == (node = predecessor(node)))) {
	        // either val is not in BST, or it is the last value-> no successor
	        return -1; // -1, for instance;
	    }

	    return node.key;
	}
	
	public static boolean remove(int key) {

		// Start at the top of the tree
		Node focusNode = root;
		Node parent = root;

		// When searching for a Node this will
		// tell us whether to search to the
		// right or left
		boolean isItALeftChild = true;

		// While we haven't found the Node
		// keep looking
		while (focusNode.key != key) {

			parent = focusNode;

			// If we should search to the left
			if (key < focusNode.key) {
				isItALeftChild = true;
				// Shift the focus Node to the left child
				focusNode = focusNode.leftChild;
			} else {
				// Greater than focus node so go to the right
				isItALeftChild = false;
				// Shift the focus Node to the right child
				focusNode = focusNode.rightChild;
			}
			// The node wasn't found
			if (focusNode == null)
				return false;
		}

		// If Node doesn't have children delete it
		if (focusNode.leftChild == null && focusNode.rightChild == null) {

			// If root delete it
			if (focusNode == root)
				root = null;
			// If it was marked as a left child
			// of the parent delete it in its parent
			else if (isItALeftChild)
				parent.leftChild = null;
			// Vice versa for the right child
			else
				parent.rightChild = null;
		}

		// If no right child
		else if (focusNode.rightChild == null) {

			if (focusNode == root)
				root = focusNode.leftChild;
			// If focus Node was on the left of parent
			// move the focus Nodes left child up to the
			// parent node
			else if (isItALeftChild)
				parent.leftChild = focusNode.leftChild;
			// Vice versa for the right child
			else
				parent.rightChild = focusNode.leftChild;
		}

		// If no left child
		else if (focusNode.leftChild == null) {

			if (focusNode == root)
				root = focusNode.rightChild;
			// If focus Node was on the left of parent
			// move the focus Nodes right child up to the
			// parent node
			else if (isItALeftChild)
				parent.leftChild = focusNode.rightChild;

			// Vice versa for the left child
			else
				parent.rightChild = focusNode.rightChild;
		}
		// Two children so I need to find the deleted nodes
		// replacement

		else {

			Node replacement = getReplacementNode(focusNode);
			// If the focusNode is root replace root
			// with the replacement
			if (focusNode == root)
				root = replacement;

			// If the deleted node was a left child
			// make the replacement the left child
			else if (isItALeftChild)
				parent.leftChild = replacement;

			// Vice versa if it was a right child
			else
				parent.rightChild = replacement;

			replacement.leftChild = focusNode.leftChild;
		}
		return true;
	}
	
	public static Node getReplacementNode(Node replacedNode) {

		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		Node focusNode = replacedNode.rightChild;

		// While there are no more left children
		while (focusNode != null) {
			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.leftChild;
		}
		// If the replacement isn't the right child
		// move the replacement into the parents
		// leftChild slot and move the replaced nodes
		// right child into the replacements rightChild
		if (replacement != replacedNode.rightChild) {
			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;
		}
		return replacement;

	}
	
	
	public static void main(String[] args) {
		
		BST theTree = new BST();
		int[] userInput = theTree.getUserInput();
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i<userInput.length; i++){
			theTree.addNode(userInput[i]);
		}
		System.out.print("Pre-order: ");
		preOrderTraverseTree(root);
		System.out.println();
		System.out.print("In-order: ");
		inOrderTraverseTree(root);
		System.out.println();
		System.out.print("Post-order: ");
		postOrderTraverseTree(root);
		System.out.println();
		
		System.out.println("Command?");
		System.out.println(" I Insert a value");
		System.out.println(" D Delete a value");
		System.out.println(" P Find predecessor");
		System.out.println(" S Find successor");
		System.out.println(" E Exit the program");
		System.out.println(" H Display this message");
		char input = sc.next().charAt(0);
		input = Character.toUpperCase(input);
		boolean stop = true;
		while(stop==true){
		switch (input) {
		case 'I':
			int inp = sc.nextInt();
			if(findNode(inp) == false) {
				insertNode(inp);
				System.out.print("In-order: ");
				inOrderTraverseTree(root);
				System.out.println();
				System.out.println("Command?");
				input = sc.next().charAt(0);
				input = Character.toUpperCase(input);
			} else {
				System.out.println(inp + " already exists, ignore.");
				System.out.print("In-order: ");
				inOrderTraverseTree(root);
				System.out.println();
				System.out.println("Command?");
				input = sc.next().charAt(0);
				input = Character.toUpperCase(input);
			}
			break;
		case 'D':
			int del = sc.nextInt();
			if (remove(del)== true) {
				System.out.print("In-order: ");
				inOrderTraverseTree(root);
				System.out.println();
				System.out.println("Command?");
				input = sc.next().charAt(0);
				input = Character.toUpperCase(input);
			} else {
				System.out.println(del + " doesn't exist!");
				System.out.print("In-order: ");
				inOrderTraverseTree(root);
				System.out.println();
				System.out.println("Command?");
				input = sc.next().charAt(0);
				input = Character.toUpperCase(input);
			}
			break;
		case 'P':
			int pre = sc.nextInt();
			System.out.println(getPredecessor(pre));
			System.out.println("Command?");
			input = sc.next().charAt(0);
			input = Character.toUpperCase(input);
			break;
		case 'S':
			int suc = sc.nextInt();
			System.out.println(getSuccessor(suc));
			System.out.println("Command?");
			input = sc.next().charAt(0);
			input = Character.toUpperCase(input);
			break;
		case 'E':
			System.out.println("Thank you for using!");
			System.exit(0);
			stop=false;
			break;
		case 'H':
			System.out.println("Command?");
			System.out.println(" I Insert a value");
			System.out.println(" D Delete a value");
			System.out.println(" P Find predecessor");
			System.out.println(" S Find successor");
			System.out.println(" E Exit the program");
			System.out.println(" H Display this message");
			input = sc.next().charAt(0);
			input = Character.toUpperCase(input);
			break;
		default:
			System.out.println("Invalid Response. Try again.");
			System.out.println("Command?");
			System.out.println(" I Insert a value");
			System.out.println(" D Delete a value");
			System.out.println(" P Find predecessor");
			System.out.println(" S Find successor");
			System.out.println(" E Exit the program");
			System.out.println(" H Display this message");
			input = sc.next().charAt(0);
			break;
		}
		}
	}
}








