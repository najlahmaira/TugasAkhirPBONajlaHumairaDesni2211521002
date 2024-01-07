package tugasakhirpbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BarangGaransi extends BarangElektro {
    public void tampilkanBarangGaransi(){
        ArrayList<String> barangElektronikGaransi = new ArrayList<>();
        barangElektronikGaransi.add ("TV\t\tHarga:RP.1200000");
        barangElektronikGaransi.add ("Kulkas\tHarga:RP.700000");
        barangElektronikGaransi.add ("Setrika\tHarga:RP.300000");

        System.out.println("Berikut Daftar Barang Elektronik Bergaransi Yang Tersedia");
        for(String garansi :barangElektronikGaransi){
            System.out.println("- "+garansi);
        }
    }

    public void claimGaransi(){
        Scanner isi= new Scanner(System.in);
        String URL = "jdbc:mysql://localhost:3306/toko_elektronik";
        String USER = "root";
        String PASSWORD = "";
        System.out.print("Masukkan nama pelanggan yang ingin melakukan claim : ");
        String namaPelanggan = isi.nextLine();

        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk mencari data pelanggan berdasarkan nama
            String queryCari = "SELECT * FROM elektronana WHERE nama_pelanggan = ? AND nama_barang IN ('TV', 'Kulkas', 'Setrika')";
            PreparedStatement preparedStatementCari = connection.prepareStatement(queryCari);
            preparedStatementCari.setString(1, namaPelanggan);

            // Eksekusi query pencarian
            ResultSet resultSet = preparedStatementCari.executeQuery();

            // Cek apakah data ditemukan
            if (resultSet.next()) {
                // Jika data ditemukan, tampilkan data lama dan minta input data baru
                System.out.println("Data pelanggan Ditemukan:");
                System.out.println("Nama: " + resultSet.getString("nama_pelanggan"));
                System.out.println("No HP: " + resultSet.getString("no_hp"));
                System.out.println("Alamat: " + resultSet.getString("alamat"));
                System.out.println("Nama Barang: " + resultSet.getString("nama_barang"));
                System.out.println("Jumlah Barang: " + resultSet.getString("jumlah_barang"));
                System.out.println("Total Harga: " + resultSet.getString("total_harga"));
                preparedStatementCari.close();
                connection.close();
                System.out.println("Claim Garansi anda sedang diproses");
                System.out.println("Akan dikonfirmasi setelah pengecekan barang dan ketersediaan barang kami");
                System.out.println("Terimakasih");
            } else {
                System.out.println("Data pelanggan dengan nama " + namaPelanggan + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
