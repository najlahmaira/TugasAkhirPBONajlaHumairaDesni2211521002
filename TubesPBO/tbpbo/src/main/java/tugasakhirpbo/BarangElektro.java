package tugasakhirpbo;

import java.util.ArrayList;

public class BarangElektro {
    public void tampilkanBarang(){
        ArrayList<String> barangElektronik = new ArrayList<>();
        barangElektronik.add ("TV\t\tHarga:RP.1000000");
        barangElektronik.add ("Kulkas\tHarga:RP.600000");
        barangElektronik.add ("Setrika\tHarga:RP.100000");
        barangElektronik.add ("Blender\tHarga:RP.150000");
        barangElektronik.add ("Kipas Angin\tHarga:RP.90000");

        System.out.println("Berikut Daftar Barang Elektronik Yang Tersedia");
        for(String elektro :barangElektronik){
            System.out.println("- "+elektro);
        }
    }
}
