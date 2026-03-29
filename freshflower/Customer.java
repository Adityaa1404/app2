package freshflower;

public class Customer {
    private String nama = "ceci";
    private String alamat;
    private String noTelp = "08123456789";
    private String password = "ceci123";

    // constructor
    public Customer() {
    }
    // constructor dengan parameter
    public Customer(String nama, String alamat, String noTelp, String password) {
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.password = password;
    }

    // setter dan getter
    public String getNama() {
        return this.nama;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public String getNoTelp() {
        return this.noTelp;
    }

    public String getPassword() {
        return this.password;
    }

    public String setNama(String nama) {
        this.nama = nama;
        return this.nama;
    }

    public String setAlamat(String alamat) {
        this.alamat = alamat;
        return this.alamat;
    }

    public String setNoTelp(String noTelp) {
        this.noTelp = noTelp;
        return this.noTelp;
    }

    public String setPassword(String password) {
        this.password = password;
        return this.password;
    }

    // method login
    boolean login(String noTelp, String password) {
        return this.noTelp.equals(noTelp) && this.password.equals(password);
    }

}
