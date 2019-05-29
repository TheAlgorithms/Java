package Others;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SkylineProblem {
    Building[] building;
    int count;

    public void run() {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        this.building = new Building[num];

        for (int i = 0; i < num; i++) {
            String input = sc.next();
            String[] data = input.split(",");
            this.add(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        }
        this.print(this.findSkyline(0, num - 1));

        sc.close();
    }

    public void add(int left, int height, int right) {
        building[count++] = new Building(left, height, right);
    }

    public void print(ArrayList<Skyline> skyline) {
        Iterator<Skyline> it = skyline.iterator();

        while (it.hasNext()) {
            Skyline temp = it.next();
            System.out.print(temp.coordinates + "," + temp.height);
            if (it.hasNext()) {
                System.out.print(",");
            }
        }

    }

    public ArrayList<Skyline> findSkyline(int start, int end) {
        if (start == end) {
            ArrayList<Skyline> list = new ArrayList<>();
            list.add(new Skyline(building[start].left, building[start].height));
            list.add(new Skyline(building[end].right, 0));

            return list;
        }

        int mid = (start + end) / 2;

        ArrayList<Skyline> sky1 = this.findSkyline(start, mid);
        ArrayList<Skyline> sky2 = this.findSkyline(mid + 1, end);

        return this.mergeSkyline(sky1, sky2);
    }

    public ArrayList<Skyline> mergeSkyline(ArrayList<Skyline> sky1, ArrayList<Skyline> sky2) {
        int currentH1 = 0, currentH2 = 0;
        ArrayList<Skyline> skyline = new ArrayList<>();
        int maxH = 0;

        while (!sky1.isEmpty() && !sky2.isEmpty()) {
            if (sky1.get(0).coordinates < sky2.get(0).coordinates) {
                int currentX = sky1.get(0).coordinates;
                currentH1 = sky1.get(0).height;

                if (currentH1 < currentH2) {
                    sky1.remove(0);
                    if (maxH != currentH2) skyline.add(new Skyline(currentX, currentH2));
                } else {
                    maxH = currentH1;
                    sky1.remove(0);
                    skyline.add(new Skyline(currentX, currentH1));
                }
            } else {
                int currentX = sky2.get(0).coordinates;
                currentH2 = sky2.get(0).height;

                if (currentH2 < currentH1) {
                    sky2.remove(0);
                    if (maxH != currentH1) skyline.add(new Skyline(currentX, currentH1));
                } else {
                    maxH = currentH2;
                    sky2.remove(0);
                    skyline.add(new Skyline(currentX, currentH2));
                }
            }
        }

        while (!sky1.isEmpty()) {
            skyline.add(sky1.get(0));
            sky1.remove(0);
        }

        while (!sky2.isEmpty()) {
            skyline.add(sky2.get(0));
            sky2.remove(0);
        }

        return skyline;
    }

    public class Skyline {
        public int coordinates;
        public int height;

        public Skyline(int coordinates, int height) {
            this.coordinates = coordinates;
            this.height = height;
        }
    }

    public class Building {
        public int left;
        public int height;
        public int right;

        public Building(int left, int height, int right) {
            this.left = left;
            this.height = height;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        SkylineProblem skylineProblem = new SkylineProblem();
        skylineProblem.run();
    }
}
