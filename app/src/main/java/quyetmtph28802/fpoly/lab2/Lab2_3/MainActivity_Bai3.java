package quyetmtph28802.fpoly.lab2.Lab2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import quyetmtph28802.fpoly.lab2.R;

public class MainActivity_Bai3 extends AppCompatActivity implements View.OnClickListener {
    public static final String SERVER_NAME = "http://10.24.21.147/Lab2_quyetmtph28802/rectangle_POST.php";
    private EditText edtCanh;
    private Button btnSend;
    private TextView txtResult;
    String strCanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bai3);
        edtCanh = findViewById(R.id.edtCanh);
        btnSend = findViewById(R.id.btnSend);
        txtResult = findViewById(R.id.txtResult);

        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        strCanh = edtCanh.getText().toString();
        BackgroundTask_POST backgroundTaskPost = new BackgroundTask_POST(this, txtResult);
        backgroundTaskPost.execute();
    }
}