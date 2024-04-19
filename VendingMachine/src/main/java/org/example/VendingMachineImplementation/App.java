package org.example.VendingMachineImplementation;

import org.example.VendingMachineImplementation.controller.VendingMachineController;
import org.example.VendingMachineImplementation.dao.VendingMachineDao;
import org.example.VendingMachineImplementation.dao.VendingMachineDaoImpl;
import org.example.VendingMachineImplementation.service.VendingMachineServiceLayer;
import org.example.VendingMachineImplementation.service.VendingMachineServiceLayerImpl;
import org.example.VendingMachineImplementation.ui.UserIo;
import org.example.VendingMachineImplementation.ui.UserIoImpl;
import org.example.VendingMachineImplementation.ui.VendingMachineView;

public class App {
    public static void main(String[] args) {
        UserIo io = new UserIoImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineDao dao = new VendingMachineDaoImpl();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);
        VendingMachineController controller = new VendingMachineController(view, service);

        controller.run();
    }

}
