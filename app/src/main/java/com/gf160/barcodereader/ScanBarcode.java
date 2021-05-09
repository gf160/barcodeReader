package com.gf160.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScanBarcode extends AppCompatActivity {

    private TextView todayStr;
    private TextView orgBarcode;
    private EditText productCode;
    private Button btnSale;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_barcode);

        todayStr = (EditText) findViewById(R.id.date_str);
        orgBarcode = (TextView) findViewById(R.id.org_barcode);
        productCode = (EditText) findViewById(R.id.produce_code);

        btnSale = (Button) findViewById(R.id.btn_sale);
        btnReturn = (Button) findViewById(R.id.btn_return);

        btnSale.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "sale click", Toast.LENGTH_LONG).show();
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "return click", Toast.LENGTH_LONG).show();
            }
        });

        IntentIntegrator qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false); // default가 세로모드인데 휴대폰 방향에 따라 가로, 세로로 자동 변경됩니다.
        qrScan.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "ScanBarcode Cancelled", Toast.LENGTH_LONG).show();
                // todo
            } else {
                Toast.makeText(this, "ScanBarcode Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                // todo
                todayStr.setText(getTodayStr());
                orgBarcode.setText(result.getContents());
                productCode.setText(result.getContents().substring(8,12));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private String getTodayStr(){
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);

        SimpleDateFormat simpleDate = new SimpleDateFormat("MM월 dd일 HH:mm");
        String getTime = simpleDate.format(mDate);
        return getTime;
    }
}
