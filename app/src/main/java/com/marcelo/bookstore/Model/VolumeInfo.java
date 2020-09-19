
package com.marcelo.bookstore.Model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VolumeInfo implements Parcelable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("authors")
    @Expose
    private List<String> authors = null;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("industryIdentifiers")
    @Expose
    private List<IndustryIdentifier> industryIdentifiers = null;
    @SerializedName("readingModes")
    @Expose
    private ReadingModes readingModes;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("printType")
    @Expose
    private String printType;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("maturityRating")
    @Expose
    private String maturityRating;
    @SerializedName("allowAnonLogging")
    @Expose
    private Boolean allowAnonLogging;
    @SerializedName("contentVersion")
    @Expose
    private String contentVersion;
    @SerializedName("panelizationSummary")
    @Expose
    private PanelizationSummary panelizationSummary;
    @SerializedName("imageLinks")
    @Expose
    private ImageLinks imageLinks;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("previewLink")
    @Expose
    private String previewLink;
    @SerializedName("infoLink")
    @Expose
    private String infoLink;
    @SerializedName("canonicalVolumeLink")
    @Expose
    private String canonicalVolumeLink;
    public final static Parcelable.Creator<VolumeInfo> CREATOR = new Creator<VolumeInfo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public VolumeInfo createFromParcel(Parcel in) {
            return new VolumeInfo(in);
        }

        public VolumeInfo[] newArray(int size) {
            return (new VolumeInfo[size]);
        }

    }
    ;

    protected VolumeInfo(Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.subtitle = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.authors, (java.lang.String.class.getClassLoader()));
        this.publisher = ((String) in.readValue((String.class.getClassLoader())));
        this.publishedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.industryIdentifiers, (com.marcelo.bookstore.Model.IndustryIdentifier.class.getClassLoader()));
        this.readingModes = ((ReadingModes) in.readValue((ReadingModes.class.getClassLoader())));
        this.pageCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.printType = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.categories, (java.lang.String.class.getClassLoader()));
        this.maturityRating = ((String) in.readValue((String.class.getClassLoader())));
        this.allowAnonLogging = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.contentVersion = ((String) in.readValue((String.class.getClassLoader())));
        this.panelizationSummary = ((PanelizationSummary) in.readValue((PanelizationSummary.class.getClassLoader())));
        this.imageLinks = ((ImageLinks) in.readValue((ImageLinks.class.getClassLoader())));
        this.language = ((String) in.readValue((String.class.getClassLoader())));
        this.previewLink = ((String) in.readValue((String.class.getClassLoader())));
        this.infoLink = ((String) in.readValue((String.class.getClassLoader())));
        this.canonicalVolumeLink = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VolumeInfo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<IndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public ReadingModes getReadingModes() {
        return readingModes;
    }

    public void setReadingModes(ReadingModes readingModes) {
        this.readingModes = readingModes;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public Boolean getAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging(Boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public PanelizationSummary getPanelizationSummary() {
        return panelizationSummary;
    }

    public void setPanelizationSummary(PanelizationSummary panelizationSummary) {
        this.panelizationSummary = panelizationSummary;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(subtitle);
        dest.writeList(authors);
        dest.writeValue(publisher);
        dest.writeValue(publishedDate);
        dest.writeValue(description);
        dest.writeList(industryIdentifiers);
        dest.writeValue(readingModes);
        dest.writeValue(pageCount);
        dest.writeValue(printType);
        dest.writeList(categories);
        dest.writeValue(maturityRating);
        dest.writeValue(allowAnonLogging);
        dest.writeValue(contentVersion);
        dest.writeValue(panelizationSummary);
        dest.writeValue(imageLinks);
        dest.writeValue(language);
        dest.writeValue(previewLink);
        dest.writeValue(infoLink);
        dest.writeValue(canonicalVolumeLink);
    }

    public int describeContents() {
        return  0;
    }

}
