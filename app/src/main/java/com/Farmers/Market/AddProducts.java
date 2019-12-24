package com.Farmers.Market;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.function.DoubleUnaryOperator;

import static com.Farmers.Market.DatabaseManager.PRODUCT;

public class AddProducts extends AppCompatActivity {
    Button addproduct;
    EditText id,name,price,quantity,category,url;
    DatabaseManager mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        addproduct = (Button)findViewById(R.id.btnAddProduct);
        id = (EditText)findViewById(R.id.txtid);
        name = (EditText)findViewById(R.id.txtName);
        price = (EditText)findViewById(R.id.txtPrice);
        quantity = (EditText)findViewById(R.id.txtQuantity);
        category = (EditText)findViewById(R.id.txtCategory);
        url = (EditText)findViewById(R.id.imgUrl);
        mydb = new DatabaseManager(this);
        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.equals("") || name.equals("") || price.equals("") || quantity.equals("") || category.equals("") || url.equals("")) {
                    Toast.makeText(getBaseContext(), "Please Fill in all your Details", Toast.LENGTH_SHORT).show();
                }else
                {
                    boolean isinserted = mydb.insertProductData(name.toString(),Double.parseDouble(price.getText().toString()),Integer.parseInt(quantity.getText().toString()),category.toString(),url.toString());
                    if(isinserted){
                        Toast.makeText(AddProducts.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(AddProducts.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
            }
    });
}
}
