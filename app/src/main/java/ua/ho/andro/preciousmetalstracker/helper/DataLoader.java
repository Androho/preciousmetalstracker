package ua.ho.andro.preciousmetalstracker.helper;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by piter on 21.06.16.
 */
public class DataLoader extends AsyncTask<Void, Void, Void> {

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
//        goldPrise = doc.select("#sp-price-gold").toString();
//        goldTrend = doc.select("#sp-icon-gold").toString();
//        silverPrise = doc.select("#sp-price-silver").toString();
//        silverTrend = doc.select("#sp-icon-silver").toString();
//        ptPrise = doc.select("#sp-price-platinum").toString();
//        ptTrend = doc.select("#sp-icon-platinum").toString();
//        pdPrise = doc.select("#sp-price-palladium").toString();
//        pdTrend = doc.select("#sp-icon-palladium").toString();
        int q = 4;
        }
    return doc;}

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