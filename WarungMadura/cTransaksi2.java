package WarungMadura;

public class cTransaksi2 {
    // fields
    private cBarang barang[];
    private cPembeli pembeli;
    private int idTransaksi, jumlah[], idx;
    private double totalHarga[], gTotal;

    // constructor
    cTransaksi2(int n) {
        barang = new cBarang[n];
        jumlah = new int[n];
        totalHarga = new double[n];
        idx = 0;
        gTotal = 0;
    }

    //method untuk menambah transaksi
    public boolean tambahTransaksi(int i, cBarang b, cPembeli p, int j) {
        if (idx >= barang.length) {
            System.out.println("keranjang penuh");
            return false;
            //kembali menu utama
            
        }

        idTransaksi = i;
        barang[idx] = b;
        pembeli = p;
        jumlah[idx] = j;
        totalHarga[idx] = barang[idx].getHargaBarang() * jumlah[idx];
        gTotal += totalHarga[idx];
        idx++;
        return true;
    }

    // method untuk menampilkan transaksi
    public void tampilTransaksi() {
        System.out.println("Data Transaksi");
        System.out.println("ID Transaksi: " + idTransaksi);
        System.out.println("Nama Pembeli: " + pembeli.getNama());
        System.out.println("Daftar Belanja");
        for (int i = 0; i < idx; i++) {
            System.out.print((i + 1) + ". Nama Barang: " + barang[i].getNamaBarang());
            System.out.print("\tHarga Barang: " + barang[i].getHargaBarang());
            System.out.print("\tJumlah: " + jumlah[i]);
            System.out.println("\tTotal Harga: " + totalHarga[i]);
        }
        System.out.println("Grand Total: " + gTotal);
    }
    //batasan transaksi 5 barang
    
}
