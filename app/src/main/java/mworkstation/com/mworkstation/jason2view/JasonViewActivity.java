package mworkstation.com.mworkstation.jason2view;

import androidx.appcompat.app.AppCompatActivity;
import mworkstation.com.mjason2view.DynamicView;
import mworkstation.com.mjason2view.DynamicViewId;
import mworkstation.com.mworkstation.R;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JasonViewActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> dynamicVaules;
    EditText[] edLit;
    ArrayList<View> viewList;
    View sampleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edLit = new EditText[10];
        dynamicVaules = new ArrayList<>();


        JSONObject jsonObject;

        try {

            jsonObject = new JSONObject(readFile("sample.json", this));


            // String widget = jsonObject.getString("widget");
        } catch (JSONException je) {
            je.printStackTrace();
            jsonObject = null;
        }

        if (jsonObject != null) {

            /* create dynamic view and return the view with the holder class attached as tag */
            sampleView = DynamicView.createView(this, jsonObject, SampleViewHolder.class);
            /* get the view with id "testClick" and attach the onClickListener */
            ((SampleViewHolder) sampleView.getTag()).clickableView.setOnClickListener(this);

            /* add Layout Parameters in just created view and set as the contentView of the activity */
            sampleView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
            setContentView(sampleView);
//activity_jason2_view
        } else {
            Log.e("Json2View", "Could not load valid json file");
        }

    }

    @Override
    public void onClick(View v) {


        edLit[0] = (EditText) ((SampleViewHolder) sampleView.getTag()).e1;
        edLit[1] = (EditText) ((SampleViewHolder) sampleView.getTag()).e2;
        edLit[2] = (EditText) ((SampleViewHolder) sampleView.getTag()).e3;
        edLit[3] = (EditText) ((SampleViewHolder) sampleView.getTag()).e4;
        edLit[4] = (EditText) ((SampleViewHolder) sampleView.getTag()).e5;
        edLit[5] = (EditText) ((SampleViewHolder) sampleView.getTag()).e6;
        edLit[6] = (EditText) ((SampleViewHolder) sampleView.getTag()).e7;
        edLit[7] = (EditText) ((SampleViewHolder) sampleView.getTag()).e8;
        edLit[8] = (EditText) ((SampleViewHolder) sampleView.getTag()).e9;
        edLit[9] = (EditText) ((SampleViewHolder) sampleView.getTag()).e10;
        dynamicVaules.clear();
        for (int i = 0; i < 10; i++) {
            if (edLit[i] != null) {
                dynamicVaules.add(edLit[i].getText().toString());
            }
        }


         Toast.makeText(this, ""+dynamicVaules.toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Helper function to load file from assets
     */
    private String readFile(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets().open(fileName);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line;
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null) isr.close();
                if (fIn != null) fIn.close();
                if (input != null) input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    /**
     * Holder class that keep UI Component from the Dynamic View
     */
    static public class SampleViewHolder {
        @DynamicViewId(id = "submit")
        public View clickableView;

        @DynamicViewId(id = "e1")
        public View e1;
        @DynamicViewId(id = "e2")
        public View e2;
        @DynamicViewId(id = "e3")
        public View e3;
        @DynamicViewId(id = "e4")
        public View e4;
        @DynamicViewId(id = "e5")
        public View e5;
        @DynamicViewId(id = "e6")
        public View e6;
        @DynamicViewId(id = "e7")
        public View e7;
        @DynamicViewId(id = "e8")
        public View e8;
        @DynamicViewId(id = "e9")
        public View e9;
        @DynamicViewId(id = "e10")
        public View e10;

        public View[] viewList;

        public SampleViewHolder() {

            viewList = new View[10];

        }
    }

}
