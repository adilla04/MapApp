package com.example.mapapp;

import static com.example.mapapp.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class AboutPage extends AppCompatActivity {
    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_about_page);
        getSupportActionBar().setTitle("ABOUT PAGE");

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);


    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (finalI == 0) {
                        Intent intent = new Intent(AboutPage.this, News_Json.class);
                        startActivity(intent);
                    } else if (finalI == 1) {
                        Intent intent = new Intent(AboutPage.this, MapsActivity.class);
                        startActivity(intent);

                    } else if (finalI == 2) {
                        Intent intent = new Intent(AboutPage.this, about_us.class);
                        startActivity(intent);

                    } else if (finalI == 3) {
                        Intent intent = new Intent(AboutPage.this, resource.class);
                        startActivity(intent);

                    }
                }


            });
        }
    }
}

