package kr.hs.sweetie616.mirimbaseball.enhanced;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import kr.hs.sweetie616.mirimbaseball.R;
import kr.hs.sweetie616.mirimbaseball.enhanced.model.PlayRecord;

public class StageActivity extends ActionBarActivity implements OnClickListener {
    protected ArrayList<PlayRecord> playList;
    protected PlayListAdapter playAdapter;
    protected ListView listView;

    protected TextView tv_playerName;
    protected TextView TextViewValue[] = new TextView[3];

    protected ArrayList<Integer> numAnswerList = new ArrayList<Integer>();

    protected int nums_you[] = new int[3];
    protected int cnt_play1, cnt_play2, inputPos = 0;        //몇번째 도전인지 카운트, 현재 포지션 카운트
    protected boolean isPlay = true;
    protected boolean player = true;    //플레이어
    protected String player1 = "문예빈";
    protected String player2 = "윤소연";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1);

        tv_playerName = (TextView) findViewById(R.id.tv_player);
        TextViewValue[0] = (TextView) findViewById(R.id.tv_value_1);
        TextViewValue[1] = (TextView) findViewById(R.id.tv_value_2);
        TextViewValue[2] = (TextView) findViewById(R.id.tv_value_3);

        tv_playerName.setText(player1 + " 선수 공격!");

        listView = (ListView) findViewById(android.R.id.list);
        playList = new ArrayList<PlayRecord>();
        playAdapter = new PlayListAdapter(this, playList);
        listView.setAdapter(playAdapter);

        initGames();
    }

    @Override
    public void onClick(View arg0) {
    }

    //숫자 버튼을 클릭되었을때 호출되는 메소드
    public void clickBtnNumListener(View target) {
        //넘겨받은 타겟을 버튼으로 다운
        Button btnNum = ((Button) target);
        String strNum = btnNum.getText().toString();    //눌린 버튼의 숫자를 가져오기
        //tv_resultScreen.append(strNum);

        if (isPlay) {    //게임 실행중일때 숫자버튼 눌렀을 때
            if (inputPos < 3) {    //
                for (TextView v : TextViewValue) {
                    if (v.getText().toString().trim().equals(strNum)) {
                        return;
                    }
                }
                TextViewValue[inputPos].setText(strNum);
                inputPos++;

            } else {
                Toast.makeText(this, "공격버튼을 눌러주세요", Toast.LENGTH_SHORT).show();
            }
        } else {    //게임 종료때 숫자버튼 눌렀을 때
            Toast.makeText(this, "게임을 새로 시작해주세요!", Toast.LENGTH_SHORT).show();
        }
    }

    // <- 백스페이스 버튼이 클릭되었을때 호출되는 메서드
    public void clickBtnDelListener(View target) {
        if (inputPos > 0) {
            if (TextViewValue[--inputPos].getText().toString().trim().length() == 1) {
                TextViewValue[inputPos].setText(" ");
            }
        }
    }

    // 샷버튼 클릭되었을 때 호출되는 메소드
    public void clickBtnShotListener(View but) {
        nums_you = new int[3]; // 결과 초기화

        if (isPlay) {
            // 입력값 체크 (입력받은 숫자가 3개가 아니면 메시지창 출력)
            if (inputPos != 3) {
                Toast.makeText(this, "숫자 3개를 입력해주세요 ^*^", Toast.LENGTH_SHORT).show();
                return;
            }

            int userNumbers[] = new int[3];
            for (int i = 0; i < TextViewValue.length; i++) {
                userNumbers[i] = Integer.parseInt(TextViewValue[i].getText().toString());
            }

            PlayRecord playRecord = new PlayRecord(userNumbers);
            playRecord.calculate(numAnswerList);
            // Log.e("", playRecord.toString());

            // 입력값을 표시하는 텍스트필드 초기화
            for (TextView v : TextViewValue) {
                v.setText(" ");
            }
            inputPos = 0;

            // 플레이어 전환, 이름바꿔 출력
            if (player) {
                playRecord.setPlayerName(player1);
                playRecord.setCount(++cnt_play1);
                player = !player;
                playAdapter.setPlayer(0);
                tv_playerName.setText(player2 + " 선수 공격!");
            } else {
                playRecord.setPlayerName(player2);
                playRecord.setCount(++cnt_play2);
                player = !player;
                playAdapter.setPlayer(1);
                tv_playerName.setText(player1 + " 선수 공격!");
            }

            addPlayRecord(playRecord);

            if (nums_you[0] == 3) {
                //TODO Intent
                //어펜드 리절트 대신 다음 레이아웃으로 인텐트시켜서 이긴 사용자 이름 띄워준 후
                //랜덤벌칙 또는 메인으로 버튼 두개 있도록 다음 레이아웃 만들기
                appendResult("\n문예빈 님이 이겼습니다 축하합니다~ ^*^");
                //tv_resultScreen.append("\n문예빈 님이 이겼습니다 축하합니다~ ^*^");
                isPlay = false;

            }// if

            if ((cnt_play1 + cnt_play2) == 18) { // 한경기에 9회전까지
                //tv_resultScreen.append("\n윤소연 님이 졌습니다 분발하세요~ ^*^");
                appendResult("\n윤소연 님이 졌습니다 분발하세요~ ^*^");
                isPlay = false;

            }// if
        } else {
            Toast.makeText(getApplicationContext(), "게임을 새로 시작해주세요!",
                    Toast.LENGTH_SHORT).show();
        }//if
        Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show();
    }

    public void addPlayRecord(PlayRecord playRecord) {
        playList.add(playRecord);
        int pos = playAdapter.getCount() - 1;
        listView.smoothScrollToPosition(pos);
        playAdapter.notifyDataSetChanged();
    }

    public void appendResult(String msg) {
        playList.add(new PlayRecord(msg));
        int pos = playAdapter.getCount() - 1;
        listView.smoothScrollToPosition(pos);
        playAdapter.notifyDataSetChanged();
    }

    // 게임 초기화
    public void initGames() {
        cnt_play1 = 0; // 공격횟수 초기화;
        cnt_play2 = 0; // 공격횟수 초기화;
        for (TextView v : TextViewValue) {
            v.setText(" ");
        }

        Integer numAnswers[] = new Integer[3];
        // 중복되지 않는 3개의 난수 저장.
        int i = 0;
        do {
            int r = (int) (Math.random() * 9) + 1;    //1~9 사이의 값 추출
            numAnswers[i] = r;

            for (int j = 0; j < i; j++) { // 중복되는 값 검사.
                if (numAnswers[i].equals(numAnswers[j])) {
                    i--;
                    break;    //중복되는 값이 있으면 i를 빼준 후 반복문 종료
                }
            }

            i++;
        } while (i < 3); // 3회전

        numAnswerList.addAll(Arrays.asList(numAnswers));

        Toast toast = Toast.makeText(this, numAnswerList.toString(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        inputPos = 0;
        cnt_play1 = 0;
        cnt_play2 = 0;
        isPlay = true;    //플레이중으로 전환
    }

}
