package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        if (a1 == null || a2 == null) return 0;

        // Nach Nachname
        int lastNameCompare = safeCompareIgnoreCase(a1.getLastname(), a2.getLastname());
        if (lastNameCompare != 0) return lastNameCompare;

        // Nach Vorname
        int firstNameCompare = safeCompareIgnoreCase(a1.getFirstname(), a2.getFirstname());
        if (firstNameCompare != 0) return firstNameCompare;

        // Nach Stadt
        int cityCompare = safeCompareIgnoreCase(a1.getCity(), a2.getCity());
        if (cityCompare != 0) return cityCompare;

        // Nach Email
        int emailCompare = safeCompareIgnoreCase(a1.getEmail(), a2.getEmail());
        if (emailCompare != 0) return emailCompare;

        // Nach Telefonnummer
        int phoneCompare = safeCompare(a1.getPhonenumber(), a2.getPhonenumber());
        if (phoneCompare != 0) return phoneCompare;

        // Nach Registrierungsdatum
        if (a1.getRegistrationDate() == null || a2.getRegistrationDate() == null) return 0;
        return a1.getRegistrationDate().compareTo(a2.getRegistrationDate());
    }

    // Hilfsmethoden für null-sichere Vergleiche
    private int safeCompareIgnoreCase(String s1, String s2) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null) return -1;
        if (s2 == null) return 1;
        return s1.compareToIgnoreCase(s2);
    }

    private int safeCompare(String s1, String s2) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null) return -1;
        if (s2 == null) return 1;
        return s1.compareTo(s2);
    }
}
