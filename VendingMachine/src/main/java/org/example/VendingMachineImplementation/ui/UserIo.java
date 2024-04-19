package org.example.VendingMachineImplementation.ui;

import java.math.BigDecimal;

public interface UserIo {
    void print(String msg);
    double readDouble(String prompt);
    double readDouble(String prompt, double min, double max);
    float readFloat(String prompt);
    float readFloat(String prompt, float min, float max);
    int readInt(String prompt);
    int readInt(String prompt, int min, int max);
    long readLong(String prompt);
    long readLong(String prompt, long min, long max);

    String readString(String prompt);
    BigDecimal readBigDecimal(String prompt);
}
