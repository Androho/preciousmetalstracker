package ua.ho.andro.preciousmetalstracker.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import ua.ho.andro.preciousmetalstracker.*;


public class GoldDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_detail);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_trend_down);

        Intent intent = getIntent();

        String gp=intent.getStringExtra("gp");

        TextView textView = (TextView)findViewById(R.id.testText);
       // TextView gP =(TextView)findViewById(R.id.gold_desc);
        textView.setText("eeeeeeeeeeeeeeeeeeeeee");
       // gP.setText("Gold prise: $ " +gp);

        String gf = gp.substring( gp.lastIndexOf(",")+1);
        double f = new Double(Double.parseDouble(gf)+1000);
        int d= ( (Double) Math.ceil( f) ).intValue();
        int t=d;


        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1232),
                new DataPoint(1, 1231),
                new DataPoint(2, 1233),
                new DataPoint(3, 1230),
                new DataPoint(4,d )
        });
        graph.addSeries(series);
    }



}
