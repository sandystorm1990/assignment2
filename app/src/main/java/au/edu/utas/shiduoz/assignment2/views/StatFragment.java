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
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.ChartData;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;

public class StatFragment extends Fragment {

    Button dayBtn,weekBtn,monthBtn,yearBtn;

    private LineChartView mChartView;

    private List<Line> lines;
    private Line line;
    private LineChartData lineChartData;
    private List<PointValue> points;
    private Axis axisX = new Axis(),axisY = new Axis();
    Database dbConn;
    SQLiteDatabase db;
    ArrayList<Entry> mEntries;

    ArrayList<AxisValue> axisValuesX = new ArrayList<AxisValue>();
    ArrayList<AxisValue> axisValuesY = new ArrayList<AxisValue>();

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

        //In most cased you can call data model methods in builder-pattern-like manner.



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
        points = new ArrayList<PointValue>();

        int count = 0;
        points.add(new PointValue(0, 0));
        setAxisValues();
        for (Entry entry: mEntries
             ) {
            points.add(new PointValue(++count, (float) entry.getmMoodLevel()));
            axisValuesX.add(new AxisValue(count).setValue(count).setLabel(entry.getmMood()));
            //axisValuesY.add(new AxisValue(count).setValue(count).setLabel(""+entry.getmMoodLevel()));

        }
        axisX.setValues(axisValuesX);
        //axisY.setValues(axisValuesY);
//        points.add(new PointValue(3, 0));
//        points.add(new PointValue(2, 2));
//        points.add(new PointValue(1, 3));
//        points.add(new PointValue(0, 4));
//        points.add(new PointValue(4, 2));
//        points.add(new PointValue(5, 5));
//        points.add(new PointValue(6, 4));


        line = new Line(points);
        // set line color
        line.setColor(Color.BLUE);
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

        axisX.setName("Mood");
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
//        ArrayList<AxisValue> axisValuesX = new ArrayList<AxisValue>();//定义X轴刻度值的数据集合
//        for (int i = 0; i < 15; i++) {
//            axisValuesX.add(new AxisValue(i).setValue(i).setLabel(i+""));
//        }
//        axisX.setValues(axisValuesX);//为X轴显示的刻度值设置数据集合
        //axisValuesY = new ArrayList<AxisValue>();//定义Y轴刻度值的数据集合
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
