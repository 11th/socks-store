package com.eleventh.socks.model;

import java.util.Arrays;
import java.util.Optional;

public enum Operation {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal");

    private final String value;

    Operation(String value) {
        this.value = value;
    }

    public static Optional<Operation> fromValue(String value) {
        return Arrays.stream(values())
                .filter(bl -> bl.value.equalsIgnoreCase(value))
                .findFirst();
    }
}
