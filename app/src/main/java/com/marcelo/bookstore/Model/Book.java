package com.marcelo.bookstore.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book implements Parcelable
{

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("selfLink")
    @Expose
    private String selfLink;
    @SerializedName("volumeInfo")
    @Expose
    private VolumeInfo volumeInfo;
    @SerializedName("saleInfo")
    @Expose
    private SaleInfo saleInfo;
    @SerializedName("accessInfo")
    @Expose
    private AccessInfo accessInfo;
    @SerializedName("searchInfo")
    @Expose
    private SearchInfo searchInfo;

    private boolean isFavorite;

    public final static Parcelable.Creator<Book> CREATOR = new Creator<Book>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return (new Book[size]);
        }

    }
    ;

    protected Book(Parcel in) {
        this.kind = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.etag = ((String) in.readValue((String.class.getClassLoader())));
        this.selfLink = ((String) in.readValue((String.class.getClassLoader())));
        this.volumeInfo = ((VolumeInfo) in.readValue((VolumeInfo.class.getClassLoader())));
        this.saleInfo = ((SaleInfo) in.readValue((SaleInfo.class.getClassLoader())));
        this.accessInfo = ((AccessInfo) in.readValue((AccessInfo.class.getClassLoader())));
        this.searchInfo = ((SearchInfo) in.readValue((SearchInfo.class.getClassLoader())));
        this.isFavorite = in.readBoolean();
    }

    public Book() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

    public SearchInfo getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(SearchInfo searchInfo) {
        this.searchInfo = searchInfo;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(kind);
        dest.writeValue(id);
        dest.writeValue(etag);
        dest.writeValue(selfLink);
        dest.writeValue(volumeInfo);
        dest.writeValue(saleInfo);
        dest.writeValue(accessInfo);
        dest.writeValue(searchInfo);
        dest.writeBoolean(isFavorite);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", volumeInfo=" + volumeInfo +
                ", saleInfo=" + saleInfo +
                ", isFavorite=" + isFavorite +
                '}'+"\n";
    }
}
