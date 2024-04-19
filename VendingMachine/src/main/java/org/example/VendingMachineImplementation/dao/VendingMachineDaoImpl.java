package org.example.VendingMachineImplementation.dao;

import org.example.VendingMachineImplementation.dto.Items;
import org.example.VendingMachineImplementation.service.NoItemInInventoryException;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class VendingMachineDaoImpl implements VendingMachineDao{

    private final Map <String, Items> items = new HashMap<>();

    public static final String DELIMITER = "::";
    public  final String VENDING_MACHINE_FILE;

    public VendingMachineDaoImpl() {
        VENDING_MACHINE_FILE = "VendingMachine.txt";
    }

    public VendingMachineDaoImpl(String testFile){
        VENDING_MACHINE_FILE = testFile;
    }
    @Override
    public void removeItemFromInventory(String name) throws VendingMachinePersistenceException {
        loadMachine();
        if (items.containsKey(name)){
            int prevInventory = items.get(name).getInventory();
            items.get(name).setInventory(prevInventory - 1);
        } else {
            throw new VendingMachinePersistenceException(" Unknown Error");
        }
        writeMachine();
    }

    @Override
    public List<Items> getAllItems() throws VendingMachinePersistenceException {
        loadMachine();
        return new ArrayList<>(items.values());
    }

    @Override
    public int getItemInventory(String name) throws VendingMachinePersistenceException {
        loadMachine();
        if (items.containsKey(name)) {
            return items.get(name).getInventory();
        } else {
            throw new VendingMachinePersistenceException(" Unknown Error");
        }
    }

    //Returns item or null if there is no item associated with the given item name
    @Override
    public Items getItem(String name) throws VendingMachinePersistenceException {
        loadMachine();
        return items.get(name);
    }

    //Returning a map of the items that are currently in stock
    @Override
    public Map<String, BigDecimal> getItemNameAndCostInStock() throws VendingMachinePersistenceException {
        loadMachine();
        //Each entry in the map represents an item in the machine, with count greater than 0, and collecting the rest into a new map
        return items.entrySet()
                .stream()
                .filter(map -> map.getValue().getInventory() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, map -> map.getValue().getCost()));
    }

    //Transforming memory representation of an object into a format
    private String marshallItem (Items anItem){
        String itemAsText = anItem.getName() + DELIMITER;
        itemAsText += anItem.getCost() + DELIMITER;
        itemAsText += anItem.getInventory();
        return itemAsText;
    }
    // Transforming the object format into memory representation
    private Items unmarshallItem (String itemAsText){
        String [] itemTokens = itemAsText.split("::");
        String name = itemTokens[0];
        Items itemFromFile = new Items(name);
        BigDecimal bigDecimal = new BigDecimal(itemTokens[1]);
        itemFromFile.setCost(bigDecimal);
        itemFromFile.setInventory((Integer.parseInt(itemTokens[2])));
        return itemFromFile;
    }
    private void loadMachine() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VENDING_MACHINE_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load item data into memory.", e);
        }
        String currentLine;
        Items currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }
    private void writeMachine() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDING_MACHINE_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save Item data", e);
        }
        String itemAsText;
        List <Items> itemList = this.getAllItems();
        for (Items currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }
}
