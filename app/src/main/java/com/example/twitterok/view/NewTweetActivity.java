package com.example.twitterok.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.twitterok.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewTweetActivity extends AppCompatActivity {

    @BindView(R.id.new_tweet_text)
    public EditText newTweetText;

    @BindView(R.id.add_tweet)
    public Button newTweetAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tweet);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_tweet)
    public void addTweet(View view){
        Toast.makeText(this,"Tweet added",Toast.LENGTH_SHORT).show();
    }
}
