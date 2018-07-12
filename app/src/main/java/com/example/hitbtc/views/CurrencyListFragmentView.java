package com.example.hitbtc.views;

import com.example.hitbtc.api.Symbol;

import java.util.List;

public interface CurrencyListFragmentView {

    void onLoadSymbolList(List<Symbol> symbols);

    void onErrorLoadSymbolList(String msg);
}
