package com.marcelo.bookstore.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetailPrice_ implements Parcelable
{

    @SerializedName("amountInMicros")
    @Expose
    private Integer amountInMicros;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    public final static Parcelable.Creator<RetailPrice_> CREATOR = new Creator<RetailPrice_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RetailPrice_ createFromParcel(Parcel in) {
            return new RetailPrice_(in);
        }

        public RetailPrice_[] newArray(int size) {
            return (new RetailPrice_[size]);
        }

    }
    ;

    protected RetailPrice_(Parcel in) {
        this.amountInMicros = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.currencyCode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RetailPrice_() {
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
