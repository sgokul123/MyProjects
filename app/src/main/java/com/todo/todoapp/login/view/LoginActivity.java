package com.todo.todoapp.login.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.todo.todoapp.R;
import com.todo.todoapp.login.presenter.LoginLoginPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginInterface {
    private  String TAG ="LoginActivity";
    AppCompatButton mButtonLogin;
    AppCompatEditText mEditTextEmail,mEditTextPassword;
    AppCompatTextView textview1;
    private String mStrPass,mStrEmail;
    private Pattern mPattern;
    private LoginLoginPresenter login;
    private Matcher mMatcher;
    ProgressDialog progressDialog;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mButtonLogin =(AppCompatButton) findViewById(R.id.button_login);
        mEditTextEmail=(AppCompatEditText) findViewById(R.id.edittext_email);
        mEditTextPassword=(AppCompatEditText) findViewById(R.id.edittext_password);
        textview1=(AppCompatTextView) findViewById(R.id.textviewmarquee);
        mButtonLogin.setOnClickListener(this);
        Log.i(TAG, "onCreate: ");
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait while login ...");
       login = new LoginLoginPresenter(LoginActivity.this);
      /*  Animation animationToRight = new TranslateAnimation(-400,400, 0, 0);
        animationToRight.setDuration(12000);
        animationToRight.setRepeatMode(Animation.RESTART);
        animationToRight.setRepeatCount(Animation.INFINITE);
        textview1.setAnimation(animationToRight);*/
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "login...", Toast.LENGTH_SHORT).show();
       // LoginPresenterInterface presenter=new LoginLoginPresenter();
        doAuthentication();
    }

    private void doAuthentication() {
        mPattern = Pattern.compile(EMAIL_PATTERN);
        mStrEmail = mEditTextEmail.getText().toString();
        mStrPass = mEditTextPassword.getText().toString();
        Log.i(TAG, "doAuthentication: "+mStrEmail);
        if (!(mStrEmail.equals("") && mStrPass.equals(""))) {
            mMatcher = mPattern.matcher(mStrEmail);
            if (mMatcher.matches()) {
                login.getLogin(mStrEmail,mStrPass);

            } else {
                Toast.makeText(getApplicationContext(), "Please Enter Valid Email ...", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter The User Name  And Password ... ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "Success....", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "loginSuccess: ");

    }

    @Override
    public void loginFailuar() {
        progressDialog.dismiss();
        Log.i(TAG, "loginFailuar: ");
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void closeProgress() {
        progressDialog.dismiss();
    }
}
