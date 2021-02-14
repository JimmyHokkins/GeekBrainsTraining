package lesson7;

public class Team {

    private String name;
    private Competitor[] competitors = new Competitor[4];
    private boolean[] out = new boolean[4];

    public Team(String name, Competitor cmptr1, Competitor cmptr2, Competitor cmptr3, Competitor cmptr4) {
        this.name = name;
        competitors[0] = cmptr1;
        competitors[1] = cmptr2;
        competitors[2] = cmptr3;
        competitors[3] = cmptr4;
    }

    public Competitor[] getCompetitors() {
        return competitors;
    }

    public void showTeamInfoOut() {
        for (int i = 0; i < competitors.length; i++) {
            String compOut = "overcame course";
            if (out[i]) compOut = "out";
            System.out.println(competitors[i].getInfo() + ", status: " + compOut);
        }
    }

    public void showTeamInfo() {
        System.out.println("Team name is " + name);
        System.out.println("Competitors: ");
        for (Competitor cmptr : competitors) {
            System.out.println(cmptr.getInfo());
        }
    }

    public boolean[] getOut() {
        return out;
    }
}
