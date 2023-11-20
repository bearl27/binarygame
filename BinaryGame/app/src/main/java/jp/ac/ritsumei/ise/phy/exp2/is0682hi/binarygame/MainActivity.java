package jp.ac.ritsumei.ise.phy.exp2.is0682hi.binarygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button easy = findViewById(R.id.easy);
        Button normal = findViewById(R.id.normal);
        Button hard = findViewById(R.id.hard);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                // インテントに値を追加
                intent.putExtra("myValueKey", 0);
                startActivity(intent);
                //メニューバーを閉じる処理
                overlay = true;
                overlayButton(v);
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                // インテントに値を追加
                intent.putExtra("myValueKey", 1);
                startActivity(intent);
                //メニューバーを閉じる処理
                overlay = true;
                overlayButton(v);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                // インテントに値を追加
                intent.putExtra("myValueKey", 2);
                startActivity(intent);
                //メニューバーを閉じる処理
                overlay = true;
                overlayButton(v);
            }
        });

    }

    //三本線ボタンの関数
    private boolean overlay = false;
    public void overlayButton(View view){
        overlay = !overlay;//booleanの変更
        //Viewの呼び出し
        ImageView over_option = findViewById(R.id.option_over);
        TextView rank = findViewById(R.id.rank);
        TextView intro = findViewById(R.id.intro);
        if(overlay) {
            over_option.setVisibility(View.VISIBLE);// 見えるようにする
            rank.setVisibility(View.VISIBLE); // 見えるようにする
            intro.setVisibility(View.VISIBLE); // 見えるようにする
        }else{
            over_option.setVisibility(View.GONE);// 見えなくして領域も解放する
            rank.setVisibility(View.GONE); // 見えなくして領域も解放する
            intro.setVisibility(View.GONE); // 見えなくして領域も解放する
        }
    }

    public void goRank(View view){
        Intent intent = new Intent(MainActivity.this, Ranking.class);
        startActivity(intent);
        //メニューバーを閉じる処理
        overlayButton(view);
    }

    public void goIntro(View view){
        Intent intent = new Intent(MainActivity.this, Introduction.class);
        startActivity(intent);
        //メニューバーを閉じる処理
        overlayButton(view);
    }
}