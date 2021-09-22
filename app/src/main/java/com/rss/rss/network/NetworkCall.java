package com.rss.rss.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonSyntaxException;
import com.rss.rss.MainActivity;
import com.rss.rss.Model.other.FreelanceSupportEngineerPostResponse;
import com.rss.rss.Model.other.JobPlacementPostResponse;
import com.rss.rss.Model.adpost.product_promotion.ProductPromotionPostResponse;
import com.rss.rss.Model.services.ClientService;
import com.rss.rss.Model.services.ClientServiceGetResponse;
import com.rss.rss.Model.services.ClientServicePostResponse;
import com.rss.rss.Model.user.ResponseModel.LoginResponse;
import com.rss.rss.Model.user.ResponseModel.RegistrationResponse;
import com.rss.rss.utils.Session;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall extends AppCompatActivity {

    private static final String TAG = "NetworkCall";

    //variable
    public static List<ClientService> clientServiceList;

    public static void doLogin(String email, String password, final Context context, final Activity activity, ProgressBar progressBar){

        progressBar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        try {
            // finally, execute the request
            Call<LoginResponse> call = apiInterface.doLogin(email,password);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "onResponse: here");
                        LoginResponse result = response.body();
                        assert result != null;
                        Log.d(TAG, "onResponse: "+result.toString());
                        Toast.makeText(context, "Login is successful.", Toast.LENGTH_SHORT).show();

                        Session session = new Session(context);
                        session.setLoginStatus(true);
                        session.setAuthToken(result.getAccess_token());
                        session.setSetName(result.getUser().getName());
                        session.setUserID(result.getUser().getId());
                        session.setUserEmail(result.getUser().getEmail());


                        Intent intent = new Intent(context, MainActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }else {
                        Log.d(TAG, "not success "+ response.errorBody().toString());
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Log.d(TAG, "onFailure: "+t.getMessage() );
                    Toast.makeText(context, "Login failed! The provided credentials are incorrect", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            Log.d(TAG, "doLogin: "+e.getMessage().toString());
        }

    }

    public static void doRegistration(final Context context, String auth_token, String email, String name, String user_name, String phone, String birthday, int package_id, String password, int confirm_password){

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        Call<RegistrationResponse> call = apiInterface.doRegister(auth_token,email,name,user_name,phone,birthday,package_id,password,confirm_password);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.isSuccessful()){
                    RegistrationResponse result = response.body();
                    assert result != null;
                    Log.d(TAG, "onResponse: "+result.toString());
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
                Toast.makeText(context, "Login failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void getClientServices(String token, final Context context,final Activity activity){
        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<ClientServiceGetResponse> call = apiInterface.getClientService(token);
        call.enqueue(new Callback<ClientServiceGetResponse>() {
            @Override
            public void onResponse(Call<ClientServiceGetResponse> call, Response<ClientServiceGetResponse> response) {
                if (response.isSuccessful()){
                    ClientServiceGetResponse result = response.body();
                    if (result.getStatus().equals("Success")){
                        clientServiceList = result.getData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ClientServiceGetResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    public static void sendClientServicesRequest(final Context context,final Activity activity,String token, int userId,String service_type,String service_name,String company_name,String address,String description,String phone,String email){
        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", String.valueOf(token));
        map.put("userId",String.valueOf(userId));

        Call<ClientServicePostResponse> call = apiInterface.postClientService(map,service_type,service_name,company_name,address,description,phone,email);
        call.enqueue(new Callback<ClientServicePostResponse>() {
            @Override
            public void onResponse(Call<ClientServicePostResponse> call, Response<ClientServicePostResponse> response) {
                if (response.isSuccessful()){
                    ClientServicePostResponse result = response.body();
                    if (result.getStatus().equals("Success")){
                        Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ClientServicePostResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });
    }

    public static void sendJobPlacement(final Context context, final Activity activity, String token, int userId, String f_name, String l_name, String email, String phone, String last_degree, String last_empl_history, String additional_info, String cvUriPath){

        RequestBody f_namePart = RequestBody.create(MultipartBody.FORM,f_name);
        RequestBody l_namePart = RequestBody.create(MultipartBody.FORM,l_name);
        RequestBody emailPart = RequestBody.create(MultipartBody.FORM,email);
        RequestBody phonePart = RequestBody.create(MultipartBody.FORM,phone);
        RequestBody last_degreePart = RequestBody.create(MultipartBody.FORM,last_degree);
        RequestBody last_empl_historyPart = RequestBody.create(MultipartBody.FORM,last_empl_history);
        RequestBody additional_infoPart = RequestBody.create(MultipartBody.FORM,additional_info);

        File originalFile = new File(cvUriPath);

        RequestBody cvPart =  RequestBody.create(MediaType.parse("multipart/form-data"), originalFile);
        MultipartBody.Part myCv =  MultipartBody.Part.createFormData("cv", originalFile.getName(), cvPart);

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", String.valueOf(token));
        map.put("userId",String.valueOf(userId));

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<JobPlacementPostResponse> call = apiInterface.postJobPlacementData(map,f_namePart,l_namePart,emailPart,phonePart,last_degreePart,last_empl_historyPart,additional_infoPart,myCv);
        call.enqueue(new Callback<JobPlacementPostResponse>() {
            @Override
            public void onResponse(Call<JobPlacementPostResponse> call, Response<JobPlacementPostResponse> response) {
                if (response.isSuccessful()){
                    JobPlacementPostResponse result = response.body();
                    if (result.getStatus().equals("Success")){
                        Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<JobPlacementPostResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    public static void sendFreelanceSupportEngineerPostResponse(final Context context, final Activity activity, String token,int userId, String father_name, String mother_name, String nid, String expert_area, String last_degree, String last_empl_history, String additional_info, String cvUriPath){

        RequestBody father_namePart = RequestBody.create(MultipartBody.FORM,father_name);
        RequestBody mother_namePart = RequestBody.create(MultipartBody.FORM,mother_name);
        RequestBody nidPart = RequestBody.create(MultipartBody.FORM,nid);
        RequestBody expert_areaPart = RequestBody.create(MultipartBody.FORM,expert_area);
        RequestBody last_degreePart = RequestBody.create(MultipartBody.FORM,last_degree);
        RequestBody last_empl_historyPart = RequestBody.create(MultipartBody.FORM,last_empl_history);
        RequestBody additional_infoPart = RequestBody.create(MultipartBody.FORM,additional_info);

        File originalFile = new File(cvUriPath);

        RequestBody cvPart =  RequestBody.create(MediaType.parse("multipart/form-data"), originalFile);
        MultipartBody.Part myCv =  MultipartBody.Part.createFormData("cv", originalFile.getName(), cvPart);

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", String.valueOf(token));
        map.put("userId",String.valueOf(userId));

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<FreelanceSupportEngineerPostResponse> call = apiInterface.postFreelanceSupportEngineerData(map,father_namePart,mother_namePart,nidPart,expert_areaPart,last_degreePart,last_empl_historyPart,additional_infoPart,myCv);
        call.enqueue(new Callback<FreelanceSupportEngineerPostResponse>() {
            @Override
            public void onResponse(Call<FreelanceSupportEngineerPostResponse> call, Response<FreelanceSupportEngineerPostResponse> response) {
                if (response.isSuccessful()){
                    FreelanceSupportEngineerPostResponse result = response.body();
                    if (result.getStatus().equals("Success")){
                        Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<FreelanceSupportEngineerPostResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    public static void sendProductPromotion(final Context context, final Activity activity, String token,int userId, String product_name,double ad_budget,String shop_address,String email,String phone,String product_info){

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", String.valueOf(token));
        map.put("userId",String.valueOf(userId));

        Call<ProductPromotionPostResponse> call = apiInterface.postProductPromotionPost(map,product_name,ad_budget,shop_address,email,phone,product_info);
        call.enqueue(new Callback<ProductPromotionPostResponse>() {
            @Override
            public void onResponse(Call<ProductPromotionPostResponse> call, Response<ProductPromotionPostResponse> response) {
                if (response.isSuccessful()){
                    ProductPromotionPostResponse result = response.body();

                    if (result.isSuccess().equals("Success")){
                        Toast.makeText(context, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductPromotionPostResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });

    }
}