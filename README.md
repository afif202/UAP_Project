# Sistem Manajemen Hotel

Aplikasi berbasis Java untuk mengelola data tamu hotel secara grafis. Aplikasi ini memiliki fitur untuk menambah tamu, mencari data tamu, melakukan pembayaran, dan mengatur proses checkout. Selain itu, aplikasi ini juga dapat menampilkan gambar kamar secara dinamis dan mengubah warna latar berdasarkan tipe kamar yang dipilih.

## Fitur

- Menambah tamu baru dengan data seperti Nama, ID (NIK), Tipe Kamar, dan Jumlah Hari Menginap.
- Mencari data tamu berdasarkan Nama atau ID.
- Menampilkan dan memperbarui status tamu (contoh: "Reserved", "Attend", "Clear").
- Menampilkan gambar kamar secara dinamis berdasarkan tipe kamar yang dipilih.
- Menghapus data tamu atau membatalkan reservasi.

## Teknologi yang Digunakan

- **Java Swing**: Untuk membangun antarmuka grafis.
- **GridBagLayout**: Untuk tata letak yang fleksibel dan responsif.
- **JTable**: Untuk menampilkan dan mengelola data tamu.
- **BufferedImage/ImageIO**: Untuk memuat dan menampilkan gambar kamar secara dinamis.

## Cara Kerja

### Input Data:
- **Nama**: Masukkan nama tamu.
- **ID (NIK)**: Masukkan NIK tamu yang terdiri dari 16 digit unik.
- **Tipe Kamar**: Pilih tipe kamar: `Standard`, `Deluxe`, atau `Suite`.
- **Hari**: Masukkan jumlah hari tamu menginap.

### Tombol:
- **Add Guest**: Menambahkan tamu baru setelah memvalidasi data input.
- **Make Payment**: Memperbarui status tamu yang dipilih menjadi `Attend`.
- **Search**: Mencari data tamu berdasarkan Nama atau ID.
- **Checkout**: Memperbarui status tamu yang dipilih menjadi `Clear`.
- **Cancel**: Menghapus data tamu yang dipilih dari sistem.

### Pembaruan Dinamis:
- Gambar kamar akan dimuat secara otomatis berdasarkan tipe kamar yang dipilih.
- Warna latar akan berubah berdasarkan tipe kamar:
  - **Standard**: Biru Muda
  - **Deluxe**: Hijau Muda
  - **Suite**: Peach Muda

### Validasi:
- Memastikan semua kolom diisi dengan benar.
- ID harus terdiri dari 16 digit.
- Menghindari penggunaan ID yang sama dalam sistem.

## Struktur Folder

```plaintext
HotelManagementSystem/
├── src/
│   ├── images/
│   │   ├── reguler.jpeg   # Gambar untuk kamar Standard
│   │   ├── deluxe.jpeg    # Gambar untuk kamar Deluxe
│   │   ├── suite.jpeg     # Gambar untuk kamar Suite
│   └── HotelManagementSystem.java
└── README.md

```
## Contribute
-Fork this file
-Beritahu kami jika ingin di add
