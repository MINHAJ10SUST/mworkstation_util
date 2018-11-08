package mworkstation.com.mworkstation.app2install;

import androidx.appcompat.app.AppCompatActivity;
import mworkstation.com.mworkstation.R;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AppInstallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_install);

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
}
