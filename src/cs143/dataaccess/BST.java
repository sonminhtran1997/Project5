package cs143.dataaccess;

import cs143.domain.Retiree;
import java.io.Serializable;
import java.util.ArrayList;

public class BST implements Serializable {

    protected TreeNode root;
    protected int size = 0;

    public BST() {
    }

    public BST(Retiree[] objects) {
        for (Retiree object : objects) {
            insert(object);
        }
    }

    public Retiree get(long ssn) {
        TreeNode current = root; // Start from the root
        while (current != null) {
            if (current.element.getSsn() > ssn) {
                current = current.left;
            } else if (current.element.getSsn() < ssn) {
                current = current.right;
            } else {
                // element matches current.element
                return current.element; // Element is found
            }
        }
        return null;
    }

    public final boolean insert(Retiree r) {
        if (root == null) {
            root = createNewNode(r); // Create a new root
        } else {
            // Locate the parent node
            TreeNode parent = null;
            TreeNode current = root;
            while (current != null) {
                if (r.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (r.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (r.compareTo(parent.element) < 0) {
                parent.left = createNewNode(r);
            } else {
                parent.right = createNewNode(r);
            }
        }
        size++;
        return true; // Element inserted successfully
    }

    public boolean delete(Retiree e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode parent = null;
        TreeNode current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed at by current
            }
        }

        if (current == null) {
            return false; // Element is not in the tree
        }
        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else if (e.compareTo(parent.element) < 0) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode parentOfRightMost = current;
            TreeNode rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }

        size--;
        return true; // Element deleted successfully
    }

    protected TreeNode createNewNode(Retiree r) {
        return new TreeNode(r);
    }

    public int getSize() {
        return size;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public ArrayList<TreeNode> path(Retiree r) {
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {
            list.add(current);
            if (r.compareTo(current.element) < 0) {
                current = current.left;
            } else if (r.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return list;
    }

    public static class TreeNode implements Serializable {

        public Retiree element;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Retiree r) {
            element = r;
        }
    }

}
