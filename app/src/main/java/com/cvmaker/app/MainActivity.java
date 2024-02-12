package com.cvmaker.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.cvmaker.app.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private static final int PICK_IMAGE_REQUEST_CODE_10 = 10;
    //  private static final int PICK_IMAGE_REQUEST_CODE_20 = 20;
    private Uri imageUriProfile, imageUriLogo;
    private EditText ETName,
            ETCity,
            ETBorn,
            ETAddress,
            ETPhone,
            ETMail,
            ETInstagram,
            ETFacebook,
            ETSD,
            ETSDStart,
            ETSDEnd,
            ETSMP,
            ETSMPStart,
            ETSMPEnd,
            ETSMA,
            ETSMAStart,
            ETSMAEnd,
            ETUniv,
            ETUnivStart,
            ETUnivEnd,
            ETSkill1,
            ETSkill2,
            ETSkill3,
            ETWork1,
            ETWork1Start,
            ETWork1End,
            ETPosisi1,
            ETWork2,
            ETWork2Start,
            ETWork2End,
            ETPosisi2;
    private AutoCompleteTextView ETGender, ETStats;
    private Button BTNGenerate;
    private MaterialCardView cardViewOptions;
    private ImageView IMGProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ETName = binding.ETName;
        ETCity = binding.ETCity;
        ETBorn = binding.ETBorn;
        ETGender = binding.ETGender;
        ETStats = binding.ETStats;
        ETAddress = binding.ETAddress;

        ETPhone = binding.ETPhone;
        ETMail = binding.ETMail;
        ETInstagram = binding.ETInstagram;
        ETFacebook = binding.ETFacebook;

        ETSD = binding.ETSD;
        ETSDStart = binding.ETSDStart;
        ETSDEnd = binding.ETSDEnd;
        ETSMP = binding.ETSMP;
        ETSMPStart = binding.ETSMPStart;
        ETSMPEnd = binding.ETSMPEnd;
        ETSMA = binding.ETSMA;
        ETSMAStart = binding.ETSMAStart;
        ETSMAEnd = binding.ETSMAEnd;
        ETUniv = binding.ETUniv;
        ETUnivStart = binding.ETUnivStart;
        ETUnivEnd = binding.ETUnivEnd;

        ETSkill1 = binding.ETSkill1;
        ETSkill2 = binding.ETSkill2;
        ETSkill3 = binding.ETSkill3;
        
            ETWork1= binding.ETWork1;
            ETWork1Start= binding.ETWork1Start;
            ETWork1End= binding.ETWork1End;
            ETPosisi1= binding.ETPosisi1;
            ETWork2= binding.ETWork2;
            ETWork2Start= binding.ETWork2Start;
            ETWork2End= binding.ETWork2End;
            ETPosisi2= binding.ETPosisi2;
        
        BTNGenerate = binding.BTNGenerate;
        cardViewOptions = binding.cardViewOptions;
        IMGProfile = binding.IMGProfile;

        ETGender.setText("Laki-laki");

        String[] simpleItems = getResources().getStringArray(R.array.gender);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, simpleItems);
        ETGender.setAdapter(adapter);

        ETStats.setText("Belum Menikah");

        String[] simpleItemsstats = getResources().getStringArray(R.array.stats);

        ArrayAdapter<String> adapterstats =
                new ArrayAdapter<>(
                        this, android.R.layout.simple_dropdown_item_1line, simpleItemsstats);
        ETStats.setAdapter(adapterstats);

        ETBorn.setOnClickListener(
                v -> {
                    DialogEditBorn();
                });

        cardViewOptions.setOnClickListener(
                v -> {
                    Intent intent =
                            new Intent(
                                    Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE_10);
                });

        BTNGenerate.setOnClickListener(
                v -> {
                    if (isEditTextFilled()) {
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        if (imageUriProfile != null || imageUriLogo != null) {
                            if (imageUriProfile != null) {
                                intent.putExtra("imageUriProfile", imageUriProfile.toString());
                            }
                            if (imageUriLogo != null) {
                                intent.putExtra("imageUriLogo", imageUriLogo.toString());
                            }
                        }

                        intent.putExtra("name", ETName.getText().toString());
                        intent.putExtra("city", ETCity.getText().toString());
                        intent.putExtra("born", ETBorn.getText().toString());
                        intent.putExtra("gender", ETGender.getText().toString());
                        intent.putExtra("stats", ETStats.getText().toString());
                        intent.putExtra("address", ETAddress.getText().toString());
                        intent.putExtra("phone", ETPhone.getText().toString());
                        intent.putExtra("mail", ETMail.getText().toString());
                        intent.putExtra("instagram", ETInstagram.getText().toString());
                        intent.putExtra("facebook", ETFacebook.getText().toString());

                        intent.putExtra("SDName", ETSD.getText().toString().trim());
                        intent.putExtra("SDStart", ETSDStart.getText().toString().trim());
                        intent.putExtra("SDEnd", ETSDEnd.getText().toString().trim());
                        intent.putExtra("SMPName", ETSMP.getText().toString().trim());
                        intent.putExtra("SMPStart", ETSMPStart.getText().toString().trim());
                        intent.putExtra("SMPEnd", ETSMPEnd.getText().toString().trim());
                        intent.putExtra("SMAName", ETSMA.getText().toString().trim());
                        intent.putExtra("SMAStart", ETSMAStart.getText().toString().trim());
                        intent.putExtra("SMAEnd", ETSMAEnd.getText().toString().trim());
                        intent.putExtra("UnivName", ETUniv.getText().toString().trim());
                        intent.putExtra("UnivStart", ETUnivStart.getText().toString().trim());
                        intent.putExtra("UnivEnd", ETUnivEnd.getText().toString().trim());

                        intent.putExtra("Skill1", ETSkill1.getText().toString().trim());
                        intent.putExtra("Skill2", ETSkill2.getText().toString().trim());
                        intent.putExtra("Skill3", ETSkill3.getText().toString().trim());

                        intent.putExtra("Work1", ETWork1.getText().toString().trim());
                        intent.putExtra("Work1Start", ETWork1Start.getText().toString().trim());
                        intent.putExtra("Work1End", ETWork1End.getText().toString().trim());
                        intent.putExtra("Posisi1", ETPosisi1.getText().toString().trim());
                        intent.putExtra("Work2", ETWork2.getText().toString().trim());
                        intent.putExtra("Work2Start", ETWork2Start.getText().toString().trim());
                        intent.putExtra("Work2End", ETWork2End.getText().toString().trim());
                        intent.putExtra("Posisi2", ETPosisi2.getText().toString().trim());

                    
                        startActivity(intent);
                    } else {
                        Toast.makeText(
                                        MainActivity.this,
                                        "Please fill in all fields",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST_CODE_10) {
            handleScanResultImageProfile(resultCode, data, IMGProfile);
            // } else if (requestCode == PICK_IMAGE_REQUEST_CODE_20) {
            //    handleScanResultImageLogo(resultCode, data, IMGLogo);
        }
    }

    private void handleScanResultImageProfile(
            int resultCode, Intent data, ImageView targetImageViewProfile) {
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUriProfile = data.getData();
            targetImageViewProfile.setImageURI(imageUriProfile);
        }
    }

    //  private void handleScanResultImageLogo(
    //         int resultCode, Intent data, ImageView targetImageViewLogo) {
    //       if (resultCode == RESULT_OK && data != null && data.getData() != null) {
    //         imageUriLogo = data.getData();
    //         targetImageViewLogo.setImageURI(imageUriLogo);
    //      }
    //    }

    private boolean isEditTextFilled() {
        return !ETName.getText().toString().isEmpty()
                && !ETCity.getText().toString().isEmpty()
                && !ETBorn.getText().toString().isEmpty()
                && !ETGender.getText().toString().isEmpty()
                && !ETAddress.getText().toString().isEmpty()
                && !ETPhone.getText().toString().isEmpty()
                && !ETMail.getText().toString().isEmpty()
                && !ETInstagram.getText().toString().isEmpty()
                && !ETFacebook.getText().toString().isEmpty()
                && !ETSD.getText().toString().isEmpty()
                && !ETSDStart.getText().toString().isEmpty()
                && !ETSDEnd.getText().toString().isEmpty()
                && !ETSMP.getText().toString().isEmpty()
                && !ETSMPStart.getText().toString().isEmpty()
                && !ETSMPEnd.getText().toString().isEmpty()
                && !ETSMA.getText().toString().isEmpty()
                && !ETSMAStart.getText().toString().isEmpty()
                && !ETSMAEnd.getText().toString().isEmpty()
                && !ETUniv.getText().toString().isEmpty()
                && !ETUnivStart.getText().toString().isEmpty()
                && !ETUnivEnd.getText().toString().isEmpty()
                && !ETSkill1.getText().toString().isEmpty()
                && !ETSkill2.getText().toString().isEmpty()
                && !ETSkill3.getText().toString().isEmpty()
                && !ETWork1.getText().toString().isEmpty()
                && !ETWork1Start.getText().toString().isEmpty()
                && !ETWork1End.getText().toString().isEmpty()
                && !ETPosisi1.getText().toString().isEmpty()
                && !ETWork2.getText().toString().isEmpty()
                && !ETWork2Start.getText().toString().isEmpty()
                && !ETWork2End.getText().toString().isEmpty()
                && !ETPosisi2.getText().toString().isEmpty();
        
    }

    private void DialogEditBorn() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Atur Tanggal Lahir");

        MaterialDatePicker<Long> materialDatePicker = builder.build();

        materialDatePicker.addOnPositiveButtonClickListener(
                selection -> {
                    Date selectedDate = new Date(selection);
                    SimpleDateFormat dateFormat =
                            new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
                    String formattedDate = dateFormat.format(selectedDate);

                    ETBorn.setText(formattedDate);
                });

        materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER_TAG");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
