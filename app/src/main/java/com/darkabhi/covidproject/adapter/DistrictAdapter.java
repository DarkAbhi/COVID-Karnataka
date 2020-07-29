package com.darkabhi.covidproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darkabhi.covidproject.DistrictItem;
import com.darkabhi.covidproject.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.DistrictViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<DistrictItem> mDistrictList;
    private List<DistrictItem> mDistrictListFull;

    public DistrictAdapter(Context context, ArrayList<DistrictItem> districtList) {
        mContext = context;
        mDistrictList = districtList;
        mDistrictListFull = new ArrayList<>(districtList);
    }

    @NonNull
    @Override
    public DistrictViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.district_card, parent, false);
        return new DistrictViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictViewHolder holder, int position) {
        DistrictItem currentItem = mDistrictList.get(position);

        String location = currentItem.getLocation();
        String confirmed = currentItem.getConfirmed();
        String active = currentItem.getActive();
        String recovered = currentItem.getRecovered();
        String deceased = currentItem.getDeceased();

        holder.mLocationTextView.setText(location);
        holder.mConfirmedTextView.setText("Confirmed: " + confirmed);
        holder.mActiveTextView.setText("Active: " + active);
        holder.mDeceasedTextView.setText("Deceased: " + deceased);
        holder.mRecoveredTextView.setText("Recovered: " + recovered);

    }

    @Override
    public int getItemCount() {
        return mDistrictList.size();
    }

    @Override
    public Filter getFilter() {
        return districtFilter;
    }

    private Filter districtFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DistrictItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mDistrictListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DistrictItem item : mDistrictListFull) {
                    if (item.getLocation().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDistrictList.clear();
            mDistrictList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class DistrictViewHolder extends RecyclerView.ViewHolder {

        public MaterialTextView mLocationTextView, mRecoveredTextView, mDeceasedTextView, mConfirmedTextView, mActiveTextView;

        public DistrictViewHolder(@NonNull View itemView) {
            super(itemView);

            mLocationTextView = itemView.findViewById(R.id.districtLocation);
            mRecoveredTextView = itemView.findViewById(R.id.districtRecovered);
            mDeceasedTextView = itemView.findViewById(R.id.districtDeceased);
            mConfirmedTextView = itemView.findViewById(R.id.districtConfirmed);
            mActiveTextView = itemView.findViewById(R.id.districtActive);
        }
    }
}
