

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.IntStream;


public class EntryScreen {

    public EntryScreen() {
    }

    public ArrayList<Planner> managePlanners(Scanner scan, ArrayList<Planner> plans, Ministry mny, ArrayList<Bus> buses) throws NumberFormatException {
        ReportScreen r = new ReportScreen();
        char mchoice = 'c';
        String menu = "";
        while (mchoice != 'X') {
            String menuOptions = "[A]dd/Create planner\n[E]dit/Update planner\n";
            menuOptions += "[L]ist/Read planner\n[D]elete planner\nE[x]it\n";
            System.out.println(menuOptions);
            menu = scan.next().toUpperCase();
            mchoice = menu.charAt(0);
            switch (mchoice) {
                case 'A': {
                    Planner p = createPlanner(scan, mny, buses);
                    if (p != null)
                        plans.add(p);
                    break;
                }
                case 'L': {
                    Collections.sort(plans);
                    r.listPlanners(plans, System.out);
                    break;
                }
                case 'E': {
                    System.out.println("Please enter the ID of the planner to be updated:");
                    int pid = Integer.parseInt(scan.next());
                    int pdx = findPlanner(plans, pid);
                    if (pdx >= 0)
                        plans.get(pdx).updateLocalData(scan);
                    else
                        System.out.println("Planner with id " + pid + " not found.");
                    break;
                }
                case 'D': {
                    System.out.println("Please enter the ID of the planner to be deleted:");
                    int pid = Integer.parseInt(scan.next());
                    int pdx = findPlanner(plans, pid);

                    if (pdx >= 0)
                        plans.remove(pdx);
                    else
                        System.out.println("Planner with id " + pid + " not found.");
                    break;
                }


            }

        }
        return plans;
    }

    public ArrayList<Bus> manageBuses(Scanner scan, Ministry mny, ArrayList<Bus> buses) {
        ReportScreen r = new ReportScreen();
        char mchoice = 'c';
        String menu = "";
        while (mchoice != 'X') {
            String menuOptions = "[A]dd/Create bus\n[E]dit/Update bus\n";
            menuOptions += "[L]ist/Read buses\n[D]elete bus\nE[x]it\n";
            System.out.println(menuOptions);
            menu = scan.next().toUpperCase();
            mchoice = menu.charAt(0);
            switch (mchoice) {
                case 'A': {
                    final var b = createBus(scan, mny);
                    if (b != null)
                        buses.add(b);
                    break;
                }
                case 'L': {
                    Collections.sort(buses);
                    r.listBuses(buses, System.out);
                    break;
                }
                case 'E': {
                    System.out.println("Please enter the ID of the planner to be updated:");
                    int pid = Integer.parseInt(scan.next());
                    int pdx = findBus(buses, pid);
                    if (pdx >= 0)
                        buses.get(pdx).updateLocalData(scan);
                    else
                        System.out.println("Bus with id " + pid + " not found.");
                    break;
                }
                case 'D': {
                    System.out.println("Please enter the ID of the planner to be deleted:");
                    int bid = Integer.parseInt(scan.next());
                    int pdx = findBus(buses, bid);

                    if (pdx >= 0)
                        buses.remove(pdx);
                    else
                        System.out.println("Bus with id " + bid + " not found.");
                    break;
                }
            }
        }
        return buses;
    }


    public Planner createPlanner(Scanner scan, Ministry mny, ArrayList<Bus> buses) {
        Planner p = null;
        try {
            System.out.println("Please enter Planner Name:");
            String name = scan.next();
            System.out.println("Please enter Planner Budget:");
            int budget = Integer.parseInt(scan.next());
            p = new Planner(name, budget, mny, buses);
        } catch (NumberFormatException nfe) {
        }
        return p;
    }

    public Bus createBus(Scanner scan, Ministry mny) {
        if (scan == null)
            scan = new Scanner(System.in);

        scan.nextLine();

        System.out.println("Please enter bus name: ");
        String name = scan.nextLine();
        System.out.println("Please enter bus size: ");
        int size = Integer.parseInt(scan.nextLine());
        System.out.println("Please enter bus price: ");
        int price = Integer.parseInt(scan.nextLine());
        System.out.println("Please enter bus comfort level: ");
        int comfortLevel = Integer.parseInt(scan.nextLine());

        return new Bus(
                name,
                size,
                price,
                comfortLevel,
                mny
        );
    }


    public int findPlanner(ArrayList<Planner> plans, int pid) {
        return IntStream.range(0, plans.size())
                .filter(i -> plans.get(i).getId() == pid)
                .findFirst()
                .orElse(-1);
    }


    public int findBus(ArrayList<Bus> buses, int bid) {
        return IntStream.range(0, buses.size())
                .filter(i -> buses.get(i).getId() == bid)
                .findFirst()
                .orElse(-1);
    }
}

