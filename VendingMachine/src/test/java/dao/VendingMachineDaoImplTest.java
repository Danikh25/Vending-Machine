package dao;

import org.example.VendingMachineImplementation.dao.VendingMachineDao;
import org.example.VendingMachineImplementation.dao.VendingMachineDaoImpl;
import org.example.VendingMachineImplementation.dao.VendingMachinePersistenceException;
import org.example.VendingMachineImplementation.dto.Items;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VendingMachineDaoImplTest {

    VendingMachineDao testDao = new VendingMachineDaoImpl("testMachine.txt");

    public VendingMachineDaoImplTest(){

    }
    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }
    @BeforeEach
    public void setUp() {
    }
    @AfterEach
    public void tearDown() {
    }

    @Test
    @DisplayName("Removing an item from inventory test")
    public void testRemoveItem() throws VendingMachinePersistenceException {
            String itemName = "Water";
            int inventoryBefore = testDao.getItemInventory(itemName);
            testDao.removeItemFromInventory(itemName);
            int inventoryAfter = testDao.getItemInventory(itemName);

            assertTrue(inventoryAfter<inventoryBefore,"The inventory of an item should decrease");
            assertEquals(inventoryAfter, inventoryBefore - 1, "");
    }

    @Test
    @DisplayName("Getting an item test")
    public void testGetItem() throws VendingMachinePersistenceException {
        Items snickersClone = new Items("Snickers");
        snickersClone.setCost(new BigDecimal("2.10"));
        snickersClone.setInventory(0);

        //ACT
        Items retrievedItem = testDao.getItem("Snickers");

        //ASSERT
        assertNotNull(retrievedItem, "Item should not be null");
        assertEquals(retrievedItem, snickersClone,"The item retrieved should be snickers");
    }
    @Test
    @DisplayName("Getting an item inventory count test")
    public void testGetItemInventory() throws VendingMachinePersistenceException{
        String itemName = "Snickers";

        //ACT
        int retrievedInventory = testDao.getItemInventory(itemName);

        //ASSERT
        assertEquals(retrievedInventory,0,"There are 0 items of snickers left.");
    }

}
