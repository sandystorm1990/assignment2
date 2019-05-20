package au.edu.utas.shiduoz.assignment2.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import au.edu.utas.shiduoz.assignment2.R;

public class MediaFragment extends Fragment {

    Button uploadBtn;
    public MediaFragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_media, container, false);

        uploadBtn = inflateView.findViewById(R.id.uploadBtn);

        return inflateView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
