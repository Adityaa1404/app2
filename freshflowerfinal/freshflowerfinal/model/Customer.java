package freshflowerfinal.model;

public class Customer {
    private String idCustomer;
    private String nama;
    private String alamat;
    private String nomorTelepon;
    private boolean isMember;
    private String password;

    public Customer() {
        System.out.println("Data Customer dibuat");
    }

    public Customer(String idCustomer, String nama, String alamat, String nomorTelepon) {
        this.idCustomer = idCustomer;
        this.nama = nama;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
        this.isMember = false;
        this.password = null;
    }

    public Customer(String idCustomer, String nama, String alamat, String nomorTelepon, boolean isMember, String password) {
        this.idCustomer = idCustomer;
        this.nama = nama;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
        this.isMember = isMember;
        this.password = password;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean isMember) {
        this.isMember = isMember;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ID: " + idCustomer + "\tNama: " + nama + "\tAlamat: " + alamat + "\tTelp: " + nomorTelepon + (isMember ? " [MEMBER]" : "");
    }
}
