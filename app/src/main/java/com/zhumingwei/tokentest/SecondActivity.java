package com.zhumingwei.tokentest;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

//默认type会报错，但不会影响应用流程，不会崩溃。可能会影响到资源泄漏
//type为系统级的不会报错 但是需要权限
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }
    AlertDialog alertDialog;
    @Override
    protected void onStart() {
        super.onStart();
        requestOverlayPermission();
        Dialog alertDialog = new mDialog(this);
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        alertDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },3000);
    }
    int APP_PERMISSIONS = 1000;
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestOverlayPermission() {
        if(Settings.canDrawOverlays(this)){
            return;
        }

        Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        myIntent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(myIntent, APP_PERMISSIONS);
    }

    class mDialog extends Dialog{

        public mDialog( @NonNull Context context) {
            super(context);
        }

        public mDialog( @NonNull Context context, int themeResId) {
            super(context, themeResId);
        }

        protected mDialog( @NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            TextView tv = new TextView(getContext());
            tv.setLayoutParams(new ViewGroup.LayoutParams(300,300));
            setContentView(tv);
        }
    }
}
