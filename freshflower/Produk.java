package freshflower;

import java.util.Scanner;

public class Produk {
    Scanner scanner = new Scanner(System.in);
    // fields
    private String jenis;
    private double harga;
    private int stok;

    // constructor
    public Produk() {
    }

    // setter dan getter
    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    // constructor dengan parameter
    public Produk(String jenis, double harga, int stok) {
        this.jenis = jenis;
        this.harga = harga;
        this.stok = stok;
    }
    public String toString() {
        return "Jenis: " + jenis + "\nHarga: " + harga + "\nStok: " + stok;
    }

}
