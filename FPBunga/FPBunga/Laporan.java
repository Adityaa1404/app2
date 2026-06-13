import java.util.ArrayList;
// class untuk mengelola laporan transaksi
public class Laporan {
    private double totalPendapatan;
    private ArrayList<String> namaBarang;
    private ArrayList<Double> pemasukanBarang;
    public Laporan() {
        this.totalPendapatan = 0;
        this.namaBarang = new ArrayList<>();
        this.pemasukanBarang = new ArrayList<>();
    }
    // method untuk akumulasi pendapatan total
    public void tambahPendapatan(double nilai) {
        this.totalPendapatan += nilai;
    }
    // method untuk cari nama di list, jika sudah ada tambahkan nilai, jika belum buat baru
    public void tambahPemasukanBarang(String nama, double nilai) {
        this.namaBarang.add(nama);
        this.pemasukanBarang.add(nilai);
    }
    // method untuk mengurutkan barang berdasarkan pemasukan tertinggi
    public void urutkanBarang() {
        for (int i = 0; i < namaBarang.size() - 1; i++) {
            for (int j = 0; j < namaBarang.size() - i - 1; j++) {
                if (pemasukanBarang.get(j) < pemasukanBarang.get(j + 1)) {
                    // Tukar pemasukan
                    double tempPemasukan = pemasukanBarang.get(j);
                    pemasukanBarang.set(j, pemasukanBarang.get(j + 1));
                    pemasukanBarang.set(j + 1, tempPemasukan);
                    // Tukar nama barang
                    String tempNama = namaBarang.get(j);
                    namaBarang.set(j, namaBarang.get(j + 1));
                    namaBarang.set(j + 1, tempNama);
                }
            }
        }

    }
    // method untuk print total pendapatan dan rekap per barang berdasarkan pemasukan tertinggi
    public void tampilkanLaporanBarang () {
        System.out.println("Laporan Pemasukan per Barang:");
        for (int i = 0; i < namaBarang.size(); i++) {
            System.out.println((i + 1) + ". " + namaBarang.get(i) + ": Rp " + pemasukanBarang.get(i));
        }
    }
    // method untuk print total belanja per pelanggan berdasarkan data customer manager
    public void tampilkanLaporanCustomer(CustomerManager cm) {
        System.out.println("Laporan Daftar Customer:");
        cm.tampilkanCustomers();
    }
    public void tampilkanTotalPendapatan(CustomerManager cm) {
        tampilkanLaporanBarang();
        tampilkanLaporanCustomer(cm);
    }
    // method untuk menampilkan semua laporan
    public void tampilkanSemua(CustomerManager cm) {
        System.out.println("=== LAPORAN TRANSAKSI ===");
        tampilkanLaporanBarang();
        tampilkanLaporanCustomer(cm);
        System.out.println("Total Pendapatan: Rp " + totalPendapatan);
    }
}

