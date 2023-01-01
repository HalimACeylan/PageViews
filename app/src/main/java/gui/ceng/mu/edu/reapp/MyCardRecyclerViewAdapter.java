package gui.ceng.mu.edu.reapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gui.ceng.mu.edu.reapp.databinding.FragmentItemBinding;
import gui.ceng.mu.edu.reapp.placeholder.PlaceholderContent.PlaceholderItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCardRecyclerViewAdapter extends RecyclerView.Adapter<MyCardRecyclerViewAdapter.ViewHolder> {

    private final List<Material> mValues;

    public MyCardRecyclerViewAdapter(List<Material> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Material currentMaterial = mValues.get(position);
        holder.imgMaterial.setImageBitmap(mValues.get(position).getImageInBitmap());
        holder.txtMaterialName.setText(mValues.get(position).getName());
        holder.btnTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mValues.remove(currentMaterial);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imgMaterial;
        public final TextView txtMaterialName;
        public ImageButton btnTrash;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            imgMaterial = binding.imgMaterial;
            txtMaterialName = binding.txtMaterialName;
            btnTrash = binding.btnTrash;
        }

    }
}