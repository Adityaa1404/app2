public abstract class Bucket {
    // class abstrak untuk bucket yang akan diisi dengan bunga atau boneka
    private String nama;
    private double harga;
    private int stok;

    public Bucket(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // getter
    public String getNama() {
        return nama;
    }
    public double getHarga() {
        return harga;
    }
    public int getStok() {
        return stok;
    }

    // setter
    public void setHarga(double harga) {
        this.harga = harga;
    }
    public void setStok(int stok) {
        this.stok = stok;
    }

    public abstract String getDetail();

    public String toString() {
        return " Nama Bucket: " + nama + " Harga: " + harga + " stok: " + stok;

    }
}


