package com.example.app_mobile_lena;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.app_mobile_lena.ProductDetail_section.ProductDetailActivity;
import com.example.app_mobile_lena.model.Item;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class QRScanActivity extends AppCompatActivity implements MyCallback {

    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private ArrayList<Item> itemList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private boolean shouldScan = true;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1001;


    public void readData( QR QR) {
        DocumentReference docRef = db.collection(QR.getCollection()).document(QR.getDocument());

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Item> itemList = new ArrayList<>();
                    DocumentSnapshot document = task.getResult();
                    Item item =  new Item();
                    //add item
                    item.setKey(document.getId());
                    item.setSale_price(Double.valueOf(document.getData().get("sale_price").toString()));
                    item.setPrice(Double.valueOf(document.getData().get("price").toString()));
                    item.setName(document.getData().get("name").toString());
                    item.setImage(document.getData().get("image").toString());
                    item.setDescription(document.getData().get("description").toString());
                    item.setSlider((ArrayList<String>) document.getData().get("slider"));
                    item.setQuantity((Long) document.getData().get("quantity"));
                    item.setRate(Double.valueOf(document.getData().get("rate").toString()));
                    itemList.add(item);
                    Intent intent = new Intent(QRScanActivity.this, ProductDetailActivity.class);
                    intent.putExtra("item", item);
                    startActivity(intent);
                    finish();

                } else {
                    Log.w("TAG", "Error getting documents.", task.getException());
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);

        surfaceView = findViewById(R.id.surfaceView);

        SurfaceView surfaceView = findViewById(R.id.surfaceView);


        // Kiểm tra quyền truy cập camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            startCamera();
        }
    }

    private void startCamera() {
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .setAutoFocusEnabled(true)
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(QRScanActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {}

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (shouldScan && barcodes.size() > 0) {
                    shouldScan = false;
                    String qrCodeValue = barcodes.valueAt(0).displayValue;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            Gson gson = new Gson();
                            QR qr = gson.fromJson(qrCodeValue, QR.class);

                            readData(qr);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
