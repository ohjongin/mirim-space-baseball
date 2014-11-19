package kr.hs.sweetie616.mirimbaseball;

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

public class Stage1Activity extends ActionBarActivity implements OnClickListener {

    ArrayList<String> list;
    PlayListAdapter adapter;
    ListView resultScreen;

    TextView tv_playerName;
    TextView TextViewValue[] = new TextView[3];
    int nums_com[] = new int[3];
    int nums_you[] = new int[3];
    int cnt_play1, cnt_play2, inputPos = 0;        //몇번째 도전인지 카운트, 현재 포지션 카운트
    boolean isPlay = true;
    boolean player = true;    //플레이어
    String player1 = "문예빈";
    String player2 = "윤소연";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1);

        tv_playerName = (TextView) findViewById(R.id.tv_player);
        TextViewValue[0] = (TextView) findViewById(R.id.tv_value_1);
        TextViewValue[1] = (TextView) findViewById(R.id.tv_value_2);
        TextViewValue[2] = (TextView) findViewById(R.id.tv_value_3);

        tv_playerName.setText(player1 + " 선수 공격!");

        resultScreen = (ListView) findViewById(android.R.id.list);
        list = new ArrayList<String>();
        adapter = new PlayListAdapter(this, list);
        resultScreen.setAdapter(adapter);

        init_com();
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
                Toast.makeText(getApplicationContext(), "공격버튼을 눌러주세요",
                        Toast.LENGTH_SHORT).show();
            }
        } else {    //게임 종료때 숫자버튼 눌렀을 때
            Toast.makeText(getApplicationContext(), "게임을 새로 시작해주세요!",
                    Toast.LENGTH_SHORT).show();
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

    //샷버튼 클릭되었을 때 호출되는 메소드
    public void clickBtnShotListener(View but) {
        nums_you = new int[3]; // 결과 초기화

        if (isPlay) {
            // 입력값 체크 (입력받은 숫자가 3개가 아니면 메시지창 출력)
            if (inputPos != 3) {
                Toast.makeText(getApplicationContext(), "숫자 3개를 입력해주세요 ^*^",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            int user_Array[] = new int[3];
            for (int i = 0; i < TextViewValue.length; i++) {
                user_Array[i] = Integer.parseInt(TextViewValue[i].getText()
                        .toString());
            }

            // 결과 검사
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (nums_com[i] == user_Array[j]) {
                        if (i == j) { // 서로 같은 숫자가 있고 그 숫자의 자리가 같으면 스트라이크 처리
                            nums_you[0]++; // 스트라이크
                        } else { // 서로 같은 숫자가 있고 그 숫자의 자리가 다르면 볼 처리
                            nums_you[1]++; // 볼
                        }// if
                    }// if
                }// for j
            }// for i

            // 아웃 계산
            nums_you[2] = 3 - (nums_you[0] + nums_you[1]); // 아웃!

            // 입력값을 표시하는 텍스트필드 초기화
            for (TextView v : TextViewValue) {
                v.setText(" ");
            }
            inputPos = 0;

            //cnt_play++;// 플레이 카운트 증가

            // 결과값 출력
//            String strResult = null;

            //FIXME 여기서부터 adapter.setTest함수에 넘겨줄 문자열들 생성해요
            String s1 = "", s2 = "", s3 = "", s4 = "", s5 = "", s6 = "";

            //플레이어 전환, 이름바꿔 출력
            if (player) {
                s1 = (cnt_play1 + 1) + "회전";
                s2 = player1;
                player = !player;
                adapter.setPlayer(0);
                tv_playerName.setText(player2 + " 선수 공격!");
                cnt_play1++;
            } else {
                s1 = (cnt_play2 + 1) + "회전";
                s2 = player2 + "\t\t";
                player = !player;
                adapter.setPlayer(1);
                tv_playerName.setText(player1 + " 선수 공격!");
                cnt_play2++;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < user_Array.length; i++) {
                sb.append(user_Array[i]).append(",");
            }
            sb.append("]");

            s3 = sb.toString().replaceAll(",]", "]");
            s4 = drawCircle(nums_you[0]);
            s5 = drawCircle(nums_you[1]);
            s6 = drawCircle(nums_you[2]);
//            strResult += Arrays.toString(user_Array) + "\t";
//            strResult += drawCircle(nums_you[0]);
//            strResult += drawCircle(nums_you[1]);
//            strResult += drawCircle(nums_you[2]);
//
//            appendResult(strResult);

            sb = new StringBuilder();
            sb.append(s1).append(";").append(s2).append(";").append(s3).append(";").append(s4).append(";").append(s5).append(";").append(s6);
            appendResult(sb.toString());

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

    //숫자에 따라 동그라미를 그려주는 메소드
    public String drawCircle(int num) {
        String str = null;
        if (num == 0) {
            str = "\t\t○○○";
        } else if (num == 1) {
            str = "\t\t●○○";
        } else if (num == 2) {
            str = "\t\t●●○";
        } else {
            str = "\t\t●●●";
        }
        return str;
    }

    public void appendResult(String str) {

        list.add(str);
        int pos = adapter.getCount() - 1;
        resultScreen.smoothScrollToPosition(pos);
        adapter.notifyDataSetChanged();
    }//결과 뷰에 결과 출력

    //게임 초기화
    public void init_com() {

        cnt_play1 = 0; // 공격횟수 초기화;
        cnt_play2 = 0; // 공격횟수 초기화;
        for (TextView v : TextViewValue) {
            v.setText(" ");
        }
        // 중복되지 않는 3개의 난수 저장.
        int i = 0;
        do {
            int r = (int) (Math.random() * 9) + 1;    //1~9 사이의 값 추출
            nums_com[i] = r;

            for (int j = 0; j < i; j++) { // 중복되는 값 검사.
                if (nums_com[i] == nums_com[j]) {
                    i--;
                    break;    //중복되는 값이 있으면 i를 빼준 후 반복문 종료
                }// if
            }// for
            i++;
        } while (i < 3); // 3회전

        Toast toast = Toast.makeText(this, (nums_com[0] + ", " + nums_com[1] + ", " + nums_com[2]), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        inputPos = 0;
        cnt_play1 = 0;
        cnt_play2 = 0;
        isPlay = true;    //플레이중으로 전환
    }

}
