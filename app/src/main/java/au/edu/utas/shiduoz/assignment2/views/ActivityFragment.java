package au.edu.utas.shiduoz.assignment2.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import au.edu.utas.shiduoz.assignment2.R;

public class ActivityFragment extends Fragment {
    TextView aReading, aDinner, aDriving, aBathing, aDating, aShopping, aSleeping, aDrinking,
            aGaming,aMeeting,aMovie,aMusic, aTea, aWorking, aTv;

    public String activity = "";

    public ActivityFragment()
    {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_activity, container, false);

        aReading = inflateView.findViewById(R.id.aReading);
        aDinner = inflateView.findViewById(R.id.aMeal);
        aDriving = inflateView.findViewById(R.id.aDriving);
        aBathing = inflateView.findViewById(R.id.aBathing);
        aDating = inflateView.findViewById(R.id.aDating);

        aShopping = inflateView.findViewById(R.id.aShopping);
        aSleeping = inflateView.findViewById(R.id.aSleeping);
        aDrinking = inflateView.findViewById(R.id.aDrinking);
        aGaming = inflateView.findViewById(R.id.aGaming);
        aMeeting = inflateView.findViewById(R.id.aMeeting);

        aMovie = inflateView.findViewById(R.id.aMovie);
        aMusic = inflateView.findViewById(R.id.aMusic);
        aTea = inflateView.findViewById(R.id.aTea);
        aWorking = inflateView.findViewById(R.id.aWorking);
        aTv = inflateView.findViewById(R.id.aTv);

        selectItem();

        return inflateView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void selectItem() {
        aReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "reading";
                aReading.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "dinner";
                aDinner.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aDriving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "driving";
                aDriving.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aBathing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "bathing";
                aBathing.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aDating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "dating";
                aDating.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "shopping";
                aShopping.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aDrinking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "drinking";
                aDrinking.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aGaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "gaming";
                aGaming.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "meeting";
                aMeeting.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "movie";
                aMovie.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "music";
                aMusic.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "tea";
                aTea.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aWorking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "working";
                aWorking.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
        aTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelection();
                activity = "tv";
                aTv.setBackgroundColor(getResources().getColor(R.color.activityBg));
            }
        });
    }

    private void clearSelection() {
        //aReading.setBackgroundColor(getResources().getColor(R.color.activityBg));
        aReading.setBackgroundColor(Color.TRANSPARENT);
        aDinner.setBackgroundColor(Color.TRANSPARENT);
        aDriving.setBackgroundColor(Color.TRANSPARENT);
        aBathing.setBackgroundColor(Color.TRANSPARENT);
        aDating.setBackgroundColor(Color.TRANSPARENT);
        aShopping.setBackgroundColor(Color.TRANSPARENT);
        aSleeping.setBackgroundColor(Color.TRANSPARENT);
        aDrinking.setBackgroundColor(Color.TRANSPARENT);
        aGaming.setBackgroundColor(Color.TRANSPARENT);
        aMeeting.setBackgroundColor(Color.TRANSPARENT);
        aMovie.setBackgroundColor(Color.TRANSPARENT);
        aMusic.setBackgroundColor(Color.TRANSPARENT);
        aWorking.setBackgroundColor(Color.TRANSPARENT);
        aTv.setBackgroundColor(Color.TRANSPARENT);
    }
}
