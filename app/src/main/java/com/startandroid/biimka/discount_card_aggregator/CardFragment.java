package com.startandroid.biimka.discount_card_aggregator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class CardFragment extends MvpAppCompatFragment implements CardView {
    @InjectPresenter
    CardPresenter mCardPresenter;
    private EditText editTextNameCard;
    private ImageView imageViewFrontSideCard;
    private ImageView imageViewBackSideCard;
    private ImageView imageViewBarcode;
    private Button buttonCreateSave;
    private NavController navController;

    public static CardFragment newInstance() {
        return new CardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_card, null);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        editTextNameCard = rootView.findViewById(R.id.editTextNameCard);
        imageViewFrontSideCard = rootView.findViewById(R.id.textViewFrontSideCard);
        imageViewBackSideCard = rootView.findViewById(R.id.textViewBackSideCard);
        imageViewBarcode = rootView.findViewById(R.id.textViewBarcode);
        buttonCreateSave = rootView.findViewById(R.id.buttonCreateSave);
        mCardPresenter.setCard();
        return rootView;
    }

    @Override
    public void setNameCard() {
        editTextNameCard.getText().toString();
    }

    @Override
    public void onImageFrontClick() {
        imageViewFrontSideCard.setImageResource(R.mipmap.picture);
        imageViewFrontSideCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intentCardPhoto = new Intent(Intent.ACTION_PICK);
                intentCardPhoto.setType("image/*");
                intentCardPhoto.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intentCardPhoto, "Select Picture"), 1);
            }
        });
    }

    @Override
    public void onImageBackClick() {
        imageViewBackSideCard.setImageResource(R.mipmap.picture);
        imageViewBackSideCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intenCardPhoto = new Intent(Intent.ACTION_PICK);
                intenCardPhoto.setType("image/*");
                intenCardPhoto.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intenCardPhoto, "Select Picture"), 2);
            }
        });
    }

    @Override
    public void onBarcodeClick() {
        imageViewBarcode.setImageResource(R.mipmap.barcode);
        imageViewBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public void onButtonCreateSaveClick() {
        buttonCreateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.fragmentCardList);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = null;
            final Uri uri = data.getData();
            if (requestCode == 1) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Glide.with(this).load(bitmap)
                        .apply(new RequestOptions().override(500))
                        .into(imageViewFrontSideCard);
            } else if (requestCode == 2) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Glide.with(this).load(bitmap)
                        .apply(new RequestOptions().override(500))
                        .into(imageViewBackSideCard);
            }
        }
    }
}