package com.jongpp.receiver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Owner on 2017-11-21.
 */

public class VlcReceiverEvents_ {
    public static class receiverSelected implements Parcelable{

        int selected;
        //1이면 selected.
        public receiverSelected(){}

        public receiverSelected(int selected) {
            this.selected = selected;
        }

        public receiverSelected(Parcel source){ selected = source.readInt(); }

        public static final Creator<receiverSelected> CREATOR = new Creator<receiverSelected>() {
            @Override
            public receiverSelected createFromParcel(Parcel in) {
                return new receiverSelected(in);
            }

            @Override
            public receiverSelected[] newArray(int size) {
                return new receiverSelected[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(selected);
        }

        public void readFromParcel(Parcel dest){ selected = dest.readInt();}

        public int getSelected() {
            return selected;
        }
    }

    public static class receiverHasError implements Parcelable{
        String msg;

        public receiverHasError() {
        }

        public receiverHasError(String msg) {
            this.msg = msg;
        }


        protected receiverHasError(Parcel in) {
            msg = in.readString();
        }

        public static final Creator<receiverHasError> CREATOR = new Creator<receiverHasError>() {
            @Override
            public receiverHasError createFromParcel(Parcel in) {
                return new receiverHasError(in);
            }

            @Override
            public receiverHasError[] newArray(int size) {
                return new receiverHasError[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(msg);
        }

        public void readFromParcel(Parcel dest){ msg = dest.readString();}


        public String getMsg() {
            return msg;
        }
    }

    public static class receiverHasMessage implements Parcelable{
        String msg;

        public receiverHasMessage(String msg) {
            this.msg = msg;
        }

        public receiverHasMessage() {
        }

        protected receiverHasMessage(Parcel in) {
            msg = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(msg);
        }

        public void readFromParcel(Parcel dest){msg = dest.readString();}

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<receiverHasMessage> CREATOR = new Creator<receiverHasMessage>() {
            @Override
            public receiverHasMessage createFromParcel(Parcel in) {
                return new receiverHasMessage(in);
            }

            @Override
            public receiverHasMessage[] newArray(int size) {
                return new receiverHasMessage[size];
            }
        };

        public String getMsg() {
            return msg;
        }
    }

    public static class receivedSuccessfully implements Parcelable{

        String msg;

        public receivedSuccessfully(String msg) {
            this.msg = msg;
        }

        public receivedSuccessfully() {
        }

        protected receivedSuccessfully(Parcel in) {
            msg = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(msg);
        }

        public void readFromParcel(Parcel dest){msg = dest.readString();}

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<receivedSuccessfully> CREATOR = new Creator<receivedSuccessfully>() {
            @Override
            public receivedSuccessfully createFromParcel(Parcel in) {
                return new receivedSuccessfully(in);
            }

            @Override
            public receivedSuccessfully[] newArray(int size) {
                return new receivedSuccessfully[size];
            }
        };

        public String getMsg() {
            return msg;
        }
    }
}
