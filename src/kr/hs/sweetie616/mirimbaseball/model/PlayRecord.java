package kr.hs.sweetie616.mirimbaseball.model;

import java.util.ArrayList;

/**
 * Describe about this class here...
 *
 * @author ohjongin
 * @since 1.0
 * 14. 11. 18
 */
public class PlayRecord {
    protected ArrayList<Integer> userNumbers = new ArrayList<Integer>();

    protected int strike;
    protected int ball;
    protected int out;

    public PlayRecord(int[] numArray) {
        initialize(numArray[0], numArray[1], numArray[2]);
    }

    public PlayRecord(int num1, int num2, int num3) {
        initialize(num1, num2, num3);
    }

    public void initialize(int num1, int num2, int num3) {
        userNumbers.clear();

        userNumbers.add(num1);
        userNumbers.add(num2);
        userNumbers.add(num3);
    }

    public void calculate(ArrayList<Integer> numAnswerList) {
        // 결과 검사
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (numAnswerList.get(i).equals(userNumbers.get(j))) {
                    if (i == j) { // 서로 같은 숫자가 있고 그 숫자의 자리가 같으면 스트라이크 처리
                        strike++; // 스트라이크
                    } else { // 서로 같은 숫자가 있고 그 숫자의 자리가 다르면 볼 처리
                        strike++; // 볼
                    }// if
                }// if
            }// for j
        }// for i

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
}
