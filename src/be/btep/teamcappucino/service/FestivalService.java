package be.btep.teamcappucino.service;

import be.btep.teamcappucino.database.FakeDatabase;
import be.btep.teamcappucino.database.UserFakeDao;
import be.btep.teamcappucino.model.Act;
import be.btep.teamcappucino.model.Festival;

public interface FestivalService {
    void registerNewAct( int index, FakeDatabase fakeDatabase, UserFakeDao userFakeDao);
    void registerAct(Act act , int index, FakeDatabase fakeDatabase);
    void setFestival(Festival festival, FakeDatabase fakeDatabase);
    void buyTickets(boolean isManager, int index, FakeDatabase fakeDatabase, UserFakeDao userFakeDao);
    void viewBalanceSheet(boolean isManager, int index, FakeDatabase fakeDatabase, UserFakeDao userFakeDao);
    void displayLineUp(boolean isManager, int index, FakeDatabase fakeDatabase, UserFakeDao userFakeDao);

}
