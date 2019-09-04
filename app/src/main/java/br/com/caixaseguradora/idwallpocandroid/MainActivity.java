package br.com.caixaseguradora.idwallpocandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    private void initIDWall() {
        IDwallToolkit.getInstance().init(this.getApplication(), AUTH_KEY);
    }

    private void startFace(){
        IDwallToolkit.getInstance().startFlow(this, Flow.LIVENESS, Doc.CHOOSE);
    }

    private void startDocument(){
        IDwallToolkit.getInstance().startFlow(this, Flow.DOC, Doc.CHOOSE);
    }

    private void startComplete(){
        IDwallToolkit.getInstance().startFlow(this, Flow.COMPLETE, Doc.CHOOSE);
    }
    private void bindListeners() {
        Button btnFace = findViewById(R.id.btnFace);
        btnFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFace();
            }
        });

        Button btnDocument = findViewById(R.id.btnDocument);
        btnDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDocument();
            }
        });

        Button btnComplete = findViewById(R.id.btnComplete);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startComplete();
            }
        });
    }

}
