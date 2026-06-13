package freshflowerfinal.datastructure;

import freshflowerfinal.model.Bucket;

public class StackKeranjang {
    private NodeKeranjang top;

    public StackKeranjang() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Bucket bucket, int jumlah) {
        NodeKeranjang newNode = new NodeKeranjang(bucket, jumlah);
        if (top != null) {
            newNode.setNext(top);
        }
        top = newNode;
    }

    public NodeKeranjang pop() {
        if (isEmpty()) {
            return null;
        }
        NodeKeranjang poppedNode = top;
        top = top.getNext();
        return poppedNode;
    }

    public NodeKeranjang peek() {
        return top;
    }

    public double hitungTotalBayar() {
        double total = 0;
        NodeKeranjang current = top;
        while (current != null) {
            total += current.getTotalHarga();
            current = current.getNext();
        }
        return total;
    }

    public void kurangiStok() {
        NodeKeranjang current = top;
        while (current != null) {
            Bucket b = current.getBucket();
            b.setStok(b.getStok() - current.getJumlah());
            current = current.getNext();
        }
    }
    
    public int getUkuran() {
        int count = 0;
        NodeKeranjang current = top;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public void tampilkan() {
        if (isEmpty()) {
            System.out.println("Keranjang kosong.");
            return;
        }
        System.out.println("== Keranjang ==");
        NodeKeranjang current = top;
        int i = 1;
        while (current != null) {
            System.out.println(i + ". Nama Barang: " + current.getBucket().getNama());
            System.out.println("   Harga Barang: " + current.getBucket().getHarga());
            System.out.println("   Jumlah: " + current.getJumlah());
            System.out.println("   Total Harga: " + current.getTotalHarga());
            current = current.getNext();
            i++;
        }
    }
}
