package com.example.administrator.mytrain.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/11/18 0018.
 */

public class ObjectToJson implements Parcelable {
    public String name;
    public String address;
    public String location;
    public String age;

    protected ObjectToJson(Parcel in) {
        name = in.readString();
        address = in.readString();
        location = in.readString();
        age = in.readString();
    }

    private ObjectToJson(Builder builder) {
        name = builder.name;
        address = builder.address;
        location = builder.location;
        age = builder.age;
    }

    @Override
    public String toString() {
        return "bean-->"+name+"/"+address+"/"+location+"/"+age;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(location);
        dest.writeString(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ObjectToJson> CREATOR = new Creator<ObjectToJson>() {
        @Override
        public ObjectToJson createFromParcel(Parcel in) {
            return new ObjectToJson(in);
        }

        @Override
        public ObjectToJson[] newArray(int size) {
            return new ObjectToJson[size];
        }
    };

    public static final class Builder {
        private String name;
        private String address;
        private String location;
        private String age;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder location(String val) {
            location = val;
            return this;
        }

        public Builder age(String val) {
            age = val;
            return this;
        }

        public ObjectToJson build() {
            return new ObjectToJson(this);
        }
    }
}
