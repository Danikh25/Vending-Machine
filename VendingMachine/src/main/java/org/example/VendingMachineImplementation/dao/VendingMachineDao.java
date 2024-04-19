package org.example.VendingMachineImplementation.dao;

import org.example.VendingMachineImplementation.dto.Items;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineDao {
    /**
     * Removes the items from the vending machine with the given name(after being sold)
     * </p>
     * Retrieves all items in the inventory, it returns a list of Items object
     * </p>
     * This retrieves the inventory count
     * </p>
     *Retrieves a specific item
     *</p>
     *Retrieves the name and cost of an item currently in stock, it returns a map where the key(name,cost)
     */

    void removeItemFromInventory(String name) throws VendingMachinePersistenceException;

    List<Items> getAllItems() throws VendingMachinePersistenceException;
    int getItemInventory(String name) throws VendingMachinePersistenceException;
    Items getItem(String name) throws VendingMachinePersistenceException;
    Map<String, BigDecimal> getItemNameAndCostInStock() throws VendingMachinePersistenceException;

}
