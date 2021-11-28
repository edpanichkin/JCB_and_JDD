package academy.kovalevskyi.codingbootcamp.week2.day3.view;

import academy.kovalevskyi.codingbootcamp.week2.day3.model.Player;

public class Messages {

  public static void startMsg() {
    System.out.println("Добро пожаловать в игру 'Крестики - Нолики'! \n"
           + "Введите имена двух игроков через пробел:");
  }

  public static void playerMove(Player player) {
    System.out.printf("%s ВАШ ХОД! Вы ходите за '%s'. Введите координаты для хода:\n",
            player.getName(), player.getTeam());
  }

  public static void fieldNotEmpty() {
    String frame = "-";
    frame = frame.repeat(27);
    System.out.println(" " + frame);
    System.out.println(" | Поле занято, переходите |");
    System.out.println(" " + frame + "");
  }

  public static void wrongXY() {
    String frame = "-";
    frame = frame.repeat(38);
    System.out.println(" " + frame);
    System.out.println(" | Введите верные значения, x/y 0 1 2 |");
    System.out.println(" " + frame + "");
  }

  public static void winMsg(Player player) { 
    System.out.printf("Игрок %s выйграл! Поздравляю, хорошая партия! \n\n", player.getName());
  }

  public static void drawMsg() {
    System.out.println("НИЧЬЯ!!\n\n");
  }
}
