package com.media;

public class Node {
    int value;
    Node next;

    public Node() {
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Node(int value) {
        this.value = value;
    }
    public Node reverseList(Node head) {
        Node current=head;
        Node priv=null;
while(current!=null){
    Node next=current.next;
    current.next=priv;
    priv=current;
    current = next;
}

return priv;
    }
    public void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " → ");
            current = current.next;
        }
        System.out.println("null");
    }
}
