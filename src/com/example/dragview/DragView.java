package com.example.dragview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class DragView extends View {

	private float mLastTouchX;
	private float mLastTouchY;

	private float mDeltaX;
	private float mDeltaY;

	public DragView(Context context) {
		super(context);
		init();
	}

	public DragView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();

				mLastTouchX = event.getRawX();
				mLastTouchY = event.getRawY();

				switch (action) {
				case MotionEvent.ACTION_DOWN: {
					RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) getLayoutParams();
					mDeltaX = mLastTouchX - lParams.leftMargin;
					mDeltaY = mLastTouchY - lParams.topMargin;

					break;
				}
				case MotionEvent.ACTION_MOVE: {
					mLastTouchX = event.getRawX();
					mLastTouchY = event.getRawY();

					final RelativeLayout.LayoutParams params = (LayoutParams) getLayoutParams();
					params.leftMargin = (int) (mLastTouchX - mDeltaX);
					params.topMargin = (int) (mLastTouchY - mDeltaY);
					setLayoutParams(params);

					break;
				}
				}
				invalidate();

				return true;
			}
		});
	}

}
