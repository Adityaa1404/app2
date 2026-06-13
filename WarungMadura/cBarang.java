package WarungMadura;

public class cBarang {
    // data
    private int idBarang;
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
    public int getIdBarang() {
        return idBarang;
    }

    // setter
    public void setStokBarang(int stokBarang) {
        if (stokBarang<=0) {
            System.out.println("Stok Barang tidak valid");
            return;
        }
        this.stokBarang = stokBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }
    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    // to string
    public String ToString() {
        return "ID Barang: " + idBarang + " Nama Barang: " + namaBarang + " Harga Barang: " + hargaBarang + " Stok Barang: " + stokBarang;
    }

    // constructor dengan parameter
    cBarang(int id, String nama, int harga, int stok) {
        this.idBarang = id;
        this.namaBarang = nama;
        this.hargaBarang = harga;
        this.stokBarang = stok;

    }
}