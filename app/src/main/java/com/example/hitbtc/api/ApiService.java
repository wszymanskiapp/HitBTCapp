package com.example.hitbtc.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("api/2/public/symbol")
    Observable<Symbol[]> getSymbolList();

    @GET("api/2/public/ticker/{symbol}")
    Observable<Ticker> getTicker(@Path("symbol") String symbol);
}
