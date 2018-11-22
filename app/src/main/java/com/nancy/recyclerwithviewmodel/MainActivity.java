package com.nancy.recyclerwithviewmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.nancy.recyclerwithviewmodel.API.APIService;
import com.nancy.recyclerwithviewmodel.adapter.RecyclerAdapterNoDataBinding;
import com.nancy.recyclerwithviewmodel.application.FlowerApplication;
import com.nancy.recyclerwithviewmodel.model.Flower;
import com.nancy.recyclerwithviewmodel.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements Observer<List<Flower>>, RecyclerAdapterNoDataBinding.FlowerClickListener {
    private RecyclerAdapterNoDataBinding mAdapter;


    @Bind(R.id.recycler)
    private RecyclerView recyclerView;
    private List<UserViewModel> userList;
    private List<Flower> userRetroList;


    private CompositeDisposable mCompositeSubscription;

    @Inject
    APIService apiService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((FlowerApplication)getApplication())
                .getmApiComponent()
                .inject(MainActivity.this);

        userList = new ArrayList<>();
        getList();

        //used to configure views
        ButterKnife.bind(MainActivity.this);
        configView();



    }

    private void configView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new RecyclerAdapterNoDataBinding(this,getLayoutInflater());
        recyclerView.setAdapter(mAdapter);


    }

    public void loadJson()
    {

    }

    @Override
    public void onClick(int position, String name) {

        Toast.makeText(getApplicationContext(), "You clicked on " + name, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(List<Flower> flowers) {
        mAdapter.addFlowers(flowers);

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }


    public void getList(){

        unsubscribeAll();
       // subscribe();
        apiService.getUserList();


        UserViewModel l1=new UserViewModel();
        l1.name = "Nanecy";
        //l1.title = "Kapri";

//        l1.time.set("12:00");
//        l1.setText("Hi");
//        l1.setName("Nancy");

        UserViewModel l2=new UserViewModel();
        l2.name = "Adi";
       // l2.title = "Bhatia";
//        l2.time.set("1:00");
//        l2.setText("Hi bye");
//        l2.setName("Adi");

//   ListViewModel item=new ListViewModel("nancy","hi", l1);
        userList.add(l1);
        userList.add(l2);
        //return l;

    }

    @Override
    protected void onResume() {
        super.onResume();
        configureSubscription();
        subscribe(getUser(),MainActivity.this);


    }

    private Observable<Object> getUser() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribeAll();
    }


    private CompositeDisposable configureSubscription()
    {
        if(mCompositeSubscription == null || mCompositeSubscription.isDisposed())
        {
            mCompositeSubscription = new CompositeDisposable();
        }
        return mCompositeSubscription;
    }

    protected void unsubscribeAll(){
        if(mCompositeSubscription != null || !mCompositeSubscription.isDisposed())
        {
            mCompositeSubscription.dispose();
            mCompositeSubscription.clear();
        }

    }

    public<F> void subscribe(Observable<F> observable, DisposableObserver<F> observer) {

        DisposableObserver s = observable
                 .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribeWith(new DisposableObserver<F>()
                {
                    @Override
                    public void onNext(F f) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        configureSubscription().add(s);

    }
}
