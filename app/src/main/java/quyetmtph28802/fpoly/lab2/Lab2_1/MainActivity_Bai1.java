package quyetmtph28802.fpoly.lab2.Lab2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import quyetmtph28802.fpoly.lab2.R;

public class MainActivity_Bai1 extends AppCompatActivity implements View.OnClickListener {
    public static final String SERVER_NAME = "http://192.168.1.114/Lab2_quyetmtph28802/student_GET.php";
    private EditText edtName, edtScore;
    private Button btnSend;
    private TextView txtResult;
    String strName, strScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bai1);
        edtName = findViewById(R.id.edtName);
        edtScore = findViewById(R.id.edtScore);
        btnSend = findViewById(R.id.btnSend);
        txtResult = findViewById(R.id.txtResult);
        btnSend.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSend){
            strName = edtName.getText().toString();
            strScore = edtScore.getText().toString();
            BackgroundTask_GET backgroundTaskGet = new BackgroundTask_GET(txtResult, strName, strScore, this);
            backgroundTaskGet.execute();
        }
    }
}