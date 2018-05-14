package com.example.codygividen.finalprojecttaskmanger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskFragment extends Fragment {
    private ActivityCallback activityCallback;
    private TaskDatabase taskDatabase;


    @BindView(R.id.add_task_title_edit_text)
    protected EditText taskTitle;
    @BindView(R.id.task_details_edit_text)
    protected EditText taskDetails;
    @BindView(R.id.due_date_edittext)
    protected EditText taskDueDate;
    @BindView(R.id.add_creation_edit_text)
    protected EditText taskCreatedDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    public static AddTaskFragment newInstance() {

        Bundle args = new Bundle();
        AddTaskFragment fragment = new AddTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        taskDatabase = ((TaskApplication) getActivity().getApplicationContext()).getDatabase();
    }

    @OnClick(R.id.add_task_fab)
    protected void addButtonClicked(){
        if(taskTitle.getText().toString().isEmpty() || taskDetails.getText().toString().isEmpty() || taskDueDate.getText().toString().isEmpty() || taskCreatedDate.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Try Again, please fill in all options!!",Toast.LENGTH_LONG).show();
        }else{
            String title = taskTitle.getText().toString();
            String details = taskDetails.getText().toString();
            String dueDate = taskDueDate.getText().toString();
            String createdDate = taskCreatedDate.getText().toString();
            addTaskDatabase(title, details, dueDate, createdDate);



        }
    }


    private void addTaskDatabase(final String title, final String details, final String dueDate, final String createdDate){
        TaskDefault taskDefault = new TaskDefault(title, details, dueDate, createdDate);
        taskDatabase.taskDao().addTaskDefault(taskDefault);
        activityCallback.addClicked();
        Toast.makeText(getActivity(), "Task Added!!!",Toast.LENGTH_LONG).show();

    }

    public void attachParent(ActivityCallback activityCallback){
        this.activityCallback = activityCallback;

    }
    public interface ActivityCallback{
        void addClicked();

    }
}
