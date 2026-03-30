package freshflower;

class ContainerTransaksi {
    private Bucket[] daftarProduk;
    private int[] daftarQty;
    private int jumlahItem;

    public ContainerTransaksi(int kapasitas) {
        this.daftarProduk = new Bucket[kapasitas];
        this.daftarQty = new int[kapasitas];
        this.jumlahItem = 0;
    }

    public boolean tambahItem(Bucket produk, int qty) {
        int posisi = cariPosisiProduk(produk);

        if (posisi != -1) {
            daftarQty[posisi] += qty;
            return true;
        }

        if (jumlahItem >= daftarProduk.length) {
            return false;
        }

        daftarProduk[jumlahItem] = produk;
        daftarQty[jumlahItem] = qty;
        jumlahItem++;
        return true;
    }

    public int getQtyUntukProduk(Bucket produk) {
        int posisi = cariPosisiProduk(produk);
        if (posisi == -1) {
            return 0;
        }
        return daftarQty[posisi];
    }

    public double hitungTotal() {
        double total = 0;
        for (int i = 0; i < jumlahItem; i++) {
            total += daftarProduk[i].getHargaDasar() * daftarQty[i];
        }
        return total;
    }

    public void kurangiStokProduk() {
        for (int i = 0; i < jumlahItem; i++) {
            Bucket produk = daftarProduk[i];
            produk.setStok(produk.getStok() - daftarQty[i]);
        }
    }

    public boolean isKosong() {
        return jumlahItem == 0;
    }

    public int getJumlahItem() {
        return jumlahItem;
    }

    public Bucket getProduk(int index) {
        return daftarProduk[index];
    }

    public int getQty(int index) {
        return daftarQty[index];
    }

    public void kosongkan() {
        for (int i = 0; i < jumlahItem; i++) {
            daftarProduk[i] = null;
            daftarQty[i] = 0;
        }
        jumlahItem = 0;
    }

    private int cariPosisiProduk(Bucket produk) {
        for (int i = 0; i < jumlahItem; i++) {
            if (daftarProduk[i] == produk) {
                return i;
            }
        }
        return -1;
    }
}
