package com.example.;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private static final String tag = "EmployeeAdapter";
    private List<Employee>employeeList;
    private Context context;
    private LayoutInflater layoutInflater;

    public EmployeeAdapter(Context contexta, List<Employee>list) {
        context = contexta;
        employeeList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        View view = layoutInflater.inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);

    }
    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        // Get song in mSong via position
        Employee employee = employeeList.get(position);

        //bind data to viewholder
        if (employee.getFullName()!=null) {
            holder.tvFullName.setText(employee.getFullName());
        }
        else holder.tvFullName.setText("");
        // If this is a manager -> show icon manager. Otherwise, show Staff in tvPosition
        if (employee.isManager())
        {
            holder.ivManager.setVisibility(View.VISIBLE);
            holder.tvPosition.setVisibility(View.GONE);
        }
        else
        {
            holder.ivManager.setVisibility(View.GONE);
            holder.tvPosition.setVisibility(View.VISIBLE);
            holder.tvPosition.setText("Staff");
        }
        // Show different color backgrounds for 2 continuous employees
        if (position%2==0)
        {
            holder.llParent.setBackgroundResource(R.color.white);
        }
        else
        {
            holder.llParent.setBackgroundResource(R.color.light_blue);
        }
    }

    @Override
    public int getItemCount() {
        if (employeeList != null) return employeeList.size();
        return 0;
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFullName;
        private TextView tvPosition;
        private ImageView ivManager;
        private LinearLayout llParent;

        public EmployeeViewHolder(View itemView) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.item_employee_tv_fullname);
            tvPosition = itemView.findViewById(R.id.item_employee_tv_position);
            ivManager = itemView.findViewById(R.id.item_employee_iv_manager);
            llParent = itemView.findViewById(R.id.item_employee_ll_parent);
        }
    }
}
