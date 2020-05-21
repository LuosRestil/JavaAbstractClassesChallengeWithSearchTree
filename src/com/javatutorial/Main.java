package com.javatutorial;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList(null);

        String[] data = {"Darwin", "Brisbane", "Perth", "Melbourne", "Canberra", "Adelaide", "Sydney", "Canberra"};
        for (String string : data) {
            list.addItem(new Node(string));
        }
        list.traverse(list.getRoot());

        MyLinkedList list2 = new MyLinkedList(null);
        int[] data2 = {45, 2, 5, 85, 3, 1, 70};
        for (int number : data2) {
            list2.addItem(new Node(Integer.toString(number)));
        }
        MyLinkedList.traverseList(list2.getRoot());
        list2.removeItem(new Node("85"));
        list2.removeItem(new Node("84"));
        MyLinkedList.traverseList(list2.getRoot());
        list2.removeItem(new Node("45"));
        MyLinkedList.traverseList(list2.getRoot());
        list2.removeItem(new Node("1"));
        MyLinkedList.traverseList(list2.getRoot());

        System.out.println("***********************************");
        SearchTree tree = new SearchTree(null);
        for (int number : data2) {
            tree.addItem(new Node(Integer.toString(number)));
        }
        tree.traverse(tree.getRoot());
        tree.removeItem(new Node("85"));
        tree.traverse(tree.getRoot());
    }
}
