package com.rmpi.thumber.format;

import java.util.Collections;

public class BitFilter {
    private String filter;

    public BitFilter(String filter) {
        this.filter = filter;
    }

    public boolean filter(int bits) {
        String binary = Integer.toBinaryString(bits);
        if (binary.length() > filter.length())
            return false;
        binary = String.join("", Collections.nCopies(filter.length() - binary.length(), "0")) + binary;
        for (int i = 0; i < filter.length(); i++)
            if (filter.charAt(i) == 'X')
                continue;
            else if (filter.charAt(i) != binary.charAt(i))
                return false;
        return true;
    }

    public int toInteger() {
        return (int) Long.parseLong(filter.replace('X', '0'), 2);
    }
}
