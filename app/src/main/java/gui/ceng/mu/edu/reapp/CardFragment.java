package gui.ceng.mu.edu.reapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class CardFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_LIST_NAME = "list";
    // TODO: Customize parameters
    private List<Material> mlist;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CardFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CardFragment newInstance( List<Material> list) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LIST_NAME,(Serializable)list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mlist = (List<Material>) getArguments().getSerializable(ARG_LIST_NAME);
        }
    }
    public List<Material> getItemList() {
        return mlist;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mlist != null){
                MyCardRecyclerViewAdapter adapter = new MyCardRecyclerViewAdapter(mlist);
                recyclerView.setAdapter(adapter);
            }
        }
        return view;
    }
}