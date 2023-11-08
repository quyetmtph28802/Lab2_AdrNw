package quyetmtph28802.fpoly.lab2.Lab2_2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import quyetmtph28802.fpoly.lab2.Lab2_1.MainActivity_Bai1;

public class BackgroundTask_POST extends AsyncTask<Void, Void, Void> {
    String duongdan = MainActivity_Bai2.SERVER_NAME;
    Context context;
    TextView txtResult;
    String strWidth, strLength;
    String strResult;
    ProgressDialog pDialog;

    public BackgroundTask_POST(Context context, String strWidth, String strLength, TextView txtResult) {
        this.context = context;
        this.strWidth = strWidth;
        this.strLength = strLength;
        this.txtResult = txtResult;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Calculating...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(duongdan);
            String s = "chieurong=" + URLEncoder.encode(strWidth, "utf-8") + "&chieudai="
                    + URLEncoder.encode(strLength, "utf-8");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setFixedLengthStreamingMode(s.getBytes().length);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());
            printWriter.println(s);
            printWriter.close();

            String line = "";
            BufferedReader bfr = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            while ((line = bfr.readLine()) != null){
                sb.append(line);
            }
            strResult = sb.toString();
            urlConnection.disconnect();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        if (pDialog.isShowing()){
            pDialog.dismiss();
        }
        txtResult.setText(strResult);
    }
}
