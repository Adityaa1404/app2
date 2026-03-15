package JavaSelfPace;

import java.util.Scanner;

public class Main {
    // fields
    private static String namaToko = "iCafe";
    static String alamatToko = "Jl. Merdeka No. 123";

    // methods
    static void sambut(String nama, String namaToko) {
        System.out.printf("Selamat datang %s!\n", nama);
        System.out.printf("Selamat datang di %s!\n", namaToko);
    }

    // main method
    public static void main(String[] args) {
        // fields
        Scanner scanner = new Scanner(System.in);
        boolean login = false;
        String nama = null;

        do {
            System.out.print("Masukkan nama Anda: ");
            nama = scanner.nextLine();
            if (nama.toLowerCase().equals("aan")) {
                login = true;
                sambut(nama, namaToko);
            }
        } while (login == false);
        scanner.close();
    }
}
