package WarungMadura;

public class cBarang {
    // data
    private String namaBarang ;
    private int hargaBarang ;
    private int stokBarang ;

    // constructor
    cBarang() {
        System.out.println("Data Barang dibuat");
    }

    // getter
    public int getHargaBarang() {
        return hargaBarang;
    }

    public int getStokBarang() {
        return stokBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    // setter
    public void setStokBarang(int stokBarang) {
        this.stokBarang = stokBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    // to string
    public String ToString() {
        return "Nama Barang: " + namaBarang + "\nHarga Barang: " + hargaBarang + "\nStok Barang: " + stokBarang;
    }

    // constructor dengan parameter
    cBarang(String nama, int harga, int stok) {
        this.namaBarang = nama;
        this.hargaBarang = harga;
        this.stokBarang = stok;

    }
}