package app;

import model.Kendaraan;
import controller.RentalController;

public class Main {
    public static void main(String[] args) {
        RentalController rentalController = new RentalController();
        rentalController.dataAwal(new Kendaraan("B 1234 ABC", "Toyota", "MOBIL", 500000));
        rentalController.dataAwal(new Kendaraan("B 5678 DEF", "Honda", "MOTOR", 200000));   
        rentalController.dataAwal(new Kendaraan("B 9012 GHI", "Suzuki", "MOTOR", 150000));  
        rentalController.menuApp();
    }
}
