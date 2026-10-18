package controller;

import java.util.Locale;
import model.KendaraanRepository;
import model.PenyewaanRepository;
import model.Kendaraan;
import model.Penyewaan;
import view.StrukView;
import view.RentalView;

public class RentalController {
    private final KendaraanRepository kendaraanRepo = new KendaraanRepository();
    private final PenyewaanRepository penyewaanRepo = new PenyewaanRepository();
    private final RentalView rentalView = new RentalView();
    private final StrukView strukView = new StrukView();

    public void dataAwal(Kendaraan kendaraan) {
        kendaraanRepo.tambahKendaraan(kendaraan);
    }

    public void tambahKendaraan() {
        String platNomor = rentalView.bacaTeks("Masukkan plat nomor: ");
        if(platNomor.isEmpty() || kendaraanRepo.cariByPlat(platNomor) != null){
            rentalView.tampilkanPesan("Plat nomor tidak boleh kosong atau sudah ada.");
            return;
        }
        String merk = rentalView.bacaTeks("Masukkan merk: ");
        String jenis = rentalView.bacaTeks("Masukkan jenis: ").trim().toUpperCase(Locale.ROOT);
        if(!jenis.equals("MOTOR") && !jenis.equals("MOBIL")){
            rentalView.tampilkanPesan("Jenis tidak dikenali. Harus MOTOR atau MOBIL.");
            return;
        }   
        long tarifPerHari = rentalView.bacaAngka("Masukkan tarif per hari: ");
        if(tarifPerHari <= 0){
            rentalView.tampilkanPesan("Tarif per hari harus lebih dari 0.");
            return;
        }
        kendaraanRepo.tambahKendaraan(new Kendaraan(platNomor, merk, jenis, tarifPerHari));
        rentalView.tampilkanPesan("Kendaraan berhasil ditambahkan.");
    }

    public void sewaKendaraan() {
        String platNomorUpper = rentalView.bacaTeks("Masukkan plat nomor kendaraan yang ingin disewa: ").toUpperCase();
        Kendaraan kendaraan = kendaraanRepo.cariByPlat(platNomorUpper);
        if (kendaraan == null || !kendaraan.isAktif()) {
            rentalView.tampilkanPesan("Kendaraan tidak tersedia untuk disewa.");
            return;
        }
        String namaPenyewa = rentalView.bacaTeks("Masukkan nama penyewa: ");
        int lamaHari = (int) rentalView.bacaAngka("Masukkan lama sewa (hari): ");
        if (lamaHari < 1) {
            rentalView.tampilkanPesan("Lama sewa minimal 1 hari.");
            return;
        }

        Long totalBiaya = kendaraan.hitungBiayaSewa(lamaHari);
        Penyewaan penyewaan = new Penyewaan(
            penyewaanRepo.buatIdBaru(), 
            namaPenyewa, 
            kendaraan, 
            lamaHari, 
            totalBiaya
        );
        penyewaanRepo.tambahPenyewaan(penyewaan);
        kendaraan.setTersedia(false);
        strukView.cetak(penyewaan);
    }

    public void kembalikanKendaraan() {
        String platNomor = rentalView.bacaTeks("Masukkan plat nomor kendaraan yang dikembalikan: ").toUpperCase();
        Penyewaan penyewaan = penyewaanRepo.cariAktifByPlat(platNomor);
        if (penyewaan == null) {
            rentalView.tampilkanPesan("Tidak ada penyewaan aktif untuk kendaraan dengan plat nomor " + platNomor + ".");
            return;
        }

        penyewaan.selesai();
        penyewaan.getKendaraan().setTersedia(true);
        rentalView.tampilkanPesan("Kendaraan dengan plat nomor " + platNomor + " telah dikembalikan.");
    }

    public void tampilkanDaftarKendaraan() {
        rentalView.tampilkanDaftarKendaraan(kendaraanRepo.getAllKendaraan());
    }
    
    public void menuApp() {
        while (true) {
            int pilihan = (int) rentalView.tampilkanMenu();
            switch (pilihan) {
                case 1:
                    tampilkanDaftarKendaraan();
                    break;
                case 2:
                    tambahKendaraan();
                    break;  
                case 3:
                    sewaKendaraan();
                    break;
                case 4:
                    kembalikanKendaraan();
                    break;
                case 5:
                    rentalView.tampilkanRiwayatPenyewaan(penyewaanRepo.getAllPenyewaan());
                    break;
                case 0:
                    rentalView.tampilkanPesan("Terima kasih telah menggunakan aplikasi Rental Kendaraan.");
                    return;
                default:
                    rentalView.tampilkanPesan("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
}
