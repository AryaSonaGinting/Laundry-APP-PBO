package laundry.models;

public class CuciKiloan extends LayananLaundry {
    private final double HARGA_PER_KG = 5000;

    public CuciKiloan(double berat, boolean isExpress) {
        super(berat, isExpress);
    }

    @Override
    public double hitungTotalHarga() {
        double total = kuantitas * HARGA_PER_KG;
        if (isExpress) {
            total *= 1.5;
        }
        return total;
    }

    @Override
    public String getNamaLayanan() {
        return "Cuci Kiloan Biasa";
    }
}