import models.*;
import utils.PriceCalculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<LaundryOrder> orders = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== SISTEM LAUNDRY ===");
            System.out.println("1. Tambah Pesanan");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            pilihan = sc.nextInt();
            sc.nextLine();

            switch (pilihan) {
                case 1 -> tambahPesanan(sc);
                case 2 -> tampilkanPesanan();
            }

        } while (pilihan != 3);
    }

    static void tambahPesanan(Scanner sc) {
        LaundryOrder order = new LaundryOrder();

        System.out.print("Nama Customer: ");
        order.setNamaCustomer(sc.nextLine());

        System.out.print("Berat (kg): ");
        order.setBerat(sc.nextDouble());

        System.out.println("1. Cuci");
        System.out.println("2. Cuci + Setrika");
        System.out.println("3. Cuci + Lipat");
        System.out.println("4. Satuan");

        int pilihan = sc.nextInt();

        switch (pilihan) {
            case 1 -> order.setServiceType(ServiceType.CUCI);
            case 2 -> order.setServiceType(ServiceType.CUCI_SETRIKA);
            case 3 -> order.setServiceType(ServiceType.CUCI_LIPAT);
            case 4 -> order.setServiceType(ServiceType.SATUAN);
        }

        System.out.print("Express (true/false): ");
        order.setExpress(sc.nextBoolean());

        System.out.print("VIP (true/false): ");
        order.setVip(sc.nextBoolean());

        System.out.print("Antar Jemput (true/false): ");
        order.setAntarJemput(sc.nextBoolean());

        if (order.isAntarJemput()) {
            System.out.print("Jarak (km): ");
            order.setJarak(sc.nextDouble());
        }

        // Hitung harga
        double total = PriceCalculator.hitungHarga(order);
        order.setTotalHarga(total);

        // Pembayaran
        System.out.println("Metode Pembayaran:");
        System.out.println("1. CASH");
        System.out.println("2. TRANSFER");
        System.out.println("3. QRIS");

        int bayar = sc.nextInt();

        switch (bayar) {
            case 1 -> order.setMetodeBayar(PaymentMethod.CASH);
            case 2 -> order.setMetodeBayar(PaymentMethod.TRANSFER);
            case 3 -> order.setMetodeBayar(PaymentMethod.QRIS);
        }

        orders.add(order);

        System.out.println("Total Harga: Rp " + total);
        System.out.println("Pesanan berhasil ditambahkan!");
    }

    static void tampilkanPesanan() {
        for (LaundryOrder o : orders) {
            System.out.println("\n---");
            System.out.println("Nama: " + o.getNamaCustomer());
            System.out.println("Layanan: " + o.getServiceType());
            System.out.println("Total: Rp " + o.getTotalHarga());
            System.out.println("Pembayaran: " + o.getMetodeBayar());
        }
    }
}