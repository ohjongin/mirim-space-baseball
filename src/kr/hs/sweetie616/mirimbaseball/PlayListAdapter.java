package kr.hs.sweetie616.mirimbaseball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.hs.sweetie616.mirimbaseball.model.PlayRecord;

public class PlayListAdapter extends ArrayAdapter<PlayRecord> {
    protected int currPlayer = 0;
	TextView tv1;
	TextView tv2;
	TextView tv3;
	TextView tv4;
	TextView tv5;
	TextView tv6;

    public PlayListAdapter(Context context, ArrayList<PlayRecord> items) {
        super(context, R.layout.listitem_play, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			convertView = inflator.inflate(R.layout.listitem_play, null);
		}

        PlayRecord playRecord = getItem(position);
        TextView tvItem = (TextView)convertView.findViewById(R.id.text1);

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
