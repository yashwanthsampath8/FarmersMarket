package com.Farmers.Market;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {
    //
    private static final String DATABASE_NAME = "com.rafaeltimbo.shoppingbasket";
    private static final int DATABASE_VERSION = 17;
    //

    public static final String tables[]={"tbl_customer","tbl_clerk", "tbl_product", "tbl_order"};
    public static final Integer CUSTOMER = 0;
    public static final Integer CLERK = 1;
    public static final Integer PRODUCT = 2;
    public static final Integer ORDER = 3;

    private static final String tableCreatorString[] =
            {"CREATE TABLE " + DatabaseManager.tables[CUSTOMER] + " (customerId INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT," +
                    " firstname TEXT, lastname TEXT," +
                    " address TEXT, city TEXT, postalCode TEXT);",
                    "CREATE TABLE " + DatabaseManager.tables[CLERK] + " (employeeId INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT," +
                            " firstname TEXT, lastname TEXT, " +
                            " address TEXT, city TEXT, postalCode TEXT);",
                    "CREATE TABLE " + DatabaseManager.tables[PRODUCT] + " (productId INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price TEXT," +
                            " quantity INTEGER, category TEXT, imageUrl TEXT);",
                    "CREATE TABLE " + DatabaseManager.tables[ORDER] + " (orderId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "customerId INTEGER KEY REFERENCES tbl_customer(customerId), " +
                            "productId INTEGER KEY REFERENCES tbl_product(productId), " +
                            "employeeId INTEGER KEY REFERENCES tbl_clerk(employeeId), " +
                            "orderDate DATE, status TEXT," +
                            "price FLOAT, quantity INTEGER );"
                    ,};

    //class constructor
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //initialize database table names and DDL statements
    public void dbInitialize( )
    {
    }

    // Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Drop existing tables
        for (String table : tables) db.execSQL("DROP TABLE IF EXISTS " + table);
        //create them
        for (int i=0;i<tables.length;i++)
            db.execSQL(tableCreatorString[i]);
    }

    public void recreateOneTable(int i) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tables[i]);
        db.execSQL(tableCreatorString[i]);
        db.close();
    }

    //create the database
    public void createDatabase(Context context)
    {
        SQLiteDatabase mDatabase;
        mDatabase = context.openOrCreateDatabase(
                DATABASE_NAME,
                SQLiteDatabase.CREATE_IF_NECESSARY,
                null);

    }
    //delete the database
    public void deleteDatabase(Context context)
    {
        context.deleteDatabase(DATABASE_NAME);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables
        for (String table : tables) db.execSQL("DROP TABLE IF EXISTS " + table);

        // Create tables again
        onCreate(db);
    }
    /////////////////////////
    // Database operations
    /////////////////////////
    // Add a new record
    long addRecord(ContentValues values, String tableName, String fields[],String record[]) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i=1;i<record.length;i++)
            values.put(fields[i], record[i]);
        // Insert the row
        long id = db.insert(tableName, null, values);
        db.close(); //close database connection
        return id;
    }


    // Read all records
    public List getTable(String tableName) {
        List table = new ArrayList(); //to store all rows
        // Select all records
        String selectQuery = "SELECT  * FROM " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList row=new ArrayList(); //to store one row
        //scroll over rows and store each row in an array list object
        if (cursor.moveToFirst())
        {
            do
            { // for each row
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    row.add(cursor.getString(i));
                }

                //if (!table.contains(row)) {
                    table.add(row); //add row to the list
                //}

            } while (cursor.moveToNext());
        }
        cursor.close();

        // return table as a list
        return table;
    }

    public List queryTable(String tableName, String queryWhere, String[] selectionArgs) {
        List table = new ArrayList(); //to store all rows
        // Select all records
        String selectQuery = "SELECT  * FROM " + tableName + " WHERE " + queryWhere;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        ArrayList row=new ArrayList(); //to store one row
        //scroll over rows and store each row in an array list object
        if (cursor.moveToFirst())
        {
            do
            { // for each row
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    row.add(cursor.getString(i));
                }

                //if (!table.contains(row)) {
                table.add(row); //add row to the list
                //}

            } while (cursor.moveToNext());
        }
        cursor.close();

        // return table as a list
        return table;
    }

    // Update a record
    public int updateRecord(ContentValues values, String tableName, String fields[],String record[]) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i=1;i<record.length;i++)
            values.put(fields[i], record[i]);

        // updating row with given id = record[0]
        return db.update(tableName, values, fields[0] + " = ?",
                new String[] { record[0] });
    }

    // Delete a record with a given id
    public void deleteRecord(String tableName, String idName, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, idName + " = ?",
                new String[] { id });
        db.close();
    }
    //inserting product data
    public boolean insertProductData(String name, Double price, int quantity, String category, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("productId", id);
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("quantity",quantity);
        contentValues.put("category", category);
        contentValues.put("imageUrl", url);
        long result = db.insert("tbl_product", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


}