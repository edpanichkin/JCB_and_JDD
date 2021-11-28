package academy.kovalevskyi.codingbootcamp.week2.day3.controller;

import academy.kovalevskyi.codingbootcamp.week2.day3.model.FieldPoints;
import academy.kovalevskyi.codingbootcamp.week2.day3.model.Player;
import academy.kovalevskyi.codingbootcamp.week2.day3.model.RulesCheck;
import academy.kovalevskyi.codingbootcamp.week2.day3.view.TableView;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameController {
  public boolean inGame = true;
  public TableView table;
  public FieldPoints field;
  public ArrayList<Player> players;
  public Scanner reader = new Scanner(System.in);
  public int turnPointer = 0;

  public GameController() {
    this.table = new TableView();
    this.field = new FieldPoints();
    this.players = new ArrayList<>();
    TableView.clearScreen();
    System.out.println("Введите имена игроков через пробел");
    addPlayers(reader.nextLine().split(" "));
    whoStart();
    printTable();
  }

  public void addPlayers(String[] names) {
    players.add(new Player(names[0]));
    players.add(new Player(names[1]));
  }

  public void printTable() {
    table.drawTableArray();
  }

  
  public void whoStart() {
    turnPointer = new Random().nextInt(new int[]{0, 1}.length);
    System.out.println("rand " + turnPointer);
    int a = 0;
    int b = 0;
    switch (turnPointer) {
      case 0 -> b = 1;
      case 1 -> a = 1;
      default -> throw new IllegalArgumentException();
    }
    players.get(a).setTeam(TableView.X_SIGN);
    players.get(b).setTeam(TableView.O_SIGN);
  } 

  public void checkAndMove() {
    String playerName = players.get(turnPointer).getName();
    char sign = players.get(turnPointer).getTeam();
    System.out.printf("%s ВАШ ХОД! Вы ходите за '%s'. Введите координаты для хода:\n", playerName, sign);
    String[] inputData = reader.nextLine().split("\s+");
    if (inputData.length == 2 && (inputData[0].length() == 1 && inputData[1].length() == 1)
            && (inputData[0].charAt(0) >= 48 && inputData[0].charAt(0) <= 50)
            && inputData[1].charAt(0) >= 48 && inputData[1].charAt(0) <= 50) {
      int x = Integer.parseInt(inputData[0]);
      int y = Integer.parseInt(inputData[1]);
      if (!field.ifFieldBusy(x, y)) {
        table.putSymbol(x, y, sign);
        field.putPoint(x, y, sign);
      } else {
        System.out.println("Поле занято, переходите");
        nextTurn();
      }

      if (RulesCheck.checkWin(field, field.signToIntFlag(sign))) {
        System.out.printf("Игрок %s выйграл! Поздравляю, хорошая партия! \n", playerName);
        inGame = !inGame;
        return;
      } else if (RulesCheck.checkDraw(field)) {
        System.out.println("НИЧЬЯ!!\n");
        inGame = !inGame;
      }
    } else {
      TableView.clearScreen();
      System.out.println("Введите верные значения, x/y 0 1 2 \n");
      nextTurn();
    }
    nextTurn();
    printTable();
  }

  public void nextTurn() {
    turnPointer = turnPointer == 0 ? 1 : 0;
  }
}
