package tugasakhirpbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Transaksi implements IDiskon, IDatabase{
    Scanner scanner = new Scanner(System.in);
    //atribut
    String nama;
    String  no_hp;
    String alamat;
    String nama_barang;
    int jumlah_barang; 
    int total_harga;

    String URL = "jdbc:mysql://localhost:3306/toko_elektronik";
    String USER = "root";
    String PASSWORD = "";

    public void SetNama(String nama){
        this.nama = nama;
    }
    public void SetNoHP(String noHP){
        this.no_hp = noHP;
    }
    public void SetAlamat(String Alamat){
        this.alamat = Alamat;
    }
    public void SetNamaBarang(String NamaBarang){
        this.nama_barang = NamaBarang;
    }
    public void SetJumlahBarang(int JumlahBarang){
        this.jumlah_barang = JumlahBarang;
    }
    public void SetTotalHarga(int TotalHarga){
        this.total_harga = TotalHarga;
    }

    //method
    public void memesan (){
        Scanner scanner = new Scanner (System.in);
        System.out.println("masukan Nama");
        String inputnama = scanner.nextLine();
        SetNama(inputnama);
        System.out.println("masukan No HP");
        String inputnohp = scanner.nextLine();
        SetNoHP(inputnohp);
        System.out.println("masukan Alamat");
        String inputalamat = scanner.nextLine();
        SetAlamat(inputalamat);
        System.out.println("masukan Nama Barang");
        String inputnamabarang = scanner.nextLine();
        SetNamaBarang(inputnamabarang);
        System.out.println("masukan Jumlah Barang");
        int inputjumlahbarang = scanner.nextInt();
        SetJumlahBarang(inputjumlahbarang);

        System.out.println("Detail Pesanan");
        System.out.println("Nama Pelanggan : "+nama);
        System.out.println("NO HP : "+no_hp);
        System.out.println("Alamat : " +alamat );
        System.out.println("Nama Barang : " + nama_barang);
        System.out.println("Jumlah Barang : " + jumlah_barang);
        hitungTotal();

    }
    @Override
    public void hitungTotal() {
        Scanner input = new Scanner(System.in);
        String cek=this.nama_barang;
        if( cek.equals("TV")) {
            System.out.println("Apakah Anda Ingin memesan TV bergaransi ?");
            System.out.println("y/n");
            String pilihan = input.nextLine();
            if(pilihan.equals("y")){
                int total = jumlah_barang * 1200000 *95/100;
                SetTotalHarga(total);
                System.out.println("Total Harga : "+total);
            }
            else if(pilihan.equals("n")){
                int total = jumlah_barang * 1000000;
                SetTotalHarga(total);
                System.out.println("Total Harga : "+total);
            }else{
                System.out.println("pilihan tidak valid !");
            }

        } 
        else if(cek.equals("Kulkas")) {
            System.out.println("Apakah Anda Ingin memesan Kulkas bergaransi ?");
            System.out.println("y/n");
            String pilihan = input.nextLine();
            if(pilihan.equals("y")){
                int total = jumlah_barang * 700000 * 95/100;
                SetTotalHarga(total);
                System.out.println("Total Harga : "+total);
            }
            else if(pilihan.equals("n")){
                int total = jumlah_barang * 600000;
                SetTotalHarga(total);
                System.out.println("Total Harga : "+total);
            }else{
                System.out.println("pilihan tidak valid !");
            }
            
        } 
        else if(cek.equals("Setrika")) {
            System.out.println("Apakah Anda Ingin memesan Setrika bergaransi ?");
            System.out.println("y/n");
            String pilihan = input.nextLine();
            if(pilihan.equals("y")){
                int total = jumlah_barang * 300000 * 95/100;
                SetTotalHarga(total);
                System.out.println("Total Harga : "+total);
            }
           else if(pilihan.equals("n")){
                int total = jumlah_barang * 100000;
                SetTotalHarga(total);
                System.out.println("Total Harga : "+total);
            }else{
                System.out.println("pilihan tidak valid !");
            }
            
        } 
        else if(cek.equals("Blender")) {
            int total = jumlah_barang * 150000;
                SetTotalHarga(total);
                System.out.println("Total Harga : "+total);
        } 
        else if(cek.equals("Kipas Angin")) {
            int total = jumlah_barang * 90000;
                SetTotalHarga(total);
                System.out.println("Total Harga : "+total);
        } 
        else{
            System.out.println("Barang yang dipesan Tidak Ada");
        }
    }
    @Override
    public void masukkanData() {
       try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk menyimpan data pelanggan ke database
            String query = "INSERT INTO elektronana (nama_pelanggan, no_hp, alamat, nama_barang, jumlah_barang, total_harga) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Menyetel parameter query dengan nilai-nilai dari objek Pemesanan
            preparedStatement.setString(1, this.nama);
            preparedStatement.setString(2, this.no_hp);
            preparedStatement.setString(3, this.alamat);
            preparedStatement.setString(4, this.nama_barang);
            preparedStatement.setInt(5, this.jumlah_barang);
            preparedStatement.setInt(6, this.total_harga);

            // Eksekusi query
            preparedStatement.executeUpdate();

            // Menutup koneksi
            preparedStatement.close();
            connection.close();

            System.out.println("Data pelanggan berhasil disimpan ke database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     @Override
    public void hapusData() {
        System.out.print("Masukkan nama pelanggan yang ingin dihapus: ");
        String namaPelanggan = scanner.nextLine();

        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk mencari data pelanggan berdasarkan nama
            String queryCari = "SELECT * FROM elektronana WHERE nama_pelanggan = ?";
            PreparedStatement preparedStatementCari = connection.prepareStatement(queryCari);
            preparedStatementCari.setString(1, namaPelanggan);

            // Eksekusi query pencarian
            ResultSet resultSet = preparedStatementCari.executeQuery();

            // Cek apakah data ditemukan
            if (resultSet.next()) {
                // Jika data ditemukan, tampilkan data lama
                System.out.println("Data pelanggan yang akan dihapus:");
                System.out.println("Nama: " + resultSet.getString("nama_pelanggan"));
                System.out.println("No HP: " + resultSet.getString("no_hp"));
                System.out.println("Alamat: " + resultSet.getString("alamat"));
                System.out.println("Nama Barang: " + resultSet.getString("nama_barang"));
                System.out.println("Jumlah Barang: " + resultSet.getString("jumlah_barang"));
                System.out.println("Total Harga: " + resultSet.getString("total_harga"));

                // Konfirmasi pengguna
                System.out.print("Apakah Anda yakin ingin menghapus pesanan ini? (y/n): ");
                String konfirmasi = scanner.nextLine().toLowerCase();

                if (konfirmasi.equals("y")) {
                    // Jika pengguna mengonfirmasi, lakukan penghapusan
                    String queryHapus = "DELETE FROM elektronana WHERE nama_pelanggan = ?";
                    PreparedStatement preparedStatementHapus = connection.prepareStatement(queryHapus);
                    preparedStatementHapus.setString(1, namaPelanggan);


                    // Eksekusi query hapus
                    preparedStatementHapus.executeUpdate();

                    System.out.println("Data pelanggan berhasil dihapus.");
                } else {
                    System.out.println("Penghapusan dibatalkan.");
                }

                // Menutup koneksi
                preparedStatementCari.close();
                connection.close();
            } else {
                System.out.println("Data pelanggan dengan nama " + namaPelanggan + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateData() {
        System.out.print("Masukkan nama pelanggan yang ingin diupdate: ");
        String namaPelanggan = scanner.nextLine();

        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk mencari data pelanggan berdasarkan nama
            String queryCari = "SELECT * FROM elektronana WHERE nama_pelanggan = ?";
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

                // Minta input data baru
                System.out.println("Masukkan data baru:");
                System.out.println("masukan Nama Barang");
                String namabarangBaru = scanner.nextLine();
                SetNamaBarang(namabarangBaru);

                // Input untuk setiap atribut yang diizinkan diubah
                System.out.print("Masukkan jumlah barang baru : ");
                int jumlahBarangBaru = scanner.nextInt();
                SetJumlahBarang(jumlahBarangBaru);
                this.hitungTotal();
                Integer totalHargaBaru = this.total_harga;
                System.out.println("Total Harga Baru : " +totalHargaBaru);

                // Query UPDATE untuk mengubah jumlah barang
                String queryUpdate = "UPDATE elektronana SET nama_barang = ? , jumlah_barang = ?, total_harga = ? WHERE nama_pelanggan = ?";
                PreparedStatement preparedStatementUpdate = connection.prepareStatement(queryUpdate);
                preparedStatementUpdate.setString(1, namabarangBaru);
                preparedStatementUpdate.setInt(2, jumlahBarangBaru);
                preparedStatementUpdate.setInt(3, totalHargaBaru);
                preparedStatementUpdate.setString(4, namaPelanggan);

                // Eksekusi query update
                preparedStatementUpdate.executeUpdate();

                System.out.println("Data pelanggan berhasil diupdate.");

                // Menutup koneksi
                preparedStatementCari.close();
                preparedStatementUpdate.close();
                connection.close();
            } else {
                System.out.println("Data pelanggan dengan nama " + namaPelanggan + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void lihatData() {
        System.out.print("Masukkan Nama Pelanggan yang akan dilihat: ");
        String namaPelanggan = scanner.nextLine();

        try {
            // Membuat koneksi ke database (ganti dengan informasi koneksi Anda)
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query untuk mencari data pelanggan berdasarkan nama
            String queryCari = "SELECT * FROM elektronana WHERE nama_pelanggan = ?";
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
            } else {
                System.out.println("Data pelanggan dengan nama " + namaPelanggan + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
