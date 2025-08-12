package com.example.permissionapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val TAG = "PermissionApp"

    private val requestPermissionLauncher = 
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Log.i(TAG, "تم منح إذن الوصول للملفات")
            } else {
                Log.i(TAG, "تم رفض إذن الوصول للملفات")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // لا نحتاج واجهة UI هنا، فقط لطلب الإذن وطباعة رسالة في Logcat

        Log.i(TAG, "مرحبا")  // طباعة "مرحبا" عند تشغيل التطبيق

        checkAndRequestPermission()
    }

    private fun checkAndRequestPermission() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
                Log.i(TAG, "الإذن موجود مسبقاً")
            }
            else -> {
                // طلب الإذن
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }
}