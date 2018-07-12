package com.example.hitbtc.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hitbtc.MainActivity;
import com.example.hitbtc.R;
import com.example.hitbtc.UpdateService;
import com.example.hitbtc.api.Symbol;
import com.example.hitbtc.api.Ticker;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsCurrencyFragment extends Fragment {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.last)
    TextView last;
    @BindView(R.id.ask)
    TextView ask;
    @BindView(R.id.bid)
    TextView bid;
    @BindView(R.id.open)
    TextView open;
    @BindView(R.id.low)
    TextView low;
    @BindView(R.id.high)
    TextView high;
    @BindView(R.id.volume)
    TextView volume;
    @BindView(R.id.volume_quote)
    TextView volumeQuote;

    private static final String ASSETS_PATH = "file:///android_asset/";
    public static final String EXTRA_CURRENCY = "currency";
    private Symbol currency;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_currency, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() == null || !getArguments().containsKey(EXTRA_CURRENCY)) {
            throw new RuntimeException("No currency id!");
        }

        currency = getArguments().getParcelable(EXTRA_CURRENCY);

        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(currency.getBaseCurrency() + "/" + currency.getQuoteCurrency());
        Picasso.get().load(ASSETS_PATH + currency.getBaseCurrency().toLowerCase() + ".png").error(R.drawable.no_img32).fit().into(imageView);
        intent = new Intent(getActivity(), UpdateService.class).putExtra(UpdateService.EXTRA_SYMBOL_ID, currency.getId());

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Ticker ticker) {
        time.setText(ticker.getTimestamp());
        last.setText(ticker.getLast());
        ask.setText(ticker.getAsk());
        bid.setText(ticker.getBid());
        open.setText(ticker.getOpen());
        low.setText(ticker.getLow());
        high.setText(ticker.getHigh());
        volume.setText(ticker.getVolume());
        volumeQuote.setText(ticker.getVolumeQuote());

    }

    @Override
    public void onResume() {
        super.onResume();
        if (((MainActivity) getActivity()).isOnline())
            getActivity().startService(intent);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().stopService(intent);
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
