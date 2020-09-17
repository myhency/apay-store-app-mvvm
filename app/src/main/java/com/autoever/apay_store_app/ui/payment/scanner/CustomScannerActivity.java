package com.autoever.apay_store_app.ui.payment.scanner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_store_app.BR;
import com.autoever.apay_store_app.R;
import com.autoever.apay_store_app.ViewModelProviderFactory;
import com.autoever.apay_store_app.databinding.ActivityCustomScannerBinding;
import com.autoever.apay_store_app.ui.base.BaseActivity;
import com.autoever.apay_store_app.ui.payment.PaymentNavigator;
import com.autoever.apay_store_app.ui.payment.PaymentViewModel;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;

public class CustomScannerActivity extends BaseActivity<ActivityCustomScannerBinding, PaymentViewModel> implements PaymentNavigator {

    @Inject
    ViewModelProviderFactory factory;

    private PaymentViewModel mCustomScannerViewModel;
    private ActivityCustomScannerBinding mActivityCustomScannerBinding;

    private SurfaceView cameraPreview;
    private BarcodeDetector mBarcodeDetector;
    private CameraSource mCameraSource;
    private CountDownTimer countDownTimer;

    final int RequestCameraPermissionID = 1001;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CustomScannerActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_scanner;
    }

    @Override
    public PaymentViewModel getViewModel() {
        mCustomScannerViewModel = ViewModelProviders.of(this, factory).get(PaymentViewModel.class);
        return mCustomScannerViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCustomScannerBinding = getViewDataBinding();
        mCustomScannerViewModel.setNavigator(this);
        mActivityCustomScannerBinding = getViewDataBinding();

        setup();

        mCustomScannerViewModel.loadQrStoreDynamic();
    }

    private void setup() {
        countDownTimer = new CountDownTimer(60000, 1000) {


            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                mActivityCustomScannerBinding.timeLeft.setText("아래 QR 코드는 "+ millisUntilFinished/1000 +"초 이후에 만료됩니다.");
            }

            @Override
            public void onFinish() {
                mCustomScannerViewModel.loadQrStoreDynamic();
                this.start();
            }
        }.start();

        cameraPreview = mActivityCustomScannerBinding.barcodeScanner;

        mActivityCustomScannerBinding.refreshQr.setOnClickListener(v -> {
            countDownTimer.onFinish();
            countDownTimer.start();
        });

        mBarcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        mCameraSource = new CameraSource.Builder(this, mBarcodeDetector)
                .setAutoFocusEnabled(true)
                .build();

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Log.d("debug", "Camera Permission Not Granted");
                    ActivityCompat.requestPermissions(CustomScannerActivity.this,
                            new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                    return;
                }

                try {
                    mCameraSource.start(cameraPreview.getHolder());
                    Log.d("debug", "Camera Permission Granted");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mCameraSource.stop();
            }
        });

        mBarcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();
                if (qrCodes.size() != 0) {
                    Intent data = new Intent();
                    JsonObject convertedObject = new Gson().fromJson(qrCodes.valueAt(0).displayValue, JsonObject.class);
                    Log.d("debug", "shopCode:" + qrCodes.valueAt(0).displayValue);
                    data.putExtra("shopCode", qrCodes.valueAt(0).displayValue);
                    setResult(RESULT_OK, data);
                    finish();
                    Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(20);
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        mCameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openPriceFragment(String shopCode) {

    }

    @Override
    public void openPriceConfirmFragment(String shopCode, int price) {

    }

    @Override
    public void doPaymentReady() {

    }

    @Override
    public void openReceiptFragment(String storeName, Date createdDate, int amount, int userBalance) {

    }

    @Override
    public void openCancelFragment() {

    }

    @Override
    public void openCancelDetailFragment(long paymentId) {

    }

    @Override
    public void getQrStoreDynamicData(String parsedQrString) {
        Log.d("debug", parsedQrString);
        //QR 생성부분
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            //TODO. QR 에 들어갈 내용은 별로도 백앤드에 요청해야 함.
            //token, tokenSystemId 들을 post 로 보내면 됨.
            BitMatrix bitMatrix = multiFormatWriter.encode(
                    parsedQrString,
                    BarcodeFormat.QR_CODE,
                    3000,
                    3000);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            mActivityCustomScannerBinding.qrImage.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.d("debug", e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
        finish();
    }
}