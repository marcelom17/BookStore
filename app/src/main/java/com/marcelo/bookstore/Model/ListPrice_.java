package com.marcelo.bookstore.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPrice_ implements Parcelable
{

    @SerializedName("amountInMicros")
    @Expose
    private Integer amountInMicros;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    public final static Parcelable.Creator<ListPrice_> CREATOR = new Creator<ListPrice_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ListPrice_ createFromParcel(Parcel in) {
            return new ListPrice_(in);
        }

        public ListPrice_[] newArray(int size) {
            return (new ListPrice_[size]);
        }

    }
    ;

    protected ListPrice_(Parcel in) {
        this.amountInMicros = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.currencyCode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ListPrice_() {
    }

    public Integer getAmountInMicros() {
        return amountInMicros;
    }

    public void setAmountInMicros(Integer amountInMicros) {
        this.amountInMicros = amountInMicros;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(amountInMicros);
        dest.writeValue(currencyCode);
    }

    public int describeContents() {
        return  0;
    }

}
