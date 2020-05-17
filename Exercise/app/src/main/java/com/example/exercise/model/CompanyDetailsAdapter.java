package com.example.exercise.model;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyDetailsAdapter extends RecyclerView.Adapter<CompanyDetailsAdapter.MyViewHolder> {


    private List<Company> companyList;

    public CompanyDetailsAdapter(List<Company> companyList) {
        this.companyList = companyList;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_watch_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Company company = companyList.get(position);
        holder.companyName.setText(company.getCompanyName());
        holder.askPrice.setText(company.getAskPrice() + "");
        holder.bidPrice.setText(company.getBidPrice() + "");
        holder.lastPrice.setText(company.getLastPrice() + "");
        holder.highPrice.setText(company.getHighPrice() + "");
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView companyName;
        @BindView(R.id.askprice)
        TextView askPrice;
        @BindView(R.id.lastprice)
        TextView lastPrice;
        @BindView(R.id.bidprice)
        TextView bidPrice;
        @BindView(R.id.highprice)
        TextView highPrice;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
