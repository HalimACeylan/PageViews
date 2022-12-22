package gui.ceng.mu.edu.reapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link things_Info#newInstance} factory method to
 * create an instance of this fragment.
 */
public class things_Info extends Fragment {

    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "img";
    private static final String ARG_PARAM3 = "bodyText";

    private String mParam1;
    private int mParam2;
    private String mParam3;

    public things_Info() {
        // Required empty public constructor
    }

    public static things_Info newInstance(String param1, int param2, String param3) {
        things_Info fragment = new things_Info();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_things_info, container, false);
        TextView textThead = v.findViewById(R.id.txtTHead);
        ImageView imgT = v.findViewById(R.id.imgT);
        TextView textTBody = v.findViewById(R.id.txtTBodyText);
        textThead.setText(mParam1);
        imgT.setImageResource(mParam2);
        textTBody.setText(mParam3);
        return v;
    }
}