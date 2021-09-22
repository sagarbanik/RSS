package com.rss.rss.Dashboard.Others.FSE;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;
import com.rss.rss.Dashboard.AdPost.AdPostActivity;
import com.rss.rss.Dashboard.Others.OthersActivity;
import com.rss.rss.MainActivity;
import com.rss.rss.Model.other.FreelanceSupportEngineerPostResponse;
import com.rss.rss.R;
import com.rss.rss.network.ApiInterface;
import com.rss.rss.network.RetrofitApiClient;
import com.rss.rss.utils.Session;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static java.lang.Math.abs;

public class FSEFirstFragment extends Fragment {

    private static final String TAG = "FSEFirstFragment";
    
    //WIDGET
    private Button btnSubmit,btnUpload;

    private TextInputEditText inputNidOrPassport;
    private TextInputEditText inputFatherName;
    private TextInputEditText inputMotherName;
    private TextInputEditText inputEmploymentHistory;
    private TextInputEditText inputEducation;
    private TextInputEditText inputAdditionalInfo;
    private PowerSpinnerView spinnerExpertiseArea;
    private ProgressBar progressBar;

    //Spinner item
    private String item;
    private String mediaPath;

    public FSEFirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_s_e_first, container, false);
        
        initWidget(view);
        
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getContext()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getContext()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppBarLayout appbar = view.findViewById(R.id.appbar);
        CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.collapsingToolbar);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //Check if the view is collapsed
                if (abs(verticalOffset) >= appbar.getTotalScrollRange()) {
                    collapsingToolbar.setTitle("Others | Freelance Support Engineer");
                    collapsingToolbar.setExpandedTitleColor(Color.WHITE);

                } else {
                    collapsingToolbar.setTitle("");
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void initWidget(View view) {

        inputNidOrPassport = view.findViewById(R.id.inputNidOrPassport);
        inputFatherName = view.findViewById(R.id.inputFatherName);
        inputMotherName = view.findViewById(R.id.inputMotherName);
        inputEmploymentHistory = view.findViewById(R.id.inputEmploymentHistory);
        inputEducation = view.findViewById(R.id.inputEducation);
        inputAdditionalInfo = view.findViewById(R.id.inputAdditionalInfo);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        initSpinner(view);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });

        btnUpload = view.findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FilePickerActivity.class);

                intent.putExtra(FilePickerActivity.CONFIGS,new Configurations.Builder()
                        .setCheckPermission(true)
                        .setShowFiles(true)
                        .setShowImages(false)
                        .setShowAudios(false)
                        .setShowVideos(false)
                        .setSuffixes("pdf")
                        .setMaxSelection(1)
                        .setSkipZeroSizeFiles(true)
                        .build()
                );

                startActivityForResult(intent,100);
            }
        });

    }

    private void initSpinner(View view) {
        spinnerExpertiseArea = view.findViewById(R.id.spinnerExpertiseArea);

        spinnerExpertiseArea.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
            @Override
            public void onItemSelected(int i, @Nullable Object o, int i1, Object t1) {
                Log.d(TAG, "onItemSelected: i = "+i);
                Log.d(TAG, "onItemSelected: o = "+o);
                Log.d(TAG, "onItemSelected: i1 = "+i1);
                Log.d(TAG, "onItemSelected: t1 = "+t1);

                item = t1.toString();
            }
        });
    }

    public void pushFragment(Fragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rootLayout, fragment);
        fragmentTransaction.commit();
    }

    private void sendData(){

        String father_name = inputFatherName.getText().toString();
        String mother_name = inputMotherName.getText().toString();
        String nid_or_pass = inputNidOrPassport.getText().toString();
        String last_degree = inputEducation.getText().toString();
        String last_empl_history = inputEmploymentHistory.getText().toString();
        String additional_info = inputAdditionalInfo.getText().toString();

        RequestBody father_namePart = RequestBody.create(MultipartBody.FORM,father_name);
        RequestBody mother_namePart = RequestBody.create(MultipartBody.FORM,mother_name);
        RequestBody nidPart = RequestBody.create(MultipartBody.FORM,nid_or_pass);
        RequestBody expert_areaPart = RequestBody.create(MultipartBody.FORM,item);
        RequestBody last_degreePart = RequestBody.create(MultipartBody.FORM,last_degree);
        RequestBody last_empl_historyPart = RequestBody.create(MultipartBody.FORM,last_empl_history);
        RequestBody additional_infoPart = RequestBody.create(MultipartBody.FORM,additional_info);

        File originalFile = new File(mediaPath);

        RequestBody cvPart =  RequestBody.create(MediaType.parse("multipart/form-data"), originalFile);
        MultipartBody.Part myCv =  MultipartBody.Part.createFormData("cv", originalFile.getName(), cvPart);

        Session session = new Session(getContext());

        Map<String, String> map = new HashMap<>();
        map.put("Authorization", String.valueOf(session.getAuthToken()));
        map.put("userId",String.valueOf(session.getUserID()));

        progressBar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<FreelanceSupportEngineerPostResponse> call = apiInterface.postFreelanceSupportEngineerData(map,father_namePart,mother_namePart,nidPart,expert_areaPart,last_degreePart,last_empl_historyPart,additional_infoPart,myCv);
        call.enqueue(new Callback<FreelanceSupportEngineerPostResponse>() {
            @Override
            public void onResponse(Call<FreelanceSupportEngineerPostResponse> call, Response<FreelanceSupportEngineerPostResponse> response) {
                if (response.isSuccessful()){
                    FreelanceSupportEngineerPostResponse result = response.body();
                    if (result.getStatus().equals("Success")){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                        pushFragment(new FSELastFragment());
                    }
                }
            }

            @Override
            public void onFailure(Call<FreelanceSupportEngineerPostResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null){
            ArrayList<MediaFile> mediaFiles = data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);

            //String path
            mediaPath = mediaFiles.get(0).getPath();

            switch (requestCode){
                case 100:
                    Toast.makeText(getContext(), "File path: " + mediaPath, Toast.LENGTH_SHORT).show();
                    break;
            }

        }

    }
}