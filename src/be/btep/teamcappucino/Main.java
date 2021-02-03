package be.btep.teamcappucino;

import be.btep.teamcappucino.service.ManagerService;
import be.btep.teamcappucino.service.impl.ManagerServiceImpl;
import be.btep.teamcappucino.utility.KeyboardUtility;

public class Main {

    public static void main(String[] args) {

        ManagerService managerService = new ManagerServiceImpl();
        managerService.start();

        KeyboardUtility.KEYBOARD.close();




    }
}
