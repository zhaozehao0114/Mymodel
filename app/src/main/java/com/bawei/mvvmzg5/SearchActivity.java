package com.bawei.mvvmzg5;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    private MyEditText et1;
    private MyEditText et2;
    private MyEditText et3;
    private MyEditText et4;
    private MyEditText et5;
    private MyEditText et6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        et1.addTextChangedListener(new TextChangeLister(et1, et2));
        et2.setOnKeyListener(new MyOnKeyListener(et2,et1));
        et3.setOnKeyListener(new MyOnKeyListener(et3,et2));
        et4.setOnKeyListener(new MyOnKeyListener(et4,et3));
        et5.setOnKeyListener(new MyOnKeyListener(et5,et4));
        et6.setOnKeyListener(new MyOnKeyListener(et6,et5));
        et2.addTextChangedListener(new TextChangeLister(et2, et3));
        et3.addTextChangedListener(new TextChangeLister(et3, et4));
        et4.addTextChangedListener(new TextChangeLister(et4, et5));
        et5.addTextChangedListener(new TextChangeLister(et5, et6));
    }

    class TextChangeLister implements TextWatcher {
        private EditText thiset, nextEt;

        public TextChangeLister(EditText thiset, EditText nextEt) {
            this.thiset = thiset;
            this.nextEt = nextEt;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (before == 0 && count == 1) {
                thiset.clearFocus();
                nextEt.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    class MyOnKeyListener implements View.OnKeyListener{
        private EditText thiset, upDt;

        public MyOnKeyListener(EditText thiset, EditText upDt) {
            this.thiset = thiset;
            this.upDt = upDt;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction()==event.ACTION_DOWN&&keyCode==event.KEYCODE_DEL){

                    thiset.clearFocus();
                    upDt.requestFocus();

                return false;
            }
            return false;
        }
    }

    private void initView() {
        et1 = (MyEditText) findViewById(R.id.et1);
        et2 = (MyEditText) findViewById(R.id.et2);
        et3 = (MyEditText) findViewById(R.id.et3);
        et4 = (MyEditText) findViewById(R.id.et4);
        et5 = (MyEditText) findViewById(R.id.et5);
        et6 = (MyEditText) findViewById(R.id.et6);
    }
}