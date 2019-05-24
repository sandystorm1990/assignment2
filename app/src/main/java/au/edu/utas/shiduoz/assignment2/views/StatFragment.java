package au.edu.utas.shiduoz.assignment2.views;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import au.edu.utas.shiduoz.assignment2.R;
import au.edu.utas.shiduoz.assignment2.data.Database;
import au.edu.utas.shiduoz.assignment2.data.EntryTable;
import au.edu.utas.shiduoz.assignment2.models.Entry;
import au.edu.utas.shiduoz.assignment2.utils.Helper;

/*
 * HelloCharts
 *
 * Copyright 2014 Leszek Wach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * github: https://github.com/lecho/hellocharts-android
 */
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

public class StatFragment extends Fragment {

    Button dayBtn,weekBtn,monthBtn,yearBtn;

    private LineChartView mChartView;

    private List<Line> lines;
    private Line line;
    private LineChartData lineChartData;
    private Axis axisX,axisY;
    Database dbConn;
    SQLiteDatabase db;
    ArrayList<Entry> mEntries;

    ArrayList<AxisValue> axisValuesX;
    ArrayList<AxisValue> axisValuesY;

    TextView statRange;

    public StatFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_stat, container, false);
        dayBtn = inflatedView.findViewById(R.id.dayBtn);
        weekBtn = inflatedView.findViewById(R.id.weekBtn);
        monthBtn = inflatedView.findViewById(R.id.monthBtn);
        yearBtn = inflatedView.findViewById(R.id.yearBtn);
        statRange = inflatedView.findViewById(R.id.statRange);

        btnSelected();
        mChartView = inflatedView.findViewById(R.id.statChart);
        dbConn = new Database(getActivity());
        db = dbConn.open();

        return inflatedView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void drawLine(String range)
    {
        String fromDate, toDate;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        toDate = sdf.format(date);
        fromDate = Helper.calFromDate(range);
        if (fromDate.length() == 0) {
            return;
        }
        statRange.setText("Range:"+fromDate+" -- "+toDate);
        Log.d("date:", fromDate);
        mEntries = EntryTable.selectByRange(db, fromDate, toDate);
        // define a line
        lines = new ArrayList<>();
        // define point data
        List<PointValue> points = new ArrayList<PointValue>();
        axisX = new Axis();
        axisY = new Axis();
        axisValuesX = new ArrayList<AxisValue>();
        axisValuesY = new ArrayList<AxisValue>();

        int count = 0;
        points.add(new PointValue(0, 0));
        setAxisValues();
        for (Entry entry: mEntries
             ) {
            points.add(new PointValue(++count, (float) entry.getmMoodLevel()));
            axisValuesX.add(new AxisValue(count).setValue(count).setLabel(entry.getmDate().substring(5,10)+":"+entry.getmMood()));

        }
        axisX.setValues(axisValuesX);

        line = new Line(points);
        // set line color
        line.setColor(Color.BLUE);
        // set line to straight, true is curved
        line.setCubic(false);
        line.setHasLabels(true);
        // set shape of data point
        line.setShape(ValueShape.CIRCLE);
        // set point color
        line.setPointColor(Color.GREEN);
        // add data to line
        lines.add(line);

        mChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        mChartView.setInteractive(true);
        lineChartData = new LineChartData(lines);

        lineChartData.setAxisXBottom(axisX);
        lineChartData.setAxisYLeft(axisY);

        axisX.setName("Mood");//axisX.setTextSize(12);axisY.setTextSize(12);
        axisX.setHasLines(true);
        axisY.setHasLines(true);
        axisX.setTextColor(Color.BLACK);
        axisY.setName("Mood Level");
        axisY.setTextColor(Color.BLACK);
        //setAxisValues();
        lineChartData.setLines(lines);
        mChartView.setLineChartData(lineChartData);
    }

    private void setAxisValues()
    {
        for (int i = 0; i <= 5; i++) {
            axisValuesY.add(new AxisValue(i).setValue(i).setLabel(i + ""));
        }
        axisY.setValues(axisValuesY);
    }

    private void btnSelected()
    {
        dayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelected();
                dayBtn.setBackgroundColor(getResources().getColor(R.color.statBg));
                drawLine("day");
            }
        });
        weekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelected();
                weekBtn.setBackgroundColor(getResources().getColor(R.color.statBg));
                drawLine("week");
            }
        });
        monthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelected();
                monthBtn.setBackgroundColor(getResources().getColor(R.color.statBg));
                drawLine("month");
            }
        });
        yearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelected();
                yearBtn.setBackgroundColor(getResources().getColor(R.color.statBg));
                drawLine("year");
            }
        });
    }

    private void clearSelected()
    {
        dayBtn.setBackgroundColor(Color.TRANSPARENT);
        weekBtn.setBackgroundColor(Color.TRANSPARENT);
        monthBtn.setBackgroundColor(Color.TRANSPARENT);
        yearBtn.setBackgroundColor(Color.TRANSPARENT);
    }
}
