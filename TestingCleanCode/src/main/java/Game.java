import java.util.Random;
import java.util.Scanner;

public class Game {

    private Scanner input = new Scanner(System.in);
    private Random random = new Random();

    public void play() {
        printGameRules();

        ScoreBoard scoreBoard = new ScoreBoard();
        String choice = input.nextLine().toUpperCase();

        while (!choice.equals("QUIT"))
        {
            GameOptions choicenum = getChoiceNum(choice);
            while(choicenum == null)
            {
                System.out.println("Sorry, it looks like you didn't enter a correct input. Try again.");
                choice = input.nextLine().toUpperCase();
                choicenum = getChoiceNum(choice);
            }

            GameOptions compnum = getComputerChoice();
            completeGamePlay(scoreBoard, choicenum, compnum);
            printResults(scoreBoard);

            choice = input.nextLine().toUpperCase();
        }
    }

    private void printResults(ScoreBoard scoreBoard) {
        System.out.println("wins:" + scoreBoard.getWins() + "\nloses:" + scoreBoard.getLosses() + "\nties:" + scoreBoard.getTie()); //print out number of wins, ties, and loses
        System.out.println("Let's play again! \n \n"); //start game again
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
    }

    private void completeGamePlay(ScoreBoard scoreBoard, GameOptions choicenum, GameOptions compnum) {
        if(choicenum == compnum) {
            tie(scoreBoard);
        }
        else if (choicenum == GameOptions.ROCK && compnum == GameOptions.SCISSORS
                || choicenum == GameOptions.SCISSORS && compnum == GameOptions.PAPER
                || (choicenum == GameOptions.PAPER && compnum == GameOptions.ROCK)) {
            wins(scoreBoard);
        }
        lose(scoreBoard);
    }

    private void lose(ScoreBoard scoreBoard) {
        System.out.println("you lose.");
        scoreBoard.incrementLosses();
    }

    private void tie(ScoreBoard scoreBoard) {
        System.out.println("It's a tie");
        scoreBoard.incrementTie();
    }

    private void wins(ScoreBoard scoreBoard) {
        System.out.println("you win!");
        scoreBoard.incrementWins();
    }

    private GameOptions getChoiceNum(String choice) {
        GameOptions selectedOption = null;

        if (choice.equals("QUIT"))
            System.exit(0);

        try {
            selectedOption = GameOptions.valueOf(choice);
        } catch (Exception e) {
            return null;
        }
        return selectedOption;
    }

    private GameOptions getComputerChoice() {
        GameOptions option = GameOptions.values()[random.nextInt(3)];
        System.out.println("Computer chose " + option.toString().toLowerCase());
        return option;
    }

    private void printGameRules() {
        System.out.println("Let's play Rock, Paper, Scissors!");
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
    }
}