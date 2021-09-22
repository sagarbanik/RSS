package com.rss.rss.network;

import com.rss.rss.Model.adpost.car_promotion.CarPromotionPostResponse;
import com.rss.rss.Model.adpost.email_promotion.EmailPromotionPostResponse;
import com.rss.rss.Model.adpost.sms_promotion.SmsPromotionPostResponse;
import com.rss.rss.Model.adpost.social_media_promotion.SocialMediaPromotionPostResponse;
import com.rss.rss.Model.adpost.website_promotion.WebPromotionPostResponse;
import com.rss.rss.Model.course.CourseResponse;
import com.rss.rss.Model.load_ad.AdSkipResponse;
import com.rss.rss.Model.load_ad.AdSubmitResponse;
import com.rss.rss.Model.load_ad.DailyAdResponse;
import com.rss.rss.Model.load_ad.SingleAdResponse;
import com.rss.rss.Model.other.FreelanceSupportEngineerPostResponse;
import com.rss.rss.Model.other.JobPlacementPostResponse;
import com.rss.rss.Model.adpost.product_promotion.ProductPromotionPostResponse;
import com.rss.rss.Model.services.ClientServiceGetResponse;
import com.rss.rss.Model.services.ClientServicePostResponse;
import com.rss.rss.Model.user.ResponseModel.LoginResponse;
import com.rss.rss.Model.user.ResponseModel.RegistrationResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    //Login
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> doLogin(
            @Field("user_name") String user_name,
            @Field("password") String password
    );

    @FormUrlEncoded
    //@Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("registration")
    Call<RegistrationResponse> doRegister(
            @Header("Authorization") String authorization,
            @Field("email") String email,
            @Field("name") String name,
            @Field("user_name") String user_name,
            @Field("phone") String phone,
            @Field("birthday") String birthday,
            @Field("package_id") int package_id,
            @Field("password") String password,
            @Field("confirm_password") int confirm_password
    );

    //Services
    @GET("client_service/virtual")
    Call<ClientServiceGetResponse> getClientService(
            @Header("Authorization") String authorization
            );

    @FormUrlEncoded
    @POST("client_service")
    Call<ClientServicePostResponse> postClientService(
            //@Header("Authorization") String authorization,
            @HeaderMap Map<String, String> headers,
            @Field("service_type") String service_type,
            @Field("service_name") String service_name,
            @Field("company_name") String company_name,
            @Field("address") String address,
            @Field("description") String description,
            @Field("phone") String phone,
            @Field("email") String email
    );

    @Multipart
    @POST("user/job_placement")
    Call<JobPlacementPostResponse> postJobPlacementData(
            //@Header("Authorization") String authorization,
            @HeaderMap Map<String, String> headers,
            @Part("f_name") RequestBody f_name,
            @Part("l_name") RequestBody l_name,
            @Part("email") RequestBody email,
            @Part("phone") RequestBody phone,
            @Part("last_degree") RequestBody last_degree,
            @Part("last_empl_history") RequestBody last_empl_history,
            @Part("additional_info") RequestBody additional_info,
            @Part MultipartBody.Part cv
    );

    @Multipart
    @POST("user/freelance_support_engineer")
    Call<FreelanceSupportEngineerPostResponse> postFreelanceSupportEngineerData(
            //@Header("Authorization") String authorization,
            @HeaderMap Map<String, String> headers,
            @Part("father_name") RequestBody father_name,
            @Part("mother_name") RequestBody mother_name,
            @Part("nid") RequestBody nid,
            @Part("expert_area") RequestBody expert_area,
            @Part("last_degree") RequestBody last_degree,
            @Part("last_empl_history") RequestBody last_empl_history,
            @Part("additional_info") RequestBody additional_info,
            @Part MultipartBody.Part cv
    );

    @FormUrlEncoded
    @POST("user/product_promotion")
    Call<ProductPromotionPostResponse> postProductPromotionPost(
            @HeaderMap Map<String, String> headers,
            @Field("product_name") String product_name,
            @Field("ad_budget") double ad_budget,
            @Field("shop_address") String shop_address,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("product_info") String product_info
    );

    @FormUrlEncoded
    @POST("user/web_promotion")
    Call<WebPromotionPostResponse> postWebPromotionPost(
            @HeaderMap Map<String, String> headers,
            @Field("product_name") String product_name,
            @Field("ad_budget") double ad_budget,
            @Field("web_address") String shop_address,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("product_info") String product_info
    );

    @FormUrlEncoded
    @POST("user/social_media_promotion")
    Call<SocialMediaPromotionPostResponse> postSocialPromotionPost(
            @HeaderMap Map<String, String> headers,
            @Field("social_media_name") String social_media_name,
            @Field("ad_budget") double ad_budget,
            @Field("link") String link,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("additional_info") String additional_info
    );

    @FormUrlEncoded
    @POST("user/car_promotion")
    Call<CarPromotionPostResponse> postCarPromotionPost(
            @HeaderMap Map<String, String> headers,
            @Field("category") String category,
            @Field("child_category") String child_category,
            @Field("company_name") String company_name,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("additional_info") String additional_info
    );

    @FormUrlEncoded
    @POST("user/email_promotion")
    Call<EmailPromotionPostResponse> postEmailPromotionPost(
            @HeaderMap Map<String, String> headers,
            @Field("category") String category,
            @Field("child_category") String child_category,
            @Field("company_name") String company_name,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("additional_info") String additional_info
    );

    @FormUrlEncoded
    @POST("user/sms_promotion")
    Call<SmsPromotionPostResponse> postSmsPromotionPost(
            @HeaderMap Map<String, String> headers,
            @Field("category") String category,
            @Field("child_category") String child_category,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("additional_info") String additional_info
    );


    //Courses
    @GET("user/courses/advance")
    Call<CourseResponse> getAdvanceCourse(
            @HeaderMap Map<String, String> headers
    );

    //Load Ad
    @POST("load_ad")
    Call<DailyAdResponse> getDailyAdArray(
            @HeaderMap Map<String, String> headers
    );

    @POST("view_ad/{ad_id}")
    Call<SingleAdResponse> getSingleAd(
            @HeaderMap Map<String, String> headers,
            @Path("ad_id") int ad_id
    );

    @POST("skip_ad/{ad_id}")
    Call<AdSkipResponse> skipAd(
            @HeaderMap Map<String, String> headers,
            @Path("ad_id") int ad_id
    );

    @POST("viewed_ad_submit/{ad_id}")
    Call<AdSubmitResponse> submitAd(
            @HeaderMap Map<String, String> headers,
            @Path("ad_id") int ad_id
    );



}









