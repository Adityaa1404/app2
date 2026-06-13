package freshflowerfinal.datastructure;

import freshflowerfinal.model.Transaksi;

public class QueueTransaksi {
    private NodeTransaksi front;
    private NodeTransaksi rear;
    private int size;

    public QueueTransaksi() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Transaksi transaksi) {
        NodeTransaksi newNode = new NodeTransaksi(transaksi);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    public Transaksi dequeue() {
        if (isEmpty()) {
            return null;
        }
        Transaksi removedTransaksi = front.getTransaksi();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        size--;
        return removedTransaksi;
    }

    public Transaksi peek() {
        if (isEmpty()) {
            return null;
        }
        return front.getTransaksi();
    }

    public int getSize() {
        return size;
    }

    public void tampilkanAntrean() {
        if (isEmpty()) {
            System.out.println("Antrean pesanan kosong.");
            return;
        }
        System.out.println("== Antrean Pesanan ==");
        NodeTransaksi current = front;
        int i = 1;
        while (current != null) {
            System.out.println(i + ". ID Transaksi: " + current.getTransaksi().getIdTransaksi() + " - Customer: " + current.getTransaksi().getCustomer().getNama());
            current = current.getNext();
            i++;
        }
    }
}
