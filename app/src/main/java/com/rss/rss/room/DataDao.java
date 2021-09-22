package com.rss.rss.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rss.rss.Model.affiliate.ShippingAddress;

import java.util.List;

@Dao
public interface DataDao {

    //Shipping Address
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShippingAddress(ShippingAddress shippingAddress);
    @Query("SELECT * FROM ShippingAddress")
    List<ShippingAddress> getAddressList();
    @Query("SELECT * FROM ShippingAddress WHERE id = :s_id")
    ShippingAddress getShippingAddress(int s_id);
    @Query("UPDATE ShippingAddress SET title = :title, details = :details  WHERE id = :address_id")
    void updateShippingAddress(int address_id,String title,String details);
    @Delete
    void deleteAddress(ShippingAddress address);

    /*//Slider Data CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSlider(List<Slider> sliderList);

    @Query("SELECT * FROM Slider")
    List<Slider> sliderList();


    //Feature Category Data CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFeatureCategory(List<FeatureCategory> fcList);

    @Query("SELECT * FROM FeatureCategory")
    List<FeatureCategory> featureCategoryList();


    //Promotional Category Data CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPromotionalCategory(List<TopPromo> promoProductList);

    @Query("SELECT * FROM TopPromo LIMIT 3")
    List<TopPromo> promoList();

    @Query("SELECT * FROM TopPromo")
    List<TopPromo> getAllPromoList();


    //Latest Product Data CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLatestProducts(List<LatestProducts> latestProductList);

    @Query("SELECT * FROM LatestProducts LIMIT 3")
    List<LatestProducts> latestList();

    @Query("SELECT * FROM LatestProducts")
    List<LatestProducts> getAllLatestList();


    //Feature Product Data CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFeatureProducts(List<FeaturedProducts> featureProductList);

    @Query("SELECT * FROM FeaturedProducts LIMIT 3")
    List<FeaturedProducts> featuredProductsList();

    @Query("SELECT * FROM FeaturedProducts")
    List<FeaturedProducts> getAllFeaturedProductsList();


    //Random Product Data CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRandomProducts(List<Product> randomProductList);

    @Query("SELECT * FROM Product LIMIT 3")
    List<Product> randomProductList();

    @Query("SELECT * FROM Product")
    List<Product> getAllRandomProductList();


    //Shipping Address
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShippingAddress(ShippingAddress shippingAddress);
    @Query("SELECT * FROM ShippingAddress")
    List<ShippingAddress> getAddressList();
    @Query("SELECT * FROM ShippingAddress WHERE id = :s_id")
    ShippingAddress getShippingAddress(int s_id);
    @Query("UPDATE ShippingAddress SET title = :title, details = :details  WHERE id = :address_id")
    void updateShippingAddress(int address_id,String title,String details);
    @Delete
    void deleteAddress(ShippingAddress address);


    //Product Request
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProductRequest(ProductRequest productRequest);
    @Query("SELECT * FROM ProductRequest")
    List<ProductRequest> getProductRequest();
    @Query("DELETE FROM ProductRequest")
    void delete();
    @Delete
    void deleteItem(ProductRequest productRequest);*/
}
