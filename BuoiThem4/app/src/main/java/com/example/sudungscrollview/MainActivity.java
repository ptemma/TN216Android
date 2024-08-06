package com.example.sudungscrollview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String strContent = "Layout container for a view hierarchy that " +
            "can be scrolled by the user, allowing it to be larger than the" +
            "physical display.\nA ScrollView is a FrameLayout, meaning you should" +
            "place one child in it containing the entire contents to scroll; this" +
            "child may itself be a layout manager with a complex hierarchy of" +
            "objects. A child that is often used is a LinearLayout in a vertical" +
            "orientation, presenting a vertical array of top-level items that the" +
            "user can scroll through.\nYou should never use a ScrollView with a" +
            "ListView, because ListView takes care of its own vertical scrolling." +
            "Most importantly, doing this defeats all of the important" +
            "optimizations in ListView for dealing with large lists, since it" +
            "effectively forces the ListView to display its entire list of items" +
            "to fill up the infinite container supplied by ScrollView.\nThe" +
            "TextView class also takes care of its own scrolling, so does not" +
            "require a ScrollView, but using the two together is possible to" +
            "achieve the effect of a text view within a larger" +
            "container.\nScrollView only supports vertical scrolling. For" +
            "horizontal scrolling, use HorizontalScrollView.\nLayout container" +
            "for a view hierarchy that can be scrolled by the user, allowing it" +
            "to be larger than the physical display. A HorizontalScrollView is a" +
            "FrameLayout, meaning you should place one child in it containing the" +
            "entire contents to scroll; this child may itself be a layout manager" +
            "with a complex hierarchy of objects. A child that is often used is a" +
            "LinearLayout in a horizontal orientation, presenting a horizontal" +
            "array of top-level items that the user can scroll through.\nThe" +
            "TextView class also takes care of its own scrolling, so does not" +
            "require a HorizontalScrollView, but using the two together is" +
            "possible to achieve the effect of a text view within a larger" +
            "container.\nHorizontalScrollView only supports horizontal scrolling." +
            "For vertical scrolling, use either ScrollView or ListView.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtContent = findViewById(R.id.txt_content);
        txtContent.setText(strContent);
    }

    public void DongActivity(View view) {
        finish();
    }
}