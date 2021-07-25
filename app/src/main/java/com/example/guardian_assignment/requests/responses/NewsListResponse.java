package com.example.guardian_assignment.requests.responses;

import android.os.Parcelable;

import com.example.guardian_assignment.models.Article;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsListResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userTier")
    @Expose
    private String userTier;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("startIndex")
    @Expose
    private Integer startIndex;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("orderBy")
    @Expose
    private String orderBy;
    @SerializedName("results")
    @Expose
    private List<Article> articles = null;

    public final static Creator<NewsListResponse> CREATOR = new Creator<NewsListResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NewsListResponse createFromParcel(android.os.Parcel in) {
            return new NewsListResponse(in);
        }

        public NewsListResponse[] newArray(int size) {
            return (new NewsListResponse[size]);
        }

    }
            ;

    protected NewsListResponse(android.os.Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.userTier = ((String) in.readValue((String.class.getClassLoader())));
        this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.startIndex = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.pageSize = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.currentPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.pages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderBy = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.articles, (Article.class.getClassLoader()));
    }

    public NewsListResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserTier() {
        return userTier;
    }

    public void setUserTier(String userTier) {
        this.userTier = userTier;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<Article> getResults() {
        return articles;
    }

    public void setResults(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(NewsListResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("userTier");
        sb.append('=');
        sb.append(((this.userTier == null)?"<null>":this.userTier));
        sb.append(',');
        sb.append("total");
        sb.append('=');
        sb.append(((this.total == null)?"<null>":this.total));
        sb.append(',');
        sb.append("startIndex");
        sb.append('=');
        sb.append(((this.startIndex == null)?"<null>":this.startIndex));
        sb.append(',');
        sb.append("pageSize");
        sb.append('=');
        sb.append(((this.pageSize == null)?"<null>":this.pageSize));
        sb.append(',');
        sb.append("currentPage");
        sb.append('=');
        sb.append(((this.currentPage == null)?"<null>":this.currentPage));
        sb.append(',');
        sb.append("pages");
        sb.append('=');
        sb.append(((this.pages == null)?"<null>":this.pages));
        sb.append(',');
        sb.append("orderBy");
        sb.append('=');
        sb.append(((this.orderBy == null)?"<null>":this.orderBy));
        sb.append(',');
        sb.append("results");
        sb.append('=');
        sb.append(((this.articles == null)?"<null>":this.articles));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(userTier);
        dest.writeValue(total);
        dest.writeValue(startIndex);
        dest.writeValue(pageSize);
        dest.writeValue(currentPage);
        dest.writeValue(pages);
        dest.writeValue(orderBy);
        dest.writeList(articles);
    }

    public int describeContents() {
        return 0;
    }

}