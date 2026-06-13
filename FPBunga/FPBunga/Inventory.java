import java.util.ArrayList;
// class untuk mengelola daftar bucket dalam inventory

public class Inventory {
    private ArrayList<Bucket> daftarBucket;
    public Inventory() {
        daftarBucket = new ArrayList<>();
    }
    // method untuk menambahkan bucket ke dalam inventory
    public void tambahBucket(Bucket bucket) {
        daftarBucket.add(bucket);
    }
    // method untuk menampilkan semua bucket dalam inventory
    public void tampilkanInventory() {
        for (Bucket bucket : daftarBucket) {
            System.out.println(bucket.toString());
            System.out.println(bucket.getDetail());
            System.out.println();
        }
    }
    // method untuk menghapus bucket berdasarkan index
    public void hapusIndex(int index) {
        if (index >= 0 && index < daftarBucket.size()) {
            daftarBucket.remove(index);
        } else {
            System.out.println("Index tidak valid.");
        }
    }
    // method mengambil bucket berdasarkan index
    public Bucket getBucket(int index) {
        if (index >= 0 && index < daftarBucket.size()) {
            return daftarBucket.get(index);
        } else {
            System.out.println("Index tidak valid.");
            return null;
        }
    }
    // method untuk mencari bucket berdasarkan nama
    public Bucket cariByNama (String nama) {
        for (Bucket bucket : daftarBucket) {
            if (bucket.getNama().equalsIgnoreCase(nama)) {
                return bucket;
            }
        }
        System.out.println("Bucket dengan nama " + nama + " tidak ditemukan.");
        return null;
    }
    // method untuk mengecek apakah index valid
    public boolean indexValid(int i) {
        return i >= 0 && i < daftarBucket.size();
    }
    // method untuk mendapatkan ukuran inventory
    public int ukuran() {
        return daftarBucket.size();
    }
}
