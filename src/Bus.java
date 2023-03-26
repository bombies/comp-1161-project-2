

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
    private final ArrayList<Trip> approvedTrips;
    private int level; // 1,2,3 for low,medium, high respectively;
    private int id;
    private static int nextId = 0;
    private Ministry mny;
    protected String tripTypes;

    private int getNextId() {
        return ++nextId;
    }

    /**
     * Default constructor for the Bus class
     */
    public Bus() {
        approvedTrips = new ArrayList<>();
    }

    /**
     * Create a new general purpose bus
     * @param name The name of the bus
     * @param size The capacity of the bus
     * @param price The price to take the bus
     * @param lev The comfort level of the bus
     * @param mny The ministry controlling the bus
     */
    public Bus(String name, int size, int price, int lev, Ministry mny) {
        approvedTrips = new ArrayList<>();
        this.name = name;
        this.size = size;
        this.price = price;
        this.level = lev;
        this.id = getNextId();
        this.mny = mny;
        tripTypes = "BASICTRANSPORT";

    }

    public int compareTo(Bus other) {
        return this.getId() - other.getId();
    }

    public boolean available(Date date) {
        boolean retval = true;
        for (Trip t : approvedTrips)
            if (t.getDate().getDay() == date.getDay()) {
                retval = false;
                break;
            }
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

    /**
     * The method used to edit a bus in the system. This is the super class
     * that handles the editing of all the base values.
     * @param scan The scanner that will be used to read user input
     */
    public void updateLocalData(Scanner scan) {
        scan.nextLine();
        String curName = getName();
        int curSize = getSize();
        int curPrice = getPrice();
        int curLevel = getLevel();

        System.out.println("Hit enter to keep name as [" + curName + "], or enter new name:");
        String name = scan.nextLine();
        if (name.equals(""))
            name = curName;

        System.out.println("Hit enter to keep size at [" + curSize + "] or enter new size:");
        String newSize = scan.nextLine();
        int size = newSize.equals("") ? curSize : Integer.parseInt(newSize);

        System.out.println("Hit enter to keep price at [" + curPrice + "] or enter new price:");
        String newPrice = scan.nextLine();
        int price = newPrice.equals("") ? curPrice : Integer.parseInt(newPrice);


        System.out.println("Hit enter to keep comfort level at [" + curLevel + "] or enter new comfort level:");
        String newLevel = scan.nextLine();
        int level = newLevel.equals("") ? curLevel : Integer.parseInt(newLevel);

        setName(name);
        setSize(size);
        setPrice(price);
        setLevel(level);
    }

    public boolean canHold(int numPassengers, int comfortLevel) {
        int capacity = size / mny.getSeparation(comfortLevel);
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