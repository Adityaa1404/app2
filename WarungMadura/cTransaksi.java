package WarungMadura;

public class cTransaksi {
    private cBarang barang;
    private cPembeli pembeli;
    private int idTransaksi;
    private int jumlah;
    private double totalHarga;

    cTransaksi() {
    }

    public void tambahTransaksi(int i, cBarang b, cPembeli p, int j) {
        idTransaksi = i;
        barang = b;
        pembeli = p;
        jumlah = j;
        totalHarga = barang.getHargaBarang() * jumlah;
    }

    public void tampilTransaksi() {
        System.out.println("Data Transaksi");
        System.out.println("ID Transaksi: " + idTransaksi);
        System.out.println("Nama Barang: " + barang.getNamaBarang());
        System.out.println("Nama Pembeli: " + pembeli.getNama());
        System.out.println("Jumlah: " + jumlah);
        System.out.println("Total Harga: " + totalHarga);
    }
}
