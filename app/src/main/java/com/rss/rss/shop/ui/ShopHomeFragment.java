package com.rss.rss.shop.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rss.rss.Dashboard.Affiliate.AffiliateActivity;
import com.rss.rss.Model.affiliate.Brand;
import com.rss.rss.Model.affiliate.Category;
import com.rss.rss.Model.affiliate.FeatureTopic;
import com.rss.rss.Model.affiliate.Product;
import com.rss.rss.Model.affiliate.Slider;
import com.rss.rss.Model.affiliate.VendorCod;
import com.rss.rss.R;
import com.rss.rss.Model.affiliate.Express;
import com.rss.rss.adapter.affiliate.BrandAdapter;
import com.rss.rss.adapter.affiliate.CategoryAdapter;
import com.rss.rss.adapter.affiliate.ExpressAdapter;
import com.rss.rss.adapter.affiliate.FeatureTopicAdapter;
import com.rss.rss.adapter.affiliate.ProductsAdapter;
import com.rss.rss.adapter.affiliate.RandomProductsAdapter;
import com.rss.rss.adapter.affiliate.SliderAdapter;
import com.rss.rss.adapter.affiliate.VendorCodAdapter;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ShopHomeFragment extends Fragment {

    private static final String TAG = "ShopHomeFragment";

    //Widget
    //Slider
    private SliderView imageSlider;
    //Topic
    private RecyclerView topicRV;
    //T7
    private RecyclerView productRv;
    //T5
    private RecyclerView productRv1;
    //T3
    private RecyclerView productRv2;
    //SME
    private RecyclerView productRv3;
    //Priority Store
    private RecyclerView productRv4;
    //Evaly Express
    private RecyclerView expressRV;
    //Vendor COD
    private RecyclerView vendorCODRV;
    //Category
    private RecyclerView categoriesRV;
    //Brands
    private RecyclerView brandsRV;
    //Product RandRV
    private RecyclerView productRandRV;

    public ShopHomeFragment() {
        // Required empty public constructor
    }

    public static ShopHomeFragment newInstance() {
        ShopHomeFragment fragment = new ShopHomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_home, container, false);
        
        initWidget(view);
        
        return view;
    }

    private void initWidget(View view) {
        initSlider(view);
        initTopic(view);
        initT7(view);
        initT5(view);
        initT3(view);
        initSME(view);
        initPriorityStore(view);
        initEXpress(view);
        initVendorCOD(view);
        initCategory(view);
        initBrand(view);
        initProductRand(view);
    }

    private void initProductRand(View view) {
        productRandRV = view.findViewById(R.id.productRandRV);
        productRandRV.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        List<Product> productList = new ArrayList<>();

        productList.add(new Product(
                1,1,1,"pc","1","A511","Best Watch For Men ","product_1",
                150.0f,1,20.0f,120.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                2,1,1,"pc","1","A512","T-Shirt For Men ","product_2",
                250.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://strausskhan.com/wp-content/uploads/2021/04/adbe1a6b-6c37-488f-86b1-111767216bdd.jpg",
                "https://strausskhan.com/wp-content/uploads/2021/04/adbe1a6b-6c37-488f-86b1-111767216bdd.jpg",
                "https://strausskhan.com/wp-content/uploads/2021/04/adbe1a6b-6c37-488f-86b1-111767216bdd.jpg",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                3,1,1,"pc","1","A513","Vintage T-Shirt For Men ","product_3",
                350.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://i.pinimg.com/236x/3f/33/c2/3f33c2850434f50ec5c4ac3748abbd5c.jpg",
                "https://i.pinimg.com/236x/3f/33/c2/3f33c2850434f50ec5c4ac3748abbd5c.jpg",
                "https://i.pinimg.com/236x/3f/33/c2/3f33c2850434f50ec5c4ac3748abbd5c.jpg",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                4,1,1,"pc","1","A514","Cool Lighter rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr ","product_4",
                450.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/582635/pexels-photo-582635.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/582635/pexels-photo-582635.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/582635/pexels-photo-582635.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                5,1,1,"pc","1","A515","Pure Black Coffee ","product_5",
                550.0f,1,20.0f,450.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://media.istockphoto.com/photos/mug-on-plate-filled-with-coffee-surrounded-by-coffee-beans-picture-id157528129?k=6&m=157528129&s=612x612&w=0&h=-Jm8OkpkDbTHIAXLuXaZ1_VUsz8_0B9okYWQJdgvnpI=",
                "https://media.istockphoto.com/photos/mug-on-plate-filled-with-coffee-surrounded-by-coffee-beans-picture-id157528129?k=6&m=157528129&s=612x612&w=0&h=-Jm8OkpkDbTHIAXLuXaZ1_VUsz8_0B9okYWQJdgvnpI=",
                "https://media.istockphoto.com/photos/mug-on-plate-filled-with-coffee-surrounded-by-coffee-beans-picture-id157528129?k=6&m=157528129&s=612x612&w=0&h=-Jm8OkpkDbTHIAXLuXaZ1_VUsz8_0B9okYWQJdgvnpI=",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                6,1,1,"pc","1","A516","Watch ","product_6",
                650.0f,1,0,0,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                100.0f,"test"
        )) ;

        RandomProductsAdapter adapter = new RandomProductsAdapter(productList, getContext());
        productRandRV.setAdapter(adapter);
    }

    private void initBrand(View view) {
        brandsRV = view.findViewById(R.id.brandsRV);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        //GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        brandsRV.setLayoutManager(manager);

        List<Brand> brandList = new ArrayList<>();

        brandList.add(new Brand(1,"Brand 1",""));
        brandList.add(new Brand(2,"Brand 2",""));
        brandList.add(new Brand(3,"Brand 3",""));
        brandList.add(new Brand(4,"Brand 4",""));
        brandList.add(new Brand(5,"Brand 5",""));
        brandList.add(new Brand(6,"Brand 6",""));
        brandList.add(new Brand(7,"Brand 7",""));

        BrandAdapter adapter = new BrandAdapter(brandList,getContext());
        brandsRV.setAdapter(adapter);
    }

    private void initCategory(View view) {
        categoriesRV = view.findViewById(R.id.categoriesRV);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        //GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        categoriesRV.setLayoutManager(manager);

        List<Category> categoryList = new ArrayList<>();

        categoryList.add(new Category(1,"Cat 1",""));
        categoryList.add(new Category(2,"Cat 2",""));
        categoryList.add(new Category(3,"Cat 3",""));
        categoryList.add(new Category(4,"Cat 4",""));
        categoryList.add(new Category(5,"Cat 5",""));
        categoryList.add(new Category(6,"Cat 6",""));
        categoryList.add(new Category(7,"Cat 7",""));

        CategoryAdapter adapter = new CategoryAdapter(categoryList,getContext());
        categoriesRV.setAdapter(adapter);
    }

    private void initVendorCOD(View view) {
        vendorCODRV = view.findViewById(R.id.vendorCODRV);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        //GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        vendorCODRV.setLayoutManager(manager);

        List<VendorCod> vendorCodList = new ArrayList<>();

        vendorCodList.add(new VendorCod(1,"Akij Group","http://urmartbd.com/site/images/1629802675akij_urmartbd-removebg-preview.png"));
        vendorCodList.add(new VendorCod(2,"Akij Group","http://urmartbd.com/site/images/1629802675akij_urmartbd-removebg-preview.png"));
        vendorCodList.add(new VendorCod(3,"Akij Group","http://urmartbd.com/site/images/1629802675akij_urmartbd-removebg-preview.png"));
        vendorCodList.add(new VendorCod(4,"Akij Group","http://urmartbd.com/site/images/1629802675akij_urmartbd-removebg-preview.png"));
        vendorCodList.add(new VendorCod(5,"Akij Group","http://urmartbd.com/site/images/1629802675akij_urmartbd-removebg-preview.png"));
        vendorCodList.add(new VendorCod(6,"Akij Group","http://urmartbd.com/site/images/1629802675akij_urmartbd-removebg-preview.png"));
        vendorCodList.add(new VendorCod(7,"Akij Group","http://urmartbd.com/site/images/1629802675akij_urmartbd-removebg-preview.png"));
        vendorCodList.add(new VendorCod(8,"Akij Group","http://urmartbd.com/site/images/1629802675akij_urmartbd-removebg-preview.png"));



        VendorCodAdapter adapter = new VendorCodAdapter(vendorCodList,getContext());
        vendorCODRV.setAdapter(adapter);
    }

    private void initEXpress(View view) {
        expressRV = view.findViewById(R.id.expressRV);
        //LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        expressRV.setLayoutManager(manager);

        List<Express> expressList = new ArrayList<>();

        expressList.add(new Express(1,"Pran 1","http://urmartbd.com/site/images/1629802062gtttgtgtgtggt-removebg-preview.png"));
        expressList.add(new Express(2,"Pran 2","http://urmartbd.com/site/images/1629802062gtttgtgtgtggt-removebg-preview.png"));
        expressList.add(new Express(3,"Pran 3","http://urmartbd.com/site/images/1629802062gtttgtgtgtggt-removebg-preview.png"));
        expressList.add(new Express(4,"Pran 4","http://urmartbd.com/site/images/1629802062gtttgtgtgtggt-removebg-preview.png"));
        expressList.add(new Express(5,"Pran 5","http://urmartbd.com/site/images/1629802062gtttgtgtgtggt-removebg-preview.png"));
        expressList.add(new Express(6,"Pran 6","http://urmartbd.com/site/images/1629802062gtttgtgtgtggt-removebg-preview.png"));
        expressList.add(new Express(7,"Pran 7","http://urmartbd.com/site/images/1629802062gtttgtgtgtggt-removebg-preview.png"));
        expressList.add(new Express(8,"Pran 8","http://urmartbd.com/site/images/1629802062gtttgtgtgtggt-removebg-preview.png"));



        ExpressAdapter adapter = new ExpressAdapter(expressList,getContext());
        expressRV.setAdapter(adapter);
    }

    private void initPriorityStore(View view) {
        productRv4 = view.findViewById(R.id.productRv4);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        productRv4.setLayoutManager(manager);

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(
                1,1,1,"pc","1","A501","Product1 ","product_1",
                150.0f,1,20.0f,120.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                2,1,1,"pc","1","A502","Product2 ","product_2",
                250.0f,1,20.0f,200.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                3,1,1,"pc","1","A503","Product3 ","product_3",
                1050.0f,1,10.0f,945.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                4,1,1,"pc","1","A504","Product4 ","product_4",
                1750.0f,1,50.0f,875.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                5,1,1,"pc","1","A505","Product5 ","product_5",
                2150.0f,1,50.0f,1075.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                6,1,1,"pc","1","A506","Product6 ","product_6",
                1250.0f,1,10.0f,1125.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        ProductsAdapter adapter = new ProductsAdapter(productList,getContext());
        productRv4.setAdapter(adapter);
    }

    private void initSME(View view) {
        productRv3 = view.findViewById(R.id.productRv3);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        productRv3.setLayoutManager(manager);

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(
                1,1,1,"pc","1","A501","Product1 ","product_1",
                150.0f,1,20.0f,120.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                2,1,1,"pc","1","A502","Product2 ","product_2",
                250.0f,1,20.0f,200.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                3,1,1,"pc","1","A503","Product3 ","product_3",
                1050.0f,1,10.0f,945.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                4,1,1,"pc","1","A504","Product4 ","product_4",
                1750.0f,1,50.0f,875.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                5,1,1,"pc","1","A505","Product5 ","product_5",
                2150.0f,1,50.0f,1075.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                6,1,1,"pc","1","A506","Product6 ","product_6",
                1250.0f,1,10.0f,1125.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        ProductsAdapter adapter = new ProductsAdapter(productList,getContext());
        productRv3.setAdapter(adapter);
    }

    private void initT3(View view) {
        productRv2 = view.findViewById(R.id.productRv2);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        productRv2.setLayoutManager(manager);

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(
                1,1,1,"pc","1","A501","Product1 ","product_1",
                150.0f,1,20.0f,120.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                2,1,1,"pc","1","A502","Product2 ","product_2",
                250.0f,1,20.0f,200.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                3,1,1,"pc","1","A503","Product3 ","product_3",
                1050.0f,1,10.0f,945.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                4,1,1,"pc","1","A504","Product4 ","product_4",
                1750.0f,1,50.0f,875.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "hhttp://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                5,1,1,"pc","1","A505","Product5 ","product_5",
                2150.0f,1,50.0f,1075.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "hhttp://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                6,1,1,"pc","1","A506","Product6 ","product_6",
                1250.0f,1,10.0f,1125.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628940093chasi-chinigura-rice-1kg.png",
                100.0f,"test"
        )) ;

        ProductsAdapter adapter = new ProductsAdapter(productList,getContext());
        productRv2.setAdapter(adapter);
    }

    private void initT5(View view) {
        productRv1 = view.findViewById(R.id.productRv1);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        productRv1.setLayoutManager(manager);

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(
                1,1,1,"pc","1","A501","Product1 ","product_1",
                150.0f,1,20.0f,120.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                2,1,1,"pc","1","A502","Product2 ","product_2",
                250.0f,1,20.0f,200.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                3,1,1,"pc","1","A503","Product3 ","product_3",
                1050.0f,1,10.0f,945.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                4,1,1,"pc","1","A504","Product4 ","product_4",
                1750.0f,1,50.0f,875.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                5,1,1,"pc","1","A505","Product5 ","product_5",
                2150.0f,1,50.0f,1075.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                6,1,1,"pc","1","A506","Product6 ","product_6",
                1250.0f,1,10.0f,1125.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                "http://urmartbd.com/uploads/products/thumbnails/1628939758Teer-Soyabean-Oil-5-Ltr.png",
                100.0f,"test"
        )) ;

        ProductsAdapter adapter = new ProductsAdapter(productList,getContext());
        productRv1.setAdapter(adapter);
    }

    private void initT7(View view) {
        productRv = view.findViewById(R.id.productRv);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        productRv.setLayoutManager(manager);

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(
                1,1,1,"pc","1","A501","Product1 ","product_1",
                150.0f,1,20.0f,120.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                2,1,1,"pc","1","A502","Product2 ","product_2",
                250.0f,1,20.0f,200.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                3,1,1,"pc","1","A503","Product3 ","product_3",
                1050.0f,1,10.0f,945.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                4,1,1,"pc","1","A504","Product4 ","product_4",
                1750.0f,1,50.0f,875.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                5,1,1,"pc","1","A505","Product5 ","product_5",
                2150.0f,1,50.0f,1075.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        productList.add(new Product(
                6,1,1,"pc","1","A506","Product6 ","product_6",
                1250.0f,1,10.0f,1125.0f,"10",0,1,"Loren ipsam ipsam ipsam ipsam ipsam",
                "Loren ipsam ipsam ipsam ipsam ipsam","Loren ipsam ipsam ipsam ipsam ipsam",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                "http://urmartbd.com/uploads/products/images/16298088033033-300x375w-removebg-preview%20(1).png",
                100.0f,"test"
        )) ;

        ProductsAdapter adapter = new ProductsAdapter(productList,getContext());
        productRv.setAdapter(adapter);
    }

    private void initTopic(View view) {
        topicRV = view.findViewById(R.id.topicRV);

        int numberOfColumns = 5;
        topicRV.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        List<FeatureTopic> featureTopicList = new ArrayList<>();

        featureTopicList.add(new FeatureTopic(1,"Top Up","http://urmartbd.com/uploads/main_cat_icon/1628928917grocery.png"));
        featureTopicList.add(new FeatureTopic(2,"Gift Cards","http://urmartbd.com/uploads/main_cat_icon/1628928917grocery.png"));
        featureTopicList.add(new FeatureTopic(3,"Express","http://urmartbd.com/uploads/main_cat_icon/1628928917grocery.png"));
        featureTopicList.add(new FeatureTopic(4,"Category","http://urmartbd.com/uploads/main_cat_icon/1628928917grocery.png"));
        featureTopicList.add(new FeatureTopic(5,"Orders","http://urmartbd.com/uploads/main_cat_icon/1628928917grocery.png"));

        FeatureTopicAdapter adapter = new FeatureTopicAdapter(featureTopicList,getContext());
        topicRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initSlider(View view) {

        imageSlider = view.findViewById(R.id.imageSlider);

        List<Slider> sliderList = new ArrayList<>();

        sliderList.add(new Slider(1,"slider 1","data:image/jpeg;base64,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"));
        sliderList.add(new Slider(2,"slider 2","https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260"));
        sliderList.add(new Slider(3,"slider 3","https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        sliderList.add(new Slider(4,"slider 4","data:image/jpeg;base64,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"));

        SliderAdapter adapter = new SliderAdapter(getContext(),sliderList);
        imageSlider.setSliderAdapter(adapter);

        imageSlider.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setIndicatorSelectedColor(Color.WHITE);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        imageSlider.setScrollTimeInSec(3); //set scroll delay in seconds :
        imageSlider.startAutoCycle();

    }
}