package be.btep.teamcappucino.service.impl;

import be.btep.teamcappucino.database.FakeDatabase;
import be.btep.teamcappucino.database.UserFakeDao;
import be.btep.teamcappucino.model.Customer;
import be.btep.teamcappucino.model.Festival;
import be.btep.teamcappucino.service.FestivalService;
import be.btep.teamcappucino.service.ManagerService;

import java.time.LocalDate;

import static be.btep.teamcappucino.utility.KeyboardUtility.*;

import static  be.btep.teamcappucino.utility.MenuUtility.*;

public class ManagerServiceImpl implements ManagerService {

    private  int chosenFestivalIndex=0;

    private final FakeDatabase fakeDatabase = new FakeDatabase();
    private final UserFakeDao userFakeDao = new UserFakeDao();

    private final FestivalService festivalService  =new FestivalServiceImpl();

    private final static String teamName = " by TEAM CAPPUCINO";
    private final static String projectName = "FESTIVAL MANAGER";
    private final static String wantToContinue = "Do you want to continue? ";


    @Override
    public void chooseFestival(boolean isManager) {
     Festival[] festivals = fakeDatabase.getAllFestivals(userFakeDao);
     Festival[] sortedFestivals = sortFestivals(festivals);
     String[] options = new String[festivals.length];
        for (int i = 0; i < festivals.length ; i++) {

            String name = sortedFestivals[i].getName();

            options[i] = name;
        }
        chosenFestivalIndex = askForChoice(options);
        System.out.println(thickLine());
        System.out.println(thickLine());
        serveFestival(isManager);


    }

    @Override
    public void serveFestival(boolean isManager) {
        String[] options = fakeDatabase.serveFestivalDetails(userFakeDao);
        int chooseDetailIndex = askForChoice(options);

        System.out.println(thickLine());
        switch (chooseDetailIndex){
            case 0:
                festivalService.displayLineUp(isManager,chosenFestivalIndex,fakeDatabase,userFakeDao);
                break;
            case 1:
                festivalService.registerNewAct(chosenFestivalIndex,fakeDatabase,userFakeDao);
                break;
            case 2:
                festivalService.buyTickets(isManager,chosenFestivalIndex,fakeDatabase,userFakeDao);
                break;
            case 3:
                festivalService.viewBalanceSheet(isManager,chosenFestivalIndex,fakeDatabase,userFakeDao);
                break;
            case 4:
                quit();
                break;

        }
        serveFestival(isManager);
    }


    @Override
    public void start() {
        System.out.println(thickLine());
        System.out.println(center(projectName));
        System.out.println(center(teamName));
        System.out.println(thickLine());
        boolean haveAccount = askYorN("Do you have an account?") ;

        if(haveAccount) {
            chooseLoginPage();
        }

        else register();



    }

    private void register() {
        Customer customer = new Customer();
        System.out.println(thickLine());
        System.out.println(thinLine());
        System.out.println(center("Welcome REGISTER Page"));
        System.out.println(thinLine());
        System.out.println(thickLine());

        customer.setName(ask("Enter your name: "));
        customer.setSurname(ask("Enter your surname: "));
        customer.setGender(askForGender("Enter your gender: "));
        customer.setDob(askForFullDate("Enter your birthday:"));
        customer.setAddress(ask("Enter your address: "));
        customer.setEmail(askForEmail("Enter your email address: "));
        customer.setPassword(ask("Enter your password: "));
        boolean successRegister = userFakeDao.addCustomer(customer);

        if (successRegister){
            System.out.println(center("Your registration is successful"));
            System.out.println(thickLine());
            System.out.println(center("You will be redirected to the another Page"));

            chooseLoginPage();
        }


    }
    public void chooseLoginPage(){
        boolean asManager = askYorN("Dou you want to login as a Manager ? ");
        if(asManager)
            loginManager();
        else loginUser();
    }

    public void loginManager() {
        System.out.println(thickLine());
        System.out.println(thinLine());
        System.out.println(center("This Login Page is only for MANAGERS!!"));
        System.out.println(thickLine());
        System.out.println(thinLine());
        System.out.println(center("Welcome to LOGIN Page"));
        System.out.println(thinLine());
        System.out.println(thickLine());
        String email = askForEmail("Enter your email address please: ");
        String password = ask("Enter your password please : ");

        if(userFakeDao.checkManagerForLogin(email,password)){
            homePage(true);
        }else {
            System.out.println("Email or Password is not correct ! ");
            System.out.println("Or may be you are not a Manager :)  ! ");

            if(askYorN("Dou you want to continue as Manager ?"))
                loginManager();
            else{
                if (askYorN("Do you want to continue as a User? "))
                    loginUser();
                else
                    quit();
            }

        }
    }

    public void loginUser() {
        System.out.println(thickLine());
        System.out.println(thinLine());
        System.out.println(center("Welcome to LOGIN Page"));
        System.out.println(thinLine());
        System.out.println(thickLine());
        String email = askForEmail("Enter your email address: ");
        String password = ask("Enter your password: ");

        if(userFakeDao.checkCustomerForLogin(email,password)){
            homePage(false);
        }else {
            System.out.println("Email or Password is not correct ! ");

            if(askYorN(wantToContinue))
                loginUser();
            else
                quit();
        }

    }


    public void homePage(boolean isManager) {
        System.out.println(tildaLine());
        System.out.println(tildaLine());
        System.out.println(center(projectName));
        System.out.println(center(teamName));
        System.out.println(center("*** WELCOME :) ***"));
        System.out.println(center("*** IT IS NICE TO SEE YOU ***"));
        System.out.println(tildaLine());
        System.out.println(tildaLine());

        chooseFestival(isManager);

    }

    public Festival[] sortFestivals(Festival[] festivals) {
        Festival[] sortedFestivals = new Festival[festivals.length];
        int a;

        for (int i = 0; i < festivals.length; i++) {
            int count= 0;
            LocalDate ld= festivals[i].getDays()[0].getDate();
            for (int j = 0 ; j < festivals.length; j++) {
                LocalDate ld1= festivals[j].getDays()[0].getDate();
                a = ld.compareTo(ld1);
                if(a > 0 ){
                    count++;
                }
            }
            sortedFestivals[count] = festivals[i];
        }
        return sortedFestivals;
    }

    public void quit(){

        goodbyePattern();
        System.out.println(thickLine());
        System.out.println(center(projectName));
        System.out.println(center(teamName));
        System.out.println(center("*** GOODBYE :) ***"));
        System.out.println(center("*** SEE YOU LATER ***"));
        System.out.println(thickLine());
        goodbyePattern();
        System.out.println(thickLine());
        System.exit(0);


    }
    public  void goodbyePattern()
    {
        int rows = 5;
        for (int i= 0; i<= rows-1 ; i++)
        {
            for (int j=0; j <i; j++)
            {
                System.out.print(" ");
            }
            for (int k=i; k<=rows-1; k++)
            {
                System.out.print("*" + " ");
            }
            System.out.println("");
        }
        for (int i= rows-1; i>= 0; i--)
        {
            for (int j=0; j< i ;j++)
            {
                System.out.print(" ");
            }
            for (int k=i; k<=rows-1; k++)
            {
                System.out.print("*" + " ");
            }
            System.out.println("");
        }

    }


}
