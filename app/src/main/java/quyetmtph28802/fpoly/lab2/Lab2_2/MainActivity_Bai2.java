package quyetmtph28802.fpoly.lab2.Lab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import quyetmtph28802.fpoly.lab2.Lab2_1.BackgroundTask_GET;
import quyetmtph28802.fpoly.lab2.R;

public class MainActivity_Bai2 extends AppCompatActivity implements View.OnClickListener {

    public static final String SERVER_NAME = "http://10.24.21.147/Lab2_quyetmtph28802/rectangle_POST.php";
    private EditText edtWidth, edtLength;
    private Button btnSend;
    private TextView txtResult;
    String strWidth, strlength;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bai2);
        edtWidth = findViewById(R.id.edtWidth);
        edtLength = findViewById(R.id.edtLength);
        btnSend = findViewById(R.id.btnSend);
        txtResult = findViewById(R.id.txtResult);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSend){
            strWidth = edtWidth.getText().toString();
            strlength = edtLength.getText().toString();
            BackgroundTask_POST backgroundTaskPost = new BackgroundTask_POST(this, strWidth, strlength, txtResult );
            backgroundTaskPost.execute();
        }
    }
}