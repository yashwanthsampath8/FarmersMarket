package com.Farmers.Market;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ProductFragmentList extends ListFragment {

    public ProductAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        this.update();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new ProductAdapter(getActivity());
        setListAdapter(adapter);

        this.update();
        this.reset();
    }

    private void reset() {
        for (int i = 0; i < this.adapter.getCount() ; i++) {
            this.adapter.getItem(i).setQuantity(0);
        }
    }

    public void update() {
        // load initial product settings
        // Product.setDb(getActivity());

        ArrayList<Product> dbProducts = Product.fetchProducts();
        this.adapter.clear();
        this.adapter.addAll(dbProducts);
        this.adapter.notifyDataSetChanged();
        TextView total = getActivity().findViewById(R.id.totalAccumulator);
        total.setText(String.format(Locale.CANADA, "$%.2f", adapter.total));
    }
}