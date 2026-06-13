package freshflowerfinal.model;

public class Owner {
    private String nama = "Owner";
    private String password = "Owner123";

    public Owner() {}   

    public String getNama() {
        return nama;
    }

    public String getPassword() {
        return password;
    }

    public boolean login(String nama, String password) {
        if (this.nama.equals(nama) && this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
