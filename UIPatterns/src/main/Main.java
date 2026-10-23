
package main;

import navigation.BreadcrumbNavigator;
import content.ElementCard;
import input.ForgivingValidator;
import state.UISessionManager;

public class Main {

    public static void main(String[] args) {

        
        System.out.println(
            "\n 1. NAVIGATION PATTERN "
        );

        BreadcrumbNavigator navigator = new BreadcrumbNavigator();

        navigator.navigateTo("Reservations");
        navigator.navigateTo("Network Laboratory");

        System.out.println(
            "Current route: " + navigator.getCurrentRoute()
        );

        navigator.changeTab("Equipment");

        System.out.println(
            "Current route: " + navigator.getCurrentRoute()
        );


   
        System.out.println(
            "\n 2. PROGRESSIVE DISCLOSURE "
        );

        ElementCard card = new ElementCard(
            "Main Server",
            "Active",
            "IP: 192.168.1.10 | Port: 8080 | RAM: 4GB/16GB"
        );

        card.showSummary();

        System.out.println(
            "Advanced details visible: " + card.isShowAdvanced()
        );

        System.out.println(
            "User requests advanced details..."
        );

        card.toggleAdvancedDetails();

        System.out.println(
            "Advanced details visible: " + card.isShowAdvanced()
        );


        
        System.out.println(
            "\n 3. FORGIVING FORMAT "
        );

        ForgivingValidator validator = new ForgivingValidator();

        String phone = "+57 (310) 999-8877";
        String date = "23/10/2026";

        System.out.println("User phone: " + phone);
        System.out.println(
            "Normalized phone: " + validator.normalizePhone(phone)
        );

        System.out.println("User date: " + date);
        System.out.println(
            "Normalized date: " + validator.normalizeDate(date)
        );


        
        System.out.println(
            "\n 4. COMPLETENESS METER "
        );

        UISessionManager session = new UISessionManager();

        session.updateField(
            "resource", "Network Laboratory"
        );

        session.updateField(
            "date", "2026-10-23"
        );

        session.updateField(
            "contactEmail", "student@university.edu"
        );


        
        System.out.println(
            "\n 5. LAZY REGISTRATION + TOAST "
        );

        System.out.println(
            "User attempts to save without signing in..."
        );

        session.saveReservation();

        System.out.println(
            "Draft exists: " + session.hasDraft()
        );

        System.out.println(
            "User completes quick registration..."
        );

        session.registerQuickly();

        System.out.println(
            "Draft exists: " + session.hasDraft()
        );
    }
}
