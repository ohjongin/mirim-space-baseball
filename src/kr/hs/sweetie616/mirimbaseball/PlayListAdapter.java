package kr.hs.sweetie616.mirimbaseball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayListAdapter extends ArrayAdapter<String> {
    protected int currPlayer = 0;

    public PlayListAdapter(Context context, ArrayList<String> items) {
        super(context, R.layout.listitem_play_enhanced, items);
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			convertView = inflator.inflate(R.layout.listitem_play, null);
		}

        String play = getItem(position);
        String items[] = play.split(";");

        for (int i = 0; i < 6; i++) {
            int id = getContext().getResources().getIdentifier("tv_item" + i, "id", getContext().getPackageName());
            TextView view = (TextView) convertView.findViewById(id);
            view.setText(items[i]);

            if (position % 2 == currPlayer) {
                view.setTextColor(0xff336633);
            } else {
                view.setTextColor(0xffee8200);
            }
        }

				
		return convertView;
	}
	
	public void setPlayer(int player) {
		currPlayer = player;
	}
}
