package com.Farmers.Market;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ListOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orders);


        final Button listProducts = findViewById(R.id.viewProductsBtn);

        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        final boolean isClerk = preferences.getBoolean("isClerk", false);

        if (isClerk) listProducts.setVisibility(View.GONE);
        Log.e("ListOrdersActivity","navigation successfull");

        listProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listProducts = new Intent(ListOrdersActivity.this, ListProductsActivity.class);
                startActivity(listProducts);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(ListOrdersActivity.this,LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
