package com.example.guardian_assignment.models;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity (tableName = "article",indices = @Index(value = {"id"},unique = true))
public class Article implements Parcelable
{


    @PrimaryKey @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @Ignore
    private String type;

    @Ignore
    private String sectionId;

    @Ignore
    private String sectionName;

    @ColumnInfo(name = "webPubDate")
    private String webPublicationDate;

    @ColumnInfo(name = "webPubTitle")
    private String webTitle;

    @ColumnInfo(name = "webUrl")
    private String webUrl;

    @ColumnInfo(name = "apiUrl")
    private String apiUrl;

    @Ignore
    private Boolean isHosted;

    @Ignore
    private String pillarId;

    @Ignore
    private String pillarName;

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    @ColumnInfo(name = "Fields")
    private Fields fields;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @ColumnInfo(name="timestamp")
    private int timestamp;


    public final static Creator<Article> CREATOR = new Creator<Article>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Article createFromParcel(android.os.Parcel in) {
            return new Article(in);
        }

        public Article[] newArray(int size) {
            return (new Article[size]);
        }

    }
            ;

    protected Article(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.sectionId = ((String) in.readValue((String.class.getClassLoader())));
        this.sectionName = ((String) in.readValue((String.class.getClassLoader())));
        this.webPublicationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.webTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.webUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.apiUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.isHosted = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.pillarId = ((String) in.readValue((String.class.getClassLoader())));
        this.pillarName = ((String) in.readValue((String.class.getClassLoader())));
        this.fields = (Fields) in.readValue(Fields.class.getClassLoader());
        this.timestamp = in.readInt();
    }

    public Article() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Boolean getIsHosted() {
        return isHosted;
    }

    public void setIsHosted(Boolean isHosted) {
        this.isHosted = isHosted;
    }

    public String getPillarId() {
        return pillarId;
    }

    public void setPillarId(String pillarId) {
        this.pillarId = pillarId;
    }

    public String getPillarName() {
        return pillarName;
    }

    public Fields getFields() {
        return fields;
    }


    public void setPillarName(String pillarName) {
        this.pillarName = pillarName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Article.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("sectionId");
        sb.append('=');
        sb.append(((this.sectionId == null)?"<null>":this.sectionId));
        sb.append(',');
        sb.append("sectionName");
        sb.append('=');
        sb.append(((this.sectionName == null)?"<null>":this.sectionName));
        sb.append(',');
        sb.append("webPublicationDate");
        sb.append('=');
        sb.append(((this.webPublicationDate == null)?"<null>":this.webPublicationDate));
        sb.append(',');
        sb.append("webTitle");
        sb.append('=');
        sb.append(((this.webTitle == null)?"<null>":this.webTitle));
        sb.append(',');
        sb.append("webUrl");
        sb.append('=');
        sb.append(((this.webUrl == null)?"<null>":this.webUrl));
        sb.append(',');
        sb.append("apiUrl");
        sb.append('=');
        sb.append(((this.apiUrl == null)?"<null>":this.apiUrl));
        sb.append(',');
        sb.append("isHosted");
        sb.append('=');
        sb.append(((this.isHosted == null)?"<null>":this.isHosted));
        sb.append(',');
        sb.append("pillarId");
        sb.append('=');
        sb.append(((this.pillarId == null)?"<null>":this.pillarId));
        sb.append(',');
        sb.append("pillarName");
        sb.append('=');
        sb.append(((this.pillarName == null)?"<null>":this.pillarName));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(type);
        dest.writeValue(sectionId);
        dest.writeValue(sectionName);
        dest.writeValue(webPublicationDate);
        dest.writeValue(webTitle);
        dest.writeValue(webUrl);
        dest.writeValue(apiUrl);
        dest.writeValue(isHosted);
        dest.writeValue(pillarId);
        dest.writeValue(pillarName);
        dest.writeValue(fields);
        dest.writeValue(timestamp);
    }

    public int describeContents() {
        return 0;
    }

}