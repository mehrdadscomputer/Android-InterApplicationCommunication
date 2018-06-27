package ir.mehrdadscomputer.sender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mButtonSend;
    EditText mEditText;
    Button mButtonSendReceiver;

    // This is the custom intent-filter action value.
    public static final String CUSTOM_BROADCAST_ACTION = "test";

    public static final int REQUEST_CODE = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editTextInput);
        mButtonSend = findViewById(R.id.buttonSend);
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, mEditText.getText().toString());
                Log.i("ttt5", mEditText.getText().toString());
                sendIntent.setType("text/plain");
                startActivityForResult(sendIntent, REQUEST_CODE);
            }
        });
        mButtonSendReceiver = findViewById(R.id.buttonSendReceiver);
        mButtonSendReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send a normal broadcast when being clicked.
                Intent intent = new Intent(CUSTOM_BROADCAST_ACTION);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                sendBroadcast(intent);
                Toast.makeText(MainActivity.this, "onClick: sender", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String message = data.getStringExtra("MESSAGE");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}