package freshflower;

class Bucket {

    // fields
    private String nama;
    private double hargaDasar;
    private int stok;
    private String size;

    // constructor
    public Bucket(String nama, double hargaDasar, int stok, String size) {
        this.nama = nama;
        this.hargaDasar = hargaDasar;
        this.stok = stok;
        this.size = size;
    }

    //setter getters
    public String getNama() {
        return nama;
    }


    public int getStok() {
        return stok;
    }

    public String getSize() {
        return size;
    }
    public double getHargaDasar() {
        return hargaDasar;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setHargaDasar(double hargaDasar) {
        this.hargaDasar = hargaDasar;
    }
    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Bucket [nama=" + nama + ", hargaDasar=" + hargaDasar + ", stok=" + stok  + "]";
    }
}


