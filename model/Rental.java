package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Rental implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String contactNo;
    private final LocalDate dateStarted;
    private final String roomNumber;
    private final double monthlyBill;
    private final String emergencyContactNo;
    private final LocalDate dueDate;
    private String paymentStatus;

    public Rental(String name, String contactNo, LocalDate dateStarted,
                  String roomNumber, double monthlyBill, String emergencyContactNo) {
        this.name = name;
        this.contactNo = contactNo;
        this.dateStarted = dateStarted;
        this.roomNumber = roomNumber;
        this.monthlyBill = monthlyBill;
        this.emergencyContactNo = emergencyContactNo;
        this.dueDate = dateStarted.plusMonths(1);
        this.paymentStatus = "Unpaid";
    }

    public String getName() { return name; }
    public String getContactNo() { return contactNo; }
    public LocalDate getDateStarted() { return dateStarted; }
    public String getRoomNumber() { return roomNumber; }
    public double getMonthlyBill() { return monthlyBill; }
    public String getEmergencyContactNo() { return emergencyContactNo; }
    public LocalDate getDueDate() { return dueDate; }
    public String getPaymentStatus() { return paymentStatus; }

    public void markAsPaid() { paymentStatus = "Paid"; }

    public String getFormattedDateStarted() {
        return dateStarted.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String getFormattedDueDate() {
        return dueDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
