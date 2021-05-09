package com.gf160.barcodereader;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class QReaderActivity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener {

    private CaptureManager manager;

    private DecoratedBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        barcodeView = findViewById(R.id.db_qr);

        manager = new CaptureManager(this, barcodeView);
        manager.initializeFromIntent(getIntent(),savedInstanceState);
        manager.decode();
    }

    @Override
    public void onTorchOn() {
    }

    @Override
    public void onTorchOff() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        manager.onSaveInstanceState(outState);
    }

}