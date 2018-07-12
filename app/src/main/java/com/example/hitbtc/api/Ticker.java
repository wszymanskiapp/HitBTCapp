package com.example.hitbtc.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticker implements Parcelable {

    @SerializedName("ask")
    @Expose
    private String ask;
    @SerializedName("bid")
    @Expose
    private String bid;
    @SerializedName("last")
    @Expose
    private String last;
    @SerializedName("open")
    @Expose
    private String open;
    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("volumeQuote")
    @Expose
    private String volumeQuote;
    @SerializedName("timestamp")
    @Expose
    private Date timestamp;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    public final static Parcelable.Creator<Ticker> CREATOR = new Creator<Ticker>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Ticker createFromParcel(Parcel in) {
            return new Ticker(in);
        }

        public Ticker[] newArray(int size) {
            return (new Ticker[size]);
        }

    };

    protected Ticker(Parcel in) {
        this.ask = ((String) in.readValue((String.class.getClassLoader())));
        this.bid = ((String) in.readValue((String.class.getClassLoader())));
        this.last = ((String) in.readValue((String.class.getClassLoader())));
        this.open = ((String) in.readValue((String.class.getClassLoader())));
        this.low = ((String) in.readValue((String.class.getClassLoader())));
        this.high = ((String) in.readValue((String.class.getClassLoader())));
        this.volume = ((String) in.readValue((String.class.getClassLoader())));
        this.volumeQuote = ((String) in.readValue((String.class.getClassLoader())));
        this.timestamp = ((Date) in.readValue((String.class.getClassLoader())));
        this.symbol = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Ticker() {
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolumeQuote() {
        return volumeQuote;
    }

    public void setVolumeQuote(String volumeQuote) {
        this.volumeQuote = volumeQuote;
    }

    public String getTimestamp() {
        return String.valueOf(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timestamp));
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ask);
        dest.writeValue(bid);
        dest.writeValue(last);
        dest.writeValue(open);
        dest.writeValue(low);
        dest.writeValue(high);
        dest.writeValue(volume);
        dest.writeValue(volumeQuote);
        dest.writeValue(timestamp);
        dest.writeValue(symbol);
    }

    public int describeContents() {
        return 0;
    }

}
