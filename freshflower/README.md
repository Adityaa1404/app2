# FreshFlower - Aplikasi Toko Bunga

Aplikasi Java berbasis console untuk manajemen toko bunga **FreshFlower**. Program ini mensimulasikan sistem multi-peran (Admin, Customer, Owner) dengan fitur login, manajemen produk, dan registrasi pelanggan.

---

## Struktur Package

```
freshflower/
├── App.java        # Entry point — menu utama & alur program
├── Admin.java      # Kelas Admin dengan autentikasi
├── Customer.java   # Kelas Customer dengan login & registrasi
├── Owner.java      # Kelas Owner (placeholder)
├── Menu.java       # Kelas Menu (placeholder)
└── Produk.java     # Kelas model data produk bunga
```

---

## Deskripsi Kelas

### `App.java`
Entry point aplikasi. Menampilkan menu utama dan mengarahkan alur program sesuai peran yang dipilih:
- **Admin** — login lalu mengelola produk
- **Customer** — login sebagai member atau registrasi baru
- **Owner** — placeholder (belum diimplementasikan)

### `Admin.java`
Kelas yang merepresentasikan admin toko.
| Field | Tipe | Default |
|-------|------|---------|
| `username` | `String` | `"admin"` |
| `password` | `String` | `"admin123"` |

**Method:**
- `login(username, password)` — Memvalidasi kredensial admin.

### `Customer.java`
Kelas yang merepresentasikan pelanggan (member maupun non-member).
| Field | Tipe | Keterangan |
|-------|------|------------|
| `nama` | `String` | Nama pelanggan |
| `alamat` | `String` | Alamat pelanggan |
| `noTelp` | `String` | Nomor telepon (digunakan sebagai username login) |
| `password` | `String` | Password pelanggan |

**Method:**
- `login(noTelp, password)` — Memvalidasi login member berdasarkan nomor telepon dan password.
- Getter & setter untuk semua field.

### `Produk.java`
Kelas model untuk data produk bunga.
| Field | Tipe | Keterangan |
|-------|------|------------|
| `jenis` | `String` | Jenis/nama bunga |
| `harga` | `double` | Harga produk |
| `stok` | `int` | Jumlah stok tersedia |

**Method:**
- `toString()` — Menampilkan informasi produk secara terformat.
- Getter & setter untuk semua field.

### `Owner.java` & `Menu.java`
Kelas kosong, belum diimplementasikan.

---

## Fitur

### Menu Admin
Login menggunakan username dan password, kemudian dapat:
1. **Tambah Produk** — Memasukkan jenis, harga, dan stok produk baru.
2. **Lihat Produk** — Menampilkan detail produk yang sudah ditambahkan.
3. **Ubah Produk** — Mengubah data produk yang sudah ada.
4. **Hapus Produk** — Menghapus data produk.

### Menu Customer
- **Member** — Login menggunakan nomor telepon dan password.
- **Non-member** — Registrasi akun baru dengan mengisi nama, alamat, nomor telepon, dan password.

---

## Cara Menjalankan

1. Pastikan Java sudah terinstal (`java -version`).
2. Kompilasi semua file:
   ```bash
   javac freshflower/*.java
   ```
3. Jalankan aplikasi:
   ```bash
   java freshflower.App
   ```

---

## Kredensial Default

| Peran | Login | Password |
|-------|-------|----------|
| Admin | `admin` | `admin123` |
| Customer (member) | `08123456789` | `ceci123` |

---

## Catatan

- Aplikasi ini masih dalam tahap pengembangan. Kelas `Owner` dan `Menu` belum diimplementasikan.
- Data produk hanya menyimpan satu produk dalam satu waktu (belum menggunakan koleksi/list).
