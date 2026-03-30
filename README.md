# FreshFlower - Aplikasi Toko Bunga (Console Java)

FreshFlower adalah aplikasi Java berbasis console untuk simulasi operasional toko bunga.
Program ini mendukung beberapa peran pengguna, manajemen produk, keranjang belanja, checkout, diskon member, dan cetak nota transaksi.

## Gambaran Fitur

1. Multi-peran: Admin, Customer, Owner (placeholder).
2. Login Admin untuk mengelola data produk.
3. Login Customer member atau registrasi cepat non-member.
4. Keranjang transaksi yang dapat menyimpan banyak item Bucket.
5. Checkout dengan validasi stok dan pembayaran.
6. Diskon otomatis 10% untuk member.
7. Cetak nota lengkap beserta tanggal transaksi.

## Struktur Folder

```
freshflower/
|- App.java                 # Entry point dan alur menu utama
|- Admin.java               # Model admin + autentikasi admin
|- Customer.java            # Model customer + autentikasi customer
|- Bucket.java              # Model produk bucket bunga
|- ContainerTransaksi.java  # Container keranjang/transaksi banyak Bucket
|- Owner.java               # Placeholder fitur owner
|- Menu.java                # Placeholder menu terpisah
`- README.md                # Dokumentasi modul freshflower
```

## Penjelasan Class

### App
Class utama yang menjalankan seluruh flow aplikasi:
1. Inisialisasi produk awal.
2. Menampilkan menu utama (Admin/Customer/Owner/Exit).
3. Menangani menu Admin dan Customer.
4. Memproses transaksi hingga cetak nota.

Method penting di dalam App:
1. `tambahProdukAwal(...)`: menambahkan produk default saat aplikasi berjalan.
2. `lihatProduk(...)`: menampilkan daftar Bucket.
3. `pilihIndexProduk(...)`: validasi input pemilihan produk.
4. `lihatKeranjang(...)`: menampilkan isi container transaksi.
5. `cetakNota(...)`: menampilkan ringkasan akhir pembayaran.

### Bucket
Merepresentasikan produk yang dijual.

Field utama:
1. `nama` (String)
2. `hargaDasar` (double)
3. `stok` (int)
4. `size` (String)

Class ini menyediakan getter/setter untuk manipulasi data produk pada menu Admin dan saat checkout.

### ContainerTransaksi
Class container untuk menyimpan banyak objek `Bucket` dalam satu transaksi customer.

Tanggung jawab class:
1. Menyimpan item dan qty dalam struktur array internal.
2. Menambah item baru atau menambah qty item yang sama.
3. Menghitung total belanja dari seluruh item.
4. Mengurangi stok produk saat checkout berhasil.
5. Mengosongkan keranjang setelah checkout.

Method utama:
1. `tambahItem(Bucket produk, int qty)`
2. `getQtyUntukProduk(Bucket produk)`
3. `hitungTotal()`
4. `kurangiStokProduk()`
5. `kosongkan()`

### Admin
Model akun admin dengan kredensial default.

Field default:
1. Username: `admin`
2. Password: `admin123`

Method utama:
1. `login(username, password)`

### Customer
Model data customer member/non-member.

Kemampuan:
1. Menyimpan data nama, alamat, nomor telepon, password.
2. Login customer member memakai no telepon dan password.
3. Dipakai juga untuk menyimpan data registrasi customer non-member.

### Owner dan Menu
Saat ini masih placeholder dan belum memiliki implementasi fitur.

## Alur Program

1. Aplikasi menampilkan menu utama.
2. Jika Admin login berhasil:
   1. Admin bisa tambah, lihat, ubah, dan hapus produk.
3. Jika Customer dipilih:
   1. Tentukan status member/non-member.
   2. Customer memilih produk lalu menambahkan qty ke keranjang.
   3. Keranjang dikelola oleh `ContainerTransaksi`.
   4. Saat checkout, sistem menghitung total, diskon, dan kembalian.
   5. Stok produk dikurangi dan nota dicetak.

## Kredensial Default

| Peran | Login | Password |
|---|---|---|
| Admin | admin | admin123 |
| Customer Member | 08123456789 | ceci123 |

## Cara Menjalankan

1. Pastikan Java JDK sudah terpasang.
2. Cek versi Java:

```bash
java -version
```

3. Kompilasi source code:

```bash
javac freshflower/*.java
```

4. Jalankan aplikasi:

```bash
java freshflower.App
```

## Batasan Saat Ini

1. Penyimpanan data masih in-memory (belum ke database/file).
2. Class Owner dan Menu belum diimplementasikan penuh.
3. Input masih berbasis console sederhana tanpa validasi lanjutan untuk seluruh kasus.

## Rencana Pengembangan

1. Menambahkan fitur riwayat transaksi.
2. Menambahkan laporan penjualan untuk Owner.
3. Menambahkan penyimpanan persisten (file atau database).
4. Meningkatkan validasi input dan penanganan error.

