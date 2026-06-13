package freshflowerfinal.model;

public class Boneka extends Bucket {
    private String karakter;

    public Boneka() {
        System.out.println("Data Boneka dibuat");
    }

    public Boneka(double harga, int stok, String karakter) {
        super(harga, stok);
        this.karakter = karakter;
        super.setNama(karakter);
    }

    public String getKarakter() {
        return karakter;
    }

    public void setKarakter(String karakter) {
        this.karakter = karakter;
        super.setNama(karakter);
    }

    @Override
    public void setNama(String nama) {
        this.karakter = nama;
        super.setNama(nama);
    }

    @Override
    public String toString() {
        return "Nama: " + getNama() + "\tHarga: " + getHarga() + "\tStok: " + getStok();
    }
}
