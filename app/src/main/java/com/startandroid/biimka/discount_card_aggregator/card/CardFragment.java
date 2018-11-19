package com.startandroid.biimka.discount_card_aggregator.card;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.startandroid.biimka.discount_card_aggregator.BaseFragment;
import com.startandroid.biimka.discount_card_aggregator.FragmentIntentIntegrator;
import com.startandroid.biimka.discount_card_aggregator.R;

import java.io.IOException;

public class CardFragment extends BaseFragment implements CardView {
    @InjectPresenter
    CardPresenter mCardPresenter;

    public static final int REQUEST_CODE_FRONT_IMAGE = 1;
    public static final int REQUEST_CODE_BACK_IMAGE = 2;
    public static final int REQUEST_CODE_BARCODE = IntentIntegrator.REQUEST_CODE;

    private EditText editTextNameCard;

    private ImageView imageViewFrontSideCard;
    private ImageView imageViewBackSideCard;
    private ImageView imageViewBarcode;

    private TextView textViewFormatBarcode;
    private TextView textViewBarcodeContent;

    private Button buttonCreateUpdate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_card, null);

        editTextNameCard = rootView.findViewById(R.id.editTextNameCard);

        imageViewFrontSideCard = rootView.findViewById(R.id.imageViewFrontSideCard);
        imageViewBackSideCard = rootView.findViewById(R.id.imageViewBackSideCard);
        imageViewBarcode = rootView.findViewById(R.id.imageViewBarcode);

        textViewFormatBarcode = rootView.findViewById(R.id.textViewBarcodeFormat);
        textViewBarcodeContent = rootView.findViewById(R.id.textViewBarcodeContent);

        buttonCreateUpdate = rootView.findViewById(R.id.buttonCreateSave);

        imageViewFrontSideCard.setImageResource(R.mipmap.picture);
        imageViewBackSideCard.setImageResource(R.mipmap.picture);
        imageViewBarcode.setImageResource(R.mipmap.barcode);

        editTextNameCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextNameCard.setSelection(s.toString().length());
                buttonCreateUpdate.setEnabled(s.length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imageViewFrontSideCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardPresenter.onFrontSideClick();
            }
        });

        imageViewBackSideCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardPresenter.onBackSideClick();
            }
        });

        imageViewBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardPresenter.onBarcodeClick();
            }
        });

        buttonCreateUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardPresenter.createOrUpdate();
            }
        });
        return rootView;
    }

    @Override
    public void setName(String name) {
        editTextNameCard.setText(name);
    }

    @Override
    public void selectFrontImage() {
        final Intent intentCardPhoto = new Intent(Intent.ACTION_PICK);
        intentCardPhoto.setType("image/*");
        intentCardPhoto.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentCardPhoto, "Select Picture"), REQUEST_CODE_FRONT_IMAGE);
    }

    @Override
    public void setFrontImage(Bitmap image) {
        Glide.with(this).load(image)
                .apply(new RequestOptions().override(500))
                .into(imageViewFrontSideCard);
    }

    @Override
    public void selectBackImage() {
        final Intent intentCardPhoto = new Intent(Intent.ACTION_PICK);
        intentCardPhoto.setType("image/*");
        intentCardPhoto.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentCardPhoto, "Select Picture"), REQUEST_CODE_BACK_IMAGE);
    }

    @Override
    public void setBackImage(Bitmap image) {
        Glide.with(this).load(image)
                .apply(new RequestOptions().override(500))
                .into(imageViewBackSideCard);
    }

    @Override
    public void scanBarcode() {
        new FragmentIntentIntegrator(this).initiateScan();
    }

    @Override
    public void setBarcode(IntentResult result) {
        if (result != null) {
            textViewFormatBarcode.setText(getString(R.string.barcodeFormat) + result.getFormatName());
            textViewBarcodeContent.setText(getString(R.string.barcodeContent) + result.getContents());
        } else {
            textViewFormatBarcode.setText(getString(R.string.barcodeNotScanned));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = null;
            final Uri uri = data.getData();
            switch (requestCode) {
                case REQUEST_CODE_FRONT_IMAGE:
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mCardPresenter.onFrontSideImageChoosen(bitmap);
                    break;
                case REQUEST_CODE_BACK_IMAGE:
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mCardPresenter.onBackSideImageChoosen(bitmap);
                    break;
                case REQUEST_CODE_BARCODE:
                    final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                    mCardPresenter.onBarcodeScanned(result);
                    break;
            }
        }
    }
}