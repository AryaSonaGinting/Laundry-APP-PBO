package laundry.utils;

import laundry.models.LaundryOrder;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseManager {
    private static DatabaseManager instance;
    private ArrayList<LaundryOrder> ordersList;

    private final String NAMA_FILE = "data_laundry.txt";

    private DatabaseManager() {
        ordersList = new ArrayList<>();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void tambahPesanan(LaundryOrder order) {
        ordersList.add(order);
        simpanKeFileTxt();
    }

    public ArrayList<LaundryOrder> getSemuaPesanan() {
        return ordersList;
    }

    private void simpanKeFileTxt() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))) {
            for (LaundryOrder order : ordersList) {
                writer.write(order.getNamaCustomer() + "," + order.getBerat() + "kg,Rp" + order.getTotalHarga());
                writer.newLine();
            }
            System.out.println("Data berhasil disimpan ke " + NAMA_FILE);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }
}