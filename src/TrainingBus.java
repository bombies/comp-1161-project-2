
import java.util.Scanner;

public class TrainingBus extends Bus {
    private int teacherArea;


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

        this.teacherArea = teacherArea;
    }


    public String toString() {
        return "ID:" + this.getId() + ";" + this.getName() + ";#Price:" + this.getPrice() + ";Teacher Area:" + teacherArea + "";

    }

    public String toFile() {
        return "" + this.getId() + ";" + this.getName() + ";" + this.getPrice() + ";" + teacherArea;

    }


}

