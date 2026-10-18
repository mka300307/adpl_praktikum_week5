package view;

import java.util.Locale;

public class Format {
    public static String formatRupiah(long nominal) {
        return String.format(
            Locale.US, 
            "Rp%,d", nominal
        ).replace(',', '.');
    }
}
