package jp.classmethod.android.sample.sharecompat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;

public class CustomSendActivity extends Activity {
    
    private EditText mAddress;
    private EditText mSubject;
    private EditText mMessage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        
        mAddress = (EditText) findViewById(R.id.address_text);
        mSubject = (EditText) findViewById(R.id.subject_text);
        mMessage = (EditText) findViewById(R.id.message_text);
        
        mAddress.addTextChangedListener(new UITextWatcher());
        mSubject.addTextChangedListener(new UITextWatcher());
        mMessage.addTextChangedListener(new UITextWatcher());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_send, menu);
        return true;
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        
        String[] toList = new String[] {mAddress.getText().toString()};
        
        // IntentBuilder をインスタンス化
        ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(CustomSendActivity.this);
        // データをセットする
        builder.setChooserTitle("Choose Send App");
        builder.setEmailTo(toList);
        builder.setSubject(mSubject.getText().toString());
        builder.setText(mMessage.getText().toString());
        builder.setType("text/plain");
        
        ShareCompat.configureMenuItem(menu, R.id.menu_share, builder);
        
        return true;
    }
    
    private class UITextWatcher implements TextWatcher {

        @Override
        public void afterTextChanged(Editable editable) {
        }

        @Override
        public void beforeTextChanged(CharSequence text, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence text, int start, int count, int after) {
            ActivityCompat.invalidateOptionsMenu(CustomSendActivity.this);
        }
        
    }

}
