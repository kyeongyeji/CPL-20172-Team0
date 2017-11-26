package com.jongpp.navigation;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Owner on 2017-11-23.
 */

public class NavigationEvents {

    public static class userMoved implements Parcelable{

        //Vertex v;
        int index;
        String dir;

        public userMoved(int index, String dir) {

            this.index = index;
            this.dir = dir;
        }

        public userMoved() {
        }

        protected userMoved(Parcel in) {
            index = in.readInt();
            dir = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

            dest.writeInt(index);
            dest.writeString(dir);
        }

        public void readFromParcel(Parcel dest){
            index = dest.readInt();
            dir=dest.readString();
        }
        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<userMoved> CREATOR = new Creator<userMoved>() {
            @Override
            public userMoved createFromParcel(Parcel in) {
                return new userMoved(in);
            }

            @Override
            public userMoved[] newArray(int size) {
                return new userMoved[size];
            }
        };

        public int getIndex() {
            return index;
        }

        public String getDir() {
            return dir;
        }
    }

    public static class userArrived implements Parcelable{

        public userArrived() {
        }

        protected userArrived(Parcel in) {
        }

        public static final Creator<userArrived> CREATOR = new Creator<userArrived>() {
            @Override
            public userArrived createFromParcel(Parcel in) {
                return new userArrived(in);
            }

            @Override
            public userArrived[] newArray(int size) {
                return new userArrived[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }
    }

    public static class recvsuccesfully implements Parcelable{
        String msg;

        public recvsuccesfully(String msg) {
            this.msg = msg;
        }

        public recvsuccesfully() {
        }

        protected recvsuccesfully(Parcel in) {
            msg = in.readString();
        }

        public void readFromParcel(Parcel dest){msg = dest.readString();}

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(msg);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<recvsuccesfully> CREATOR = new Creator<recvsuccesfully>() {
            @Override
            public recvsuccesfully createFromParcel(Parcel in) {
                return new recvsuccesfully(in);
            }

            @Override
            public recvsuccesfully[] newArray(int size) {
                return new recvsuccesfully[size];
            }
        };

        public String getMsg() {
            return msg;
        }
    }

    public static class naviMessage implements Parcelable{
        String msg;

        public naviMessage() {
        }

        public naviMessage(String msg) {

            this.msg = msg;
        }


        protected naviMessage(Parcel in) {
            msg = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(msg);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<naviMessage> CREATOR = new Creator<naviMessage>() {
            @Override
            public naviMessage createFromParcel(Parcel in) {
                return new naviMessage(in);
            }

            @Override
            public naviMessage[] newArray(int size) {
                return new naviMessage[size];
            }
        };

        public String getMsg() {
            return msg;
        }
    }
}
