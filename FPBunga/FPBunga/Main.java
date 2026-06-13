import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Buat objek pusat aplikasi
        Inventory inventory = new Inventory();
        CustomerManager customerManager = new CustomerManager();
        Laporan laporan = new Laporan();
        Scanner scanner = new Scanner(System.in);

        // Isi data awal agar ada produk dan customer untuk dicoba
        initSampleData(inventory, customerManager);

        System.out.println("=== Selamat datang di Aplikasi FreshFLower ===");
        // Login sebelum bisa mengakses menu utama
        Customer currentCustomer = login(scanner, customerManager);

        // Menu utama berjalan sampai user pilih selesai
        boolean running = true;
        while (running) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tampilkan Inventory");
            System.out.println("2. Tambah Produk");
            System.out.println("3. Transaksi");
            System.out.println("4. Tampilkan Laporan");
            System.out.println("5. Selesai");
            System.out.print("Pilih menu: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    // Tampilkan semua produk di inventory
                    inventory.tampilkanInventory();
                    break;
                case "2":
                    // Tambah produk baru ke inventory
                    tambahProduk(scanner, inventory);
                    break;
                case "3":
                    // Jalankan proses transaksi pembelian
                    lakukanTransaksi(scanner, inventory, laporan, currentCustomer);
                    break;
                case "4":
                    // Tampilkan laporan transaksi dan customer
                    laporan.tampilkanSemua(customerManager);
                    break;
                case "5":
                    // Selesai, tampilkan laporan terakhir lalu keluar
                    laporan.tampilkanSemua(customerManager);
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }

        System.out.println("Terima kasih telah sudah mampir ke toko kami!.");
        scanner.close();
    }

    // Tambahkan data sample untuk memudahkan pengujian
    private static void initSampleData(Inventory inventory, CustomerManager customerManager) {
        inventory.tambahBucket(new Bunga("Mawar Merah", 25000, 10, "mawar"));
        inventory.tambahBucket(new Bunga("Melati Putih", 18000, 8, "melati"));
        inventory.tambahBucket(new Bunga("Tulip Kuning", 30000, 5, "tulip"));
    }

    // Method login dan register customer
    private static Customer login(Scanner scanner, CustomerManager customerManager) {
        System.out.println("Login diperlukan sebelum masuk ke menu utama.");
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Pilih: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    // Login dengan kode dan password customer
                    System.out.print("Kode customer: ");
                    String kode = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    Customer customer = customerManager.cariByKode(kode);
                    if (customer != null && customer.getPassword().equals(password)) {
                        System.out.println("Login berhasil. Selamat datang, " + customer.getNama() + "!");
                        return customer;
                    }
                    System.out.println("Login gagal. Kode atau password salah.");
                    break;
                case "2":
                    // Registrasi customer baru
                    System.out.print("Kode baru: ");
                    String newKode = scanner.nextLine();
                    System.out.print("Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Password: ");
                    String newPassword = scanner.nextLine();
                    Customer newCustomer = new Customer(newKode, nama, newPassword);
                    customerManager.tambahCustomer(newCustomer);
                    System.out.println("Registrasi berhasil. Silakan login kembali.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih 1 atau 2.");
            }
        }
    }

    // Method untuk menambahkan produk bunga baru ke inventory
    private static void tambahProduk(Scanner scanner, Inventory inventory) {
        System.out.print("Nama produk: ");
        String nama = scanner.nextLine();
        System.out.print("Harga: ");
        double harga = Double.parseDouble(scanner.nextLine());
        System.out.print("Stok: ");
        int stok = Integer.parseInt(scanner.nextLine());
        System.out.print("Jenis bunga (misal: mawar, melati, tulip): ");
        String jenisBunga = scanner.nextLine();

        Bunga bunga = new Bunga(nama, harga, stok, jenisBunga);
        inventory.tambahBucket(bunga);
        System.out.println("Produk berhasil ditambahkan ke inventory.");
    }

    // Method untuk mengelola proses transaksi pembelian
    private static void lakukanTransaksi(Scanner scanner, Inventory inventory, Laporan laporan, Customer currentCustomer) {
        if (inventory.ukuran() == 0) {
            System.out.println("Inventory kosong. Tidak ada produk untuk dibeli.");
            return;
        }

        Transaksi transaksi = new Transaksi("TRX" + System.currentTimeMillis(), currentCustomer.getNama(), false);

        while (true) {
            inventory.tampilkanInventory();
            System.out.print("Pilih indeks produk yang akan dibeli (mulai dari 0): ");
            int indeks = Integer.parseInt(scanner.nextLine());
            if (!inventory.indexValid(indeks)) {
                System.out.println("Index tidak valid. Silakan coba lagi.");
                continue;
            }

            Bucket bucket = inventory.getBucket(indeks);
            System.out.print("Jumlah beli: ");
            int jumlah = Integer.parseInt(scanner.nextLine());
            if (jumlah <= 0 || jumlah > bucket.getStok()) {
                System.out.println("Jumlah tidak valid atau stok tidak cukup. Silakan coba lagi.");
                continue;
            }

            transaksi.tambahBarang(bucket, jumlah);
            System.out.println("Item berhasil ditambahkan ke keranjang.");
            transaksi.tampilkanBelanja();

            System.out.print("Ada yang ingin dibeli lagi? (ya/tidak): ");
            String beliLagi = scanner.nextLine();
            if (!beliLagi.equalsIgnoreCase("ya")) {
                break;
            }
        }

        if (transaksi.getDaftarBelanja().isEmpty()) {
            System.out.println("Tidak ada produk dalam keranjang. Transaksi dibatalkan.");
            return;
        }

        System.out.print("Member? (ya/tidak): ");
        boolean isMember = scanner.nextLine().equalsIgnoreCase("ya");
        transaksi.setMember(isMember);

        double total = transaksi.hitungTotal();
        double diskon = transaksi.hitungDiskon();
        double bayar = total - diskon;

        System.out.println("\nRingkasan Transaksi:");
        transaksi.tampilkanBelanja();
        System.out.println("Total: Rp " + total);
        System.out.println("Diskon: Rp " + diskon);
        System.out.println("Bayar: Rp " + bayar);

        System.out.print("Konfirmasi pembayaran? (ya/tidak): ");
        if (scanner.nextLine().equalsIgnoreCase("ya")) {
            transaksi.kurangiStok();
            currentCustomer.tambahBelanja(bayar);
            laporan.tambahPendapatan(bayar);
            for (int i = 0; i < transaksi.getDaftarBelanja().size(); i++) {
                Bucket item = transaksi.getDaftarBelanja().get(i);
                int itemJumlah = transaksi.getJumlahBeli().get(i);
                laporan.tambahPemasukanBarang(item.getNama(), item.getHarga() * itemJumlah);
            }
            System.out.println("Pembayaran selesai, terimakasih mas/mbak!");
        } else {
            System.out.println("Transaksi dibatalkan.");
        }
    }
}

