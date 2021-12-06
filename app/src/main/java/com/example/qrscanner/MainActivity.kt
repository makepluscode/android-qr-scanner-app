package com.example.qrscanner

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator

import android.view.View
import android.widget.Toast
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun runQRcodeReader(view: View) {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("QR코드를 스캔해주세요!")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if (result.contents != null) {
                Toast.makeText(
                    this, "Scanned : ${result.contents} format: ${result.formatName}",
                    Toast.LENGTH_LONG
                ).show()
            }
            if(result.barcodeImagePath != null) {
                val bitmap = BitmapFactory.decodeFile(result.barcodeImagePath)
                val imgView : ImageView = findViewById(R.id.scannedBitmap)
                imgView.setImageBitmap(bitmap)
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        //super.onActivityResult()
    }
}

