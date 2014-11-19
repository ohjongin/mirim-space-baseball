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

    protected int strike;
    protected int ball;
    protected int out;

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

        /*for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (numAnswerList.get(i).equals(userNumbers.get(j))) {
                    if (i == j) { // 서로 같은 숫자가 있고 그 숫자의 자리가 같으면 스트라이크 처리
                        strike++; // 스트라이크
                    } else { // 서로 같은 숫자가 있고 그 숫자의 자리가 다르면 볼 처리
                        strike++; // 볼
                    }// if
                }// if
            }// for j
        }// for i*/

        // 아웃 계산
        out = 3 - (strike + ball); // 아웃!
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

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        sb.append(userNumbers.toString()).append("\n");
        sb.append("strike:").append(getStrike()).append("\n");
        sb.append("ball:").append(getBall()).append("\n");
        sb.append("out:").append(getOut()).append("\n");
        sb.append("}");

        return sb.toString();
    }
}