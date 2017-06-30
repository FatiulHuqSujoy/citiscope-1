package com.example.imm.citi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.example.imm.citi.R;
import com.example.imm.citi.technicalClasses.Authentication;
import com.example.imm.citi.technicalClasses.User;

import java.lang.reflect.Field;

import butterknife.OnClick;
import butterknife.Optional;

public class BottomBarActivity extends AppCompatActivity {
    Activity parent = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_in:
                showLoginPage();
                return true;
            case R.id.action_sign_out:
                Authentication auth = new Authentication();
                auth.logout(parent);
                return true;
            case R.id.action_sign_up:
                showRegisterPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showLoginPage() {

        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

    }


    private void showRegisterPage() {

        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);

    }


    @Optional
    @OnClick(R.id.img_notification)
    public void showNotification() {
        if(!(this instanceof NotificationActivity)) {
            startActivity(new Intent(this,NotificationActivity.class));
            finish();
        }
    }

    @Optional
    @OnClick(R.id.img_home)
    public void showHome() {
        if(!(this instanceof HomeActivity)) {
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }
    }

    @Optional
    @OnClick(R.id.img_profile)
    public void showProfile() {
       // Toast.makeText(parent, User.loggedIn + "__" + User.Email,Toast.LENGTH_LONG).show();

        if(!(this instanceof ProfileActivity)) {
            if(User.loggedIn){
                startActivity(new Intent(this,ProfileActivity.class));
                finish();
            }
            else{
                Toast.makeText(parent, "Sign in first",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Optional
    @OnClick(R.id.img_poll)
    public void showPoll() {
        if(!(this instanceof PollActivity)) {
            startActivity(new Intent(this,PollActivity.class));
            finish();
        }
    }

    protected void getOverflowMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
