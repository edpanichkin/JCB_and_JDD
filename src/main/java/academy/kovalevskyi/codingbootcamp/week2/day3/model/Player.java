package academy.kovalevskyi.codingbootcamp.week2.day3.model;

public class Player {

  private final String name;
  private char team;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public char getTeam() {
    return team;
  }

  public void setTeam(char team) {
    this.team = team;
  }
}
