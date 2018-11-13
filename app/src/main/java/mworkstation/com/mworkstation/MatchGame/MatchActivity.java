package mworkstation.com.mworkstation.MatchGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import mworkstation.com.mworkstation.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MatchActivity extends AppCompatActivity {
    SelectObject selectObject;
    int []gameCounter;
    int minutestest;
    double rate=5;
    long stopTime = 0;
    private int seconds = 0;
    private boolean startRun;
    Chronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        selectObject=new SelectObject();

        chronometer=findViewById(R.id.chronometer);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            startRun = savedInstanceState.getBoolean("startRun");
        }

        RecyclerView recyclerView=findViewById(R.id.boad);

        ArrayList<Match> matches=new ArrayList<>();
        matches.add(new Match("cat",R.drawable.ic_launcher_background,1,true));
        matches.add(new Match("home",R.drawable.ic_launcher_background,2,true));
        matches.add(new Match("shert",R.drawable.ic_launcher_background,3,true));
        matches.add(new Match("cap",R.drawable.ic_launcher_background,4,true));
        matches.add(new Match("family",R.drawable.ic_launcher_background,5,true));
        matches.add(new Match("hellow",R.drawable.ic_launcher_background,6,true));
        matches.add(new Match("w1",R.drawable.cate_logo_1,1,false));
        matches.add(new Match("w2",R.drawable.cate_logo_3,2,false));
        matches.add(new Match("w3",R.drawable.cate_logo_4,3,false));
        matches.add(new Match("w4",R.drawable.cate_logo_7,4,false));
        matches.add(new Match("w5",R.drawable.cate_logo_8,5,false));
        matches.add(new Match("w6",R.drawable.cate_logo_12,6,false));
        gameCounter=new int[matches.size()/2+1];
        gameCounter[0]=1;
        for (int i=1;i<=matches.size()/2;i++ ){
            gameCounter[i]=0;
        }

        timer();
        Collections.shuffle(matches);
       // LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        MatchAdapter matchmakerAdapter = new MatchAdapter(matches, this);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(matchmakerAdapter);
        matchmakerAdapter.setOnItemClickListener(new MatchOnclickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(Match item, View view) {

                if(selectObject.f1Select){

                    if(selectObject.getItem1().id==item.id && selectObject.getItem1().answer!=item.answer){
                       // view.setBackgroundColor(R.color.colorPrimaryDark);
                        view.findViewById(R.id.hide_info).setVisibility(View.GONE);
                        view.setBackgroundColor(R.color.colorPrimary);
                        selectObject.getF1().findViewById(R.id.hide_info).setVisibility(View.GONE);
                        selectObject.setF1Select(false);
                        gameCounter[item.id]=1;

                        if(isGameOver()){
                            Toast.makeText(MatchActivity.this, "Game over", Toast.LENGTH_LONG).show();
                            timer();
                        }


                    }

                }else {
                    selectObject.setF1(view);
                    view.setBackgroundColor(R.color.colorPrimaryDark);
                    selectObject.setF1Select(true);
                    selectObject.setItem1(item);
                }

            }
        });


    }

    public void reset()
    {
        selectObject.setF1Select(false);

    }

    public boolean isGameOver(){
        boolean over=true;
        for (int i=1;i<gameCounter.length;i++){
            if(gameCounter[i]==0){
                over=false;
               // return false;
                break;
            }

        }
        Toast.makeText(this, ""+over, Toast.LENGTH_SHORT).show();


        return  over;
    }


    public void timer(){
        if (!startRun) {
            onClickStart();


            chronometer.setBase(SystemClock.elapsedRealtime() + stopTime);
            chronometer.start();

        }else {
            onClickStop();
            stopTime = chronometer.getBase() - SystemClock.elapsedRealtime();
            minutestest=getMinutesFromDurationString(chronometer.getText().toString());
            chronometer.stop();





        }
    }

    public static int getMinutesFromDurationString(String value){

        String [] parts = value.split(":");

        // Wrong format, no value for you.
        if(parts.length < 2 || parts.length > 3)
            return 0;

        int seconds = 0, minutes = 0, hours = 0;

        if(parts.length == 2){
            seconds = Integer.parseInt(parts[1]);
            minutes = Integer.parseInt(parts[0]);
        }
        else if(parts.length == 3){
            seconds = Integer.parseInt(parts[2]);
            minutes = Integer.parseInt(parts[1]);
            hours = Integer.parseInt(parts[0]);
        }

        return minutes + (hours*60);
    }
    public void onClickStart() {
        startRun = true;
    }

    public void onClickStop() {
        startRun = false;
    }

    public void onClickReset() {
        startRun = false;
        seconds = 0;
    }


}
