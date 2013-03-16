package com.example.winshuttle;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.support.v4.app.NavUtils;



public class YoutubeActivity extends Activity {

	private static final String TAG = YoutubeActivity.class.getSimpleName();
	
	private YTVideoAdapter adapter;
	
	private boolean loadingMore = false;
	private boolean hasMore = true;
	
	@SuppressLint("NewApi")
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youtube);
		// Show the Up button in the action bar.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		GridView videoContainer = (GridView) findViewById(R.id.bf_video_container);
		List<YTVideo> videos = loadMoreVideo(0);
		adapter = new YTVideoAdapter(getBaseContext(),
				R.layout.video_feed_list_item_view, videos);
		videoContainer.setAdapter(adapter);
		videoContainer.setOnScrollListener(getOnScrollListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_youtube, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private OnScrollListener getOnScrollListener() {
		return new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// last item that is visible
				int lastInScreen = firstVisibleItem + visibleItemCount;
				if (hasMore && (lastInScreen == totalItemCount) && !loadingMore) {
					loadingMore = true;
					Log.d(TAG, "loadingMore items :" + loadingMore);
					Toast.makeText(getApplicationContext(),
							"Loading more items.", Toast.LENGTH_LONG).show();
					Log.d(TAG, "visible item: " + visibleItemCount);
					adapter.flipProgressBarView(
							view.getChildAt(visibleItemCount - 1), true);
					/*
					 * List<YTVideo> videos = loadMoreVideo(totalItemCount); if
					 * (videos.isEmpty()) {
					 * Toast.makeText(getApplicationContext(),
					 * "NO MORE ITEM FOUND.", Toast.LENGTH_LONG) .show();
					 * hasMore = false; return; }
					 * 
					 * adapter.addAll(videos);
					 */
					// TODO: loadmore items here in UIThread
					// Thread thread = new Thread(null, loadMoreListItems);
					// thread.start();
				}
			}
		};
	}
	
	private List<YTVideo> loadMoreVideo(int total) {

		List<YTVideo> videos = new ArrayList<YTVideo>();
		if (total >= 20) {
			loadingMore = false;
			return videos;
		}
		for (int i = 0; i <= 5; i++) {
			videos.add(new YTVideo());
		}
		try {
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		loadingMore = false;
		return videos;
	}
	
	public void NavigateMain(View view)
	{
	
	}
}
