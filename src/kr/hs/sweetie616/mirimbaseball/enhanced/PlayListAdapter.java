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
import kr.hs.sweetie616.mirimbaseball.enhanced.model.Stage;

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

        View layout_strike = convertView.findViewById(R.id.layout_stike);
        View layout_ball = convertView.findViewById(R.id.layout_ball);
        View layout_out = convertView.findViewById(R.id.layout_out);
        if (position % 2 == currPlayer) {
            color = (0xff336633);
            layout_strike.setVisibility(View.INVISIBLE);
            layout_ball.setVisibility(View.INVISIBLE);
            layout_out.setVisibility(View.INVISIBLE);
        } else {
            color = (0xffee8200);
            layout_strike.setVisibility(View.VISIBLE);
            layout_ball.setVisibility(View.VISIBLE);
            layout_out.setVisibility(View.VISIBLE);
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


        for (int i = 0; i < Stage.MAX_NUMBER_LENGTH; i++) {
            int id = getContext().getResources().getIdentifier("iv_strike" + i, "id", getContext().getPackageName());
            View view = convertView.findViewById(id);
            view.setEnabled(i < playRecord.getStrike());
            view.setVisibility(i < Stage.getNumberLength() ? View.VISIBLE : View.GONE);

            id = getContext().getResources().getIdentifier("iv_ball" + i, "id", getContext().getPackageName());
            view = convertView.findViewById(id);
            view.setEnabled(i < playRecord.getBall());
            view.setVisibility(i < Stage.getNumberLength() ? View.VISIBLE : View.GONE);

            id = getContext().getResources().getIdentifier("iv_out" + i, "id", getContext().getPackageName());
            view = convertView.findViewById(id);
            view.setEnabled(i < playRecord.getOut());
            view.setVisibility(i < Stage.getNumberLength() ? View.VISIBLE : View.GONE);

            id = getContext().getResources().getIdentifier("gap_strike" + i, "id", getContext().getPackageName());
            view = convertView.findViewById(id);
            view.setVisibility(i < Stage.getNumberLength() ? View.VISIBLE : View.GONE);

            id = getContext().getResources().getIdentifier("gap_ball" + i, "id", getContext().getPackageName());
            view = convertView.findViewById(id);
            view.setVisibility(i < Stage.getNumberLength() ? View.VISIBLE : View.GONE);

            id = getContext().getResources().getIdentifier("gap_out" + i, "id", getContext().getPackageName());
            view = convertView.findViewById(id);
            view.setVisibility(i < Stage.getNumberLength() ? View.VISIBLE : View.GONE);
        }

		return convertView;
	}
	
	public void setPlayer(int player) {
		currPlayer = player;
	}
}
