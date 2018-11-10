package com.anaadar.akaltakhatsahibpro.activities.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.anaadar.akaltakhatsahibpro.R;
import com.anaadar.akaltakhatsahibpro.activities.news.Model.DataModel;
import com.anaadar.akaltakhatsahibpro.activities.news.RecycleView.CustomAdapter;
import com.anaadar.akaltakhatsahibpro.constants.Constant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsActivity extends AppCompatActivity {

    /*Recycle*/
    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    private DatabaseReference mDatabase, mFDatabase;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        data = new ArrayList<>();
        if (Constant.haveNetworkConnection(this)) {
            firebaseInit();
            getValue();
            /*recycleView*/
            recyclerView = findViewById(R.id.my_recycler_view);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(NewsActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter = new CustomAdapter(data);
            recyclerView.setAdapter(adapter);
        } else {
            Constant.checkNetworkConnection(this);
        }
    }

    void firebaseInit() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("news");
    }

    void getValue() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataModel dataModel = new DataModel();
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> mmap = (HashMap<String, Object>) dataSnapshot.getValue();
                    dataModel = (DataModel) mmap.get("news");
                    Log.e("size", String.valueOf(dataModel.getUrl()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Firebase Failed", "loadPost:onCancelled", databaseError.toException());

            }
        });
    }

    void display(DataModel post) {
        //  BaseModel post = dataSnapshot.getValue(BaseModel.class);
        Log.d("Data", post.getTitle());
//        Log.d("Data", post.getImage());
        Log.d("Data", post.getPostedBy());
    }
}
