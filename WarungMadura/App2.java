package WarungMadura;

import java.util.Scanner;

public class App2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        cBarang barang = null;
        cPembeli pembeli = null;
        int pilih = 0, pilih2 = 0;

        do {
            System.out.println("Aplikasi Warung Madura");
            System.out.println("1. Data Barang");
            System.out.println("2. Data Pembeli");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            pilih = scanner.nextInt();
            switch (pilih) {
                case 1:
                    System.out.println("Menu Barang");
                    System.out.println("1.Tambah Barang");
                    System.out.println("2.Lihat Barang");
                    System.out.println("3.Edit Barang");
                    System.out.println("4.Hapus Barang");
                    pilih2 = scanner.nextInt();
                    switch (pilih2) {
                        case 1:
                            // barang = new cBarang("Gula", 12000, 30);
                            System.out.print("Nama= ");
                            scanner.nextLine();
                            String nm = scanner.nextLine();
                            System.out.print("Harga= ");
                            int hr = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Stok= ");
                            int st = scanner.nextInt();
                            scanner.nextLine();
                            barang = new cBarang(nm, hr, st);
                            break;

                        case 2:
                            if (barang == null) {
                                System.out.println("Data Barang kosong");
                            } else {
                                System.out.println(barang.ToString());
                            }
                            break;
                        case 3:
                            System.out.println(barang.ToString());
                            System.out.println("Harga Baru = ");
                            hr = scanner.nextInt();
                            barang.setHargaBarang(hr);
                            break;

                        case 4:
                            System.out.println("Yakin hapus? (Y/N)");
                            System.out.println("Y/N= ");
                            String hapus = scanner.nextLine();

                            if (hapus.equalsIgnoreCase("Y")) {
                                barang = null;
                                System.out.println("Data Barang dihapus");
                            } else
                                System.out.println("Data Barang tidak dihapus");

                        default:
                            System.out.println("tidak valid");
                    }
                    break;
                case 2:
                    System.out.println("Menu Pembeli");
                    break;
                case 3:
                    System.out.println("Keluar");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");

            }
        } while (pilih != 3);
        scanner.close();

    }
}
