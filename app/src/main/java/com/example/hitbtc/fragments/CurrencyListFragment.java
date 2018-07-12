package com.example.hitbtc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hitbtc.MainActivity;
import com.example.hitbtc.Presenters.CurrencyListPresenter;
import com.example.hitbtc.Presenters.CurrencyListPresenterImpl;
import com.example.hitbtc.R;
import com.example.hitbtc.adapters.CurrencyListAdapter;
import com.example.hitbtc.api.Symbol;
import com.example.hitbtc.views.CurrencyListFragmentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyListFragment extends Fragment implements CurrencyListFragmentView {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.currency_list)
    ListView currencyListView;
    @BindView(R.id.error_msg)
    TextView errorMsg;

    private CurrencyListPresenter listPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_list, container, false);
        ButterKnife.bind(this, view);
        swipeRefresh.setEnabled(false);

        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.list_of_currency_pairs));

        listPresenter = new CurrencyListPresenterImpl(this);
        currencyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Symbol symbol = (Symbol) adapterView.getItemAtPosition(pos);

                DetailsCurrencyFragment detailsCurrencyFragment = new DetailsCurrencyFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(DetailsCurrencyFragment.EXTRA_CURRENCY, symbol);
                detailsCurrencyFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, detailsCurrencyFragment)
                        .addToBackStack(detailsCurrencyFragment.getClass().getSimpleName()).commit();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (swipeRefresh != null || !swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(true);
        }

        if (((MainActivity) getActivity()).isOnline()) {
            listPresenter.getSymbolList();
        } else {
            onErrorLoadSymbolList(getString(R.string.no_internet_connection));
        }
    }

    @Override
    public void onLoadSymbolList(List<Symbol> symbols) {
        if (swipeRefresh != null)
            swipeRefresh.setRefreshing(false);

        if (errorMsg.getVisibility() == View.VISIBLE)
            errorMsg.setVisibility(View.INVISIBLE);

        CurrencyListAdapter adapter = new CurrencyListAdapter(getActivity(), symbols);
        currencyListView.setAdapter(adapter);
    }

    @Override
    public void onErrorLoadSymbolList(String msg) {
        if (swipeRefresh != null)
            swipeRefresh.setRefreshing(false);
        errorMsg.setText(getString(R.string.error_message) + "\n" + msg);
        if (errorMsg.getVisibility() == View.INVISIBLE)
            errorMsg.setVisibility(View.VISIBLE);
    }
}
