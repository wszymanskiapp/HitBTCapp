package com.example.hitbtc.Presenters;

import com.example.hitbtc.App;
import com.example.hitbtc.api.Symbol;
import com.example.hitbtc.views.CurrencyListFragmentView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CurrencyListPresenterImpl implements CurrencyListPresenter {

    private CurrencyListFragmentView fragmentView;
    private List<Symbol> symbolList;

    public CurrencyListPresenterImpl(CurrencyListFragmentView fragmentView) {
        this.fragmentView = fragmentView;
    }

    @Override
    public void getSymbolList() {
        Observable<Symbol[]> observable = App.apiService.getSymbolList().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<Symbol[]>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Symbol[] symbols) {
                symbolList = new ArrayList<>();

                for (int i = 0; i < symbols.length; i++) {
                    symbolList.add(symbols[i]);
                }

                if (fragmentView != null)
                    fragmentView.onLoadSymbolList(symbolList);
            }

            @Override
            public void onError(Throwable e) {
                if (fragmentView != null)
                    fragmentView.onErrorLoadSymbolList(e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
