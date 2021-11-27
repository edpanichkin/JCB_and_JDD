package academy.kovalevskyi.codingbootcamp.week2.day3-4;
import academy.kovalevskyi.codingbootcamp.week2.day3-4.controller.GameController;

public class Main {
  public static void main(String[] args) {
    GameController game = new GameController();
    while (game.inGame) {
      game.checkAndMove();
    }
    System.out.println("Спасибо");
  }
}

