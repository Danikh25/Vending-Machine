package org.example.VendingMachineImplementation.service;

import org.example.VendingMachineImplementation.dao.VendingMachinePersistenceException;
import org.example.VendingMachineImplementation.dto.Coins;
import org.example.VendingMachineImplementation.dto.Items;

import java.math.BigDecimal;
import java.util.Map;

public interface VendingMachineServiceLayer {
    //Check if money inserted is enough for the item
    void checkMoneyInserted(BigDecimal itemCost, BigDecimal inputMoney) throws InsufficientFundsException;
    //remove an item after being sold, if issue in writing throw persistence error
    void removeItemFromInventory(String name) throws NoItemInInventoryException, VendingMachinePersistenceException;
    //Return a map of keys (Name, cost)
    Map<String , BigDecimal> getItemNameAndCostInStock() throws VendingMachinePersistenceException;
    //Retrieves a specific item and checks if the money inserted is enough or if not in the inventory or problem reading from file
    Items getItem(String name, BigDecimal inputMoney) throws InsufficientFundsException,NoItemInInventoryException, VendingMachinePersistenceException;
    //Calculates the change due
    Map<Coins, Integer> getChangePerCoin(BigDecimal itemCost, BigDecimal inputMoney) throws InsufficientFundsException;
}
