package model;

public class Penyewaan {
    private final String idSewa;
    private final String namaPenyewa;
    private final Kendaraan kendaraan;
    private final int lamaHari;
    private final long totalBiaya;
    private boolean aktif = true;

    public Penyewaan(
        String idSewa, 
        String namaPenyewa, 
        Kendaraan kendaraan, 
        int lamaHari, 
        long totalBiaya
    ) {
        this.idSewa = idSewa;
        this.namaPenyewa = namaPenyewa;
        this.kendaraan = kendaraan;
        this.lamaHari = lamaHari;
        this.totalBiaya = totalBiaya;
    }

    public String getIdSewa() {
        return idSewa;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }   
    
    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public int getLamaHari() {
        return lamaHari;
    }

    public long getTotalBiaya() {
        return totalBiaya;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void selesai(){
        this.aktif = false;
    }
}
