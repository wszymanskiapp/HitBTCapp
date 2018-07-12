package com.example.hitbtc;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.hitbtc.api.Ticker;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UpdateService extends Service {

    public static final String EXTRA_SYMBOL_ID = "synmolId";
    private static final int UPDATE_TIME = 3000; //5s
    private String currencyID;
    private Runnable runnable;
    private Handler handler = new Handler();

    @Override
    public void onCreate() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!intent.hasExtra(EXTRA_SYMBOL_ID))
            throw new RuntimeException(getString(R.string.service_error));

        currencyID = intent.getStringExtra(EXTRA_SYMBOL_ID);

        runnable = new Runnable() {
            @Override
            public void run() {
                getCurrencyDetails(currencyID);
                handler.postDelayed(runnable, UPDATE_TIME);
            }
        };

        handler.post(runnable);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    private void getCurrencyDetails(String currencyID) {
        Observable<Ticker> observable = App.apiService.getTicker(currencyID).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<Ticker>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Ticker ticker) {
                EventBus.getDefault().post(ticker);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(UpdateService.this, getString(R.string.data_update_error) + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
