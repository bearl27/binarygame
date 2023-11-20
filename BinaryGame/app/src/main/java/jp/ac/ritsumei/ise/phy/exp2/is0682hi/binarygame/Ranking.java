package jp.ac.ritsumei.ise.phy.exp2.is0682hi.binarygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Ranking extends AppCompatActivity {

    private int score1,diff1,score2,diff2,score3,diff3;
    private TextView first,second,third,first_diff,second_diff,third_diff;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //ランキング作成
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        first_diff = findViewById(R.id.first_diff);
        second_diff = findViewById(R.id.second_diff);
        third_diff = findViewById(R.id.third_diff);

        // SharedPreferencesを取得
        sharedPreferences = getSharedPreferences("Ranking", MODE_PRIVATE);

        disp_rank();
    }

    private void disp_rank(){
        score1 = sharedPreferences.getInt("score1",0);
        diff1 = sharedPreferences.getInt("difficult1",0);
        score2 = sharedPreferences.getInt("score2",0);
        diff2 = sharedPreferences.getInt("difficult2",0);
        score3 = sharedPreferences.getInt("score3",0);
        diff3 = sharedPreferences.getInt("difficult3",0);

        if(diff1 != 0){
            first.setText("🥇  "+ score1 + "pt");
            disp_diff(first_diff,diff1);
        }else{
            first.setVisibility(View.INVISIBLE);
            first_diff.setVisibility(View.INVISIBLE);
        }
        if(diff2 != 0){
            second.setText("🥈  "+ score2 + "pt");
            disp_diff(second_diff,diff2);
        }else{
            second.setVisibility(View.INVISIBLE);
            second_diff.setVisibility(View.INVISIBLE);
        }
        if(diff3 != 0){
            third.setText("🥉  "+ score3 + "pt");
            disp_diff(third_diff,diff3);
        }else{
            third.setVisibility(View.INVISIBLE);
            third_diff.setVisibility(View.INVISIBLE);
        }
    }

    private void disp_diff(TextView text,int diff){
        switch(diff){
            case 1:
                text.setText("EASY");
                text.setTextColor(Color.parseColor("#4CAF50"));
                break;
            case 2:
                text.setText("NORMAL");
                text.setTextColor(Color.parseColor("#FFC107"));
                break;
            case 3:
                text.setText("HARD");
                text.setTextColor(Color.parseColor("#F44336"));
                break;
            default:
                break;
        }
    }

    public void onExitButtonTapped(View view) {
        finish();
    }

    public void dataDelete(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // 全てのデータを削除
        editor.apply(); // 変更を反映
        disp_rank();
    }
}