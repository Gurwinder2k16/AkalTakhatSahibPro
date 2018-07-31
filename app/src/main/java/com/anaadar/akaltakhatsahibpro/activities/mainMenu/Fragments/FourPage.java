package com.anaadar.akaltakhatsahibpro.activities.mainMenu.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anaadar.akaltakhatsahibpro.R;
import com.anaadar.akaltakhatsahibpro.activities.dailyHukamnama.DownloadHukamnama;
import com.anaadar.akaltakhatsahibpro.constants.Constant;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sumit Singh on 3/12/2018.
 */

public class FourPage extends Fragment implements OnPageChangeListener, OnLoadCompleteListener {
    public static final String SAMPLE_FILE = "http://old.sgpc.net/CDN/Rehat-Maryada-_Punjabi_.pdf";
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    private ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pagefour, container, false);
        // displayFromAsset(SAMPLE_FILE);
        if (Constant.haveNetworkConnection(getActivity())) {
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pdfView = (PDFView) view.findViewById(R.id.pdfView);
            downloadPDF();
        } else {
            checkNetworkConnection();
        }
        return view;
    }

    private void checkNetworkConnection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("No internet Connection!!");
        builder.setMessage("Please turn on internet connection to continue services!");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(FourPage.this)
                .scrollHandle(new DefaultScrollHandle(getActivity()))
                .load();
    }


    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {
            Log.e("our Fragment", String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    public void downloadPDF() {
        new DownloadFile().execute("http://old.sgpc.net/CDN/Rehat-Maryada-_Punjabi_.pdf", "rehat_maryada.pdf");
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
            Toast.makeText(getActivity(), "Download PDf successfully", Toast.LENGTH_SHORT).show();

            Log.d("Download complete", "----------");
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "PDF DOWNLOAD");
            File file = new File(folder, fileName);
            Toast.makeText(getActivity(), file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            Log.i("File", file.getAbsolutePath() + "");
            pdfView.fromFile(file)
                    .defaultPage(pageNumber)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .onPageChange(FourPage.this)
                    .enableAnnotationRendering(true)
                    .onLoad(FourPage.this)
                    .scrollHandle(new DefaultScrollHandle(getActivity()))
                    .load();
            pdfFileName = file.getAbsoluteFile().toString();
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




