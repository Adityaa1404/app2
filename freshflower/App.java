package freshflower;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {

    private static final int MAX_PRODUK = 50;
    private static final int MAX_KERANJANG = 50;

    private static void tambahProdukAwal(Bucket[] daftarBucket, int[] jumlahBucketRef) {
        daftarBucket[jumlahBucketRef[0]++] = new Bucket("Rose Mix", 50000, 20, "M");
        daftarBucket[jumlahBucketRef[0]++] = new Bucket("Lily White", 65000, 15, "L");
        daftarBucket[jumlahBucketRef[0]++] = new Bucket("Tulip Spring", 70000, 10, "L");
    }

    public static void lihatProduk(Bucket[] daftarBucket, int jumlahBucket) {
        if (jumlahBucket == 0) {
            System.out.println("Data produk kosong");
            return;
        }

        System.out.println("Daftar Produk:");
        for (int i = 0; i < jumlahBucket; i++) {
            Bucket produk = daftarBucket[i];
            System.out.println((i + 1) + ". " + produk.getNama() + " | Harga: " + produk.getHargaDasar() + " | Stok: "
                    + produk.getStok());
        }
    }

    public static int pilihIndexProduk(Scanner scanner, int jumlahBucket) {
        if (jumlahBucket == 0) {
            System.out.println("Data produk kosong");
            return -1;
        }

        System.out.print("Pilih nomor produk: ");
        int nomor = scanner.nextInt();

        if (nomor < 1 || nomor > jumlahBucket) {
            System.out.println("Nomor produk tidak valid!");
            return -1;
        }

        return nomor - 1;
    }

    private static void lihatKeranjang(Bucket[] daftarBucket, int[] indexKeranjang, int[] qtyKeranjang, int jumlahKeranjang) {
        if (jumlahKeranjang == 0) {
            System.out.println("Keranjang masih kosong");
            return;
        }

        double total = 0;
        System.out.println("Isi Keranjang:");
        for (int i = 0; i < jumlahKeranjang; i++) {
            Bucket produk = daftarBucket[indexKeranjang[i]];
            double subtotal = produk.getHargaDasar() * qtyKeranjang[i];
            total += subtotal;
            System.out.println((i + 1) + ". " + produk.getNama() + " | Qty: " + qtyKeranjang[i] + " | Subtotal: "
                    + subtotal);
        }
        System.out.println("Total sementara: " + total);
    }

    private static int cariProdukDiKeranjang(int[] indexKeranjang, int jumlahKeranjang, int indexProduk) {
        for (int i = 0; i < jumlahKeranjang; i++) {
            if (indexKeranjang[i] == indexProduk) {
                return i;
            }
        }
        return -1;
    }

    private static void cetakNota(String namaCustomer, boolean member, Bucket[] daftarBucket, int[] indexKeranjang,
            int[] qtyKeranjang, int jumlahKeranjang, double total, double diskon, double totalBayar, double bayar,
            double kembalian) {
        System.out.println("========================================");
        System.out.println("               FRESHFLOWER              ");
        System.out.println("========================================");
        System.out.println("Tanggal : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        System.out.println("Customer: " + namaCustomer);
        System.out.println("Status  : " + (member ? "Member" : "Non-member"));
        System.out.println("----------------------------------------");
        System.out.println("Item");

        for (int i = 0; i < jumlahKeranjang; i++) {
            Bucket produk = daftarBucket[indexKeranjang[i]];
            double subtotal = produk.getHargaDasar() * qtyKeranjang[i];
            System.out.println("- " + produk.getNama() + " x" + qtyKeranjang[i] + " = " + subtotal);
        }

        System.out.println("----------------------------------------");
        System.out.println("Total      : " + total);
        System.out.println("Diskon     : " + diskon);
        System.out.println("Total Bayar: " + totalBayar);
        System.out.println("Bayar      : " + bayar);
        System.out.println("Kembalian  : " + kembalian);
        System.out.println("========================================");
        System.out.println("      Terima kasih sudah berbelanja     ");
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bucket[] daftarBucket = new Bucket[MAX_PRODUK];
        int[] jumlahBucketRef = new int[] { 0 };
        Admin admin = new Admin();
        Customer customer = new Customer();
        int pilih, pilih2;

        tambahProdukAwal(daftarBucket, jumlahBucketRef);

        do {
            System.out.println("SuperApplication");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Owner");
            System.out.println("4. Exit");
            System.out.print("Pilih menu: ");
            pilih = scanner.nextInt();
            switch (pilih) {
                case 1:
                    boolean isAdminLogin = false;
                    do {
                        System.out.print("Username: ");
                        String username = scanner.next();
                        System.out.print("Password: ");
                        String password = scanner.next();
                        if (admin.login(username, password)) {
                            System.out.println("Login berhasil!");
                            isAdminLogin = true;
                            do {

                                System.out.println("Menu Admin");
                                System.out.println("1. Tambah Produk");
                                System.out.println("2. Lihat Produk");
                                System.out.println("3. Ubah Produk");
                                System.out.println("4. Hapus Produk");
                                System.out.println("5. Back");
                                System.out.print("Pilih menu: ");
                                pilih2 = scanner.nextInt();
                                switch (pilih2) {
                                    case 1:
                                        if (jumlahBucketRef[0] >= daftarBucket.length) {
                                            System.out.println("Kapasitas produk penuh");
                                            break;
                                        }

                                        scanner.nextLine();
                                        System.out.print("Nama: ");
                                        String nama = scanner.nextLine();
                                        System.out.print("Harga: ");
                                        int harga = scanner.nextInt();
                                        System.out.print("Stok: ");
                                        int stok = scanner.nextInt();
                                        daftarBucket[jumlahBucketRef[0]] = new Bucket(nama, harga, stok, null);
                                        jumlahBucketRef[0]++;
                                        System.out.println("Produk berhasil ditambahkan");

                                        break;
                                    case 2:
                                        lihatProduk(daftarBucket, jumlahBucketRef[0]);
                                        break;
                                    case 3:
                                        lihatProduk(daftarBucket, jumlahBucketRef[0]);
                                        int indexUbah = pilihIndexProduk(scanner, jumlahBucketRef[0]);
                                        if (indexUbah == -1) {
                                            break;
                                        }

                                        int menuUbah;
                                        do {
                                            System.out.println("Menu Ubah Produk");
                                            System.out.println("1. Ubah nama");
                                            System.out.println("2. Ubah harga");
                                            System.out.println("3. Ubah stok");
                                            System.out.println("4. Back");
                                            System.out.print("Pilih menu: ");
                                            menuUbah = scanner.nextInt();

                                            switch (menuUbah) {
                                                case 1:
                                                    scanner.nextLine();
                                                    System.out.print("Nama baru: ");
                                                    nama = scanner.nextLine();
                                                    daftarBucket[indexUbah].setNama(nama);
                                                    System.out.println("Nama produk berhasil diubah");
                                                    break;
                                                case 2:
                                                    System.out.print("Harga baru: ");
                                                    harga = scanner.nextInt();
                                                    daftarBucket[indexUbah].setHargaDasar(harga);
                                                    System.out.println("Harga produk berhasil diubah");
                                                    break;
                                                case 3:
                                                    System.out.print("Stok baru: ");
                                                    stok = scanner.nextInt();
                                                    daftarBucket[indexUbah].setStok(stok);
                                                    System.out.println("Stok produk berhasil diubah");
                                                    break;
                                                case 4:
                                                    break;
                                                default:
                                                    System.out.println("Pilihan tidak valid!");
                                                    break;
                                            }
                                        } while (menuUbah != 4);
                                        break;
                                    case 4:
                                        lihatProduk(daftarBucket, jumlahBucketRef[0]);
                                        int indexHapus = pilihIndexProduk(scanner, jumlahBucketRef[0]);
                                        if (indexHapus == -1) {
                                            break;
                                        }

                                        for (int i = indexHapus; i < jumlahBucketRef[0] - 1; i++) {
                                            daftarBucket[i] = daftarBucket[i + 1];
                                        }
                                        daftarBucket[jumlahBucketRef[0] - 1] = null;
                                        jumlahBucketRef[0]--;
                                        System.out.println("Produk berhasil dihapus");
                                        break;
                                    case 5:
                                        break;

                                    default:
                                        System.out.println("Pilihan tidak valid!");
                                        break;
                                }
                            } while (pilih2 != 5);
                        } else {
                            System.out.println("Login gagal!");
                        }
                    } while (isAdminLogin == false);
                    break;
                case 2:
                    System.out.println("Apakah anda member? (y/n)");
                    String jawaban = scanner.next();
                    boolean isMember = jawaban.equalsIgnoreCase("y");
                    String namaCustomer;

                    if (jawaban.equals("y")) {
                        // member login
                        boolean isCustomerLogin = false;
                        do {
                            System.out.print("No. Telepon: ");
                            String noTelp = scanner.next();
                            System.out.print("Password: ");
                            String password = scanner.next();
                            if (customer.login(noTelp, password)) {
                                System.out.println("Login berhasil!");
                                isCustomerLogin = true;
                            } else {
                                System.out.println("Login gagal!");
                            }

                        } while (isCustomerLogin == false);

                        namaCustomer = customer.getNama();

                    } else {
                        // non-member login
                        System.out.print("Masukkan nama: ");
                        String nama = scanner.next();
                        System.out.print("Masukkan alamat: ");
                        String alamat = scanner.next();
                        System.out.print("Masukkan no. telepon: ");
                        String noTelp = scanner.next();
                        System.out.print("Masukkan password: ");
                        String password = scanner.next();
                        customer = new Customer(nama, alamat, noTelp, password);
                        System.out.println("Registrasi berhasil!");
                        namaCustomer = customer.getNama();
                    }

                    int[] indexKeranjang = new int[MAX_KERANJANG];
                    int[] qtyKeranjang = new int[MAX_KERANJANG];
                    int jumlahKeranjang = 0;
                    int menuCustomer;

                    do {
                        System.out.println("Menu Customer");
                        System.out.println("1. Lihat Produk");
                        System.out.println("2. Tambah ke Keranjang");
                        System.out.println("3. Lihat Keranjang");
                        System.out.println("4. Checkout & Cetak Nota");
                        System.out.println("5. Back");
                        System.out.print("Pilih menu: ");
                        menuCustomer = scanner.nextInt();

                        switch (menuCustomer) {
                            case 1:
                                lihatProduk(daftarBucket, jumlahBucketRef[0]);
                                break;
                            case 2:
                                if (jumlahBucketRef[0] == 0) {
                                    System.out.println("Produk belum tersedia");
                                    break;
                                }

                                lihatProduk(daftarBucket, jumlahBucketRef[0]);
                                int indexProduk = pilihIndexProduk(scanner, jumlahBucketRef[0]);
                                if (indexProduk == -1) {
                                    break;
                                }

                                System.out.print("Masukkan qty: ");
                                int qty = scanner.nextInt();
                                if (qty <= 0) {
                                    System.out.println("Qty harus lebih dari 0");
                                    break;
                                }

                                Bucket produkDipilih = daftarBucket[indexProduk];
                                int posKeranjang = cariProdukDiKeranjang(indexKeranjang, jumlahKeranjang, indexProduk);
                                int qtyLama = (posKeranjang == -1) ? 0 : qtyKeranjang[posKeranjang];

                                if (qty + qtyLama > produkDipilih.getStok()) {
                                    System.out.println("Qty melebihi stok yang tersedia");
                                    break;
                                }

                                if (posKeranjang != -1) {
                                    qtyKeranjang[posKeranjang] += qty;
                                } else {
                                    if (jumlahKeranjang >= MAX_KERANJANG) {
                                        System.out.println("Keranjang penuh");
                                        break;
                                    }
                                    indexKeranjang[jumlahKeranjang] = indexProduk;
                                    qtyKeranjang[jumlahKeranjang] = qty;
                                    jumlahKeranjang++;
                                }

                                System.out.println("Produk masuk ke keranjang");
                                break;
                            case 3:
                                lihatKeranjang(daftarBucket, indexKeranjang, qtyKeranjang, jumlahKeranjang);
                                break;
                            case 4:
                                if (jumlahKeranjang == 0) {
                                    System.out.println("Keranjang masih kosong");
                                    break;
                                }

                                double total = 0;
                                for (int i = 0; i < jumlahKeranjang; i++) {
                                    Bucket produk = daftarBucket[indexKeranjang[i]];
                                    total += produk.getHargaDasar() * qtyKeranjang[i];
                                }

                                double diskon = isMember ? total * 0.1 : 0;
                                double totalBayar = total - diskon;

                                System.out.println("Total belanja: " + total);
                                System.out.println("Diskon: " + diskon);
                                System.out.println("Total bayar: " + totalBayar);
                                System.out.print("Masukkan nominal bayar: ");
                                double bayar = scanner.nextDouble();

                                if (bayar < totalBayar) {
                                    System.out.println("Uang tidak cukup, checkout dibatalkan");
                                    break;
                                }

                                for (int i = 0; i < jumlahKeranjang; i++) {
                                    Bucket produk = daftarBucket[indexKeranjang[i]];
                                    produk.setStok(produk.getStok() - qtyKeranjang[i]);
                                }

                                double kembalian = bayar - totalBayar;
                                cetakNota(namaCustomer, isMember, daftarBucket, indexKeranjang, qtyKeranjang,
                                        jumlahKeranjang, total, diskon, totalBayar, bayar, kembalian);

                                jumlahKeranjang = 0;
                                System.out.println("Checkout selesai");
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Pilihan tidak valid!");
                                break;
                        }
                    } while (menuCustomer != 5);
                    break;
                case 3:
                    System.out.println("Menu Owner");
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan SuperApplication!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        } while (pilih != 4);

        scanner.close();
    }
}
