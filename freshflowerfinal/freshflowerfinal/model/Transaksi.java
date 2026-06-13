package freshflowerfinal.model;

import freshflowerfinal.datastructure.NodeKeranjang;
import freshflowerfinal.datastructure.StackKeranjang;

public class Transaksi {
    private int idTransaksi;
    private Customer customer;
    private StackKeranjang keranjang;
    private double totalBayar;
    private boolean sudahDibayar;

    public Transaksi(int idTransaksi, Customer customer, int kapasitas) {
        this.idTransaksi = idTransaksi;
        this.customer = customer;
        this.keranjang = new StackKeranjang();
        this.totalBayar = 0;
        this.sudahDibayar = false;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public StackKeranjang getKeranjang() {
        return keranjang;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(double totalBayar) {
        this.totalBayar = totalBayar;
    }

    public boolean isSudahDibayar() {
        return sudahDibayar;
    }

    public void setSudahDibayar(boolean sudahDibayar) {
        this.sudahDibayar = sudahDibayar;
    }

    public int getUkuranKeranjang() {
        return keranjang.getUkuran();
    }

    public boolean tambahKeKeranjang(Bucket b, int jumlahBeli) {
        keranjang.push(b, jumlahBeli);
        hitungTotalDiskon();
        return true;
    }

    public boolean hapusItemTerakhir() {
        NodeKeranjang popped = keranjang.pop();
        if (popped != null) {
            System.out.println("Item " + popped.getBucket().getNama() + " berhasil dihapus (Undo).");
            hitungTotalDiskon();
            return true;
        } else {
            System.out.println("Keranjang kosong, tidak ada yang bisa di-undo.");
            return false;
        }
    }

    private void hitungTotalDiskon() {
        double totalSementara = keranjang.hitungTotalBayar();
        if (customer != null && customer.isMember()) {
            totalBayar = totalSementara * 0.95; // Diskon 5%
        } else {
            totalBayar = totalSementara;
        }
    }

    public void lihatKeranjang() {
        keranjang.tampilkan();
        if (!keranjang.isEmpty()) {
            System.out.println("Total Bayar: " + totalBayar);
        }
    }

    public boolean bayar(double uangBayar) {
        if (uangBayar >= totalBayar) {
            sudahDibayar = true;
            System.out.println("Pembayaran berhasil. Kembalian: Rp" + (uangBayar - totalBayar));
            return true;
        }

        System.out.println("Uang kurang!");
        return false;
    }

    public void kurangiStok() {
        keranjang.kurangiStok();
    }

    public void cetakRingkasan() {
        System.out.println("ID Transaksi: " + idTransaksi);
        System.out.println("Customer: " + customer.getNama());
        System.out.println("Total Bayar: " + totalBayar);
        System.out.println("Status: " + (sudahDibayar ? "Lunas" : "Belum Dibayar"));
    }

    public void strukTransaksi() {
        System.out.println("=== STRUK TRANSAKSI ===");
        System.out.println("ID Transaksi: " + idTransaksi);
        System.out.println("ID Customer: " + customer.getIdCustomer());
        System.out.println("Nama Customer: " + customer.getNama());
        lihatKeranjang();
    }
}
