import java.util.InputMismatchException;
import java.util.Scanner;

public class Mastermind {

    private final static int NB_ITEMS = 5;
    public final static int NB_TRIALS = 5;

    private final static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args){

        Boolean continueGame = true;

        do {
            GameType gameType = Mastermind.gameTypeChoice();
            GameMode gameMode = Mastermind.gameModeChoice();
            Game game = null;
            if (gameType.equals(GameType.SEARCH)) {
                game = new GameSearch();
            }
            else if (gameType.equals(GameType.MASTERMIND)) {
                game = new GameMastermind();
            }
            continueGame = game.execute(gameMode,gameType);
        } while(continueGame);
    }

    /**
     * Display the menu to choose the game type and get answer
     * @return the chosen game type
     */
    private static GameType gameTypeChoice () {
        while(true) {
            int answer = 0;
            System.out.println("Quel type choisissez-vous?");
            System.out.println("1 - Recherche ");
            System.out.println("2 - Mastermind ");
            try {
                answer = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Vous devez entrer un chiffre.");
                scanner.next();
            }
            if (answer > 0 && answer <= 2) {
                GameType answerGameType = GameType.values()[answer - 1];
                System.out.println("Vous avez choisi " + answerGameType);
                return answerGameType;
            }
        }
    }

    /**
     * Display the menu to choose the game mode and get answer
     * @return the chosen game mode
     */
    private static GameMode gameModeChoice () {
        while(true) {
            int answer = 0;
            System.out.println("Quel mode choisissez-vous?");
            System.out.println("1 - Challenger ");
            System.out.println("2 - Défenseur ");
            System.out.println("3 - Duel ");
            try {
                answer = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Vous devez entrer un chiffre.");
                scanner.next();
            }
            if (answer > 0 && answer <= 3) {
                GameMode answerGameMode = GameMode.values()[answer - 1];
                System.out.println("Vous avez choisi " + answerGameMode);
                return answerGameMode;
            }
        }
    }

}
