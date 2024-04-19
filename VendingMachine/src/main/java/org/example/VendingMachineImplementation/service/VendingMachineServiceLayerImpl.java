package org.example.VendingMachineImplementation.service;

import org.example.VendingMachineImplementation.dao.VendingMachineDao;
import org.example.VendingMachineImplementation.dao.VendingMachinePersistenceException;
import org.example.VendingMachineImplementation.dto.Change;
import org.example.VendingMachineImplementation.dto.Coins;
import org.example.VendingMachineImplementation.dto.Items;

import java.math.BigDecimal;
import java.util.Map;

/**
 * The service layer is between the DAO and controller, it is responsible for the business logic
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{

    private VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }
    //Logic: If the cost is greater than inputMoney throw exception
    @Override
    public void checkMoneyInserted(BigDecimal itemCost, BigDecimal inputMoney) throws InsufficientFundsException {
        if(inputMoney.compareTo(itemCost) < 0){
            throw new InsufficientFundsException("Error: Inserted money is less than item cost, expected: " + itemCost + "$ " + "Actual: " + inputMoney + "$");
        }
    }

    @Override
    public void removeItemFromInventory(String name) throws NoItemInInventoryException, VendingMachinePersistenceException {
        if(dao.getItemInventory(name) > 0){
            dao.removeItemFromInventory(name);
            //Add audit file logic here
        } else {
            throw new NoItemInInventoryException(" Unknown Error");
        }
    }

    @Override
    public Map<String, BigDecimal> getItemNameAndCostInStock() throws VendingMachinePersistenceException {
        return dao.getItemNameAndCostInStock();
    }
    //Logic: Check if the item returns null(exists), then pass item and money inserted to check method, remove the item from iniv
    @Override
    public Items getItem(String name, BigDecimal inputMoney) throws InsufficientFundsException, NoItemInInventoryException, VendingMachinePersistenceException {
        Items selectedItem = dao.getItem(name);
        checkMoneyInserted(selectedItem.getCost(), inputMoney);
        removeItemFromInventory(name);
        return selectedItem;
    }
    //Logic: check the money inserted and the cost of the item
    @Override
    public Map<Coins, Integer> getChangePerCoin(BigDecimal itemCost, BigDecimal inputMoney) throws InsufficientFundsException{
        BigDecimal changeDue = inputMoney.subtract(itemCost);

        checkMoneyInserted(itemCost,inputMoney);

        Change change = new Change();
        change.calculateChange(changeDue);
        return change.getCoins();
    }
}
