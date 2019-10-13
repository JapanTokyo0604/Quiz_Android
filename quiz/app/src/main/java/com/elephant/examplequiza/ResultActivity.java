package com.elephant.examplequiza;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity {

    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        linearLayout = findViewById(R.id.quizResultLinear);

        int[] resultArray = getIntent().getIntArrayExtra("Result");
        int counter = 0;
        int correct = 0;


            for(int b:resultArray) {
                counter = counter + 1;
                String str = "";
                if (b == 0) {
                    str = "○";
                    correct = correct + 1;
                } else {
                    str = "✖︎";
                }


                TextView textView = new TextView(this);
                textView.setText("第" + counter + "問目:" + str);

                linearLayout.addView(textView,
                        new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
            }


        TextView correcttextView = new TextView(this);
        correcttextView.setText("問題数:" + counter+ " / " + correct + "正解数");
        linearLayout.addView(correcttextView,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

        Button exitButton = findViewById(R.id.quizExitbutton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startHome();
            }
        });




    }

    public void startHome(){
        Intent intent = new Intent(this.getApplication(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}
