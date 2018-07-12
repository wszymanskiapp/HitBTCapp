package com.example.hitbtc.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Symbol implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("baseCurrency")
    @Expose
    private String baseCurrency;
    @SerializedName("quoteCurrency")
    @Expose
    private String quoteCurrency;
    @SerializedName("quantityIncrement")
    @Expose
    private String quantityIncrement;
    @SerializedName("tickSize")
    @Expose
    private String tickSize;
    @SerializedName("takeLiquidityRate")
    @Expose
    private String takeLiquidityRate;
    @SerializedName("provideLiquidityRate")
    @Expose
    private String provideLiquidityRate;
    @SerializedName("feeCurrency")
    @Expose
    private String feeCurrency;
    public final static Parcelable.Creator<Symbol> CREATOR = new Creator<Symbol>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Symbol createFromParcel(Parcel in) {
            return new Symbol(in);
        }

        public Symbol[] newArray(int size) {
            return (new Symbol[size]);
        }

    };

    protected Symbol(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.baseCurrency = ((String) in.readValue((String.class.getClassLoader())));
        this.quoteCurrency = ((String) in.readValue((String.class.getClassLoader())));
        this.quantityIncrement = ((String) in.readValue((String.class.getClassLoader())));
        this.tickSize = ((String) in.readValue((String.class.getClassLoader())));
        this.takeLiquidityRate = ((String) in.readValue((String.class.getClassLoader())));
        this.provideLiquidityRate = ((String) in.readValue((String.class.getClassLoader())));
        this.feeCurrency = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Symbol() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public String getQuantityIncrement() {
        return quantityIncrement;
    }

    public void setQuantityIncrement(String quantityIncrement) {
        this.quantityIncrement = quantityIncrement;
    }

    public String getTickSize() {
        return tickSize;
    }

    public void setTickSize(String tickSize) {
        this.tickSize = tickSize;
    }

    public String getTakeLiquidityRate() {
        return takeLiquidityRate;
    }

    public void setTakeLiquidityRate(String takeLiquidityRate) {
        this.takeLiquidityRate = takeLiquidityRate;
    }

    public String getProvideLiquidityRate() {
        return provideLiquidityRate;
    }

    public void setProvideLiquidityRate(String provideLiquidityRate) {
        this.provideLiquidityRate = provideLiquidityRate;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(baseCurrency);
        dest.writeValue(quoteCurrency);
        dest.writeValue(quantityIncrement);
        dest.writeValue(tickSize);
        dest.writeValue(takeLiquidityRate);
        dest.writeValue(provideLiquidityRate);
        dest.writeValue(feeCurrency);
    }

    public int describeContents() {
        return 0;
    }

}