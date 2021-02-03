package be.btep.teamcappucino.database;


import be.btep.teamcappucino.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserFakeDao {

    private Customer[] customers = new Customer[10];
    private Artist[] artists =new Artist[30];

    private  SoloArtist[] soloArtists = new SoloArtist[30];
    private  Band[] bands = new Band[30];



    private int indexCustomer = 0;
    private int indexArtist = 0;
    private int indexSoloArtist = 0;
    private int indexBands = 0;


    {
        Customer customer1 = new Customer("admin", "surname1", Gender.MALE, LocalDate.of(2000, 1, 13), "adres1", "admin@post.com", 1L,"password");
        Customer customer2 = new Customer("cust1", "surname2", Gender.MALE, LocalDate.of(1995, 2, 5), "adres2", "email2@post.com", 2L,"password");
        Customer customer3 = new Customer("cust2", "surname3", Gender.FEMALE, LocalDate.of(1998, 11, 2), "adres3", "email3@post.com", 3L,"password");

        customer1.setManager(true);

        customers[indexCustomer++]=customer1;
        customers[indexCustomer++]=customer2;
        customers[indexCustomer++]=customer3;

        Artist artist1 = new Artist("soloArtist1", "surname1", Gender.FEMALE, LocalDate.of(2000, 1, 1), "adres1", "email21@post.com", new BigDecimal("100"));
        Artist artist2 = new Artist("bandArtist1", "surname2", Gender.MALE, LocalDate.of(1995, 2, 5), "adres2", "email22@post.com", new BigDecimal("200"));
        Artist artist3 = new Artist("soloArtist2", "surname3", Gender.FEMALE, LocalDate.of(1998, 11, 2), "adres3", "email23@post.com", new BigDecimal("300"));
        Artist artist4 = new Artist("bandArtist2", "surname4", Gender.FEMALE, LocalDate.of(1990, 12, 8), "adres4", "email24@post.com", new BigDecimal("50"));
        Artist artist5 = new Artist("soloArtist3", "surname5", Gender.MALE, LocalDate.of(2001, 5, 9), "adres5", "email25@post.com", new BigDecimal("150"));
        Artist artist6 = new Artist("soloArtist4", "surname6", Gender.MALE, LocalDate.of(1992, 2, 13), "adres6", "email26@post.com", new BigDecimal("130"));
        Artist artist7 = new Artist("bandArtist3", "surname7", Gender.FEMALE, LocalDate.of(1998, 9, 2), "adres7", "email27@post.com", new BigDecimal("100"));
        Artist artist8 = new Artist("bandArtist4", "surname8", Gender.FEMALE, LocalDate.of(1990, 12, 13), "adres8", "email28@post.com", new BigDecimal("50"));
        Artist artist9 = new Artist("bandArtist5", "surname9", Gender.MALE, LocalDate.of(2001, 8, 9), "adres9", "email29@post.com", new BigDecimal("150"));
        Artist artist10 = new Artist("bandArtist6", "surname10", Gender.MALE, LocalDate.of(2001, 2, 9), "adres10", "email30@post.com", new BigDecimal("150"));

        artists[indexArtist++] = artist1;
        artists[indexArtist++] = artist2;
        artists[indexArtist++] = artist3;
        artists[indexArtist++] = artist4;
        artists[indexArtist++] = artist5;
        artists[indexArtist++] = artist6;
        artists[indexArtist++] = artist7;
        artists[indexArtist++] = artist8;
        artists[indexArtist++] = artist9;
        artists[indexArtist++] = artist10;

        SoloArtist soloArtist1 =new SoloArtist("Music Java",artist1);
        SoloArtist soloArtist2 =new SoloArtist("Music Spring",artist3);
        SoloArtist soloArtist3 =new SoloArtist("Music Hibernate",artist5);
        SoloArtist soloArtist4 =new SoloArtist("Music JUnit",artist6);


      soloArtists[indexSoloArtist++] = soloArtist1;
      soloArtists[indexSoloArtist++] = soloArtist2;
      soloArtists[indexSoloArtist++] = soloArtist3;
      soloArtists[indexSoloArtist++] = soloArtist4;

      Artist[] bandArtists1 = {artist2,artist4,artist7};
      Artist[] bandArtists2 = {artist8,artist9,artist10};
      Band band1 = new Band("Band Java",bandArtists1);
      Band band2 = new Band("Band Spring",bandArtists2);


      bands[indexBands++]=band1;
      bands[indexBands++]=band2;


    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }
    public SoloArtist[] getSoloArtists() {

        return soloArtists;
    }

    public Band[] getBands() {
        return bands;
    }

    public Artist[] getArtists() {
        return artists;
    }

    public void setArtists(Artist[] artists) {
        this.artists = artists;
    }

    public boolean addCustomer(Customer customer){
        if(indexCustomer==customers.length) return  false;
        customers[indexCustomer++] = customer;
        return true;
    }
    public boolean addSoloArtist(SoloArtist soloArtist){
        if(indexSoloArtist==soloArtists.length)
            return  false;
        soloArtists[indexSoloArtist++] = soloArtist;

        return true;
    }
    public boolean addBand(Band band){
        if(indexBands==bands.length)
            return  false;
        bands[indexBands++] = band;
        return true;
    }

    public boolean addArtist(Artist artist){
        if(indexArtist==artists.length)
            return  false;
        artists[indexArtist++] = artist;
        return true;
    }


    public boolean checkCustomerForLogin(String email, String password){
        for (Customer customer : customers) {
            if(customer!=null && customer.getEmail().equals(email) && customer.getPassword().equals(password))
                return  true;
        }
        return false;
    }

    public boolean checkManagerForLogin(String email, String password){
        for (Customer customer : customers) {
            if(customer!=null && customer.getEmail().equals(email) && customer.getPassword().equals(password) && customer.isManager())
                return  true;
        }
        return false;
    }

    public boolean checkTalentName(int choose,String talentName){
        if(choose==0){
            for (SoloArtist soloArtist : soloArtists) {
                if (soloArtist != null && soloArtist.getName().equals(talentName))
                    return true;
            }
            return false;
        }else{
            for (Band band : bands) {
                if (band != null && band.getName().equals(talentName))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

}
