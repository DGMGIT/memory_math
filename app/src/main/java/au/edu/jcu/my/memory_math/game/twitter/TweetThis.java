package au.edu.jcu.my.memory_math.game.twitter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import au.edu.jcu.my.memory_math.R;

import android.widget.Button;
import android.widget.TextView;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetThis extends AppCompatActivity {

    private TextView tweetEg;
    private Button buttonPostIt;
    private Button buttonNope;

    private String username;
    private String mode;
    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_this);


        username = getIntent().getStringExtra("username");
        mode = getIntent().getStringExtra("mode");
        score = getIntent().getIntExtra("Score", 30);

        tweetEg = findViewById(R.id.tweetEg);


        buttonPostIt = findViewById(R.id.buttonPostIt);
        buttonPostIt.setOnClickListener(v -> buttonPressed("post"));

        buttonNope = findViewById(R.id.buttonNope);
        buttonNope.setOnClickListener(v -> buttonPressed("nope"));
    }

    public void buttonPressed(String i) {
        if (i.equals("post")) {
            postTweet();
            finish();
        }else {
            finish();
        }
    }

    public void postTweet(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("COJmbfyLrsQtN3zmESIzXjZQf")
                .setOAuthConsumerSecret("Yd4Sz5d3GPCEZNdujnIIxHJaiwIVpUZWlKDCOkJg9QF0MpNOo6")
                .setOAuthAccessToken("1250966422458527745-XBMhOAaSDDUHggUfWwNxVpzm31znBd")
                .setOAuthAccessTokenSecret("Z8SM49sY8fFdHu6flZT5GTzzxFrLlAQHHYbx6pwrJtz37");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        String latestStatus = "Users " + username + " got a new HighScore on " + mode + " mode: " + score + "Points WoW";
        Status status = null;
        try {
            status = twitter.updateStatus(latestStatus);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        assert status != null;
        System.out.println("Successfully updated the status to ["+status.getText()+"].");
    }
}

