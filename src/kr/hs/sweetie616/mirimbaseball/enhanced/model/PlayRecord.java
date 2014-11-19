package kr.hs.sweetie616.mirimbaseball.enhanced.model;

import java.util.ArrayList;

/**
 * Describe about this class here...
 *
 * @author ohjongin
 * @since 1.0
 * 14. 11. 18
 */
public class PlayRecord {
    protected ArrayList<Integer> userNumbers = new ArrayList<Integer>(3);

    protected int count;
    protected int strike;
    protected int ball;
    protected int out;
    protected String playerName;

    protected String message;

    public PlayRecord(int[] numArray) {
        initialize(numArray[0], numArray[1], numArray[2]);
    }

    public PlayRecord(int num1, int num2, int num3) {
        initialize(num1, num2, num3);
    }

    public PlayRecord(String msg) {
        message = msg;
    }

    public void initialize(int num1, int num2, int num3) {
        userNumbers.clear();

        userNumbers.add(0, num1);
        userNumbers.add(1, num2);
        userNumbers.add(2, num3);
    }

    public void calculate(ArrayList<Integer> numAnswerList) {
        // 결과 검사
        for (int i = 0; i < 3; i++) {
            int num = numAnswerList.get(i);

            int index = userNumbers.indexOf(num);
            if (index >= 0) {
                if (index == i) strike++;
                else ball++;
            } else {
                out++;
            }
        }
    }

    public int getStrike() {
        return strike;
    }

    public void setStrike(int strike) {
        this.strike = strike;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNumbers() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Integer num : userNumbers) {
            sb.append(num).append(",");
        }
        sb.append("]");

        return sb.toString().replaceAll(",]", "]");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append(userNumbers.toString()).append(", ");
        sb.append("strike:").append(getStrike()).append(", ");
        sb.append("ball:").append(getBall()).append(", ");
        sb.append("out:").append(getOut()).append("");
        sb.append("}");

        return sb.toString();
    }
}
