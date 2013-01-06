package jp.classmethod.android.sample.sharecompat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class SimpleSendActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_send, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.menu_share == item.getItemId()) {
            EditText address = (EditText) findViewById(R.id.address_text);
            EditText subject = (EditText) findViewById(R.id.subject_text);
            EditText message = (EditText) findViewById(R.id.message_text);
            
            String[] toList = new String[] {address.getText().toString()};
            
            // IntentBuilder をインスタンス化
            ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(SimpleSendActivity.this);
            // データをセットする
            builder.setChooserTitle("Choose Send App");
            builder.setEmailTo(toList);
            builder.setSubject(subject.getText().toString());
            builder.setText(message.getText().toString());
            builder.setType("text/plain");
            // Intent を起動する
            builder.startChooser();
        }
        return super.onOptionsItemSelected(item);
    }

}
