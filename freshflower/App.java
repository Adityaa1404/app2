package freshflower;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        boolean isLogin = false;
        Scanner scanner = new Scanner(System.in);
        Produk produk = null;
        Admin admin = new Admin();
        Customer customer = new Customer();
        int pilih, pilih2 ;
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
                    do {
                        System.out.print("Username: ");
                        String username = scanner.next();
                        System.out.print("Password: ");
                        String password = scanner.next();
                        if (admin.login(username, password)) {
                            System.out.println("Login berhasil!");
                            isLogin = true;
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
                                        System.out.println("Jenis: ");
                                        scanner.nextLine();
                                        String jenis = scanner.nextLine();
                                        System.out.println("Harga: ");
                                        int harga = scanner.nextInt();
                                        System.out.println("Stok: ");
                                        int stok = scanner.nextInt();
                                        produk = new Produk(jenis, harga, stok);

                                        break;
                                    case 2:
                                        if (produk == null) {
                                            System.out.println("Data produk kosong");
                                        } else {
                                            System.out.println(produk.toString());
                                        }
                                        break;
                                    case 3:
                                        if (produk == null) {
                                            System.out.println("Data produk kosong");
                                        } else {
                                            System.out.println(produk.toString());
                                            System.out.println("Jenis: ");
                                            scanner.nextLine();
                                            jenis = scanner.nextLine();
                                            produk.setJenis(jenis);
                                            System.out.println("Harga: ");
                                            harga = scanner.nextInt();
                                            produk.setHarga(harga);
                                            System.out.println("Stok: ");
                                            stok = scanner.nextInt();
                                            produk.setStok(stok);
                                        }
                                        break;
                                    case 4:
                                        if (produk == null) {
                                            System.out.println("Data produk kosong");
                                        } else {
                                            produk = null;
                                            System.out.println("Produk berhasil dihapus");
                                        }
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
                    } while (isLogin == false);
                    break;
                case 2:
                    System.out.println("Apakah anda member? (y/n)");
                    String jawaban = scanner.next();
                    if (jawaban.equals("y")) {
                        // member login
                        do {
                            System.out.println("No. Telepon: ");
                            String noTelp = scanner.next();
                            System.out.println("Password: ");
                            String password = scanner.next();
                            if (customer.login(noTelp, password)) {
                                System.out.println("Login berhasil!");
                                isLogin = true;
                                // masuk menu
                                System.out.println("Menu");
                            } else {
                                System.out.println("Login gagal!");
                            }

                        } while (isLogin == false);

                    } else {
                        // non-member login
                        System.out.println("Masukkan nama: ");
                        String nama = scanner.next();
                        System.out.println("Masukkan alamat: ");
                        String alamat = scanner.next();
                        System.out.println("Masukkan no. telepon: ");
                        String noTelp = scanner.next();
                        System.out.println("Masukkan password: ");
                        String password = scanner.next();
                        customer = new Customer(nama, alamat, noTelp, password);
                        System.out.println("Registrasi berhasil!");
                        // masuk menu
                        System.out.println("Menu");
                    }
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
