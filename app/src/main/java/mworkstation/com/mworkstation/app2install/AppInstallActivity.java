package mworkstation.com.mworkstation.app2install;

import androidx.appcompat.app.AppCompatActivity;
import mworkstation.com.mworkstation.R;
import mworkstation.com.mworkstation.util.KeyString;
import mworkstation.com.mworkstation.util.MDate;
import mworkstation.com.mworkstation.util.SharedPreferenceManager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import static mworkstation.com.mworkstation.util.Encryption.decrypt;
import static mworkstation.com.mworkstation.util.Encryption.encrypt;
import static mworkstation.com.mworkstation.util.KeyString.APP_INSTALL_RECORD;

public class AppInstallActivity extends AppCompatActivity {
    private SharedPreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_install);
        init();
        initOnClick();
        offerBonus();


    }





    public void setepToInstall(String  uri){
        boolean isAppInstalled = appInstalledOrNot(uri);

        if(isAppInstalled) {

            Intent LaunchIntent = getPackageManager()
                    .getLaunchIntentForPackage(uri);
            startActivity(LaunchIntent);


        } else {
            final String appPackageName =uri;
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
            saveAppRecord(new AppRecord(appPackageName, MDate.getDateTime()));

        }
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {


        }

        return false;
    }

    public void init(){
        preferenceManager = new SharedPreferenceManager(this, KeyString.PREFERENCE_NAME);
    }

    public void initOnClick(){
        findViewById(R.id.pathao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setepToInstall("com.pathao.user");
            }
        });
        findViewById(R.id.uber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setepToInstall("com.ubercab.eats");
            }
        });


    }

    public AppRecord getAppRecord() {
        Gson gson = new Gson();
        return gson.fromJson(decrypt(preferenceManager.getValue(APP_INSTALL_RECORD, "")), AppRecord.class);
    }



    public  void saveAppRecord(AppRecord appRecord){
        Gson gson = new Gson();
        preferenceManager.setValue(APP_INSTALL_RECORD,encrypt(gson.toJson(appRecord)));

    }

    public void offerBonus(){
        AppRecord appRecord=getAppRecord();
        if(appRecord!=null)
        if(appInstalledOrNot(appRecord.getAppId())){
            Toast.makeText(this, ""+MDate.printDifference(appRecord.tigerTime,MDate.getDateTime()), Toast.LENGTH_SHORT).show();
        }
    }

}
