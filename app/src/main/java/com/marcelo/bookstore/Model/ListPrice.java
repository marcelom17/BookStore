
package com.marcelo.bookstore.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPrice implements Parcelable
{

    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    public final static Parcelable.Creator<ListPrice> CREATOR = new Creator<ListPrice>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ListPrice createFromParcel(Parcel in) {
            return new ListPrice(in);
        }

        public ListPrice[] newArray(int size) {
            return (new ListPrice[size]);
        }

    }
    ;

    protected ListPrice(Parcel in) {
        this.amount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.currencyCode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ListPrice() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(amount);
        dest.writeValue(currencyCode);
    }

    public int describeContents() {
        return  0;
    }

}
