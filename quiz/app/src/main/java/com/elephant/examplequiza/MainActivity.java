package com.elephant.examplequiza;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.homePage);
        viewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager()));

    }

    public void startQuiz(String str){
        Intent intent = new Intent(this.getApplication(), QuizActivity.class);
        intent.putExtra("QuizList",str);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }


//    Intent intent = new Intent(this.getApplication(), SubActivity.class);
//    startActivity(intent);
}