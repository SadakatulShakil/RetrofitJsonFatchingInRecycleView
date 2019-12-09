package com.example.retrofitjsonfatchinginrecycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DisplayActivity extends AppCompatActivity {

    private TextView nameTV,realNameTv,teamTV,firstAppearanceTV,createdByTV,publisherTV,biographyTV;
    CircleImageView heroImage;
    private Hero heroData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        initView();

        Intent intent = getIntent();
        heroData = intent.getExtras().getParcelable("HeroData");

        setHeroData();
    }

    private void setHeroData() {
        nameTV.setText(heroData.getName());
        realNameTv.setText(heroData.getRealname());
        teamTV.setText(heroData.getTeam());
        firstAppearanceTV.setText(heroData.getFirstappearance());
        createdByTV.setText(heroData.getCreatedby());
        publisherTV.setText(heroData.getPublisher());
        biographyTV.setText(heroData.getBio());
        Picasso.get().load(heroData.getImageurl()).into(heroImage);
    }

    private void initView() {
        nameTV = findViewById(R.id.nameTv);
        realNameTv= findViewById(R.id.realNameTv);
        teamTV= findViewById(R.id.teamTv);
        firstAppearanceTV= findViewById(R.id.appearanceTv);
        createdByTV= findViewById(R.id.createdTv);
        publisherTV= findViewById(R.id.publisherTv);
        biographyTV= findViewById(R.id.biographyTv);
        heroImage = findViewById(R.id.profile_image);
    }
}
