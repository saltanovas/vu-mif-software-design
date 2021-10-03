package vu.mif.lazdauskas.matas.softwaredesign.domain.helpers;

import java.util.Arrays;

public class StringUtil {

    public int firstInSet(String str, String set) {
        int pos = 0;

        for (char c : str.toCharArray()) {
            if (set.indexOf(c) >= 0) return pos;
            ++pos;
        }

        return -1;
    }

    public boolean isAlphanumericOrSpecial(String str, String specialChars) {
        return str.toLowerCase().chars().allMatch(c -> Character.isLetterOrDigit(c) || specialChars.indexOf(c) != -1);
    }

    public boolean charNotFirstOrLastOrConsecutive(String str, String c) {
        return !str.startsWith(c)
                && !str.endsWith(c)
                && Arrays.stream(str.split("[" + c + "]")).allMatch(x -> x.length() != 0);
    }
}
