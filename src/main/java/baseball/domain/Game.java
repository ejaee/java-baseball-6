package baseball.domain;

import baseball.ui.Input;
import baseball.ui.Message;
import baseball.ui.Output;
import baseball.utils.Utility;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {

    private final int NUMBER_BALLS;
    private final Input input;
    private final Output output;
    private List<Command> commands = new ArrayList<>();
    private Computer computer;
    private User user;

    public Game(final int NUMBER_BALLS, final Input input, final Output output) {
        this.NUMBER_BALLS = NUMBER_BALLS;
        this.input = input;
        this.output = output;

        initGameCommand();
    }

    private void initGameCommand() {
        commands.add(Command.RESTART);
        commands.add(Command.END);
    }

    public void run() {
        while (true) {
            output.print(Message.START);
            play();
            if (!isRestart()) {
                break;
            }
        }
    }

    private void play() {
        initPlayer();
        while (true) {
            if (isAnswer()) {
                break;
            }
        }
        output.print(Message.END);
    }

    private void initPlayer() {
        user = new User(input);
        computer = new Computer(output);
        computer.generatePlayerNumber(NUMBER_BALLS);
    }

    private boolean isAnswer() {
        BallCounter ballCounter = checkResult();
        return ballCounter.isAllStrike(NUMBER_BALLS);
    }

    private BallCounter checkResult() {
        BallCounter ballCounter = new BallCounter(computer.getNumbers(), guessNumber());
        computer.announceResult(ballCounter);
        return ballCounter;
    }

    private List<Integer> guessNumber() {
        output.print(Message.REQUEST_INPUT);
        user.generatePlayerNumber(NUMBER_BALLS);
        return Utility.convertStringToBall(user.getNumbers(), NUMBER_BALLS);
    }

    private boolean isRestart() {
        Command command = chooseRestartGame();
        return Objects.equals(command.getCommand(), "1");
    }

    private Command chooseRestartGame() {
        output.print(Message.RESTART);
        return user.selectCommand();
    }
}
