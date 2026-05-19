package utils;

import models.LaundryOrder;
import models.ServiceType;

public class PriceCalculator {

    public static double hitungHarga(LaundryOrder order) {
        double hargaPerKg = 0;

        switch (order.getServiceType()) {
            case CUCI: hargaPerKg = 5000; break;
            case CUCI_SETRIKA: hargaPerKg = 7000; break;
            case CUCI_LIPAT: hargaPerKg = 6000; break;
            case SATUAN: hargaPerKg = 10000; break;
        }

        double total = hargaPerKg * order.getBerat();

        // Express +50%
        if (order.isExpress()) {
            total *= 1.5;
        }

        // VIP diskon 10%
        if (order.isVip()) {
            total *= 0.9;
        }

        // Antar jemput
        if (order.isAntarJemput()) {
            total += order.getJarak() * 2000;
        }

        return total;
    }
}