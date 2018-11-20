package mworkstation.com.mworkstation.encrypt2spref;

import androidx.appcompat.app.AppCompatActivity;
import mworkstation.com.mworkstation.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static mworkstation.com.mworkstation.util.Encryption.decrypt;
import static mworkstation.com.mworkstation.util.Encryption.encrypt;

public class DataEncyptActivity extends AppCompatActivity {
    SharedPreferences preferences;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_encypt);
        context=this;
        preferences = getSharedPreferences("some_prefs_name", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        findViewById(R.id.save_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(encrypt("password"), encrypt("dummypass"));
                editor.apply();
            }
        });
        findViewById(R.id.get_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passEncrypted = preferences.getString(encrypt("password"), encrypt("default"));
                String pass = decrypt(passEncrypted);

                Toast.makeText(context, "" + pass.toString(), Toast.LENGTH_LONG).show();

            }
        });

        // Write


        // Read
    }


}
