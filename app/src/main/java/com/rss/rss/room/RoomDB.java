package com.rss.rss.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.rss.rss.Model.affiliate.Product;
import com.rss.rss.Model.affiliate.ShippingAddress;

@Database(
        entities = {
                Product.class,
                ShippingAddress.class
        }, version = 1,exportSchema = false)

public abstract class RoomDB extends RoomDatabase {
    public abstract DataDao dataDao();
}
