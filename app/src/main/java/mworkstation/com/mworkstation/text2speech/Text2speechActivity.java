package mworkstation.com.mworkstation.text2speech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import mworkstation.com.mworkstation.R;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Text2speechActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {
    HorizontalStepView setpview5;
    List<StepBean> stepsBeanList;
    private LinearLayout option1, option2, option3, option4;
    private ImageView next, speaker, rw1, rw2, rw3, rw4;
    int quizNo = 0;
    boolean played = false;
    ArrayList<Quiz> quizzes;
    Quiz loadQuiz;
    private TextToSpeech tts;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text2speech);
        setTitle("");
        context = this;
        init();

    }


    public void loadBoard(Quiz quiz) {
        option1.setBackgroundResource(quiz.getOption1());
        option2.setBackgroundResource(quiz.getOption2());
        option3.setBackgroundResource(quiz.getOption3());
        option4.setBackgroundResource(quiz.getOption4());
        next.setBackgroundResource(R.drawable.right_arrow_disable);
        speakOut(quiz.speech);

    }


    @Override
    public void onClick(View view) {
        Toast.makeText(this, "ddd", Toast.LENGTH_LONG).show();
        switch (view.getId()) {
            case R.id.option_1:

                if (!played) {
                    played = true;
                    if (loadQuiz.answer == loadQuiz.option1) {
                        showCorrect(loadQuiz);
                        stepper(quizNo,1);
                    } else {
                        stepper(quizNo,0);
                        showCorrect(loadQuiz);
                        showWrong(rw1);
                    }

                    next.setBackgroundResource(R.drawable.right_arrow);


                }
                break;
            case R.id.option_2:
                if (!played) {
                    played = true;
                    if (loadQuiz.answer == loadQuiz.option2) {
                        showCorrect(loadQuiz);
                        stepper(quizNo,1);
                    } else {
                        stepper(quizNo,0);
                        showCorrect(loadQuiz);
                        showWrong(rw1);
                    }
                    next.setBackgroundResource(R.drawable.right_arrow);

                }
                break;
            case R.id.option_3:
                if (!played) {
                    played = true;
                    if (loadQuiz.answer == loadQuiz.option3) {
                        stepper(quizNo,1);
                        showCorrect(loadQuiz);
                    } else {
                        stepper(quizNo,0);
                        showCorrect(loadQuiz);
                        showWrong(rw1);
                    }
                    next.setBackgroundResource(R.drawable.right_arrow);

                }
                break;
            case R.id.option_4:
                if (!played) {
                    played = true;
                    if (loadQuiz.answer == loadQuiz.option4) {
                        stepper(quizNo,1);
                        showCorrect(loadQuiz);
                    } else {
                        stepper(quizNo,0);
                        showCorrect(loadQuiz);
                        showWrong(rw1);
                    }
                    next.setBackgroundResource(R.drawable.right_arrow);

                }
                break;
            case R.id.next:

                if (played && quizNo<quizzes.size()-1) {
                    quizNo++;
                    showReset();
                    if (quizNo < quizzes.size()) {
                        played = false;
                        loadQuiz = quizzes.get(quizNo);
                    }
                    loadBoard(loadQuiz);
                }
                break;
            case R.id.speaker:
                speakOut(loadQuiz.speech);
                break;
        }
    }

    public void showCorrect(Quiz quiz) {

        if (quiz.answer == quiz.option1) {
            rw1.setVisibility(View.VISIBLE);
            rw1.setBackgroundResource(R.drawable.checked);
        } else if (quiz.answer == quiz.option2) {
            rw2.setVisibility(View.VISIBLE);
            rw2.setBackgroundResource(R.drawable.checked);
        } else if (quiz.answer == quiz.option3) {
            rw3.setVisibility(View.VISIBLE);
            rw3.setBackgroundResource(R.drawable.checked);
        } else if (quiz.answer == quiz.option4) {
            rw4.setVisibility(View.VISIBLE);
            rw4.setBackgroundResource(R.drawable.checked);
        }

    }

    public void showWrong(ImageView imageView) {
        imageView.setBackgroundResource(R.drawable.close);
        imageView.setVisibility(View.VISIBLE);
    }

    public void showReset() {

        rw1.setVisibility(View.GONE);
        rw2.setVisibility(View.GONE);
        rw3.setVisibility(View.GONE);
        rw4.setVisibility(View.GONE);
        next.setBackgroundResource(R.drawable.right_arrow_disable);
        played = false;

    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.JAPANESE);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                //tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    private void speakOut(String text) {

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    public void stepper(int pos,int result) {


        if (pos > -1 && pos<quizzes.size()) {
            stepsBeanList.clear();
            quizzes.get(pos).setResult(result);
            for (int i = 0; i < quizzes.size(); i++) {
                if (i <= pos) {
                    stepsBeanList.add(new StepBean( quizzes.get(i).getSpeech(), quizzes.get(i).getResult()));
                }else {
                    stepsBeanList.add(new StepBean( "", quizzes.get(i).getResult()));

                }
            }
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(stepsBeanList.size() * 190,
                LinearLayout.LayoutParams.WRAP_CONTENT); // or set height to any fixed value you want
        lp.gravity = Gravity.LEFT;
        setpview5.setLayoutParams(lp);


        setpview5
                .setStepViewTexts(stepsBeanList)//总步骤
                .setTextSize(10)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(context, R.color.colorAccent))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(context, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(context, R.color.colorAccent))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(context, R.drawable.circle_with_check))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.attention))
                .setMinimumWidth(stepsBeanList.size() * 100);//设置StepsViewIndicator AttentionIcon
    }


    public void init() {
        tts = new TextToSpeech(this, this);
        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        rw1 = findViewById(R.id.r_w_1);
        rw2 = findViewById(R.id.r_w_2);
        rw3 = findViewById(R.id.r_w_3);
        rw4 = findViewById(R.id.r_w_4);

        next = findViewById(R.id.next);
        speaker = findViewById(R.id.speaker);
        next.setOnClickListener(this);
        speaker.setOnClickListener(this);

        quizzes = new ArrayList<>();

        quizzes.add(new Quiz("Cap", R.drawable.cate_logo_1, R.drawable.cate_logo_7, R.drawable.cate_logo_3, R.drawable.cate_logo_8, R.drawable.cate_logo_7,-1));
        quizzes.add(new Quiz("Family", R.drawable.cate_logo_8, R.drawable.cate_logo_7, R.drawable.cate_logo_3, R.drawable.cate_logo_1, R.drawable.cate_logo_8,-1));
        quizzes.add(new Quiz("Home", R.drawable.cate_logo_1, R.drawable.cate_logo_7, R.drawable.cate_logo_3, R.drawable.cate_logo_8, R.drawable.cate_logo_3,-1));
        quizzes.add(new Quiz("Cat", R.drawable.cate_logo_3, R.drawable.cate_logo_7, R.drawable.cate_logo_1, R.drawable.cate_logo_8, R.drawable.cate_logo_1,-1));
        quizzes.add(new Quiz("Cap", R.drawable.cate_logo_1, R.drawable.cate_logo_7, R.drawable.cate_logo_3, R.drawable.cate_logo_8, R.drawable.cate_logo_7,-1));
        quizzes.add(new Quiz("Cap", R.drawable.cate_logo_1, R.drawable.cate_logo_7, R.drawable.cate_logo_3, R.drawable.cate_logo_8, R.drawable.cate_logo_7,-1));
        loadQuiz = quizzes.get(quizNo);
        loadBoard(loadQuiz);

        setpview5 = (HorizontalStepView) findViewById(R.id.step_view5);
        stepsBeanList = new ArrayList<>();
        for (int i = 0; i < quizzes.size(); i++) {
            stepsBeanList.add(new StepBean("", -1));
        }

        stepper(-1,-1);
    }
}
