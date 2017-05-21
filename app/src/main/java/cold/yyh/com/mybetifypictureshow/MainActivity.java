package cold.yyh.com.mybetifypictureshow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mt.mtxx.image.JNI;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView iv_mychangedcolor;
    private Button btn_showdata,btn_showdata2,btn_showdata3;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_mychangedcolor = (ImageView) findViewById(R.id.iv_mychangedcolor);
        btn_showdata = (Button) findViewById(R.id.btn_showdata);
        btn_showdata2 = (Button) findViewById(R.id.btn_showdata2);
        btn_showdata3 = (Button) findViewById(R.id.btn_showdata3);
        btn_showdata.setOnClickListener(this);
        btn_showdata2.setOnClickListener(this);
        btn_showdata3.setOnClickListener(this);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.iv_persion);
        iv_mychangedcolor.setImageBitmap(bitmap);

    }

    @Override
    public void onClick(View view) {
        JNI jni = new JNI();
        int[] paramArrayOfInt = new int[bitmap.getWidth()*bitmap.getHeight()];
        bitmap.getPixels(paramArrayOfInt,0,bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());
        Bitmap bitmap2;
        switch (view.getId()){
            case R.id.btn_showdata:
                jni.StyleLomoB(paramArrayOfInt,bitmap.getWidth(),bitmap.getHeight());
                jni.SharpPreview(paramArrayOfInt,bitmap.getWidth(),bitmap.getHeight(),10,10);
                bitmap2 = Bitmap.createBitmap(paramArrayOfInt,bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
                iv_mychangedcolor.setImageBitmap(bitmap2);
                break;
            case R.id.btn_showdata2:
               jni.StyleLomoB(paramArrayOfInt,bitmap.getWidth(),bitmap.getHeight());
                bitmap2 = Bitmap.createBitmap(paramArrayOfInt,bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
                iv_mychangedcolor.setImageBitmap(bitmap2);
                break;
            case R.id.btn_showdata3:
                jni.Sharp(paramArrayOfInt,bitmap.getWidth(),bitmap.getHeight(),2,10);//蜡像
                bitmap2 = Bitmap.createBitmap(paramArrayOfInt,bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
                iv_mychangedcolor.setImageBitmap(bitmap2);
                break;
        }
    }
    static {
        System.loadLibrary("mtimage-jni");
    }


    /**
     * @param
     * @param inStream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }
}
