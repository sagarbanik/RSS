package com.rss.rss.Model.affiliate;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    public static List<Product> cartList = new ArrayList<>();
    public static List<Product> wishList = new ArrayList<>();

    private float finalCheckoutAmount;
    private int finalCheckoutQuantity;

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int category_id;
    private int brand_id;
    private String uom_id;
    private String unit;
    private String code;
    private String name;
    private String slug;
    private float price;
    private int promoted;
    private float percent_off;
    private float promotion_price;
    private String quantity;
    private int views;
    private int published;
    private String description;
    private String description_long;
    private String tags;
    private String thumbnail;
    private String tiny_image;
    private String image;
    private float cost_price;
    private String specifications;

    public Product() {
    }

    @Ignore
    public Product(int id, String name, float price, String image, int finalCheckoutQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.finalCheckoutQuantity = finalCheckoutQuantity;
        this.finalCheckoutAmount = price*finalCheckoutQuantity;
        this.image = image;
    }

    @Ignore
    public Product(int id, String name, float price, float percent_off, float promotion_price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.percent_off = percent_off;
        this.promotion_price = promotion_price;
        this.image = image;
    }

    @Ignore
    public Product(int id, int category_id, int brand_id, String uom_id, String unit, String code, String name, String slug, float price, int promoted, float percent_off, float promotion_price, String quantity, int views, int published, String description, String description_long, String tags, String thumbnail, String tiny_image, String image, float cost_price, String specifications) {
        this.id = id;
        this.category_id = category_id;
        this.brand_id = brand_id;
        this.uom_id = uom_id;
        this.unit = unit;
        this.code = code;
        this.name = name;
        this.slug = slug;
        this.price = price;
        this.promoted = promoted;
        this.percent_off = percent_off;
        this.promotion_price = promotion_price;
        this.quantity = quantity;
        this.views = views;
        this.published = published;
        this.description = description;
        this.description_long = description_long;
        this.tags = tags;
        this.thumbnail = thumbnail;
        this.tiny_image = tiny_image;
        this.image = image;
        this.cost_price = cost_price;
        this.specifications = specifications;
    }

    public static void setCartList(List<Product> cartList) {
        Product.cartList = cartList;
    }

    public static void setWishList(List<Product> wishList) {
        Product.wishList = wishList;
    }

    public float getFinalCheckoutAmount() {
        //finalCheckoutAmount = price*finalCheckoutQuantity;
        return finalCheckoutAmount;
    }
    public void setFinalCheckoutAmount(float finalCheckoutAmount) {
        this.finalCheckoutAmount = finalCheckoutAmount;
    }
    public int getFinalCheckoutQuantity() {
        return finalCheckoutQuantity;
    }
    public void setFinalCheckoutQuantity(int finalCheckoutQuantity) {
        this.finalCheckoutQuantity = finalCheckoutQuantity;
    }

    //Add product to the shopping cart
    public void addCartList(Product product) {
        cartList.add(0,product);
    }
    //Remove product from the shopping cart
    public void removeCartList(int position){
        cartList.remove(position);
    }
    //Get shopping cart
    public List<Product> getCartList(){
        return cartList;
    }

    //Add product to the WishList
    public void addWishList(Product product) {
        wishList.add(0,product);
    }
    //Remove product from the WishList
    public void removeWishList(int position){
        wishList.remove(position);
    }
    //Get WishList
    public List<Product> getWishList(){
        return wishList;
    }


    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getUom_id() {
        return uom_id;
    }

    public void setUom_id(String uom_id) {
        this.uom_id = uom_id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPromoted() {
        return promoted;
    }

    public void setPromoted(int promoted) {
        this.promoted = promoted;
    }

    public float getPercent_off() {
        return percent_off;
    }

    public void setPercent_off(float percent_off) {
        this.percent_off = percent_off;
    }

    public float getPromotion_price() {
        return promotion_price;
    }

    public void setPromotion_price(float promotion_price) {
        this.promotion_price = promotion_price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_long() {
        return description_long;
    }

    public void setDescription_long(String description_long) {
        this.description_long = description_long;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTiny_image() {
        return tiny_image;
    }

    public void setTiny_image(String tiny_image) {
        this.tiny_image = tiny_image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getCost_price() {
        return cost_price;
    }

    public void setCost_price(float cost_price) {
        this.cost_price = cost_price;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }


    @Override
    public String toString() {
        return "Product{" +
                "finalCheckoutAmount=" + finalCheckoutAmount +
                ", finalCheckoutQuantity=" + finalCheckoutQuantity +
                ", id=" + id +
                ", category_id=" + category_id +
                ", brand_id=" + brand_id +
                ", uom_id='" + uom_id + '\'' +
                ", unit='" + unit + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", price=" + price +
                ", promoted=" + promoted +
                ", percent_off=" + percent_off +
                ", promotion_price=" + promotion_price +
                ", quantity='" + quantity + '\'' +
                ", views=" + views +
                ", published=" + published +
                ", description='" + description + '\'' +
                ", description_long='" + description_long + '\'' +
                ", tags='" + tags + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", tiny_image='" + tiny_image + '\'' +
                ", image='" + image + '\'' +
                ", cost_price=" + cost_price +
                ", specifications='" + specifications + '\'' +
                '}';
    }
}
