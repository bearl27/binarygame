package jp.ac.ritsumei.ise.phy.exp2.is0682hi.binarygame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import jp.ac.ritsumei.ise.phy.exp2.is0682hi.binarygame.Binary;

public class GameActivity extends AppCompatActivity {
    private TextView inputText, scoreText, question, mode, lastscore;

    private ImageView boolimg, lifeimg1,lifeimg2,lifeimg3,overimg,gametext,overlay;

    private final StringBuilder inputStringBuilder = new StringBuilder();

    private List<Integer> numberSequence;
    private List<String>  questions;
    private int currentIndex;

    private int diff = 0;

    private boolean gameOver = false;
    private boolean gameClear = false;

    private int life = 0;
    private int score = 0;
    private int round = 1;


//    private Binary binary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //テキストの設定
        inputText = findViewById(R.id.input_text);
        scoreText = findViewById(R.id.score);
        question = findViewById(R.id.question);
        mode = findViewById(R.id.round);
        lastscore= findViewById(R.id.lastscore);

        //画像の設定
        boolimg = findViewById(R.id.bool);
        lifeimg1 = findViewById(R.id.life1);
        lifeimg2 = findViewById(R.id.life2);
        lifeimg3 = findViewById(R.id.life3);
        overimg = findViewById(R.id.overimg);
        gametext = findViewById(R.id.gametext);
        overlay = findViewById(R.id.overlay);

