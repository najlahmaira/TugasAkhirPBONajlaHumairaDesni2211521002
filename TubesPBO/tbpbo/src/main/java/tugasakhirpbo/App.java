package tugasakhirpbo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {   //objek
        BarangElektro barang1 = new BarangElektro();
        BarangGaransi barang2 = new BarangGaransi();
        Transaksi transaksi = new Transaksi();
        Scanner scanner = new Scanner(System.in);
        boolean mulai=false ;
        int pilihan ;
        
        String username = "nana";
        String pass = "nana1234";
        boolean lgn=false;

        Date tanggal = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
        String tanggalTransaksi = dateFormat.format(tanggal);
        
        System.out.println("" + dateFormat.format(tanggal));
        while (!lgn) {
            System.out.print("Silahkan Login ");
            System.out.print("\nMasukkan username: ");
            String inputUsername = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String inputPassword = scanner.nextLine();

            if (login(username, pass, inputUsername, inputPassword)) {
                String captcha = generateCaptcha();
                System.out.println("CAPTCHA: " + captcha);

                System.out.print("Masukkan CAPTCHA: ");
                String inputCaptcha = scanner.nextLine();

                if (checkCaptcha(captcha, inputCaptcha)) {
                    System.out.println("Login berhasil!");
                    lgn=true;
                } else {
                    System.out.println("Login gagal. CAPTCHA salah.");
                }
            } else {
                System.out.println("Login gagal. Periksa kembali username dan password.");
            }
        }
        
        while (!mulai)
            {
                System.out.println("");
                System.out.println("|||||||||||||||||||||||||||||||||||||||||||");
                System.out.println("          TOKO ELEKRONIK NANA       ");
                System.out.println("|||||||||||||||||||||||||||||||||||||||||||");
                System.out.println("1. Tampilkan Daftar Barang ");
                System.out.println("2. Pesan");
                System.out.println("3. Hapus Data");
                System.out.println("4. Update Data ");
                System.out.println("5. Claim Garansi"); 
                System.out.println("6. Lihat Data Pelanggan"); 
                System.out.println("7. Tutup");
                System.out.println("___________________________________________");
                System.out.println("");
                System.out.print("Masukkan Pilihan : ");
                pilihan = scanner.nextInt();
                
                switch (pilihan){ 
                case 1 : 
                barang1.tampilkanBarang();
                barang2.tampilkanBarangGaransi();
                break;

                case 2 :
                transaksi.memesan();
                transaksi.masukkanData();
                break;

                case 3 : 
                transaksi.hapusData();
                break;   

                case 4 :
                transaksi.updateData();
                break;
                
                case 5 : 
                BarangGaransi barang3= new BarangGaransi();
                barang3.claimGaransi();
                break;

                case 6 :
                transaksi.lihatData();
                break;

                case 7 :
                System.out.println("Toko Sudah Tutup.\nTerima kasih !");
                break;
            }
        }
       
    }
    private static String format(Date tanggal) {
        return null;
    }
    private static boolean login(String correctUsername, String correctPassword, String inputUsername, String inputPassword) {
        return correctUsername.equals(inputUsername) && correctPassword.equals(inputPassword);
    }
    private static String generateCaptcha() {
        Random rand = new Random();
        char[] captchaLetters = "abjdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            char randChar = captchaLetters[rand.nextInt(captchaLetters.length)];
            captcha.append(randChar);
        }
        return captcha.toString();
    }
    private static boolean checkCaptcha(String correctCaptcha, String inputCaptcha) {
        return correctCaptcha.equalsIgnoreCase(inputCaptcha);
    }
}
