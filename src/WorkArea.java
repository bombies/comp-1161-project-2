

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WorkArea {

    public WorkArea() {
    }

    ArrayList<Planner> planners = new ArrayList<Planner>();
    ArrayList<Bus> buses = new ArrayList<Bus>();
    Ministry mny = new Ministry("TRANSPORT");
    ReportScreen r = new ReportScreen();


    public void clearData() {
        planners.clear();
        buses.clear();
        Planner.resetId();
        Bus.resetId();
    }


    public ArrayList<Planner> loadPlanners(String pfile, Ministry mny, ArrayList<Bus> bus) {
        Scanner pscan = null;
        ArrayList<Planner> plist = new ArrayList<Planner>();
        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0];
                int budget = Integer.parseInt(nextLine[1]);
                Planner p = new Planner(name, budget, mny, buses);
                plist.add(p);
            }

            pscan.close();
        } catch (IOException e) {
        } catch (NumberFormatException nfe) {
        }

        return plist;

    }

    public ArrayList<Planner> loadTripsToPlanners(ArrayList<Planner> plist, String tfile) {
        Scanner tscan = null;
        try {
            tscan = new Scanner(new File(tfile));
            while (tscan.hasNext()) {
                String[] nextLine = tscan.nextLine().split(" ");
                String name = nextLine[0];
                int numPatrons = Integer.parseInt(nextLine[1]);
                String ttype = nextLine[2];
                Date day = new Date(Integer.parseInt(nextLine[3]));
                int comfort = Integer.parseInt(nextLine[4]);

                int pdx = findPlannerByName(plist, name);
                if (pdx >= 0)
                    plist.get(pdx).addPlan(new Plan(numPatrons, ttype, day, comfort));
            }

            tscan.close();
        } catch (IOException e) {
        } catch (NumberFormatException nfe) {
        }

        return plist;

    }


    public ArrayList<Bus> loadBuses(String bfile) {
        try {
            Scanner bscan = new Scanner(new File(bfile));
            ArrayList<Bus> blist = new ArrayList<>();

            while (bscan.hasNextLine()) {
                final var line = bscan.nextLine().split(" ");
                blist.add(new Bus(
                                line[0],
                                Integer.parseInt(line[1]),
                                Integer.parseInt(line[2]),
                                Integer.parseInt(line[3]),
                                mny
                        )
                );
            }

            return blist;
        } catch (FileNotFoundException e) {
            System.out.printf("There was no file with the name %s%n", bfile);
            return new ArrayList<>();
        }
    }

    public void loadTestCase(int caseNo, Scanner scan) {
        loadData(caseNo);
        System.out.print("Test case " + caseNo + " loaded: Show data?[y/n]");
        String response = scan.next();
        if (response.toUpperCase().charAt(0) == 'Y') {
            ReportScreen r = new ReportScreen();
            r.listBuses(buses, System.out);
            r.listPlanners(planners, System.out);

        }

    }


    public void loadData(int caseNo) {
        clearData();
        buses = loadBuses(getBusFile(caseNo));
        planners = loadPlanners(getPlFile(caseNo), mny, buses);
        planners = loadTripsToPlanners(planners, getTripFile(caseNo));

    }


    private String getPlFile(int caseNo) {
        return "cases/Planner." + caseNo + ".txt";
    }


    private String getBusFile(int caseNo) {
        return "cases/Bus." + caseNo + ".txt";
    }

    private String getTripFile(int caseNo) {
        return "cases/Trip." + caseNo + ".txt";
    }

    public int findPlannerByName(ArrayList<Planner> plans, String name) {
        int pdx = -1;
        int currdx = 0;
        while ((currdx < plans.size()) && (pdx == -1)) {
            if (plans.get(currdx).getName().equals(name))
                pdx = currdx;
            currdx++;


        }

        return pdx;

    }
}
