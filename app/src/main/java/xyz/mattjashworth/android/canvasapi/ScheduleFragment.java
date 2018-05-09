package xyz.mattjashworth.android.canvasapi;


import android.app.ActionBar;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import xyz.mattjashworth.android.timelineview.TimelineStage;
import xyz.mattjashworth.android.timelineview.Orientation;
import xyz.mattjashworth.android.timelineview.TimeLineModel;

/**
 * Created by mattjashworth on 07/05/2018.
 * For University of Hull Tour Guide Manager
 * Originally CanvasAPI.
 */

public class ScheduleFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    private Orientation mOrientation;
    private boolean mWithLinePadding;
    private int timeline;

    protected String[] schedule;
    protected String[] scheduleTimeline;
    String [][] scheduleArray;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        mOrientation = Orientation.VERTICAL;
        mWithLinePadding = true;
        timeline = FragmentExperience.EXTRA_TIMELINE;


        getActivity().setTitle(mOrientation == Orientation.HORIZONTAL ? getResources().getString(R.string.schedule) : getResources().getString(R.string.schedule));


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        Resources res = getResources();
        schedule = res.getStringArray(R.array.schedule);
        scheduleTimeline = res.getStringArray(R.array.schedule_timeline);

        initView();

        return rootView;
    }


    private LinearLayoutManager getLinearLayoutManager() {
        if (mOrientation == Orientation.HORIZONTAL) {
            return new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        } else {
            return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        }
    }

    private void initView() {
        setDataListItems();
        mTimeLineAdapter = new TimeLineAdapter(mDataList, mOrientation, mWithLinePadding);
        mRecyclerView.setAdapter(mTimeLineAdapter);
    }

    private void setDataListItems() {

            for (int i = 0; i < schedule.length; i++) {

                if(scheduleTimeline[i].contains("FUTURE DATE")) {

                    mDataList.add(new TimeLineModel(schedule[i], scheduleTimeline[i], TimelineStage.FUTURE));
                } else {
                    mDataList.add(new TimeLineModel(schedule[i], scheduleTimeline[i], TimelineStage.PAST));
                }

            }

            //addSched();


        }


        private void addSched() {


            final ParseQuery<ParseObject> query = ParseQuery.getQuery("schedule");

// Retrieve the object by id
            query.getInBackground("XDC3G0WHHA", new GetCallback<ParseObject>() {
                public void done(ParseObject timeline, ParseException e) {
                    if (e == null) {
                        Log.e("SUCCESS", "Yay");
                        // Now let's update it with some new data. In this case, only cheatMode and score
                        // will get sent to the Parse Cloud. playerName hasn't changed.
                        //timeline.addAll("timeline", mDataList);
                        timeline.put("timeline", scheduleArray);
                       timeline.saveInBackground();
                    }
                    if (e != null) {
                        Log.e("OBJECT", e.toString());
                    }
                }
            });



        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Menu
        switch (item.getItemId()) {
            //When home is clicked
            case android.R.id.home:
                //Do Something
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if(mOrientation!=null)
            savedInstanceState.putSerializable(FragmentExperience.EXTRA_ORIENTATION, mOrientation);
        super.onSaveInstanceState(savedInstanceState);
    }


}
