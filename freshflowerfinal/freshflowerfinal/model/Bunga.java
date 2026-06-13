package freshflowerfinal.model;

public class Bunga extends Bucket {
    private String jenis;

    public Bunga() {
        System.out.println("Data Bunga dibuat");
    }

    public Bunga(double harga, int stok, String jenis) {
        super(harga, stok);
        this.jenis = jenis;
        super.setNama(jenis);
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
        super.setNama(jenis);
    }

    @Override
    public void setNama(String nama) {
        this.jenis = nama;
        super.setNama(nama);
    }

    @Override
    public String toString() {
        return "Nama: " + getNama() + "\tHarga: " + getHarga() + "\tStok: " + getStok();
    }
}
