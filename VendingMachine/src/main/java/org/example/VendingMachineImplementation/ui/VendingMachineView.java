package org.example.VendingMachineImplementation.ui;

import org.example.VendingMachineImplementation.dto.Change;
import org.example.VendingMachineImplementation.dto.Coins;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class VendingMachineView {

    private UserIo io;

    public VendingMachineView(UserIo io) {
        this.io = io;
    }
    //Asking the user to insert Money
    public BigDecimal getMoney(){
        return io.readBigDecimal("Please insert the amount in dollars $");
    }

    public void displayMenuBanner(){
        io.print("=== Menu ===");
    }
    //displayMenu takes Map(list of items)  as an argument then we iterate over each entry in the map
    //In each entry it executes a lambda function that take 2 parameters to display them as wanted
    public void displayMenu(Map<String, BigDecimal> itemsInStock){
        AtomicInteger counter = new AtomicInteger(1);
        itemsInStock.forEach((key, value) -> System.out.println(counter.getAndIncrement() + "." + key + ": $" + value));
    }
    public int getItemSelection(int maxAmount){
        int selection = io.readInt("Please select the item you want to buy or 0 to EXIT: ",0, maxAmount);
        if (selection == 0){
            io.print("Good bye!!");
            System.exit(0);
        }
        return selection;
    }
    public void displayEnjoyBanner(String name){
        io.print("Enjoy your " + name + "!");
    }
    public void displayChangeDue(Map<Coins, Integer> calculateChange){
        io.print("Change due in coins: " + calculateChange);
    }
    public void displayTotalValue(Map<Coins, Integer> coinsMap){
        Change change = new Change();
        BigDecimal totalValue = change.calculateTotalValue(coinsMap);
        io.print("Change due: " + totalValue + "$");
    }
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayErrorMessage (String errorMsg) {
        io.print("=== Error ===");
        io.print(errorMsg);
    }

    public void displayPleaseTryAgain() {
        io.print("Please select something else.");
    }
}
