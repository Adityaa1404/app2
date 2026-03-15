package WarungMadura;

public class cPembeli {
    // data
    private String namaPembeli;
    private int idPembeli;
    private String alamatPembeli;

    cPembeli() {
        System.out.println("Data Pembeli dibuat");
    }

    cPembeli(String n, int i, String a) {
        namaPembeli = n;
        idPembeli = i;
        alamatPembeli = a;
        System.out.printf("Data Pembeli %s dibuat\n", namaPembeli);
    }

    // setter
    public void setAlamatPembeli(String a) {
        alamatPembeli = a;
    }

    // getter
    public String getNama() {
        return namaPembeli;
    }

    public int getId() {
        return idPembeli;
    }

    public String getAlamat() {
        return alamatPembeli;
    }

    public String ToString() {
        return "Nama Pembeli: " + namaPembeli + "\nID Pembeli: " + idPembeli + "\nAlamat Pembeli: " + alamatPembeli;
    }
}
