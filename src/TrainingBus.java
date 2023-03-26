
import java.util.Scanner;

public class TrainingBus extends Bus {
    private int teacherArea;


    /**
     * Default constructor for the TrainingBus class
     * @param b The Bus to base the TrainingBus on
     * @param teacherArea The area of the teacher area on the bus
     */
    public TrainingBus(Bus b, int teacherArea) {
        super(b.getName(), b.getSize(), b.getPrice(), b.getLevel(), b.getMinistry());
        this.teacherArea = teacherArea;

    }

    public int getEstimate(String type) {
        int price = super.getPrice();

        //System.out.println(this.getName()+":estimate  to hold a "+type +" is "+ price);
        return price;

    }


    public int getTeacherArea() {
        return teacherArea;
    }


    public void setTeacherArea(int instructorArea) {
        this.teacherArea = instructorArea;
    }


    public String toString() {
        return "ID:" + this.getId() + ";" + this.getName() + ";#Price:" + this.getPrice() + ";Teacher Area:" + teacherArea + "";

    }

    public String toFile() {
        return "" + this.getId() + ";" + this.getName() + ";" + this.getPrice() + ";" + teacherArea;

    }

    /**
     * Overrides the updateLocalData method in the Bus class to allow for the teacher area to be updated
     * @param scan The scanner that will be used to read user input
     */
    @Override
    public void updateLocalData(Scanner scan) {
        super.updateLocalData(scan);

        int curTeacherArea = getTeacherArea();
        System.out.println("Hit enter to keep teacher area at [" + curTeacherArea + "] or enter new teacher area:");
        String newTeacherArea = scan.nextLine();
        int teacherArea = newTeacherArea.equals("") ? curTeacherArea : Integer.parseInt(newTeacherArea);

        setTeacherArea(teacherArea);
    }
}

