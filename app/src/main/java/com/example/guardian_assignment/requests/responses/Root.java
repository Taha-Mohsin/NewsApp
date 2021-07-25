package com.example.guardian_assignment.requests.responses;


import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Root implements Parcelable
{

    @SerializedName("response")
    @Expose
    private NewsListResponse response;

    @SerializedName("error")
    @Expose()
    private String error;

    public String getError() {
        return error;
    }
    public final static Creator<Root> CREATOR = new Creator<Root>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Root createFromParcel(android.os.Parcel in) {
            return new Root(in);
        }

        public Root[] newArray(int size) {
            return (new Root[size]);
        }

    }
            ;

    protected Root(android.os.Parcel in) {
        this.response = ((NewsListResponse) in.readValue((NewsListResponse.class.getClassLoader())));
    }

    public Root() {
    }

    public NewsListResponse getResponse() {
        return response;
    }

    public void setResponse(NewsListResponse response) {
        this.response = response;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Root.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("response");
        sb.append('=');
        sb.append(((this.response == null)?"<null>":this.response));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(response);
    }

    public int describeContents() {
        return 0;
    }

}