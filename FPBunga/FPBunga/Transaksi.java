import java.util.ArrayList;
public class Transaksi {

    private String idTransaksi;
    private String namaPembeli;
    private ArrayList<Bucket> daftarBelanja;
    private ArrayList<Integer> jumlahBeli;
    private boolean isMember;
    public Transaksi(String idTransaksi, String namaPembeli, boolean isMember) {
        this.idTransaksi = idTransaksi;
        this.namaPembeli = namaPembeli;
        this.isMember = isMember;
        this.daftarBelanja = new ArrayList<>();
        this.jumlahBeli = new ArrayList<>();
    }
    // method untuk menambahkan barang ke dalam daftar belanja
    public void tambahBarang(Bucket bucket, int jumlah) {
        daftarBelanja.add(bucket);
        jumlahBeli.add(jumlah);
    }
    // method untuk menghapus barang dari daftar belanja berdasarkan index
    public void hapusBarang(int index) {
        if (index >= 0 && index < daftarBelanja.size()) {
            daftarBelanja.remove(index);
            jumlahBeli.remove(index);
        } else {
            System.out.println("Index tidak valid.");
        }
    }
    // method untuk menghitung total belanja sebelum diskon
    public double hitungTotal() {
        double total = 0;
        for (int i = 0; i < daftarBelanja.size(); i++) {
            total += daftarBelanja.get(i).getHarga() * jumlahBeli.get(i);
        }
        return total;
    }
    // method untuk menghitung diskon berdasarkan status member
    public double hitungDiskon() {
        double total = hitungTotal();
        if (isMember) {
            return total * 0.1; // Diskon 10% untuk member
        }
        return 0;
    }
    // method untuk mengurangi stok barang setelah transaksi selesai
    public void kurangiStok() {
        for (int i = 0; i < daftarBelanja.size(); i++) {
            Bucket bucket = daftarBelanja.get(i);
            int jumlah = jumlahBeli.get(i);
            bucket.setStok(bucket.getStok() - jumlah);
        }
    }
    // method untuk menampilkan detail belanja
    public void tampilkanBelanja() {
        System.out.println(" Transaksi ID: " + idTransaksi);
        System.out.println(" Nama Pembeli: " + namaPembeli);
        System.out.println(" Daftar Belanja:");
        for (int i = 0; i < daftarBelanja.size(); i++) {
            Bucket bucket = daftarBelanja.get(i);
            int jumlah = jumlahBeli.get(i);
            System.out.println("- " + bucket.getNama() + " (Jumlah: " + jumlah + ", Harga: " + bucket.getHarga() + ")");
        }
    }
    // getter nya
    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setMember(boolean isMember) {
        this.isMember = isMember;
    }

    public ArrayList<Bucket> getDaftarBelanja() {
        return daftarBelanja;
    }

    public ArrayList<Integer> getJumlahBeli() {
        return jumlahBeli;
    }
}
