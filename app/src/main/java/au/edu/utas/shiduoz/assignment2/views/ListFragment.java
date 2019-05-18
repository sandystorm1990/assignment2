package au.edu.utas.shiduoz.assignment2.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import au.edu.utas.shiduoz.assignment2.R;

public class ListFragment extends Fragment {

    private TextView textView;
    public ListFragment() {
        //textView = getView().findViewById()
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_list, container, false);
        textView = inflateView.findViewById(R.id.testText);
        textView.setText("This is the list fragment");
        return inflateView;

        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
