package freshflowerfinal.datastructure;

import freshflowerfinal.model.Transaksi;

public class NodeTransaksi {
    private Transaksi transaksi;
    private NodeTransaksi next;

    public NodeTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
        this.next = null;
    }

    public Transaksi getTransaksi() { return transaksi; }
    public NodeTransaksi getNext() { return next; }
    public void setNext(NodeTransaksi next) { this.next = next; }
}
