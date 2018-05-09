package xyz.mattjashworth.android.timelineview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class AdModel implements Parcelable {

    private String mMessage;
    private String mDate;
    private TimelineStage mTime;

    public AdModel() {
    }

    public AdModel(String mMessage, String mDate, TimelineStage mTime) {
        this.mMessage = mMessage;
        this.mDate = mDate;
        this.mTime = mTime;
    }

    public String getMessage() {
        return mMessage;
    }

    public void semMessage(String message) {
        this.mMessage = message;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public TimelineStage getStatus() {
        return mTime;
    }

    public void setStatus(TimelineStage mTime) {
        this.mTime = mTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mMessage);
        dest.writeString(this.mDate);
        dest.writeInt(this.mTime == null ? -1 : this.mTime.ordinal());
    }

    protected AdModel(Parcel in) {
        this.mMessage = in.readString();
        this.mDate = in.readString();
        int tmpmTime = in.readInt();
        this.mTime = tmpmTime == -1 ? null : TimelineStage.values()[tmpmTime];
    }

    public static final Creator<AdModel> CREATOR = new Creator<AdModel>() {
        @Override
        public AdModel createFromParcel(Parcel source) {
            return new AdModel(source);
        }

        @Override
        public AdModel[] newArray(int size) {
            return new AdModel[size];
        }
    };
}
