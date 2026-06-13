import java.util.ArrayList;
// class untuk mengelola daftar customer
public class CustomerManager {
    private ArrayList<Customer> daftarCustomers;

    public CustomerManager() {
        daftarCustomers = new ArrayList<>();
    }
    // method untuk menambahkan customer ke dalam daftar
    public void tambahCustomer(Customer c) {
        daftarCustomers.add(c);
    }
    // method untuk menampilkan semua customer dalam daftar
    public void tampilkanCustomers() {
        for (Customer c : daftarCustomers) {
            System.out.println(c.toString());
        }
    }
    // method untuk menghapus customer berdasarkan index
    public void hapusIndex(int index) {
        if (index >= 0 && index < daftarCustomers.size()) {
            daftarCustomers.remove(index);
        } else {
            System.out.println("Index tidak valid.");
        }
    }
    // method untuk mencari customer berdasarkan kode
    public Customer cariByKode(String kode) {
        for (Customer c : daftarCustomers) {
            if (c.getKode().equalsIgnoreCase(kode)) {
                return c;
            }
        }
        System.out.println("Customer dengan kode " + kode + " tidak ditemukan.");
        return null;
    }
    // method untuk mengecek apakah index valid
    public boolean indexValid(int i) {
        return i >= 0 && i < daftarCustomers.size();
    }
    // method untuk cek apakah daftar customer kosong
    public boolean kosong() {
        return daftarCustomers.isEmpty();
    }
    // method untuk ambil customer berdasarkan index
    public Customer getIndex(int index) {
        if (index >= 0 && index < daftarCustomers.size()) {
            return daftarCustomers.get(index);
        } else {
            System.out.println("Index tidak valid.");
            return null;
        }
    }


}
