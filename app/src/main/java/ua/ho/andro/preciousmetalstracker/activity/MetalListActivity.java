package ua.ho.andro.preciousmetalstracker.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import ua.ho.andro.preciousmetalstracker.Currencylayer;
import ua.ho.andro.preciousmetalstracker.DataMetalList;
import ua.ho.andro.preciousmetalstracker.adapter.MLAdapter;
import ua.ho.andro.preciousmetalstracker.R;


public class MetalListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CardView mCardView;
    private DataMetalList[] arr;


    private String test;
    private String goldResult;
    private String goldTrendResult;
    private String goldTrend;


    private String goldPrise;
    private String silverResult;
    private String silverTrendResult;
    private String silverTrend;
    private String silverPrise;
    private String ptResult;
    private String ptTrendResult;
    private String ptTrend;
    private String ptPrise;
    private String pdResult;
    private String pdTrendResult;
    private String pdTrend;
    private String pdPrise;
    private ProgressDialog dialog;
    private int imageTrendGold = R.drawable.ic_action_trend_down,
            imageTrendSilver = R.drawable.ic_action_trend_down,
            imageTrendPt = R.drawable.ic_action_trend_down,
            imageTrendPd = R.drawable.ic_action_trend_down;
    public SharedPreferences sPref;
    private int id0;
    private InterstitialAd mInterstitialAd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metal_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Metal List");
       // toolbar.setNavigationIcon(R.style.Material_Drawable_NavigationDrawerIcon);
        toolbar.setNavigationIcon(R.drawable.ic_action_trend_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mCardView = (CardView) findViewById(R.id.cv);

        mRecyclerView = (RecyclerView) findViewById(R.id.metal_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        new RequestTask().execute();
        fetchData();

        mAdapter = new MLAdapter(this, arr);
        mRecyclerView.setAdapter(mAdapter);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                beginPlayingGame();
            }
        });

        requestNewInterstitial();

        Intent intent = getIntent();
        goldResult = intent.getStringExtra("gpjs");


    }

    private void beginPlayingGame() {
        Intent intent = new Intent(MetalListActivity.this, GoldDetailActivity.class);
        intent.putExtra("gp",goldResult);
        startActivity(intent);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void fetchData() {
        arr = new DataMetalList[]{
                new DataMetalList(R.drawable.ic_gold_coin, "Au", imageTrendGold, goldResult, goldTrendResult),
                new DataMetalList(R.drawable.ic_silver_coin, "Ag", imageTrendSilver, silverResult, silverTrendResult),
                new DataMetalList(R.drawable.ic_pl_coin, "Pt", imageTrendPt, ptResult, ptTrendResult),
                new DataMetalList(R.drawable.ic_pd_coin, "Pd", imageTrendPd, pdResult, pdTrendResult)
        };
    }

    class RequestTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.moneymetals.com/").get();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (doc != null) {
                goldPrise = doc.select("#sp-price-gold").toString();
                goldTrend = doc.select("#sp-icon-gold").toString();
                silverPrise = doc.select("#sp-price-silver").toString();
                silverTrend = doc.select("#sp-icon-silver").toString();
                ptPrise = doc.select("#sp-price-platinum").toString();
                ptTrend = doc.select("#sp-icon-platinum").toString();
                pdPrise = doc.select("#sp-price-palladium").toString();
                pdTrend = doc.select("#sp-icon-palladium").toString();
                int q = 4;
            } else
                test = "Ошибка";
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            dialog.dismiss();
            super.onPostExecute(result);
            // stroka
            goldResult = goldPrise.substring(goldPrise.indexOf("$") + 1, goldPrise.lastIndexOf("<"));
            goldTrendResult = goldTrend.substring(goldTrend.indexOf(">") + 1, goldTrend.lastIndexOf("<"));
            double goldTrend = Double.parseDouble(goldTrendResult);
            if (goldTrend >= 0) {
                imageTrendGold = R.drawable.ic_action_trend_up;
            }
            silverResult = silverPrise.substring(silverPrise.indexOf("$") + 1, silverPrise.lastIndexOf("<"));
            silverTrendResult = silverTrend.substring(silverTrend.indexOf(">") + 1, silverTrend.lastIndexOf("<"));
            double silverTrend = Double.parseDouble(silverTrendResult);
            if (silverTrend >= 0) {
                imageTrendSilver = R.drawable.ic_action_trend_up;
            }
            ptResult = "$" +ptPrise.substring(ptPrise.indexOf("$") + 1, ptPrise.lastIndexOf("<"))+"\n per 1 Oz";
            ptTrendResult = ptTrend.substring(ptTrend.indexOf(">") + 1, ptTrend.lastIndexOf("<"));
            double ptTrend = Double.parseDouble(ptTrendResult);
            if (ptTrend >= 0) {
                imageTrendPt = R.drawable.ic_action_trend_up;
            }
            pdResult = pdPrise.substring(pdPrise.indexOf("$") + 1, pdPrise.lastIndexOf("<"));
            pdTrendResult = pdTrend.substring(pdTrend.indexOf(">") + 1, pdTrend.lastIndexOf("<"));
            double pdTrend = Double.parseDouble(pdTrendResult);
            if (pdTrend >= 0) {
                imageTrendPd = R.drawable.ic_action_trend_up;
            }
            fetchData();
            saveData();
            mAdapter = new MLAdapter(MetalListActivity.this, arr);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(mAdapter);
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MetalListActivity.this);
            dialog.setMessage("Загружаюсь...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(true);
            dialog.show();
            super.onPreExecute();
        }
    }

    ////////
    void saveData() {
        sPref = getPreferences(MODE_PRIVATE);
        sPref.edit().remove("goldRes").commit();
        Set<String> goldData = new HashSet<>();
        goldData.add(goldResult);
        goldData.add(goldTrendResult);
        goldData.add(Integer.toString(imageTrendGold));
        SharedPreferences.Editor ed = sPref.edit();
        ed.putStringSet("goldRes", goldData);
        ed.commit();
    }

    void loadData() {
        sPref = getPreferences(MODE_PRIVATE);
        Set<String> getGoldData = new HashSet<>();
        Set<String> savedText = sPref.getStringSet("goldRes", getGoldData);
        String[] a = savedText.toArray(new String[0]);
        if (a.length == 3) {
            goldResult = a[0];
            goldTrendResult = a[1];
            String ggg = a[2];
            imageTrendGold = new Integer(ggg);
            sPref.edit().remove("goldRes").commit();
        } else return;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (!isOnline(this)) {
//            loadData();
//        }
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        saveData();
//    }

    public void clickCard(View v) {
//        int itemPosition =  mRecyclerView.getChildPosition(v);
//        int i = mCardView.getId();
        switch (v.getId()) {
            case R.id.cv:
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    beginPlayingGame();
                }

                break;
            case 0:
                Intent intent1 = new Intent(MetalListActivity.this, Currencylayer.class);
                startActivity(intent1);
                break;
        }
    }
//    public void Tests (){
//        mAdapter = new MLAdapter(MetalListActivity.this, arr);
//        mAdapter.notifyDataSetChanged();
//        mRecyclerView.setAdapter(mAdapter);
//    }
}