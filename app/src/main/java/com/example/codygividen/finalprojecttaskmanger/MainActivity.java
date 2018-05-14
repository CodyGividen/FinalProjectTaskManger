package com.example.codygividen.finalprojecttaskmanger;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements TaskAdapter.AdapterCallback, AddTaskFragment.ActivityCallback {
    @BindView(R.id.task_recycler_view)
    protected RecyclerView recyclerView;

    private TaskAdapter taskAdapter;
    private TaskDatabase taskDatabase;
    private AddTaskFragment addTaskFragment;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onStart() {
        super.onStart();
        taskAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        taskDatabase = ((TaskApplication) getApplicationContext()).getDatabase();
        setUpRecyclerView();
        }
    private void setUpRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        taskAdapter = new TaskAdapter(taskDatabase.taskDao().getTaskDefault(),this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.notifyDataSetChanged();
    }
    @OnClick(R.id.add_task_button)
    protected void addTaskButtonClicked(){
        addTaskFragment = AddTaskFragment.newInstance();
        addTaskFragment.attachParent(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, addTaskFragment).commit();
    }


    @Override
    public void addClicked() {
        getSupportFragmentManager().beginTransaction().remove(addTaskFragment).commit();
        taskAdapter.updateList(taskDatabase.taskDao().getTaskDefault());
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void rowClicked(TaskDefault taskDefault) {
        if (taskDefault.isCompleted()) {
            markAsCompleted(taskDefault);
        } else {
            markAsNotCompleted(taskDefault);
        }
    }

    private void markAsNotCompleted(final TaskDefault taskDefault) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mark task as not completed?").setMessage("Are you sure you would like to mark this task as not completed?")
                .setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        taskDefault.setCompleted(true);
                        //update database with updated task info
                        taskDatabase.taskDao().updateTaskDefault(taskDefault);
                        //let our adapter know the info in the database has changed to update our view accordingly
                        taskAdapter.updateList(taskDatabase.taskDao().getTaskDefault());
                        Toast.makeText(MainActivity.this, "Task marked not completed!", Toast.LENGTH_LONG).show();
                    }
                }).setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    @Override
    public void rowLongClicked(final TaskDefault taskDefault) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Task?").setMessage("Are you sure you would like to delete this task?").setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                taskDatabase.taskDao().deleteTaskDefault(taskDefault);

                taskAdapter.updateList(taskDatabase.taskDao().getTaskDefault());

                Toast.makeText(MainActivity.this, "Task has been deleted!", Toast.LENGTH_LONG).show();
            }
        }).setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    private void markAsCompleted(final TaskDefault taskDefault) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mark task completed?").setMessage("Are you sure you would like to mark this task as completed?")
                .setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        taskDefault.setCompleted(false);
                        //update database with updated task info
                        taskDatabase.taskDao().updateTaskDefault(taskDefault);
                        //let our adapter know the info in the database has changed to update our view accordingly
                        taskAdapter.updateList(taskDatabase.taskDao().getTaskDefault());
                        Toast.makeText(MainActivity.this, "Task marked completed!", Toast.LENGTH_LONG).show();
                    }
                }).setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }
}
