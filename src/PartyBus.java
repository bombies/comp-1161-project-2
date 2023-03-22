

import java.util.Scanner;

public class PartyBus extends SportsBus {
    int barArea;


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


}
