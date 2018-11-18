package com.nancy.recyclerwithviewmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nancy.recyclerwithviewmodel.API.APIService;
import com.nancy.recyclerwithviewmodel.API.retroFitClass;
import com.nancy.recyclerwithviewmodel.adapter.RecyclerViewAdapter;
import com.nancy.recyclerwithviewmodel.model.User;
import com.nancy.recyclerwithviewmodel.model.UserList;
import com.nancy.recyclerwithviewmodel.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private RecyclerViewAdapter mAdapter;
    private RecyclerView recyclerView;
    private List<UserViewModel> userList;
    private List<User> userRetroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = new ArrayList<>();
        getList();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new RecyclerViewAdapter(userList);



        recyclerView.setAdapter(mAdapter);


    }


    public void loadJson()
    {
        APIService api = retroFitClass.getAPIService();
        Observable<UserList> observable = api.getUserList().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<UserList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserList userList) {

                userRetroList = new ArrayList<>();
                List<User> l = userList.getEmployees();

                for(int i=0;i<l.size();i++)
                {
                    User u =new User();
                    u.setName(l.get(i).getName());
                    u.setTitle(l.get(i).getTitle());
                    userRetroList.add(u);

                }

                //now set your custom adapter here

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void getList(){

        UserViewModel l1=new UserViewModel();
        l1.name = "Nanecy";
        l1.title = "Kapri";

//        l1.time.set("12:00");
//        l1.setText("Hi");
//        l1.setName("Nancy");

        UserViewModel l2=new UserViewModel();
        l2.name = "Adi";
        l2.title = "Bhatia";
//        l2.time.set("1:00");
//        l2.setText("Hi bye");
//        l2.setName("Adi");

//   ListViewModel item=new ListViewModel("nancy","hi", l1);
        userList.add(l1);
        userList.add(l2);
        //return l;

    }

}
