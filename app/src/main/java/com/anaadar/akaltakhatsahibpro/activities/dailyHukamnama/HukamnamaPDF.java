package com.anaadar.akaltakhatsahibpro.activities.dailyHukamnama;

/**
 * Created by Sumit Singh on 3/13/2018.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.anaadar.akaltakhatsahibpro.R;
import com.anaadar.akaltakhatsahibpro.constants.Constant;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;
import java.io.IOException;


/**
 * Created by IamDeveloper on 10/18/2016.
 */


public class HukamnamaPDF extends Activity implements OnPageChangeListener {
    private PDFView pdfView;
    private int num = 1;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        if (Constant.haveNetworkConnection(this)) {
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pdfView = (PDFView) findViewById(R.id.pdf_view);
            downloadPDF();
        }
        else
        {
            Constant.checkNetworkConnection(this);
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

        num = page;
    }

    public void downloadPDF() {
        new DownloadFile().execute("http://old.sgpc.net/hukumnama/jpeg%20hukamnama/hukamnama.pdf", "hukamnama.pdf");
    }


    private class DownloadFile extends AsyncTask<String, Void, Void> {
        String fileUrl;
        String fileName;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showpDialog();
        }

        @Override
        protected Void doInBackground(String... strings) {
            fileUrl = strings[0];
            fileName = strings[1];
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "PDF DOWNLOAD");
            folder.mkdir();
            File pdfFile = new File(folder, fileName);
            try {
                pdfFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DownloadHukamnama.downloadFile(fileUrl, pdfFile);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hidepDialog();
            Toast.makeText(getApplicationContext(), "Download PDf successfully", Toast.LENGTH_SHORT).show();

            Log.d("Download complete", "----------");
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "PDF DOWNLOAD");
            File file = new File(folder, fileName);
            Toast.makeText(getApplicationContext(), file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            Log.i("File", file.getAbsolutePath() + "");
            pdfView.fromFile(file)
                    .defaultPage(num)
                    .onPageChange(HukamnamaPDF.this)
                    .load();
        }
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}