package quyetmtph28802.fpoly.lab2.Lab2_3;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import quyetmtph28802.fpoly.lab2.Lab2_2.MainActivity_Bai2;

public class BackgroundTask_POST extends AsyncTask<Void, Void, Void> {
    String duongdan = MainActivity_Bai3.SERVER_NAME;
    Context context;
    TextView txtKetqua;
    String strKetqua;
    ProgressDialog pDialog;

    public BackgroundTask_POST(Context context, TextView txtKetqua) {
        this.context = context;
        this.txtKetqua = txtKetqua;
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
            String s = "canh=" + URLEncoder.encode(voids[0].toString(), "utf-8");
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
            strKetqua = sb.toString();
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
        txtKetqua.setText(strKetqua);
    }

}
