
import java.util.Scanner;

public class SportsBus extends Bus {
    private int competitorArea;
    private int numSecurity;
    private final Bus bus;

    /**
     * Default constructor for the SportsBus class
     * @param b The Bus to base the SportsBus on
     * @param competitorArea The area of the competitor area on the bus
     * @param numSecurity The number of security on the bus
     */
    public SportsBus(Bus b, int competitorArea, int numSecurity) {
        super(b.getName(), b.getSize(), b.getPrice(), b.getLevel(), b.getMinistry());
        bus = b;
        this.competitorArea = competitorArea;
        this.numSecurity = numSecurity;

    }

    public int getCompetitorArea() {
        return competitorArea;
    }

    public int getNumSecurity() {
        return numSecurity;
    }

    public Bus getBus() {
        return bus;
    }

    public void setCompetitorArea(int competitorArea) {

        this.competitorArea = competitorArea;
    }

    public void setNumSecurity(int numSecurity) {

        this.numSecurity = numSecurity;
    }

    public String toString() {
        return "ID:" + this.getId() + ";" + this.getName() + ";#Price:" + this.getPrice() + ";Compet Area:" + competitorArea + ";#Sec:" + numSecurity;

    }

    public String toFile() {
        return "" + this.getId() + ";" + this.getName() + ";" + this.getPrice() + ";" + competitorArea + ";" + numSecurity;

    }

    /**
     * Overrides the updateLocalData method in the Bus class to allow for the competitor area and number of security to be updated
     * @param scan The scanner that will be used to read user input
     */
    @Override
    public void updateLocalData(Scanner scan) {
        super.updateLocalData(scan);

        int curCompetitorArea = getCompetitorArea();
        System.out.println("Hit enter to keep competitor area at [" + curCompetitorArea + "] or enter new competitor area:");
        String newCompetitorArea = scan.nextLine();
        int competitorArea = newCompetitorArea.equals("") ? curCompetitorArea : Integer.parseInt(newCompetitorArea);

        int curNumSecurity = getNumSecurity();
        System.out.println("Hit enter to keep num security at [" + curNumSecurity + "] or enter new num security:");
        String newNumSecurity = scan.nextLine();
        int numSecurity = newNumSecurity.equals("") ? curNumSecurity : Integer.parseInt(newNumSecurity);

        setCompetitorArea(competitorArea);
        setNumSecurity(numSecurity);
    }


}