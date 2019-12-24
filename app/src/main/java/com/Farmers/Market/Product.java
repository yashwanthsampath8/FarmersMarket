package com.Farmers.Market;

import android.content.ContentValues;
import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    public String id;
    public String name;
    public Double price;
    public Integer quantity;
    public String category;
    public String imageUrl;

    private static DatabaseManager db;
    public static final String tbl_product_fields[] = {
            "productId", "name", "price", "quantity", "category", "imageUrl"
    };
    public static final int size = tbl_product_fields.length;

    public Product(String id, String name, Double price, Integer quantity, String category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Product(List record) {
        this(record.get(0).toString(),
             record.get(1).toString(),
             Double.valueOf(record.get(2).toString()),
             Integer.valueOf(record.get(3).toString()),
             record.get(4).toString(),
             record.get(5).toString());
    }

    public static void setDb(Context context) {
        db = new DatabaseManager(context);
        initProducts();
    }

    @Override
    public String toString() {
        return  "Product.toString()>> id: " + this.id + ", name: " + this.name +
                ", category: " + this.category +
                ", price: " + this.price +
                ", quantity: " + this.quantity +
                ", imageUrl: " + this.imageUrl;
    }

    public static void initProducts() {
        db.recreateOneTable(DatabaseManager.PRODUCT);
        new Product( "1", "Potato", 18.00, 0, "Vegetables",
            "https://images-na.ssl-images-amazon.com/images/I/81459bqY2HL._SX679_.jpg")
            .addToDatabase();
        new Product( "2", "Onion", 19.99, 0, "Vegetables",
            "https://img.etimg.com/thumb/width-640,height-480,imgsize-876498,resizemode-1,msid-67172581/despite-scheme-onions-plunge-to-rs-1-50-in-maharashtra.jpg")
            .addToDatabase();
        new Product( "3", "Ginger", 109.99, 0, "Vegetables",
            "https://cdn-prod.medicalnewstoday.com/content/images/hero/265/265990/265990_1100.jpg")
            .addToDatabase();
        new Product( "4", "carrot", 79.99, 0, "Vegetables",
            "https://cms.splendidtable.org/sites/default/files/styles/w2000/public/ThinkstockPhotos-507126001_0.jpg?itok=_hsOnNh3")
            .addToDatabase();
        new Product("5", "Beans", 19.99, 0, "Vegetables",
            "https://thefamilydinnerproject.org/wp-content/uploads/2013/09/Green-bean-lime-633x326.jpg")
            .addToDatabase();
        new Product("6", "Tomato", 79.99, 0, "Vegetables",
                "https://edge.bonnieplants.com/www/uploads/20190122192650/Solanum_MountainMerit_BonniePlants_v2.jpg")
            .addToDatabase();
    }

    String[] getRecord() {
        return new String[] {
            this.id,
            this.name,
            String.valueOf(this.price),
            String.valueOf(this.quantity),
            this.category,
            this.imageUrl
        };
    }

    public static Product queryProductByName(String name) {
        List productList = db.queryTable(DatabaseManager.tables[DatabaseManager.PRODUCT], "name = ?", new String[]{ name } );
        return new Product(
            (List) productList.get(0)
        );
    }

    public static Product queryProductById(String id) {
        List productList = db.queryTable(DatabaseManager.tables[DatabaseManager.PRODUCT], "productId = ?", new String[] { id });
        return new Product(
                (List) productList.get(0)
        );
    }


    public void addToDatabase() {
        db.addRecord(new ContentValues(), DatabaseManager.tables[DatabaseManager.PRODUCT], tbl_product_fields, this.getRecord() );
    }

    private void updateDatabase() {
        db.updateRecord(new ContentValues(), DatabaseManager.tables[DatabaseManager.PRODUCT], tbl_product_fields,  this.getRecord() );
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.updateDatabase();
    }

    public static ArrayList<Product> fetchProducts() {
        List productList = db.getTable(DatabaseManager.tables[DatabaseManager.PRODUCT]);
        ArrayList<Product> productArrayList = new ArrayList<Product>();
        for (int i = 0; i < productList.size() ; i++) {
            List l = (List) productList.get(i);
            productArrayList.add(new Product(
                l.get(Product.size * i).toString(),
                l.get(Product.size * i + 1).toString(),
                Double.valueOf(l.get(Product.size * i + 2).toString()),
                Integer.valueOf(l.get(Product.size * i + 3).toString()),
                l.get(Product.size * i + 4).toString(),
                l.get(Product.size * i + 5).toString()
            ));
            /*
            for (int j = 0; j < Product.size ; j++) {
                //Log.d("timbo.j " + j, l.get(j).toString());
                Log.d("timbo result", l.get(Product.size * i + j).toString());
            }
            Log.d("timbo.fetchProducts " + i, productList.get(i).toString());
            //productArrayList.add((Product) productList.get(i));
            */
        }
        return productArrayList;
        //return new ArrayList<Product>();
    }
}
