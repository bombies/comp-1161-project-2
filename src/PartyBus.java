

import java.util.Scanner;

public class PartyBus extends SportsBus {
    int barArea;


    /**
     * Default constructor for the PartyBus class
     * @param s The SportsBus to base the PartyBus on
     * @param barArea The area of the bar on the bus
     */
    public PartyBus(SportsBus s, int barArea) {
        super(s.getBus(), s.getCompetitorArea(), s.getNumSecurity());
        this.barArea = barArea;
    }


    public int getBarArea() {
        return barArea;
    }


    public void setBarArea(int barArea) {

        this.barArea = barArea;
    }


    public String toString() {
        return "ID:" + this.getId() + ";" + this.getName() + ";#Price:" + this.getPrice() + ";Bar Area:" + barArea + ";#Sec:" + getNumSecurity();

    }

    public String toFile() {
        return "" + this.getId() + ";" + this.getName() + ";" + this.getPrice() + ";" + barArea + ";" + getNumSecurity();

    }

    /**
     * Overrides the updateLocalData method in the Bus class to allow for the bar area to be updated
     * @param scan The scanner that will be used to read user input
     */
    @Override
    public void updateLocalData(Scanner scan) {
        super.updateLocalData(scan);

        int curBarArea =getBarArea();
        System.out.println("Hit enter to keep bar area at [" + curBarArea + "] or enter new bar area:");
        String newBarArea = scan.nextLine();
        int barArea = newBarArea.equals("") ? curBarArea : Integer.parseInt(newBarArea);

        setBarArea(barArea);
    }
}
