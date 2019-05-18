package au.edu.utas.shiduoz.assignment2.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.edu.utas.shiduoz.assignment2.R;

public class StatFragment extends Fragment {
    public StatFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_stat, container, false);

        return inflatedView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
