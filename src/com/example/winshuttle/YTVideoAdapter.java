package com.example.winshuttle;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class YTVideoAdapter extends ArrayAdapter<YTVideo> {

	List<YTVideo> videos;
	int textViewResourceId;
	
     
	public YTVideoAdapter(Context context, int textViewResourceId,
			List<YTVideo> objects) {
		super(context, textViewResourceId, objects);
		this.videos = objects;
		this.textViewResourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflator = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflator.inflate(textViewResourceId, parent, false);
		}
		flipProgressBarView(view, false);
		return view;
	}

	public void flipProgressBarView(View view, boolean showPrgressBar) {
		view.findViewById(R.id.video_single_view).setVisibility(
				showPrgressBar ? View.GONE : View.VISIBLE);
		view.findViewById(R.id.progressBar).setVisibility(
				showPrgressBar ? View.VISIBLE : View.GONE);
	}

}
