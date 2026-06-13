package freshflowerfinal.datastructure;

import freshflowerfinal.model.Bucket;

public class NodeKeranjang {
    private Bucket bucket;
    private int jumlah;
    private double totalHarga;
    private NodeKeranjang next;

    public NodeKeranjang(Bucket bucket, int jumlah) {
        this.bucket = bucket;
        this.jumlah = jumlah;
        this.totalHarga = bucket.getHarga() * jumlah;
        this.next = null;
    }

    public Bucket getBucket() { return bucket; }
    public int getJumlah() { return jumlah; }
    public double getTotalHarga() { return totalHarga; }
    public NodeKeranjang getNext() { return next; }
    public void setNext(NodeKeranjang next) { this.next = next; }
}
