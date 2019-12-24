package com.Farmers.Market;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rafaeltimbo on 25/12/17.
 */
public class ProductTest {
    DatabaseManager db;
    @Before
    public void setUp() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        db = new DatabaseManager(appContext);
        Product.setDb(appContext);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void initProducts() throws Exception {
    }

    @Test
    public void queryProductByName() throws Exception {
        String name = "Bananas";
        Product banana = Product.queryProductByName(name);
        assertNotNull(banana);
        assertEquals(banana.name, name);
        Log.d("timbo.query", banana.toString());
    }

    @Test
    public void addToDatabase() throws Exception {
    }

    @Test
    public void fetchProducts() throws Exception {
        List l = Product.fetchProducts();
        Log.d("rafaeltimbo.timbo.fetch", l.toString());
    }

}