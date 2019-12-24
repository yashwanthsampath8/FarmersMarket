package com.Farmers.Market;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class ProductAdapter extends ArrayAdapter<Product> {

    public double total = 0;

    public ProductAdapter(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item, parent, false);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "ON CLICK LISTENER" + product.toString(), Toast.LENGTH_SHORT).show();

                Intent productDetails = new Intent(getContext(), ProductDetailsActivity.class);
                productDetails.putExtra("product", product);
                productDetails.putExtra("productPosition", position);
                productDetails.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                getContext().startActivity(productDetails);
            }
        });

        // Lookup view for data population
        TextView tvName = convertView.findViewById(R.id.productName);
        TextView tvQuantity = convertView.findViewById(R.id.productQuantity);
        TextView tvPrice = convertView.findViewById(R.id.productPrice);
        ImageView ivThumbnail = convertView.findViewById(R.id.productThumbnail);

        // Populate the data into the template view using the data object
        Log.d("rafaeltimbo.PRODUCT=", product.toString());

        tvName.setText(product.name);
        tvQuantity.setText(String.valueOf(product.quantity));
        tvPrice.setText(String.format(Locale.CANADA,"$%.2f", product.price * product.quantity));

        ProgressBar spinningWheel = convertView.findViewById(R.id.productProgressBar);
        new ImageDownloader(getContext()).download(ivThumbnail, spinningWheel, product.imageUrl);
        //new DownloadImageTask().setImageView(ivThumbnail).execute(product.imageUrl);

        //tvQuantity.setVisibility(View.GONE);



        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        total = 0;
        for (int i = 0; i < getCount(); i++) {
            Product product = getItem(i);
            total += product.quantity * product.price;
        }
    }
}