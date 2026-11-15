package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Rental;

public final class BubbleSort {

    private BubbleSort() {
    }

    // Sorts rentals by date started, oldest to newest.
    public static List<Rental> sortByDateStarted(List<Rental> rentals) {
        if (rentals == null) {
            throw new IllegalArgumentException("Rental list cannot be null.");
        }

        List<Rental> result = new ArrayList<>(rentals);

        for (Rental rental : result) {
            if (rental == null || rental.getDateStarted() == null) {
                throw new IllegalArgumentException("Rental records must have valid starting dates.");
            }
        }

        for (int pass = 0; pass < result.size() - 1; pass++) {
            boolean swapped = false;

            for (int i = 0; i < result.size() - 1 - pass; i++) {
                if (result.get(i).getDateStarted().isAfter(result.get(i + 1).getDateStarted())) {
                    Collections.swap(result, i, i + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break; // already sorted
            }
        }

        return result;
    }
}