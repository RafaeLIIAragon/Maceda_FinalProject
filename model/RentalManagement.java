package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RentalManagement {
    private static final String FILE_NAME = "boarders.dat";
    private List<Rental> rentals;

    public RentalManagement() {
        rentals = loadFromFile();
    }

    public List<Rental> getAllRentals() {
        return new ArrayList<>(rentals);
    }

    public boolean roomIsOccupied(String roomNumber) {
        String room = roomNumber.trim();
        return rentals.stream().anyMatch(r -> r.getRoomNumber().equalsIgnoreCase(room));
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
        saveToFile();
    }

    public boolean markAsPaid(int index) {
        if (!isValidIndex(index)) return false;
        rentals.get(index).markAsPaid();
        saveToFile();
        return true;
    }

    public boolean deleteRental(int index) {
        if (!isValidIndex(index)) return false;
        rentals.remove(index);
        saveToFile();
        return true;
    }

    public void replaceAll(List<Rental> newRentals) {
        rentals = new ArrayList<>(newRentals);
        saveToFile();
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < rentals.size();
    }

    @SuppressWarnings("unchecked")
    private List<Rental> loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            Object data = input.readObject();
            if (data instanceof List<?>) return (List<Rental>) data;
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Unable to load saved boarders: " + ex.getMessage());
        }
        return new ArrayList<>();
    }

    private void saveToFile() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            output.writeObject(rentals);
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to save boarder records.", ex);
        }
    }
}