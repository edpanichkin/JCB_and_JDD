package Controller;

import Model.FieldPoints;
import Model.Player;
import Model.RulesCheck;
import View.TableView;

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
    System.out.println("Введите имена игроков через пробел");
    addPlayers(reader.nextLine().split(" "));
    whoStart();
    printTable();
  }

  public void addPlayers(String[] names) {
    players.add(new Player(names[0]));
    players.add(new Player(names[1]));
  }

  public void whoStart() {
    turnPointer = new Random().nextInt(new int[]{0, 1}.length);
    System.out.println("rand " + turnPointer);
    int a = 0;
    int b = 0;
    switch(turnPointer) {
      case 0 -> a = 1;
      case 1 -> b = 1;
    }
      players.get(a).setTeam(TableView.X_SIGN);
      players.get(b).setTeam(TableView.O_SIGN);
  }

  public void printTable() {
    table.drawTableArray();
  }

  public void checkAndMove() {
    String playerName = players.get(turnPointer).getName();
    char sign = players.get(turnPointer).getTeam();
    System.out.printf("%s ВАШ ХОД! Вы ходите за '%s'. Введите координаты для хода:\n", playerName, sign);
    String[] inputData = reader.nextLine().split(" ");
    int x = Integer.parseInt(inputData[0]);
    int y = Integer.parseInt(inputData[1]);
    if (x >= 0 && x < 3 && y >= 0 && y < 3) {
      if (!field.ifFieldBusy(x,y)) {
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
