package lesson7;

public class SevenApp {

    public static void main(String[] args) {
        Course course = new Course();
        Team team = new Team("TEXAS RANGERS",
                new Cat("Tom", 1.3, 250),
                new Human("John Doe", 2.0, 500),
                new Robot("Amigo", 2.8, 700),
                new Dog("Killer", 1.5, 1000));

        System.out.println("We begin competitions!!!");
        System.out.println("Our obstacles today: ");
        for (Obstacle obstcl : course.getObstacles()) {
            System.out.println(obstcl.getInfo());
        }
        System.out.println("*********************************************************");
        System.out.println("Our team today: ");
        team.showTeamInfo();
        System.out.println("*********************************************************");
        System.out.println("Well... GO!!!");
        course.dolt(team);
        System.out.println("*********************************************************");
        System.out.println("OK... Finish... OUR RESULTS:");
        team.showTeamInfoOut();
    }
}
