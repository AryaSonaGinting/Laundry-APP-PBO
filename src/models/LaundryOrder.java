package laundry.models;

public class LaundryOrder {
    private String namaCustomer;
    private double berat;

    private LayananLaundry layanan;

    private boolean express;
    private boolean vip;
    private boolean antarJemput;
    private double jarak;
    private double totalHarga;
    private PaymentMethod metodeBayar;

    public LaundryOrder() {}

    public String getNamaCustomer() { return namaCustomer; }
    public void setNamaCustomer(String namaCustomer) { this.namaCustomer = namaCustomer; }

    public double getBerat() { return berat; }
    public void setBerat(double berat) { this.berat = berat; }

    public LayananLaundry getLayanan() { return layanan; }
    public void setLayanan(LayananLaundry layanan) { this.layanan = layanan; }

    public boolean isExpress() { return express; }
    public void setExpress(boolean express) { this.express = express; }

    public boolean isVip() { return vip; }
    public void setVip(boolean vip) { this.vip = vip; }

    public boolean isAntarJemput() { return antarJemput; }
    public void setAntarJemput(boolean antarJemput) { this.antarJemput = antarJemput; }

    public double getJarak() { return jarak; }
    public void setJarak(double jarak) { this.jarak = jarak; }

    public double getTotalHarga() { return totalHarga; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }

    public PaymentMethod getMetodeBayar() { return metodeBayar; }
    public void setMetodeBayar(PaymentMethod metodeBayar) { this.metodeBayar = metodeBayar; }
}
