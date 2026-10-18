package model;


public class Minibus extends Kendaraan {
        private static final long BIAYA_SOPIR_PER_HARI = 150000;

        public Minibus(String platNomor, String merk, long tarifPerHari) {
                super(platNomor, merk, tarifPerHari);
        }

        @Override
        public String getJenis() {
                return "MINIBUS";
        }

        @Override
        public long hitungBiaya(int hari) {
                return (getTarifPerHari() + BIAYA_SOPIR_PER_HARI) * hari;
        }
}
