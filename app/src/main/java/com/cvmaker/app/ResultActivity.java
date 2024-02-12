package com.cvmaker.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.cvmaker.app.databinding.ActivityResultBinding;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.makeramen.roundedimageview.RoundedImageView;

import yuku.ambilwarna.AmbilWarnaDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultActivity extends AppCompatActivity {
    private ActivityResultBinding binding;

    private static final int PICK_IMAGE_REQUEST_CODE_10 = 10;
    private TextView TXTName,
            TXTStats,
            TXTBorn,
            TXTGender,
            TXTAddress,
            TXTPhone,
            TXTMail,
            TXTInstagram,
            TXTFacebook,
            TXTSD,
            TXTSMP,
            TXTSMA,
            TXTUniv,
            TXTSDStartEnd,
            TXTSMPStartEnd,
            TXTSMAStartEnd,
            TXTUnivStartEnd,
            TXTSkill1,
            TXTSkill2,
            TXTSkill3,
            TXTWork1StartEnd,
            TXTWork1,
            TXTPosisi1,
            TXTWork2StartEnd,
            TXTWork2,
            TXTPosisi2;
    private RoundedImageView IMGProfile;
    private Button BTNPdfSave, BTNColor, BTNBackground;
    private ImageView bgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bgBack = binding.bgBack;

        TXTPhone = binding.TXTPhone;
        TXTMail = binding.TXTMail;
        TXTInstagram = binding.TXTInstagram;
        TXTFacebook = binding.TXTFacebook;
        TXTName = binding.TXTName;
        TXTStats = binding.TXTStats;
        TXTBorn = binding.TXTBorn;
        TXTGender = binding.TXTGender;
        TXTAddress = binding.TXTAddress;

        TXTSD = binding.TXTSD;
        TXTSMP = binding.TXTSMP;
        TXTSMA = binding.TXTSMA;
        TXTUniv = binding.TXTUniv;
        TXTSDStartEnd = binding.TXTSDStartEnd;
        TXTSMPStartEnd = binding.TXTSMPStartEnd;
        TXTSMAStartEnd = binding.TXTSMAStartEnd;
        TXTUnivStartEnd = binding.TXTUnivStartEnd;

        TXTSkill1 = binding.TXTSkill1;
        TXTSkill2 = binding.TXTSkill2;
        TXTSkill3 = binding.TXTSkill3;

        TXTWork1StartEnd = binding.TXTWork1StartEnd;
        TXTWork1 = binding.TXTWork1;
        TXTPosisi1 = binding.TXTPosisi1;
        TXTWork2StartEnd = binding.TXTWork2StartEnd;
        TXTWork2 = binding.TXTWork2;
        TXTPosisi2 = binding.TXTPosisi2;

        IMGProfile = binding.IMGProfile;

        BTNPdfSave = binding.BTNPdfSave;
        BTNColor = binding.BTNColor;
        BTNBackground = binding.BTNBackground;

        //  Typeface customFont = Typeface.createFromAsset(getAssets(), "varsity.ttf");
        //  TXTTitle.setTypeface(customFont);

        String imageUriProfileString = getIntent().getStringExtra("imageUriProfile");

        if (imageUriProfileString != null) {
            Uri imageUriProfile = Uri.parse(imageUriProfileString);
            IMGProfile.setImageURI(imageUriProfile);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name", "");
            String stats = extras.getString("stats", "");
            String city = extras.getString("city", "");
            String born = extras.getString("born", "");
            String gender = extras.getString("gender", "");
            String address = extras.getString("address", "");

            String phone = extras.getString("phone", "");
            String mail = extras.getString("mail", "");
            String instagram = extras.getString("instagram", "");
            String facebook = extras.getString("facebook", "");

            String SDName = extras.getString("SDName", "");
            String SDStart = extras.getString("SDStart", "");
            String SDEnd = extras.getString("SDEnd", "");
            String SMPName = extras.getString("SMPName", "");
            String SMPStart = extras.getString("SMPStart", "");
            String SMPEnd = extras.getString("SMPEnd", "");
            String SMAName = extras.getString("SMAName", "");
            String SMAStart = extras.getString("SMAStart", "");
            String SMAEnd = extras.getString("SMAEnd", "");
            String UnivName = extras.getString("UnivName", "");
            String UnivStart = extras.getString("UnivStart", "");
            String UnivEnd = extras.getString("UnivEnd", "");

            String Skill1 = extras.getString("Skill1", "");
            String Skill2 = extras.getString("Skill2", "");
            String Skill3 = extras.getString("Skill3", "");

            String Work1 = extras.getString("Work1", "");
            String Work1Start = extras.getString("Work1Start", "");
            String Work1End = extras.getString("Work1End", "");
            String Posisi1 = extras.getString("Posisi1", "");
            String Work2 = extras.getString("Work2", "");
            String Work2Start = extras.getString("Work2Start", "");
            String Work2End = extras.getString("Work2End", "");
            String Posisi2 = extras.getString("Posisi2", "");

            TXTName.setText(name);
            TXTStats.setText(stats);
            TXTBorn.setText(city + "," + born);
            TXTGender.setText(gender);
            TXTAddress.setText(address);

            TXTPhone.setText(phone);
            TXTMail.setText(mail);
            TXTInstagram.setText(instagram);
            TXTFacebook.setText(facebook);

            TXTSD.setText(SDName);
            TXTSMP.setText(SMPName);
            TXTSMA.setText(SMAName);
            TXTUniv.setText(UnivName);
            TXTSDStartEnd.setText(SDStart + "-" + SDEnd);
            TXTSMPStartEnd.setText(SMPStart + "-" + SMPEnd);
            TXTSMAStartEnd.setText(SMAStart + "-" + SMAEnd);
            TXTUnivStartEnd.setText(UnivStart + "-" + UnivEnd);

            TXTSkill1.setText(Skill1);
            TXTSkill2.setText(Skill2);
            TXTSkill3.setText(Skill3);

            TXTWork1StartEnd.setText(Work1Start + "-" + Work1End);
            TXTWork1.setText(Work1);
            TXTPosisi1.setText(Posisi1);
            TXTWork2StartEnd.setText(Work2Start + "-" + Work2End);
            TXTWork2.setText(Work2);
            TXTPosisi2.setText(Posisi2);
        }

        BTNPdfSave.setOnClickListener(
                v -> {
                    createPdfBack();
                });

        BTNColor.setOnClickListener(
                v -> {
                    int currentColor = TXTName.getCurrentTextColor();

                    AmbilWarnaDialog colorPickerDialog =
                            new AmbilWarnaDialog(
                                    this,
                                    currentColor,
                                    new AmbilWarnaDialog.OnAmbilWarnaListener() {
                                        @Override
                                        public void onOk(AmbilWarnaDialog dialog, int color) {

                                            TXTStats.setTextColor(color);
                                            TXTBorn.setTextColor(color);
                                            TXTGender.setTextColor(color);
                                            TXTAddress.setTextColor(color);

                                            TXTPhone.setTextColor(color);
                                            TXTMail.setTextColor(color);
                                            TXTInstagram.setTextColor(color);
                                            TXTFacebook.setTextColor(color);

                                            TXTSD.setTextColor(color);
                                            TXTSMP.setTextColor(color);
                                            TXTSMA.setTextColor(color);
                                            TXTUniv.setTextColor(color);

                                            TXTSkill1.setTextColor(color);
                                            TXTSkill2.setTextColor(color);
                                            TXTSkill3.setTextColor(color);

                                            TXTWork1.setTextColor(color);
                                            TXTWork2.setTextColor(color);
                                        }

                                        @Override
                                        public void onCancel(AmbilWarnaDialog dialog) {
                                            // Handle cancellation if needed
                                        }
                                    });

                    colorPickerDialog.show();
                });

        BTNBackground.setOnClickListener(
                v -> {
                    BTNBackgroundClick();
                });
    }

    private void BTNBackgroundClick() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Pilih Gambar")
                .setItems(
                        new CharSequence[] {"Gambar External", "Gambar Drawable"},
                        (dialog, which) -> {
                            if (which == 0) {
                                Intent intent =
                                        new Intent(
                                                Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE_10);
                            } else if (which == 1) {
                                int[] drawableImages = {
                                    R.drawable.bg, R.drawable.bg1, R.drawable.bg2, R.drawable.bg3,
                                };
                                showDrawableImagesDialog(drawableImages);
                            }
                        })
                .show();
    }

    private void showDrawableImagesDialog(int[] drawableImages) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Pilih Gambar dari Drawable");

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.select_dialog_item);
        for (int drawableId : drawableImages) {
            adapter.add(getString(drawableId));
        }

        builder.setPositiveButton(
                "Batal",
                (dialog1, which1) -> {
                    BTNBackgroundClick();
                    dialog1.dismiss();
                });

        builder.setCancelable(false);

        builder.setAdapter(
                adapter,
                (dialog, which) -> {
                    int selectedImageId = drawableImages[which];

                    MaterialAlertDialogBuilder imagePreviewDialogBuilder =
                            new MaterialAlertDialogBuilder(this);
                    imagePreviewDialogBuilder
                            .setTitle("Preview Gambar")
                            .setPositiveButton(
                                    "Pilih",
                                    (dialog1, which1) -> {
                                        bgBack.setImageResource(selectedImageId);
                                    })
                            .setNegativeButton(
                                    "Batal",
                                    (dialog1, which1) -> {
                                        showDrawableImagesDialog(drawableImages);
                                        dialog1.dismiss();
                                    });

                    View dialogView =
                            getLayoutInflater().inflate(R.layout.dialog_image_preview, null);
                    ImageView imageView = dialogView.findViewById(R.id.imageViewPreview);
                    imageView.setImageResource(selectedImageId);

                    imagePreviewDialogBuilder.setView(dialogView);
                    imagePreviewDialogBuilder.setCancelable(false);
                    imagePreviewDialogBuilder.show();
                });
        builder.show();
    }

    private void createPdfBack() {
        PdfDocument document = new PdfDocument();
        View content = findViewById(R.id.contentBack);

        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(content.getWidth(), content.getHeight(), 1)
                        .create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Bitmap bitmap =
                Bitmap.createBitmap(
                        content.getWidth(), content.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        content.draw(canvas);

        page.getCanvas().drawBitmap(bitmap, 0, 0, null);

        document.finishPage(page);

        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.activity_edit_name, null);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Simpan PDF");
        builder.setView(customView);

        final EditText input = customView.findViewById(R.id.alertInput);
        builder.setCancelable(false);

        builder.setPositiveButton(
                "Simpan",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String fileName = input.getText().toString() + ".pdf";
                        savePdf(fileName, document);
                    }
                });

        builder.setNegativeButton(
                "Batal",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        document.close();
                    }
                });

        builder.show();
    }

    private void savePdf(String fileName, PdfDocument document) {
        File downloadFolder =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File pdfFile = new File(downloadFolder, fileName);

        try (FileOutputStream outputStream = new FileOutputStream(pdfFile)) {
            document.writeTo(outputStream);
            outputStream.flush();
            outputStream.close();
            Toast.makeText(
                            ResultActivity.this,
                            "Berhasil menyimpan difolder Download!",
                            Toast.LENGTH_SHORT)
                    .show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        document.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST_CODE_10 && data != null) {
                Uri selectedImageUri = data.getData();

                if (selectedImageUri != null) {
                    try {
                        Bitmap bitmap =
                                MediaStore.Images.Media.getBitmap(
                                        this.getContentResolver(), selectedImageUri);

                        bgBack.setImageBitmap(bitmap);

                        Toast.makeText(
                                        ResultActivity.this,
                                        "Berhasil mengubah background!",
                                        Toast.LENGTH_SHORT)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
