package xyz.mattjashworth.android.canvasapi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import xyz.mattjashworth.android.timelineview.TimelineView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MattJAshworth 12/05/2017.
 */
public class TimeLineViewHolder extends RecyclerView.ViewHolder {

    public TextView mDate;
    public TextView mMessage;
    public  TimelineView mTimelineView;

    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);

        mDate = (TextView) itemView.findViewById(R.id.text_timeline_date);
        mMessage = (TextView) itemView.findViewById(R.id.text_timeline_title);
        mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);

        mTimelineView.initLine(viewType);
    }
}
