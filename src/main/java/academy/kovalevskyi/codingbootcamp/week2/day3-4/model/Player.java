package academy.kovalevskyi.codingbootcamp.week2.day3-4.model;

public class Player {

  private String name;
  private char team;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public char getTeam() {
    System.out.println(team);
    return team;
  }

  public void setTeam(char team) {
    this.team = team;
  }
}
