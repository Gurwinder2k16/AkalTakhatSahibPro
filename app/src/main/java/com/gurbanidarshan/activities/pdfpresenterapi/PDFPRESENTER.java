package com.gurbanidarshan.activities.pdfpresenterapi;

/**
 * Created by Sumit Singh on 3/13/2018.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.gurbanidarshan.R;
import com.gurbanidarshan.constants.Constant;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;
import java.io.IOException;


/**
 * Created by IamDeveloper on 10/18/2016.
 */


public class PDFPRESENTER extends AppCompatActivity implements OnPageChangeListener {
    private PDFView pdfView;
    private int num = 1;
    private ProgressDialog pDialog;
    private String identifier, identifierName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        identifier = getIntent().getStringExtra("identifier");
        identifierName = getIntent().getStringExtra("identifierName");
       if(Constant.haveNetworkConnection(this)) {
           pDialog = new ProgressDialog(this);
           pDialog.setMessage("Please wait...");
           pDialog.setCancelable(false);
           pdfView = (PDFView) findViewById(R.id.pdf_view);
           if (!identifier.isEmpty() && !identifierName.isEmpty()) {
               getSupportActionBar().setTitle(identifierName);
               downloadPDF();
           } else {
               Toast.makeText(PDFPRESENTER.this, "Technical Error,Please contact to administrator!", Toast.LENGTH_SHORT).show();
               finish();
           }
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
        new DownloadFile().execute(identifier, "temp.pdf");
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

            Log.d("Download complete", "----------");
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "PDF DOWNLOAD");
            File file = new File(folder, fileName);
            Log.i("File", file.getAbsolutePath() + "");
            pdfView.fromFile(file)
                    .defaultPage(num)
                    .onPageChange(PDFPRESENTER.this)
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