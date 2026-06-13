public class Boneka extends Bucket {
    private String jenisBoneka;
    public Boneka(String nama, double harga, int stok, String jenisBoneka){
        super(nama, harga, stok);
        this.jenisBoneka = jenisBoneka;
    }
    private String namaCharacter;
    public String getNamaCharacter() {
        return namaCharacter;
    }
    private String setNamaCharacter(String namaCharacter) {
        this.namaCharacter = namaCharacter;
        return this.namaCharacter;
    }
        
    
    @Override
    public String getDetail() {
        return "Character : " + namaCharacter;
    }
}
