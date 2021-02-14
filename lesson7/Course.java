package lesson7;

import java.util.Random;

public class Course {

    private static final Random random = new Random();

    private Obstacle[] obstacles;

    public Course() {
        generateObstacles();
    }

    private void generateObstacles() {
        obstacles = new Obstacle[5];
        for (int i = 0; i < obstacles.length; i++) {
            Obstacle obstcl;
            int v = random.nextInt(2);
            if (v == 0) {
                int lenght = random.nextInt(951) + 50;
                obstcl = new RunningTrack(lenght);
            }
            else {
                double height = random.nextDouble() * 1.5 + 1.0;
                obstcl = new Wall(height);
            }
            obstacles[i] = obstcl;
        }
    }

    public void dolt(Team team) {
        Competitor[] cmptrs = team.getCompetitors();
        for (int i = 0; i < cmptrs.length; i++) {
            for (Obstacle obstcl : obstacles) {
                if (!obstcl.overcome(cmptrs[i])) {
                    System.out.println("Competitor №" + (i + 1) + " didn't overcome the obstacle and drops" +
                            " out of the competition");
                    System.out.println("Info:\ncompetitor - " + cmptrs[i].getInfo());
                    System.out.println("obstacle - " + obstcl.getInfo());
                    team.getOut()[i] = true;
                    break;
                }
            }
        }
    }

    public Obstacle[] getObstacles() {
        return obstacles;
    }
}
