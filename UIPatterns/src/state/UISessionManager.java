
package state;

import java.util.LinkedHashMap;
import java.util.Map;

public class UISessionManager {

    private Map<String, String> reservation;
    private Map<String, String> draft;
    private boolean userAuthenticated;

    public UISessionManager() {
        reservation = new LinkedHashMap<>();
        draft = null;
        userAuthenticated = false;

        reservation.put("resource", "");
        reservation.put("date", "");
        reservation.put("contactEmail", "");
    }

    public void updateField(String field, String value) {
        if (!reservation.containsKey(field)) {
            System.out.println("Unknown form field.");
            return;
        }

        reservation.put(field, value == null ? "" : value);
        showCompleteness();
    }

    public int calculateCompleteness() {
        int completedFields = 0;

        for (String value : reservation.values()) {
            if (value != null && !value.trim().isEmpty()) {
                completedFields++;
            }
        }

        if (reservation.isEmpty()) {
            return 0;
        }

        return (int) Math.round(
            completedFields * 100.0 / reservation.size()
        );
    }

    public void showCompleteness() {
        int completedFields = 0;

        for (String value : reservation.values()) {
            if (value != null && !value.trim().isEmpty()) {
                completedFields++;
            }
        }

        System.out.println(
            "[Progress: " + calculateCompleteness()
            + "%] Fields completed: " + completedFields
            + " of " + reservation.size()
        );

        if (completedFields < reservation.size()) {
            for (Map.Entry<String, String> entry
                    : reservation.entrySet()) {
                if (entry.getValue() == null
                        || entry.getValue().trim().isEmpty()) {
                    System.out.println(
                        "Missing field: " + entry.getKey()
                    );
                    break;
                }
            }
        } else {
            System.out.println("Form complete!");
        }
    }

    public void saveReservation() {
        if (calculateCompleteness() < 100) {
            System.out.println(
                "Please complete the required fields before saving."
            );
            return;
        }

        if (!userAuthenticated) {
            draft = new LinkedHashMap<>(reservation);

            System.out.println(
                "[Lazy Registration] Reservation saved as a "
                + "temporary draft. Please create an account to finish."
            );
            return;
        }

        draft = null;
        showToast("Reservation saved successfully.");
    }

    public void registerQuickly() {
        userAuthenticated = true;

        System.out.println(
            "Quick registration completed. Session active = true"
        );

        if (draft != null) {
            reservation = new LinkedHashMap<>(draft);
            saveReservation();
        }
    }

    public void cancelReservation() {
        reservation.clear();

        reservation.put("resource", "");
        reservation.put("date", "");
        reservation.put("contactEmail", "");

        draft = null;

        System.out.println("Reservation cancelled.");
    }

    public void logout() {
        userAuthenticated = false;
        System.out.println("Session closed.");
    }

    private void showToast(String message) {
        System.out.println("[Toast] " + message);
    }

    public boolean isUserAuthenticated() {
        return userAuthenticated;
    }

    public boolean hasDraft() {
        return draft != null;
    }
}
