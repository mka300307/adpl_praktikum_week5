package view;

import model.Penyewaan;

public class StrukView {
    public void cetak(Penyewaan penyewaan) {
        System.out.println("------------ Struk Penyewaan ------------");
        System.out.println("No Sewa       : " + penyewaan.getIdSewa());
        System.out.println("Nama Penyewa  : " + penyewaan.getNamaPenyewa());
        System.out.println("Kendaraan     : " + penyewaan.getKendaraan().getMerk());
        System.out.println("Plat Nomor    : " + penyewaan.getKendaraan().getPlatNomor());
        System.out.println("Jenis         : " + penyewaan.getKendaraan().getJenis());
        System.out.println("Lama Hari     : " + penyewaan.getLamaHari() + " hari");
        System.out.println("Total Biaya   : " + Format.formatRupiah(penyewaan.getTotalBiaya()));
        System.out.println("-----------------------------------------");
    }
}
