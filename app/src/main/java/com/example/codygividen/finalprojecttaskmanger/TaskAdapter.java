package com.example.codygividen.finalprojecttaskmanger;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<TaskDefault> taskDefaultList;
    private AdapterCallback adapterCallback;

    public TaskAdapter(List<TaskDefault> taskDefaultList, AdapterCallback adapterCallback) {
        this.taskDefaultList = taskDefaultList;
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_task, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.bind(taskDefaultList.get(position));
        holder.itemView.setOnClickListener(holder.onClick(taskDefaultList.get(position)));
        holder.itemView.setOnLongClickListener(holder.onLongClick(taskDefaultList.get(position)));

    }

    @Override
    public int getItemCount() {
        return taskDefaultList.size();
    }

    public void updateList(List<TaskDefault> list) {
        taskDefaultList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_row_layout)
        protected ConstraintLayout rowLayout;
        @BindView(R.id.item_title)
        protected TextView itemTaskTitle;
        @BindView(R.id.item_task_details_textview)
        protected TextView itemTaskDetails;
        @BindView(R.id.item_due_date)
        protected TextView itemTaskDueDate;
        @BindView(R.id.item_date_created)
        protected TextView itemCreationDate;
        @BindView(R.id.item_completed_date)
        protected TextView itemCompletedDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bind(TaskDefault taskDefault) {
            itemTaskTitle.setText(taskDefault.getTitle());
            itemTaskDetails.setText(adapterCallback.getContext().getString(R.string.task_details, taskDefault.getDetailsOfTask()));
            itemTaskDueDate.setText(adapterCallback.getContext().getString(R.string.task_due_date, taskDefault.getTaskDueDate()));
            itemCreationDate.setText(adapterCallback.getContext().getString(R.string.task_creation__date, taskDefault.getTaskCreatedDate()));
            itemCompletedDate.setText(adapterCallback.getContext().getString(R.string.task_completed_date, taskDefault.getTaskCompletedDate()));


            if (taskDefault.isCompleted()) {
                //make the due date visible
                itemCompletedDate.setVisibility(View.VISIBLE);
                //show the day the game was checked out on
                taskDefault.setTaskCompletedDate(new Date());
                //change background to red
                rowLayout.setBackgroundResource(R.color.green);
                //calculate check back in date
                int numberOfDays = 0;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(taskDefault.getTaskCompletedDate());
                calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
                Date date = calendar.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                itemCompletedDate.setText(adapterCallback.getContext().getString(R.string.task_completed_date, formatter.format(date)));
            } else {
                itemCompletedDate.setVisibility(View.INVISIBLE);
                rowLayout.setBackgroundResource(R.color.red);
            }
        }

        public View.OnClickListener onClick(final TaskDefault taskDefault) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterCallback.rowClicked(taskDefault);
                }
            };
        }

        //Delete video game
        public View.OnLongClickListener onLongClick(final TaskDefault taskDefault) {
            return new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    adapterCallback.rowLongClicked(taskDefault);
                    return true;
                }
            };
        }
    }

    public interface AdapterCallback {
        Context getContext();

        void rowClicked(TaskDefault taskDefault);

        void rowLongClicked(TaskDefault taskDefault);
    }
}
