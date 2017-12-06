import java.util.*;

class TreeNode 
{
	Integer data;
	TreeNode left;
	TreeNode right;
	
	public TreeNode() 
	{
		left = null;
		right = null;
	}
	public TreeNode(Integer i)
	{
		left = null;
		right = null;
		data = i;
	}
}

public class BinaryTree 
{
	public TreeNode root;
	
	public BinaryTree()
	{
		root = null;
	}

	public void insert(Integer i)
	{
		if (i == null)
		{
			throw new NullPointerException();
		}
		if (root == null)
		{
			root = new TreeNode(i);
		}
		else
		{
			insert(root, i);
		}
	}

	private int insert(TreeNode n, Integer i)
	{
		if (i <= n.data)
		{
			if (n.left != null)
			{
				insert(n.left, i);
			}
			else 
			{
				n.left = new TreeNode(i);
				return 1;
			}
		}
		else
		{
			if (n.right != null)
			{
				insert(n.right, i);
			}
			else
			{
				n.right = new TreeNode(i);
				return 1;
			}
		}
		return 0;
	}

	public void inorder()
	{
		inordert(root);
	}

	public void inordert(TreeNode n)
	{
		if (n == null)
		{
			return;
		}
		inordert(n.left);
		System.out.println(n.data + " ");
		inordert(n.right);
	}

	public void preorder()
	{
		preordert(root);
	}

	public void preordert(TreeNode n)
	{
		if (n == null)
		{
			return;
		}
		System.out.println(n.data + " ");
		preordert(n.left);
		preordert(n.right);
	}

	public void postorder()
	{
		postordert(root);
	}

	public void postordert(TreeNode n)
	{
		if (n == null)
		{
			return;
		}
		postordert(n.left);
		postordert(n.right);
		System.out.println(n.data + " ");
	}

	public void breadthfirst(TreeNode root) 
	{
		bfs(root);
	}

	public void bfs(TreeNode root)
	{
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		if (root == null)
		{
			return;
		}
		q.add(root);
		while (!q.isEmpty())
		{
			TreeNode n = (TreeNode) q.remove();
			System.out.print(n.data + " ");
			if (n.left != null)
			{
				q.add(n.left);
			}
			if (n.right != null)
			{
				q.add(n.right);
			}
		}
	}

	public void delete(Integer i)
	{
		TreeNode parent = root;
		TreeNode curr = root;
		boolean isLeftChild = false;
		while (curr.data != i) 
		{
			parent = curr;
			if (curr.data > i) 
			{
				isLeftChild = true;
				curr = curr.left;
			}
			else
			{
				isLeftChild = false;
				curr = curr.right;
			}
			if (curr == null)
			{
				return;
			}
		}
		if (curr.left == null && curr.right == null)
		{
			if (curr == root)
			{
				root = null;
			}
			if (isLeftChild == true)
			{
				parent.left = null;
			}
			else
			{
				parent.right = null;
			}
		}
		else if (curr.right == null)
		{
			if (curr == root)
			{
				root = curr.left;
			}
			else if (isLeftChild)
			{
				parent.left = curr.left;
			}
			else
			{
				parent.right = curr.left;
			}
		}
		else if (curr.left == null)
		{
			if (curr == root)
			{
				root = curr.right;
			}
			else if (isLeftChild)
			{
				parent.left = curr.right;
			}
			else
			{
				parent.right = curr.right;
			}
		}
		else if (curr.left != null && curr.right != null)
		{
			TreeNode successor = getSuccessor(curr);
			if (curr == root)
			{
				root = successor;
			}
			else if (isLeftChild)
			{
				parent.left = successor;
			}
			else
			{
				parent.right = successor;
			}
			successor.left = curr.left;
		}
	}

	public TreeNode getSuccessor(TreeNode deleteNode)
	{
		TreeNode successor = null;
		TreeNode successorParent = null;
		TreeNode current = deleteNode.right;
		while (current != null)
		{
			successorParent = successor;
			successor = current;
			current = current.left;
		}
		if (successor != deleteNode.right)
		{
			successorParent.left = successor.right;
			successor.right = deleteNode.right;
		}
		return successor;
	}

	public boolean lookup(Integer i)
	{
		TreeNode n = root;
		if (n == null)
		{
			return false;
		}
		while (n != null)
		{
			if (n.data == i)
				return true;
			if (i < n.data)
				n = n.left;
			if (i > n.data)
				n = n.right;
		}
		return false;
	}

	public static void main (String[] args)
	{
		Scanner cin = new Scanner(System.in);
		BinaryTree btree = new BinaryTree();

		System.out.println("\n\n***************************");
		System.out.println("BINARY TREE LAB        FALL2017");
		System.out.println("EVAN                  FULCINITI");

		do
		{

			System.out.println("\nPlease choose from the following menu: ");
			System.out.println("1: Insert an integer into the binary tree");
			System.out.println("2: Lookup an integer in the binary tree");
			System.out.println("3: Delete an integer from the binary tree");
			System.out.println("4: Print btree");
			System.out.println("0: Terminate this application");

			int menu_choice;
			menu_choice = cin.nextInt();
			cin.nextLine();

			if (menu_choice == 1)
			{
				System.out.println("INTEGER: " );
				int input;
				input = cin.nextInt();
				cin.nextLine();
				btree.insert(input);
				System.out.println("\n" + input + " has been inserted into the btree\n");
			}

			else if (menu_choice == 2)
			{
				System.out.println("INTEGER TO LOOKUP: ");
				int input;
				input = cin.nextInt();
				cin.nextLine();
				if(btree.lookup(input) == true)
				{
					System.out.println("\n" + input + " exists in the btree\n");
				}
				else
				{
					System.out.println("\n" + input + " does not exist in the btree. would you liek to add it? (1 for yes. 2 for no)");
					int choice;
					choice = cin.nextInt();
					cin.nextLine();
					if (choice == 1)
					{
						System.out.println("INTEGER: ");
						input = cin.nextInt();
						cin.nextLine();
						btree.insert(input);
						System.out.println("\n" + input + " has been inserted into the btree\n");
					}
					else
					{
						System.out.println("BTREE NOT CHANGED. RETURNING TO MAIN MENU\n");
					}
				}
			}

			else if (menu_choice == 3)
			{
				System.out.println("INTEGER TO DELETE FROM BTREE");
				int input;
				input = cin.nextInt();
				cin.nextLine();
				if (btree.lookup(input) == true)
				{
					btree.delete(input);
					System.out.print("\n" + input + " has been deleted from the btree\n");
				}
				else
				{
					System.out.println("\n" + input + " does not exist in btree\n");
				}
			}

			else if (menu_choice == 4)
			{
				System.out.println("Print btree selected. Please choose from following menu:");
				System.out.println("1: Print inorder");
				System.out.println("2: Print preorder");
				System.out.println("3: Print postorder");
				System.out.println("4: Print breadthfirst");
				int choice;
				choice = cin.nextInt();
				cin.nextLine();
				if (choice == 1)
				{
					btree.inorder();
				}
				else if (choice == 2)
				{
					btree.preorder();
				}
				else if (choice == 3)
				{
					btree.postorder();
				}
				else if (choice == 4)
				{
					btree.bfs(btree.root);
				}
				else 
				{
					System.out.println("invalid selection");
				}
			}

			else 
			{
				break;
			}

		} while(true);
	}
}
