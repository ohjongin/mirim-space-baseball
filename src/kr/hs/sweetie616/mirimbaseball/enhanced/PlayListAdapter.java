package kr.hs.sweetie616.mirimbaseball.enhanced;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.hs.sweetie616.mirimbaseball.R;
import kr.hs.sweetie616.mirimbaseball.enhanced.model.PlayRecord;

public class PlayListAdapter extends ArrayAdapter<PlayRecord> {
    protected int currPlayer = 0;

    public PlayListAdapter(Context context, ArrayList<PlayRecord> items) {
        super(context, R.layout.listitem_play_enhanced, items);
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
            convertView = inflator.inflate(R.layout.listitem_play_enhanced, null);
        }

        int color = 0x00ffffff;

        if (position % 2 == currPlayer) {
            color = (0xff336633);
        } else {
            color = (0xffee8200);
        }

        PlayRecord playRecord = getItem(position);

        TextView tvCount = (TextView) convertView.findViewById(R.id.tv_count);
        tvCount.setText(playRecord.getCount() + "회전");
        tvCount.setTextColor(color);

        TextView tvPlayer = (TextView) convertView.findViewById(R.id.tv_player);
        tvPlayer.setText(playRecord.getPlayerName());
        tvPlayer.setTextColor(color);

        TextView tvNumbers = (TextView) convertView.findViewById(R.id.tv_numbers);
        tvNumbers.setText(playRecord.getNumbers());
        tvNumbers.setTextColor(color);

        for (int i = 0; i < 3; i++) {
            int id = getContext().getResources().getIdentifier("iv_strike" + i, "id", getContext().getPackageName());
            View view = convertView.findViewById(id);
            view.setEnabled(i < playRecord.getStrike());

            id = getContext().getResources().getIdentifier("iv_ball" + i, "id", getContext().getPackageName());
            view = convertView.findViewById(id);
            view.setEnabled(i < playRecord.getBall());

            id = getContext().getResources().getIdentifier("iv_out" + i, "id", getContext().getPackageName());
            view = convertView.findViewById(id);
            view.setEnabled(i < playRecord.getOut());
        }

		return convertView;
	}
	
	public void setPlayer(int player) {
		currPlayer = player;
	}
}
