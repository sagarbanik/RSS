package com.rss.rss.Dashboard.Others.JobPlacement;

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
import com.rss.rss.Model.other.JobPlacementPostResponse;
import com.rss.rss.R;
import com.rss.rss.network.ApiInterface;
import com.rss.rss.network.NetworkCall;
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

public class JobPlacementFragment extends Fragment {

    private static final String TAG = "JobPlacementFragment";

    //WIDGET
    private Button btnSubmit,btnUpload;
    private TextInputEditText inputFirstName;
    private TextInputEditText inputLastName;
    private TextInputEditText inputEmail;
    private TextInputEditText inputPhone;
    private TextInputEditText inputEmploymentHistory;
    private TextInputEditText inputAdditionalInfo;
    private PowerSpinnerView spinnerEducation;
    private ProgressBar progressBar;

    //Variable
    private String item;
    private String path;

    public JobPlacementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_placement, container, false);

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
                    collapsingToolbar.setTitle("Others | Job Placement");
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

        inputFirstName = view.findViewById(R.id.inputFirstName);
        inputLastName = view.findViewById(R.id.inputLastName);
        inputEmail = view.findViewById(R.id.inputEmail);
        inputPhone = view.findViewById(R.id.inputPhone);
        inputEmploymentHistory = view.findViewById(R.id.inputEmploymentHistory);
        inputAdditionalInfo = view.findViewById(R.id.inputAdditionalInfo);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        initSpinner(view);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pushFragment(new JobPlacementLastFragment());
                if (path != null )
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

                startActivityForResult(intent,102);
            }
        });

    }

    private void initSpinner(View view) {

        spinnerEducation = view.findViewById(R.id.spinnerEducation);

        spinnerEducation.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
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

    private void sendData() {
        String f_name = inputFirstName.getText().toString();
        String l_name = inputLastName.getText().toString();
        String email = inputEmail.getText().toString();
        String phone = inputPhone.getText().toString();
        String emp_history = inputEmploymentHistory.getText().toString();
        String addi_info = inputAdditionalInfo.getText().toString();

        Session session = new Session(getContext());

        if (f_name != null && l_name != null && email != null && phone != null && emp_history != null && addi_info != null){
            RequestBody f_namePart = RequestBody.create(MultipartBody.FORM,f_name);
            RequestBody l_namePart = RequestBody.create(MultipartBody.FORM,l_name);
            RequestBody emailPart = RequestBody.create(MultipartBody.FORM,email);
            RequestBody phonePart = RequestBody.create(MultipartBody.FORM,phone);
            RequestBody last_degreePart = RequestBody.create(MultipartBody.FORM,item);
            RequestBody last_empl_historyPart = RequestBody.create(MultipartBody.FORM,emp_history);
            RequestBody additional_infoPart = RequestBody.create(MultipartBody.FORM,addi_info);

            File originalFile = new File(path);

            RequestBody cvPart =  RequestBody.create(MediaType.parse("multipart/form-data"), originalFile);
            MultipartBody.Part myCv =  MultipartBody.Part.createFormData("cv", originalFile.getName(), cvPart);

            Map<String, String> map = new HashMap<>();
            map.put("Authorization", String.valueOf(session.getAuthToken()));
            map.put("userId",String.valueOf(session.getUserID()));

            progressBar.setVisibility(View.VISIBLE);

            ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
            Call<JobPlacementPostResponse> call = apiInterface.postJobPlacementData(map,f_namePart,l_namePart,emailPart,phonePart,last_degreePart,last_empl_historyPart,additional_infoPart,myCv);
            call.enqueue(new Callback<JobPlacementPostResponse>() {
                @Override
                public void onResponse(Call<JobPlacementPostResponse> call, Response<JobPlacementPostResponse> response) {
                    if (response.isSuccessful()){
                        JobPlacementPostResponse result = response.body();
                        if (result.getStatus().equals("Success")){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), ""+result.getMessage(), Toast.LENGTH_SHORT).show();
                            pushFragment(new JobPlacementLastFragment());
                        }
                    }
                }

                @Override
                public void onFailure(Call<JobPlacementPostResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: "+t.getMessage());
                }
            });
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null){
            ArrayList<MediaFile> mediaFiles = data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);

            //String path
            path = mediaFiles.get(0).getPath();

            switch (requestCode){
                case 102:
                    Toast.makeText(getContext(), "File path: " + path, Toast.LENGTH_SHORT).show();
                    break;
            }

        }

    }
}