package com.elephant.examplequiza;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    int quiz_number;
    ArrayList<DtoStatment> quizArray;
    int[] quiz_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quiz_number = 0;

        //  statment初期化
        initStatment();

        //  画面セット
        setStatmentView();

        quiz_result = new int[quizArray.size()];
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("c").setTitle("aa")
//                .setPositiveButton("a", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                    }
//                });
//        builder.show();


    }

    Boolean initStatment(){
        //  String(APIで持ってきてシェアプリで保存) → JSON → DtoStatment
        Intent getIntent = getIntent();
        quizArray = new ArrayList<DtoStatment>();

        try {
            JSONArray jsonArray = new JSONArray(getIntent.getStringExtra("QuizList"));

            for (int i = 0; i < jsonArray.length(); i ++) {
                JSONObject json = jsonArray.getJSONObject(i);

                quizArray.add(new DtoStatment(json.getString("問題ID"),
                        json.getString("問題文"),
                        json.getString("回答1"),
                        json.getString("回答2"),
                        json.getString("回答3"),
                        json.getString("回答4"),
                        json.getString("解説"),
                        json.getString("正答"))
                );

            }

            return true;

        }catch (JSONException e){
            e.printStackTrace();
        return false;
        }
    }

    public void setStatmentView(){
        TextView question = findViewById(R.id.quizViewquestion);//問題文
        TextView title = findViewById(R.id.quizViewtitle);//難問めか
        Button OKButton = findViewById(R.id.quizViewOkbutton);//ボタン

        final RadioGroup rga = findViewById(R.id.RadioGroupAnswer);
        RadioButton rba1 = findViewById(R.id.RadioButtonAnswer1);
        RadioButton rba2 = findViewById(R.id.RadioButtonAnswer2);
        RadioButton rba3 = findViewById(R.id.RadioButtonAnswer3);
        RadioButton rba4 = findViewById(R.id.RadioButtonAnswer4);

        question.setText(quizArray.get(quiz_number).statement);
        title.setText("第" + (quiz_number + 1) + "問目");

        rga.check(-1); ;
        rba1.setText(quizArray.get(quiz_number).sec1);
        rba2.setText(quizArray.get(quiz_number).sec2);
        rba3.setText(quizArray.get(quiz_number).sec3);
        rba4.setText(quizArray.get(quiz_number).sec4);

        OKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (-1 != rga.getCheckedRadioButtonId()) {
if(((RadioButton)findViewById(rga.getCheckedRadioButtonId())).
        getText().equals(quizArray.get(quiz_number).Answer)){
    quiz_result[quiz_number] = 0;
    showExplanationAlert(true);
        }else {
    quiz_result[quiz_number] = 1;
    showExplanationAlert(false);
        }



                } else {
                    Toast.makeText(getApplicationContext(),
                            "何も選択されていません",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void showExplanationAlert(Boolean b){
        String title = "";
        if(b){
            title = "正解です";
        }else{
            title = "不正解です";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(quizArray.get(quiz_number).explanation).setTitle(title)
                .setPositiveButton("次の問題へ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        quiz_number = quiz_number + 1;
                        if(quiz_number>=quizArray.size()) {
                            startResult();
                        }else{
                            setStatmentView();
                        }
                    }
                });
        builder.show();

    }

    public void startResult(){
        Intent intent = new Intent(this.getApplication(), ResultActivity.class);
        intent.putExtra("Result",quiz_result);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    //        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("c").setTitle("aa")
//                .setPositiveButton("a", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                    }
//                });
//        builder.show();


    public DtoStatment getStatment(int i){
        return (DtoStatment) quizArray.get(i);
    }



}
