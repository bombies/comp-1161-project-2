

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Bus
 **/
public class Bus implements Comparable<Bus> {
    private String name;
    private int size;
    private int price;
    private ArrayList<Trip> approvedTrips;
    private int level; // 1,2,3 for low,medium, high repectively;
    private int id;
    private static int nextId = 0;
    private Ministry mny;
    protected String tripTypes;

    private int getNextId() {
        return ++nextId;
    }

    public Bus() {
        approvedTrips = new ArrayList<Trip>();
    }

    public Bus(String name, int size, int price, int lev, Ministry mny) {
        approvedTrips = new ArrayList<Trip>();
        this.name = name;
        this.size = size;
        this.price = price;
        this.level = lev;
        this.id = getNextId();
        this.mny = mny;
        tripTypes = "BASICTRANSPORT";

    }

    public int compareTo(Bus other) {
        return this.getName().compareTo(other.getName());
    }

    public boolean available(Date date) {
        boolean retval = true;
        for (Trip t : approvedTrips)
            if (t.getDate().getDay() == date.getDay())
                retval = false;
        return retval;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getLevel() {
        return level;
    }

    public Ministry getMinistry() {
        return mny;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public boolean isSuitable(String type) {
        return tripTypes.contains(type);
    }

    public int getEstimate(String type, int numPassengers, int comfortLevel) {
        return price;
    }

    public boolean canHold(int numPassengers, int comfortLevel) {
        int capacity = (int) (size / mny.getSeparation(comfortLevel));
        return numPassengers <= capacity;

    }


    public void promoteTrips() {
        System.out.println();
        System.out.println("TRIPS ON " + getName());
        System.out.println("===================");
        for (Trip t : approvedTrips)
            System.out.println(t);

    }

    public int reserve(Trip trip, Ministry mny) {
        int retval = -1;
        ApprovalRequest ar = new ApprovalRequest(trip, this);
        int result = mny.checkApproval(ar);
        if (result >= 0) {
            int est = getEstimate(trip.getType(), trip.getNumPeople(), trip.getComfortLevel());
            if (trip.getPlanner().getBudget() >= getEstimate(trip.getType(), trip.getNumPeople(), trip.getComfortLevel())) {
                approvedTrips.add(trip);
                trip.setBus(this);
                retval = result;
            }
        }
        return retval;


    }


    public void promoteTrips(PrintStream outStream) {
        outStream.println("TRIPS ON " + getName());
        outStream.println("===================");
        for (Trip t : approvedTrips)
            outStream.println(t);
        outStream.println("--------------------");

    }

    public ArrayList<Trip> getApprovedTrips() {

        return approvedTrips;
    }


    public String toString() {
        return "ID:" + this.getId() + ";" + this.name + ";#Price:" + this.getPrice() + ";Area:" + this.getSize();

    }

    public String toFile() {
        return "" + this.getId() + ";" + this.name + ";" + this.getPrice() + ";" + this.getSize();

    }


    public static void resetId() {

        nextId = 0;
    }


}