package laundry.controllers;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import laundry.models.*;
import laundry.utils.DatabaseManager;

public class FormTambahPesanan {

    public void tampilkan() {
        Stage window = new Stage();
        window.setTitle("Tambah Pesanan Baru");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label lblNama = new Label("Nama Customer:");
        TextField txtNama = new TextField();

        Label lblBerat = new Label("Berat/Jumlah:");
        TextField txtBerat = new TextField();

        Label lblLayanan = new Label("Jenis Layanan:");
        ComboBox<String> cbLayanan = new ComboBox<>();
        cbLayanan.getItems().addAll("Cuci Kiloan Biasa", "Cuci Satuan VIP");
        cbLayanan.setValue("Cuci Kiloan Biasa");

        CheckBox chkExpress = new CheckBox("Express (+50% Harga)");
        CheckBox chkVip = new CheckBox("Member VIP (-10% Harga)");

        Label lblBayar = new Label("Metode Bayar:");
        ComboBox<PaymentMethod> cbBayar = new ComboBox<>();
        cbBayar.getItems().addAll(PaymentMethod.values());
        cbBayar.setValue(PaymentMethod.CASH);

        Button btnSimpan = new Button("Simpan Pesanan");

        btnSimpan.setOnAction(e -> {
            try {
                String nama = txtNama.getText();
                if (nama.isEmpty()) {
                    throw new Exception("Nama pelanggan tidak boleh kosong!");
                }

                double berat = Double.parseDouble(txtBerat.getText());
                if (berat <= 0) {
                    throw new Exception("Berat atau jumlah harus lebih dari 0!");
                }

                LayananLaundry layananTerpilih;
                if (cbLayanan.getValue().equals("Cuci Kiloan Biasa")) {
                    layananTerpilih = new CuciKiloan(berat, chkExpress.isSelected());
                } else {
                    layananTerpilih = new CuciSatuan(berat, chkExpress.isSelected());
                }

                LaundryOrder pesananBaru = new LaundryOrder();
                pesananBaru.setNamaCustomer(nama);
                pesananBaru.setBerat(berat);
                pesananBaru.setLayanan(layananTerpilih);
                pesananBaru.setExpress(chkExpress.isSelected());
                pesananBaru.setVip(chkVip.isSelected());
                pesananBaru.setMetodeBayar(cbBayar.getValue());

                double total = layananTerpilih.hitungTotalHarga();
                if (pesananBaru.isVip()) total *= 0.9;
                pesananBaru.setTotalHarga(total);

                DatabaseManager.getInstance().tambahPesanan(pesananBaru);

                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Berhasil");
                info.setHeaderText(null);
                info.setContentText("Pesanan berhasil disimpan! Total Harga: Rp " + total);
                info.showAndWait();

                window.close();

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Validasi");
                alert.setHeaderText("Input Tidak Valid");
                alert.setContentText("Kolom berat WAJIB diisi dengan angka (contoh: 2.5)");
                alert.showAndWait();
            } catch (Exception ex) {
                // Menangkap error logis lainnya (misal: nama kosong)
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Terdapat Kesalahan");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });

        grid.add(lblNama, 0, 0); grid.add(txtNama, 1, 0);
        grid.add(lblBerat, 0, 1); grid.add(txtBerat, 1, 1);
        grid.add(lblLayanan, 0, 2); grid.add(cbLayanan, 1, 2);
        grid.add(chkExpress, 1, 3);
        grid.add(chkVip, 1, 4);
        grid.add(lblBayar, 0, 5); grid.add(cbBayar, 1, 5);

        VBox layout = new VBox(20, grid, btnSimpan);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 350, 450);
        window.setScene(scene);
        window.show();
    }
}