package com.javatutorial;

import java.util.List;

public class SearchTree implements NodeList {
    private ListItem root = null;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        // tree was empty, newItem becomes root
        if (this.root == null) {
            // list was empty
            this.root = newItem;
            return true;
        }

        // otherwise, start comparing from the head of the tree
        ListItem currentItem = this.root;
        // until you reach the end of the node list...
        while (currentItem != null) {
            int comparison = currentItem.compareTo(newItem);
            if (comparison < 0) {
                // new item is greater, move right if possible
                if (currentItem.next() != null) {
                    currentItem = currentItem.next();
                } else {
                    //there is no node to the right, so add at this point
                    currentItem.setNext(newItem);
                    return true;
                }
            } else if (comparison > 0) {
                // new item is less, move left if possible
                if (currentItem.previous() != null) {
                    currentItem = currentItem.previous();
                } else {
                    currentItem.setPrevious(newItem);
                    return true;
                }
            } else {
                // equal, so don't add
                System.out.println(newItem.getValue() + " is already present, not added.");
                return false;
            }
        }
        // we can't actually get here, but Java complains if there's no return
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());
        }
        ListItem currentItem = this.root;
        ListItem parentItem = currentItem;

        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if (comparison < 0) {
                parentItem = currentItem;
                currentItem = currentItem.next();
            } else if (comparison > 0) {
                parentItem = currentItem;
                currentItem = currentItem.previous();
            } else {
                // found item
                performRemoval(currentItem, parentItem);
                return true;
            }
        }
        return false;
    }

    private void performRemoval(ListItem item, ListItem parent) {
        if (item.next() == null) {
            // no right tree, make parent point to left tree, (which may be null)
            if (parent.next() == item) {
                // item is right child
                parent.setNext(item.previous());
            } else if (parent.previous() == item) {
                // item is left child
                parent.setPrevious(item.previous());
            } else {
                // parent must be item, which means we are looking at root of tree
                this.root = item.previous();
            }
        } else if (item.previous() == null) {
            // same idea as previous condition, but with right tree
            if (parent.next() == item) {
                parent.setNext(item.next());
            } else if (parent.previous() == item) {
                parent.setNext(item.next());
            } else {
                this.root = item.next();
            }
        } else {
            // neither left nor right are null, deletion is now a lot trickier
            // from the right sub-tree, find the smallest value (i.e. the leftmost)
            ListItem current = item.next();
            ListItem leftmostParent = item;
            while (current.previous() != null) {
                leftmostParent = current;
                current = current.previous();
            }
            // now put the smallest value into our node to be deleted
            item.setValue(current.getValue());
            // and delete the smallest
            if (leftmostParent == item) {
                // there was no leftmost node, so current points to the smallest node
                // (the one that must now be deleted)
                item.setNext(current.next());
            } else {
                // set the smallest node's parent to point to the smallest node's right child
                // (which may be null)
                leftmostParent.setPrevious(current.next());
            }
        }
    }

    @Override
    public void traverse(ListItem root) {
        // recursive method
        if (root != null) {
            traverse(root.previous());
            System.out.println(root.getValue());
            traverse(root.next());
        }
    }
}
