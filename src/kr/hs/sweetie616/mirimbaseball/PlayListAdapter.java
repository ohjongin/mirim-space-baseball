package kr.hs.sweetie616.mirimbaseball;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class PlayListAdapter<String> extends ArrayAdapter<String> {
	protected int currPlayer = 0;
	TextView tv1;
	TextView tv2;
	TextView tv3;
	TextView tv4;
	TextView tv5;
	TextView tv6;
	
	public PlayListAdapter(Context context, ArrayList<String> items) {
		super(context, R.layout.listitem_play, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		//이 텍스뷰들을 리스트뷰에 임의로 6개 생성해서 여기로 불러왔는데 이게 맞는지 모르겠어요ㅜㅜ
		tv1 = (TextView) parent.findViewById(R.id.tv_list1);
		tv2 = (TextView) parent.findViewById(R.id.tv_list2);
		tv3 = (TextView) parent.findViewById(R.id.tv_list3);
		tv4 = (TextView) parent.findViewById(R.id.tv_list4);
		tv5 = (TextView) parent.findViewById(R.id.tv_list5);
		tv6 = (TextView) parent.findViewById(R.id.tv_list6);

		//FIXME 이부분은 굳이 있어야 하는지 모르겠어요..
		if ((tv1 != null ) & (tv2 != null)) {
			
			tv1.setText("");

			tv2.setText("");
		}
		
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			convertView = inflator.inflate(R.layout.listitem_play, null);
		}
		
		String play = getItem(position);
		TextView tvItem = (TextView)convertView.findViewById(R.id.text1);
		tvItem.setText((CharSequence) play);
		
		if (position % 2 == currPlayer) {
			tvItem.setTextColor(0xff336633);
		} else {
			tvItem.setTextColor(0xffee8200);
		}
				
		return convertView;
	}
	
	public void setPlayer(int player) {
		currPlayer = player;
	}
	
	//FIXME	이부분도 맞게 한건지 잘 모르겠어요ㅜㅜ
	public void setText(String s1, String s2, String s3, String s4, String s5, String s6) {
		tv1.setText("" + s1);
		tv2.setText("" + s2);
		tv3.setText("" + s3);
		tv4.setText("" + s4);
		tv5.setText("" + s5);
		tv6.setText("" + s6);
	}
}
