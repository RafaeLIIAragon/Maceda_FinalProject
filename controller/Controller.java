package controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import model.Rental;
import model.RentalManagement;
import util.BubbleSort;

public class Controller {
    private static final String PHONE_PATTERN = "\\d{11}";

    private final RentalManagement management;

    public Controller(RentalManagement management) {
        this.management = management;
        sortStoredRentals(); // sort any previously saved data
    }

    public void addBoarder(String name, String contactNo, String dateStarted,
                           String roomNumber, String monthlyBill, String emergencyContactNo) {
        requireText(name, "Name");
        requireText(contactNo, "Contact number");
        requireText(dateStarted, "Date started");
        requireText(roomNumber, "Room number");
        requireText(monthlyBill, "Monthly bill");
        requireText(emergencyContactNo, "Emergency contact number");

        LocalDate startDate = parseStartDate(dateStarted);
        double bill = parseBill(monthlyBill);

        requirePhone(contactNo, "contact number");
        requirePhone(emergencyContactNo, "emergency contact number");

        if (management.roomIsOccupied(roomNumber)) {
            throw new IllegalArgumentException("That room number is already assigned.");
        }

        management.addRental(new Rental(name.trim(), contactNo.trim(), startDate,
                roomNumber.trim(), bill, emergencyContactNo.trim()));
        sortStoredRentals();
    }

    // The stored list is always sorted, so table rows match the stored indexes.
    public List<Rental> getBoardersSortedByDate() {
        return management.getAllRentals();
    }

    public boolean markAsPaid(int index) {
        return management.markAsPaid(index);
    }

    public boolean deleteBoarder(int index) {
        return management.deleteRental(index);
    }

    private void sortStoredRentals() {
        management.replaceAll(BubbleSort.sortByDateStarted(management.getAllRentals()));
    }

    private LocalDate parseStartDate(String text) {
        LocalDate date;
        try {
            date = LocalDate.parse(text.trim());
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Date Started must use YYYY-MM-DD format.");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date Started cannot be in the future.");
        }
        return date;
    }

    private double parseBill(String text) {
        double bill;
        try {
            bill = Double.parseDouble(text.trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Monthly Bill must be a valid number.");
        }
        if (!Double.isFinite(bill) || bill <= 0) {
            throw new IllegalArgumentException("Monthly Bill must be greater than zero.");
        }
        return bill;
    }

    private void requirePhone(String value, String label) {
        if (!value.trim().matches(PHONE_PATTERN)) {
            throw new IllegalArgumentException(
                    "The " + label + " must be exactly 11 digits (numbers only).");
        }
    }

    private void requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " is required.");
        }
    }
}