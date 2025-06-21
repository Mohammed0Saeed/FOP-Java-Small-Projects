package h05;

public class Runway {
    private final int  runwayLength;

    public Runway(int runwayLength) {
        this.runwayLength = runwayLength;
    }

    public int getRunwayLength() {
        return runwayLength;
    }

    /**
     * calculate the required distance for a plane to land
     * @param Mass the mass of the plane
     * @return the required distance to land
     */
    public static double calculateLandingDistance(double Mass) {
        return (Mass / 40);
    }

    /**
     * check if the plane can land in the runway
     * @param plane the plane that will land
     * @return true if the length of the runway is enough for the plan to land
     */
    public boolean canLand(Plane plane) {
        return (calculateLandingDistance(plane.mass()) <= getRunwayLength());
    }

    /**
     * print a message in the console whether the plan landed successfully
     * @param plane the plane that lands
     */
    public void land(Plane plane) {
        if (canLand(plane)) {
            plane.land();
            System.out.println("Plane " + plane.getIdentifier() + " has landed successfully.");
        }
        else
            System.out.println("Plane " + plane.getIdentifier() + " couldn't land. The runway is too short");
    }
}
