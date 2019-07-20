package com.ts.demo_project.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ts.demo_project.R;
import com.ts.demo_project.databinding.ActivityMainBinding;
import com.ts.demo_project.utils.Api_response;
import com.ts.demo_project.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    public TextInputLayout tilPassword, tilUserId;
    private LoginViewModel viewModel;
    private String mUserName, mpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }

        activityBinding.setModel(viewModel);
        viewModel.getLiveData().observe(this, this::consumeResponse);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void consumeResponse(Api_response api_response) {

        switch (api_response.status) {

            case LOADING:
                Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_LONG);

                break;

            case SUCCESS:
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG);

                break;

            case ERROR:
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
                break;

            default:
                break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = viewModel.getUserName();
    }

    public String getMpassword() {
        return mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = viewModel.getPassword();
    }

}