        //ボタンの生成
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button confirmButton = findViewById(R.id.confirm_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button retry = findViewById(R.id.retry);
        Button back = findViewById(R.id.back);

        //難易度
        // Intent から int 値を取得
        Intent intent = getIntent();
        diff = intent.getIntExtra("myValueKey", 0);
        //System.out.println(diff);


        // ランダムな5つの数字の数列を生成
        generateNumber();

        // 初期表示
        displayCurrentNumber();


        //botton設定
        //0のボタン
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputStringBuilder.append("0");
                inputText.setText(inputStringBuilder.toString());
            }
        });

        //1のボタン
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputStringBuilder.append("1");
                inputText.setText(inputStringBuilder.toString());
            }
        });

        //確定ボタン
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNextNumber();
                inputStringBuilder.setLength(0);
                inputText.setText(inputStringBuilder.toString());
                gameover_clear();
            }
        });

        //削除
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputStringBuilder.length() > 0) {
                    inputStringBuilder.deleteCharAt(inputStringBuilder.length() - 1);
                    inputText.setText(inputStringBuilder.toString());
                }
            }
        });

        //ボタンを非表示
        retry.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
    }

    //ゲームオーバー，ゲームクリアの画面
    private void gameover_clear(){
        Button retry = findViewById(R.id.retry);
        Button back = findViewById(R.id.back);
        //gameover,clear画面のボタン
        if (gameOver || gameClear) {
            retry.setVisibility(View.VISIBLE);
            back.setVisibility(View.VISIBLE);
            //最終score表示
            lastscore.setText("SCORE："+score);
            lastscore.setVisibility(View.VISIBLE);
            overlay.setImageResource(R.drawable.overlay);
            overimg.setImageResource(R.drawable.white);
            if(gameOver){
                gametext.setImageResource(R.drawable.overtext);
            }else{
                gametext.setImageResource(R.drawable.cleartext);
            }
        } else {
            retry.setVisibility(View.GONE);
            back.setVisibility(View.GONE);
            lastscore.setVisibility(View.GONE);
            overlay.setImageResource(R.drawable.none);
            overimg.setImageResource(R.drawable.none);
            gametext.setImageResource(R.drawable.none);
        }
    }

    //ホーム画面に戻る
    public void onExitButtonTapped(View view) {
        database();
        finish();
    }

    //データを保存する
    private void database(){
        int temp_diff = diff+1;
        int temp_score = score;
        System.out.println("temp"+temp_diff);
        // SharedPreferencesを取得
        SharedPreferences sharedPreferences = getSharedPreferences("Ranking", MODE_PRIVATE);
        // SharedPreferences.Editorを取得してデータを保存
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(temp_score >= sharedPreferences.getInt("score1", 0)-1){
            editor.putInt("difficult1", temp_diff);
            editor.putInt("score1", temp_score);
            temp_diff = sharedPreferences.getInt("difficult1", 0);
            temp_score = sharedPreferences.getInt("score1", 0);
            //System.out.println("temp:" +temp_diff);//デバック用
        }
        if(temp_diff != 0 && temp_score >= sharedPreferences.getInt("score2", 0)-1){
            editor.putInt("difficult2", temp_diff);
            editor.putInt("score2", temp_score);
            temp_diff = sharedPreferences.getInt("difficult2", 0);
            temp_score = sharedPreferences.getInt("score2", 0);
        }
        if(temp_diff != 0 && temp_score >= sharedPreferences.getInt("score3", 0)-1 ){
            editor.putInt("difficult3", temp_diff);
            editor.putInt("score3", temp_score);
        }
        editor.apply(); // 変更を反映
    }

    //全てをきれいにする
    public void clear(View view){
        database();
        gameOver = false;
        gameClear = false;
        currentIndex = 0;
        score = 0;
        life = 0;
        round = 1;
        boolimg.setImageResource(R.drawable.none);
        //score表示
        scoreText.setText("SCORE："+score);
        mode.setText("Round1");
        generateNumber();
        displayCurrentNumber();
        gameover_clear();
        lifeimg1.setImageResource(R.drawable.life);
        lifeimg2.setImageResource(R.drawable.life);
        lifeimg3.setImageResource(R.drawable.life);
    }


    //問題を作る
    private void generateNumber() {
        numberSequence = new ArrayList<>();
        Random random = new Random();
        int intnum;
        String binary;
        questions = new ArrayList<>();


        int basenum = 16;
        for (int i = 0; i < 4; i++) {
            intnum = random.nextInt(basenum);
            numberSequence.add(intnum);
            binary = Integer.toBinaryString(intnum);
            questions.add(binary);
        }
        for (int i = 0; i < 4; i++) {
            intnum = random.nextInt(basenum) + basenum;
            numberSequence.add(intnum);
            binary = Integer.toBinaryString(intnum);
            questions.add(binary);
        }
        for (int i = 0; i < 4; i++) {
            intnum = random.nextInt(basenum *2) + basenum *2;
            numberSequence.add(intnum);
            binary = Integer.toBinaryString(intnum);
            questions.add(binary);
        }
        for (int i = 0; i < 4; i++) {
            intnum = random.nextInt(basenum *4) + basenum *4;
            numberSequence.add(intnum);
            binary = Integer.toBinaryString(intnum);
            questions.add(binary);
        }
        for (int i = 0; i < 4; i++) {
            intnum = random.nextInt(basenum *8) + basenum *8;
            numberSequence.add(intnum);
            binary = Integer.toBinaryString(intnum);
            questions.add(binary);
        }
        currentIndex = 0;
    }


    //現在の問題表示
    private void displayCurrentNumber() {
        if (currentIndex < numberSequence.size()) {
            question.setText(String.valueOf(numberSequence.get(currentIndex + diff*4)));
        }
    }

    //次の問題表示
    private void displayNextNumber() {
        boolean ok = inputText.getText().toString().equals(String.valueOf(questions.get(currentIndex + 4*diff)));
        if(ok) {
            currentIndex++;
            score = score + (round + diff) * 5;
            //score表示
            scoreText.setText("SCORE："+score);
            //正解
            boolimg.setImageResource(R.drawable.maru);
        }
        else {
            //不正解
            boolimg.setImageResource(R.drawable.batu);
            life++;
            lifedisp();
            if(life > 2){
                mode.setText("GAMEOVER");
                gameOver = true;
            }
        }
        // 問題が12問以下のとき
        if (currentIndex < 12) {
            displayCurrentNumber();
        } else {
            mode.setText("GAMECLEAR");
            gameClear = true;
        }

        //round表示
        if(currentIndex%4 == 0 && ok) {
            round++;
            mode.setText("Round" + round);
        }

    }

    //残機表示
    private void lifedisp(){
        switch(life){
            case 0:
                lifeimg1.setImageResource(R.drawable.life);
                lifeimg2.setImageResource(R.drawable.life);
                lifeimg3.setImageResource(R.drawable.life);
            case 1:
                lifeimg1.setImageResource(R.drawable.none);
                break;
            case 2:
                lifeimg2.setImageResource(R.drawable.none);
                break;
            case 3:
                lifeimg3.setImageResource(R.drawable.none);
                break;
        }
    }

    }

