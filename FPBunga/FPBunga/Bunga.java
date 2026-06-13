public class Bunga extends Bucket {
    private String jenisBunga;
    
    public Bunga(String nama, double harga, int stok, String jenisBunga) {
        super(nama, harga, stok);
        this.jenisBunga = jenisBunga;
    }
    
    public String getJenisBunga() {
        return jenisBunga;
    }
    
    public void setJenisBunga(String jenisBunga) {
        this.jenisBunga = jenisBunga;
    }
    
    @Override
    public String getDetail() {
        return "Jenis bunga: " + jenisBunga;
    }
}