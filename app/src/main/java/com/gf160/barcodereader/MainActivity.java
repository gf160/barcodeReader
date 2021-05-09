package com.gf160.barcodereader;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gf160.barcodereader.common.DBHelper;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText iKnowCode;
    private Button scanBarcodeBtn;

    private DBHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        helper = new DBHelper(MainActivity.this, "lee.db", null, 1);
//        db = helper.getWritableDatabase();
//        helper.onCreate(db);

        iKnowCode = (EditText) findViewById(R.id.i_know_code);

        scanBarcodeBtn = (Button) findViewById(R.id.scanBarcode);
        scanBarcodeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String iKnowStr = iKnowCode.getText().toString();
                if(iKnowStr.length() == 0){
                    Intent intent = new Intent(MainActivity.this, ScanBarcode.class);
                    startActivity(intent);
                } else {
                    //TODO DB에 저장
                    //TODO listView refresh
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                //Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                Log.d("Main", "Cancelled");
            } else {
                Log.d("Main", "Scanned: " +  result.getContents());
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

//                ContentValues values = new ContentValues();
//                values.put("date_str", getTodayStr());
//                values.put("org_barcode", result.getContents());
//                values.put("product_code", result.getContents().substring(8, 12));
//                values.put("product_type", "sale");
//                db.insert("sale_log",null,values);
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private String getTodayStr(){
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);

        SimpleDateFormat simpleDate = new SimpleDateFormat("MM월 dd일 hh:mm");
        String getTime = simpleDate.format(mDate);
        return getTime;
    }

    private String getProductCode_sam(String _org_barcode){
        return _org_barcode.substring(8, 12);
    }
}
