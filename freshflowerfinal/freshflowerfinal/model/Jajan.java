package freshflowerfinal.model;

public class Jajan extends Bucket {
    private String merk;

    public Jajan() {
        System.out.println("Data Jajan dibuat");
    }

    public Jajan(double harga, int stok, String merk) {
        super(harga, stok);
        this.merk = merk;
        super.setNama(merk);
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
        super.setNama(merk);
    }

    @Override
    public void setNama(String nama) {
        this.merk = nama;
        super.setNama(nama);
    }

    @Override
    public String toString() {
        return "Nama: " + getNama() + "\tHarga: " + getHarga() + "\tStok: " + getStok();
    }
}
