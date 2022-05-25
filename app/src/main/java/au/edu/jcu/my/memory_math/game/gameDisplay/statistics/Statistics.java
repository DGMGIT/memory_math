package au.edu.jcu.my.memory_math.game.gameDisplay.statistics;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import au.edu.jcu.my.memory_math.GameData;
import au.edu.jcu.my.memory_math.R;

public class Statistics extends AppCompatActivity {

    private GameData gameData;
    private ArrayAdapter<String> adapter;

    private String username;
    private String mode;


    private TextView modeE;
    private TextView modeM;
    private TextView modeH;

    private TextView avgE;
    private TextView avgM;
    private TextView avgH;

    private ListView scoreE;
    private ListView scoreM;
    private ListView scoreH;

    private TextView CTLE;
    private TextView CTLM;
    private TextView CTLH;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        //import data
        username = getIntent().getStringExtra("username");
        mode = getIntent().getStringExtra("mode");


        gameData = new GameData(this);

        modeE = findViewById(R.id.modeE);
        modeE.setText("Easy");
        modeM = findViewById(R.id.modeM);
        modeM.setText("Medium");
        modeH = findViewById(R.id.modeH);
        modeH.setText("Hard");

        avgE = findViewById(R.id.avgE);
        avgM = findViewById(R.id.avgM);
        avgH = findViewById(R.id.avgH);

        scoreE = findViewById(R.id.scoreE);
        scoreM = findViewById(R.id.scoreM);
        scoreH = findViewById(R.id.scoreH);

        CTLE = findViewById(R.id.CTLE);
        CTLM = findViewById(R.id.CTLM);
        CTLH = findViewById(R.id.CTLH);

        List<String> dataAllE = getDataAll("EASY");
        List<String> dataAllM = getDataAll("MEDIUM");
        List<String> dataAllH = getDataAll("HARD");

        setDataAll(scoreE, dataAllE);
        setDataAll(scoreM, dataAllM);
        setDataAll(scoreH, dataAllH);

        List<Integer> dataAvgE = setAvg("EASY");
        List<Integer> dataAvgM = setAvg("MEDIUM");
        List<Integer> dataAvgH = setAvg("HARD");

        double avgOfE = calculateAverage(dataAvgE);
        double avgOfM = calculateAverage(dataAvgM);
        double avgOfH = calculateAverage(dataAvgH);

        avgE.setText(String.format("AVG: %s", avgOfE));
        avgM.setText(String.format("AVG: %s",  avgOfM));
        avgH.setText(String.format("AVG: %s",  avgOfH));



    }

    public List<String> getDataAll(String table) {
        return gameData.getAllBased("USERNAME", table, username);
    }

    public List<Integer> setAvg(String table) {
        return gameData.getSelectBasedInt("USERNAME", table, 1, username);
    }

    public void setDataAll(ListView lV,List<String> result) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lV.setAdapter(adapter);
        adapter.addAll(result);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private double calculateAverage(List <Integer> marks) {
        return marks.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }

}