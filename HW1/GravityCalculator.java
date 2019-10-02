/*
    This program is a Gravity Calculator class, which is used to calculate the
    velocity and distance travelled by an object.

    Verson: 1.0
    Revisions: 0
    
    @authors: Anmol Jaising, Asmita Hari
*/

/*
    The class reads from the command line to accept the planet and time lapse.
    The program exits if the number of arguements isn't two, if the planet is not
    earth/mars, or if the time lapsed is negative.
*/
public class GravityCalculator {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect Paramneters passed");
            System.exit(0);
        }
        if (!args[0].toLowerCase().equals("earth") && !args[0]
                 .toLowerCase().equals("mars")) {

            System.out.println("Enter either Earth or Mars");
            System.exit(0);
        }
        if (Integer.parseInt(args[1]) < 0) {
            System.out.println("Time cannot be negative");
            System.exit(0);
        } else {
            String planet = args[0].toLowerCase();
            int time = Integer.parseInt(args[1]);

            System.out.println("The object's position after 5.0 seconds is " +
                    GravityCalculator.position(planet, time) + " m from"
                    + " starting positionCurrent velocity is " +
                    GravityCalculator.velocity(planet, time) + "m/s");
        }
    }

     /*
        This function is used to calculate the distance travelled by the object.
        @parameters - String planet - name of the planet
                      int time - time lapse
        @return - Double value - distance travelled by the object
    */

    public static double position(String planet, int time) {
        Double value;
        if ("earth".equals(planet)) {
            String str = String.format("%1.2f", 0.5 * 9.807 * time * time);
            value = Double.valueOf(str);
        } else {
            String str = String.format("%1.2f", 0.5 * 3.711 * time * time);
            value = Double.valueOf(str);
        }
        return value;
    }

    /*
        This function is used to calculate the velocity of an object from earth/
        mars
        @parameters - String planet - name of the planet
                      int time - time lapse
        @return - Double value - velocity of the object.     
    */

    public static double velocity(String planet, int time) {
        Double value;
        if ("earth".equals(planet)) {
            String str = String.format("%1.2f", 9.807 * time);
            value = Double.valueOf(str);
        } else {
            String str = String.format("%1.2f", 3.711 * time);
            value = Double.valueOf(str);
        }
        return value;
    }
}

