package freshflowerfinal.model;

public class Bucket {
    private int idBucket;
    private double harga;
    private int stok;
    private String nama;

    public int getIdBucket() { return idBucket; }
    public void setIdBucket(int idBucket) { this.idBucket = idBucket; }

    public Bucket() {
        System.out.println("Data Bucket dibuat");
    }

    public Bucket(double harga, int stok) {
        this.harga = harga;
        this.stok = stok;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String toString() {
        return "Nama: " + nama + "\tHarga: " + harga + "\tStok: " + stok +" " ;
    }
}
