public class Customer {
    // class untuk merepresentasikan data customer
    private String kode;
    private String nama;
    private String password;
    private double totalBelanja;

    public Customer(String kode, String nama, String password) {
        this.kode = kode;
        this.nama = nama;
        this.password = password;
        this.totalBelanja = 0.0;
    }
    public String getKode () {
        return kode;
    }
    public String getNama () {
        return nama;
    }
    public String getPassword () {
        return password;
    }
    public double getTotalBelanja () {
        return totalBelanja;
    }
    public void setNama (String nama) {
        this.nama = nama;
    }
    public void setKode (String kode) {
        this.kode = kode;
    }
    public void setPassword (String password) {
        this.password = password;
    }
    public void tambahBelanja (double jumlah) {
        this.totalBelanja += jumlah;
    }
    @Override
    public String toString() {
        return "Data Customer{" +
                "kode='" + kode + '\'' +
                ", nama='" + nama + '\'' +
                ", password='" + password + '\'' +
                ", totalBelanja=" + totalBelanja +
                '}';
    }
}
