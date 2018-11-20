package mworkstation.com.mworkstation.screenshot;

import androidx.appcompat.app.AppCompatActivity;
import mworkstation.com.mworkstation.R;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ScreenShotActivity extends AppCompatActivity {
    ImageView screen_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);
        screen_img=findViewById(R.id.screen_img);
       // screen_img.setImageBitmap();
        screenShot(screen_img);


    }

    public Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
