package laundry.controllers;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import laundry.models.LaundryOrder;
import laundry.utils.DatabaseManager;
import java.util.ArrayList;

public class FormLihatPesanan {

    public void tampilkan() {
        Stage window = new Stage();
        window.setTitle("Daftar Pesanan Laundry");

        TextArea areaTeks = new TextArea();
        areaTeks.setEditable(false);
        areaTeks.setPrefHeight(350);

        ArrayList<LaundryOrder> daftarPesanan = DatabaseManager.getInstance().getSemuaPesanan();

        if (daftarPesanan.isEmpty()) {
            areaTeks.setText("Belum ada pesanan yang masuk.");
        } else {
            StringBuilder teks = new StringBuilder();
            teks.append("=== DAFTAR PESANAN ===\n\n");

            for (int i = 0; i < daftarPesanan.size(); i++) {
                LaundryOrder order = daftarPesanan.get(i);
                teks.append("Pesanan #").append(i + 1).append("\n");
                teks.append("Nama       : ").append(order.getNamaCustomer()).append("\n");
                teks.append("Layanan    : ").append(order.getLayanan().getNamaLayanan()).append("\n");
                teks.append("Berat/Jml  : ").append(order.getBerat()).append("\n");
                teks.append("Status VIP : ").append(order.isVip() ? "Ya" : "Tidak").append("\n");
                teks.append("Metode Bayar: ").append(order.getMetodeBayar()).append("\n");
                teks.append("Total Harga: Rp ").append(order.getTotalHarga()).append("\n");
                teks.append("-----------------------------------\n");
            }
            areaTeks.setText(teks.toString());
        }

        VBox layout = new VBox(10, areaTeks);
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.show();
    }
}