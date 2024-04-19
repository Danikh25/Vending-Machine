package org.example.VendingMachineImplementation.controller;

import org.example.VendingMachineImplementation.dao.VendingMachinePersistenceException;
import org.example.VendingMachineImplementation.dto.Coins;
import org.example.VendingMachineImplementation.dto.Items;
import org.example.VendingMachineImplementation.service.InsufficientFundsException;
import org.example.VendingMachineImplementation.service.NoItemInInventoryException;
import org.example.VendingMachineImplementation.service.VendingMachineServiceLayer;
import org.example.VendingMachineImplementation.ui.UserIo;
import org.example.VendingMachineImplementation.ui.UserIoImpl;
import org.example.VendingMachineImplementation.ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class VendingMachineController {
    private UserIo io = new UserIoImpl();
    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        BigDecimal inputMoney;
        Map<String, BigDecimal> itemsInStock;

            while (keepGoing) {
                view.displayMenuBanner();
                try {
                    itemsInStock = getMenu();
                    view.displayMenu(itemsInStock); //Display the menu first wihtout selection
                    inputMoney = view.getMoney();  // Ask to insert money

                    int itemNumber = view.getItemSelection(itemsInStock.size()); //Get the selected item number
                    String itemName = new ArrayList<>(itemsInStock.keySet()).get(itemNumber - 1);
                    getItem(itemName, inputMoney); //Check the money and then dispense item
                    keepGoing = false;
                    break;
                } catch (InsufficientFundsException | NoItemInInventoryException |
                         VendingMachinePersistenceException e) {
                    view.displayErrorMessage(e.getMessage());
                    view.displayPleaseTryAgain();
                }
            }
            view.displayExitBanner();
    }
    private Map<String, BigDecimal> getMenu() throws VendingMachinePersistenceException{
        return service.getItemNameAndCostInStock();
    }
    private void getItem(String name, BigDecimal money) throws InsufficientFundsException, NoItemInInventoryException, VendingMachinePersistenceException{
        Items selectedItem = service.getItem(name, money);
        BigDecimal itemCost = selectedItem.getCost();
        Map<Coins, Integer> calculateChange = service.getChangePerCoin(itemCost, money);
        view.displayEnjoyBanner(name);
        view.displayTotalValue(calculateChange);
        view.displayChangeDue(calculateChange);
    }

}
