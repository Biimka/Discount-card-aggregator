package com.startandroid.biimka.discount_card_aggregator;

import android.os.Bundle;
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
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class CardFragment extends MvpAppCompatFragment implements MyViewCardFragment {
    @InjectPresenter
    MyPresenterCardFragment myPresenterFrag2;
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
        myPresenterFrag2.setCard();
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

            }
        });
    }

    @Override
    public void onImageBackClick() {
        imageViewBackSideCard.setImageResource(R.mipmap.picture);
        imageViewBackSideCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
}

interface MyViewCardFragment extends MvpView {
    void setNameCard();

    void onImageFrontClick();

    void onImageBackClick();

    void onBarcodeClick();

    void onButtonCreateSaveClick();
}