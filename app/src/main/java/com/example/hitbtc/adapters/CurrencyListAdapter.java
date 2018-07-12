package com.example.hitbtc.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hitbtc.R;
import com.example.hitbtc.api.Symbol;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyListAdapter extends ArrayAdapter<Symbol> {

    private List<Symbol> symbolList;
    private LayoutInflater inflater;

    public CurrencyListAdapter(@NonNull Context context, @NonNull List<Symbol> symbolList) {
        super(context, R.layout.item_currency_list, symbolList);
        this.symbolList = symbolList;

        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return symbolList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_currency_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Symbol symbol = symbolList.get(position);
        Picasso.get().load("file:///android_asset/" + symbol.getBaseCurrency().toLowerCase() + ".png").error(R.drawable.no_img32).fit().into(holder.ico);
        holder.baseCurrency.setText(symbol.getBaseCurrency());
        holder.quoteCurrency.setText(symbol.getQuoteCurrency());
        holder.quantityIncrement.setText(symbol.getQuantityIncrement());
        holder.tickSize.setText(symbol.getTickSize());

        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.imageView)
        ImageView ico;
        @BindView(R.id.base_currency)
        TextView baseCurrency;
        @BindView(R.id.quote_currency)
        TextView quoteCurrency;
        @BindView(R.id.quantity_increment)
        TextView quantityIncrement;
        @BindView(R.id.tick_size)
        TextView tickSize;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
