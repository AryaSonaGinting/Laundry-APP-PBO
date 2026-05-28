package laundry.models;

public class CuciSatuan extends LayananLaundry {
    private final double HARGA_PER_POTONG = 10000;

    public CuciSatuan(double jumlahPotong, boolean isExpress) {
        super(jumlahPotong, isExpress);
    }

    @Override
    public double hitungTotalHarga() {
        double total = kuantitas * HARGA_PER_POTONG;
        if (isExpress) {
            total *= 1.5;
        }
        return total;
    }

    @Override
    public String getNamaLayanan() {
        return "Cuci Satuan VIP";
    }
}