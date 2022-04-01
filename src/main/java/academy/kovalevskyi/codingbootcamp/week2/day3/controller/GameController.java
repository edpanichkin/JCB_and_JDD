package academy.kovalevskyi.codingbootcamp.week2.day3.controller;

import academy.kovalevskyi.codingbootcamp.week2.day3.model.FieldPoints;
import academy.kovalevskyi.codingbootcamp.week2.day3.model.Player;
import academy.kovalevskyi.codingbootcamp.week2.day3.model.RulesCheck;
import academy.kovalevskyi.codingbootcamp.week2.day3.view.Messages;
import academy.kovalevskyi.codingbootcamp.week2.day3.view.TableView;

import javax.swing.text.TableView;
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
    Messages.startMsg();
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
    char sign = players.get(turnPointer).getTeam();
    Messages.playerMove(players.get(turnPointer));
    String[] inputData = reader.nextLine().split("\s+");
    if (inputData.length == 2 && (inputData[0].length() == 1 && inputData[1].length() == 1)
            && (inputData[0].charAt(0) >= 48 && inputData[0].charAt(0) <= 50)
            && inputData[1].charAt(0) >= 48 && inputData[1].charAt(0) <= 50) {
      int x = Integer.parseInt(inputData[0]);
      int y = Integer.parseInt(inputData[1]);
      if (!field.ifFieldBusy(x, y)) {
        table.putSymbol(x, y, sign);
        field.putPoint(x, y, sign);
        TableView.clearScreen();
        TableView.drawHeader();
      } else {
        TableView.clearScreen();
        Messages.fieldNotEmpty();
        nextTurn();
      }
      if (RulesCheck.checkWin(field, field.signToIntFlag(sign))) {
        printTable();
        Messages.winMsg(players.get(turnPointer));
        inGame = !inGame;
        return;
      } else if (RulesCheck.checkDraw(field)) {
        printTable();
        Messages.drawMsg();
        inGame = !inGame;
      }
    } else {
      TableView.clearScreen();
      Messages.wrongPoint();
      nextTurn();
    }
    nextTurn();
    printTable();
  }

  public void nextTurn() {
    turnPointer = turnPointer == 0 ? 1 : 0;
  }
}
