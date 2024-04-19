package org.example.VendingMachineImplementation.service;

public class NoItemInInventoryException extends Exception{
    public NoItemInInventoryException(String message) {
        super(message);
    }

    public NoItemInInventoryException(String message, Throwable cause) {
        super(message,cause);
    }
}
