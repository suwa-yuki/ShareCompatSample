package jp.classmethod.android.sample.sharecompat;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.widget.ImageView;
import android.widget.TextView;

public class RecieveActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve);
        
        // ShareCompat.IntentReader をインスタンス化
        ShareCompat.IntentReader reader = ShareCompat.IntentReader.from(RecieveActivity.this);
        
        // アプリアイコン
        ImageView appImageView = (ImageView) findViewById(R.id.app_image_view);
        appImageView.setImageDrawable(reader.getCallingApplicationIcon());
        // Activity アイコン
        ImageView activityImageView = (ImageView) findViewById(R.id.activity_image_view);
        activityImageView.setImageDrawable(reader.getCallingActivityIcon());
        
        // 呼び出し元の Activity
        String callingActivity = "null";
        ComponentName componentName = reader.getCallingActivity();
        if (componentName != null) {
            callingActivity = componentName.getClassName();
        }
        
        // 各データを読み込んで TextView に表示する
        String detail =  "CallingApplicationLabel=" + reader.getCallingApplicationLabel();
        detail += "\n" + "CallingPackage=" + reader.getCallingPackage();
        detail += "\n" + "CallingActivity=" + callingActivity;
        detail += "\n" + "Type=" + reader.getType();
        detail += "\n" + "Subject=" + reader.getSubject();
        detail += "\n" + "Text=" + reader.getText();
        detail += "\n" + "HtmlText=" + reader.getHtmlText();
        String [] emailToList = reader.getEmailTo();
        if (emailToList != null) {
            for (String emailTo : emailToList) {
                detail += "\n" + "EmailTo=" + emailTo;
            }
        } else {
            detail += "\n" + "EmailTo=null";
        }
        String [] emailCcList = reader.getEmailCc();
        if (emailCcList != null) {
            for (String emailCc : emailCcList) {
                detail += "\n" + "EmailCc=" + emailCc;
            }
        } else {
            detail += "\n" + "EmailCc=null";
        }
        String [] emailBccList = reader.getEmailBcc();
        if (emailBccList != null) {
            for (String emailBcc : emailBccList) {
                detail += "\n" + "EmailBcc=" + emailBcc;
            }
        } else {
            detail += "\n" + "EmailBcc=null";
        }
        int streamCount = reader.getStreamCount();
        detail += "\n" + "StreamCount=" + streamCount;
        for (int i = 0; i < streamCount; i++) {
            detail += "\n" + "Stream=" + reader.getStream(i);
        }
        
        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(detail);
    }

}
