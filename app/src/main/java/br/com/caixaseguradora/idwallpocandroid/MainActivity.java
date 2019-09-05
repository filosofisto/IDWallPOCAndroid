package br.com.caixaseguradora.idwallpocandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import co.idwall.toolkit.IDwallToolkit;
import co.idwall.toolkit.flow.core.Doc;
import co.idwall.toolkit.flow.core.Flow;

public class MainActivity extends AppCompatActivity {

    private static final String AUTH_KEY = "23ba2b9963f6e5f5e97229f1a16ccc5e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initIDWall();
        bindListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String token = "";
        if (resultCode == RESULT_OK && requestCode == IDwallToolkit.IDWALL_REQUEST) {
            if (data != null && data.getExtras() != null && data.getExtras().containsKey(IDwallToolkit.TOKEN)) {
                token = data.getStringExtra(IDwallToolkit.TOKEN);
                showToken(token);
            } else {
                showError();
            }
        }
    }

    private void showToken(String token) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage(token)
                .setTitle(R.string.dialog_title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage(R.string.token_error)
                .setTitle(R.string.dialog_error);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initIDWall() {
        IDwallToolkit.getInstance().init(this.getApplication(), AUTH_KEY);
    }

    private void startFace() {
        IDwallToolkit.getInstance().startFlow(this, Flow.LIVENESS, Doc.CHOOSE);
    }

    private void startDocument() {
        IDwallToolkit.getInstance().startFlow(this, Flow.DOC, Doc.CHOOSE);
    }

    private void startComplete() {
        IDwallToolkit.getInstance().startFlow(this, Flow.COMPLETE, Doc.CHOOSE);
    }

    private void bindListeners() {
        findViewById(R.id.btnFace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFace();
            }
        });

        findViewById(R.id.btnDocument).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDocument();
            }
        });

        findViewById(R.id.btnComplete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startComplete();
            }
        });
    }
}
