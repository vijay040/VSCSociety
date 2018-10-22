package com.mmcs.societymaintainance.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.mmcs.societymaintainance.R;
import com.mmcs.societymaintainance.adaptor.EmployeeAdapter;
import com.mmcs.societymaintainance.adaptor.VisitorAdapter;
import com.mmcs.societymaintainance.model.EmployeeModel;
import com.mmcs.societymaintainance.model.EmployeeRestMeta;
import com.mmcs.societymaintainance.model.VisitorModel;
import com.mmcs.societymaintainance.model.VisitorRestMeta;
import com.mmcs.societymaintainance.util.Shprefrences;
import com.mmcs.societymaintainance.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView listEmployee;
    ProgressBar progressBar;
    RelativeLayout txtAdd;
    Shprefrences sh;
    ArrayList<EmployeeModel> employeeModels=new ArrayList();
    EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        listEmployee=findViewById(R.id.listEmployee);
        progressBar=findViewById(R.id.progress);
        SearchView editTextName=(SearchView) findViewById(R.id.edt);
        editTextName.setQueryHint(getString(R.string.search_here));
        editTextName.setOnQueryTextListener(this);
        txtAdd=findViewById(R.id.txtAdd);
        sh=new Shprefrences(this);
        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeListActivity.this,AddEmployeeActivity.class));
            }
        });
        setTitle();
        back();
    }
    private void setTitle() {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getString(R.string.employee_list));
    }
    private void back() {
        RelativeLayout drawerIcon = (RelativeLayout) findViewById(R.id.drawerIcon);
        drawerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        getEmployee("","");
    }

    public void getEmployee(String userid, String branchid) {

        Singleton.getInstance().getApi().getEmployeeList(userid, branchid).enqueue(new Callback<EmployeeRestMeta>() {
            @Override
            public void onResponse(Call<EmployeeRestMeta> call, Response<EmployeeRestMeta> response) {
                if(response.body()==null)
                    return;
                employeeModels=response.body().getResponse();
                employeeAdapter=new EmployeeAdapter(EmployeeListActivity.this,employeeModels);
                listEmployee.setAdapter(employeeAdapter);
                listEmployee.setEmptyView(findViewById(R.id.imz_nodata));
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<EmployeeRestMeta> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }



    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        s=s.toLowerCase();
        ArrayList<EmployeeModel> newlist=new ArrayList<>();
        for(EmployeeModel filterlist:employeeModels)
        {
            String name=filterlist.getName().toLowerCase();
            String address =filterlist.getPre_address().toLowerCase();
            String email =filterlist.getEmail().toLowerCase();
            String desi =filterlist.getMember_type().toLowerCase();
            String mob =filterlist.getContact().toLowerCase();
            if(name.contains(s)||address.contains(s)||email.contains(s)||desi.contains(s)||mob.contains(s)) {
                newlist.add(filterlist);
            }
        }
        employeeAdapter.filter(newlist);
        return true;
    }
}