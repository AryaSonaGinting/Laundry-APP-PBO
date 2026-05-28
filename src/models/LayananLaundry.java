package laundry.models;

public abstract class LayananLaundry {
    protected double kuantitas;
    protected boolean isExpress;

    public LayananLaundry(double kuantitas, boolean isExpress) {
        this.kuantitas = kuantitas;
        this.isExpress = isExpress;
    }

    public abstract double hitungTotalHarga();
    public abstract String getNamaLayanan();
}