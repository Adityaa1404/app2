package freshflower;

class Transaksi {
    private Bucket[] buckets;
    private Customer customer;
    private int[] Qty;
    private int jumlahItem;

    //constructor
    public Transaksi(int kapasitas) {
        this.buckets = new Bucket[kapasitas];
        this.Qty = new int[kapasitas];
        this.jumlahItem = 0;
    }


    public boolean tambahItem(Bucket produk, int qty) {
        int posisi = cariPosisiProduk(produk);

        if (posisi != -1) {
            Qty[posisi] += qty;
            return true;
        }

        if (jumlahItem >= buckets.length) {
            return false;
        }

        buckets[jumlahItem] = produk;
        Qty[jumlahItem] = qty;
        jumlahItem++;
        return true;
    }

    public int getQtyUntukProduk(Bucket produk) {
        int posisi = cariPosisiProduk(produk);
        if (posisi == -1) {
            return 0;
        }
        return Qty[posisi];
    }

    public double hitungTotal() {
        double total = 0;
        for (int i = 0; i < jumlahItem; i++) {
            total += buckets[i].getHargaDasar() * Qty[i];
        }
        return total;
    }

    public void kurangiStokProduk() {
        for (int i = 0; i < jumlahItem; i++) {
            Bucket produk = buckets[i];
            produk.setStok(produk.getStok() - Qty[i]);
        }
    }

    public boolean isKosong() {
        return jumlahItem == 0;
    }

    public int getJumlahItem() {
        return jumlahItem;
    }

    public Bucket getProduk(int index) {
        return buckets[index];
    }

    public int getQty(int index) {
        return Qty[index];
    }

    public void kosongkan() {
        for (int i = 0; i < jumlahItem; i++) {
            buckets[i] = null;
            Qty[i] = 0;
        }
        jumlahItem = 0;
    }

    private int cariPosisiProduk(Bucket produk) {
        for (int i = 0; i < jumlahItem; i++) {
            if (buckets[i] == produk) {
                return i;
            }
        }
        return -1;
    }
}
