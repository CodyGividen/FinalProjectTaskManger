package com.example.codygividen.finalprojecttaskmanger;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.codygividen.finalprojecttaskmanger.MainActivity.TASK_EDIT;

public class EditTaskFragment extends Fragment {
    private TaskDatabase taskDatabase;
    @BindView(R.id.item_title_text_view_edit_1)
    protected TextInputEditText itemTitleEdit;
    @BindView(R.id.item_details_text_view_edit_3)
    protected TextInputEditText itemDetailsEdit;
    @BindView(R.id.item_due_date_text_view_edit_4)
    protected TextInputEditText itemDueDateEdit;
    @BindView(R.id.item_creation_date_text_view_edit_2)
    protected TextInputEditText itemCreationDateEdit;

    private TaskDefault taskDefault;
    private String title;
    private String details;
    private String dueDate;
    private String createdDate;

    private ActivityCallback2 activityCallback2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_task, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
        public static EditTaskFragment newInstance(){

            Bundle args = new Bundle();

            EditTaskFragment fragment = new EditTaskFragment();
            fragment.setArguments(args);
            return fragment;
        }

    @Override
    public void onStart() {
        super.onStart();
        taskDatabase = ((TaskApplication) getActivity().getApplicationContext()).getDatabase();
        taskDefault = getArguments().getParcelable(TASK_EDIT);
    }

    @OnClick(R.id.add_edit_task_fab)
    protected void addButtonClicked(){
        itemTitleEdit.setHint(taskDefault.getTitle().toString());
        itemDetailsEdit.setHint(taskDefault.getDetailsOfTask().toString());
        itemDueDateEdit.setHint(taskDefault.getTaskDueDate().toString());
        itemCreationDateEdit.setHint(taskDefault.getTaskCreatedDate().toString());
        if(itemTitleEdit.getText().toString().isEmpty()) {
            itemTitleEdit.setText(taskDefault.getTitle());
        }else{
            String title = itemTitleEdit.getText().toString();
        }if(itemDetailsEdit.getText().toString().isEmpty()){
            itemDetailsEdit.setText(taskDefault.getDetailsOfTask());
        }else{
            String details = itemDetailsEdit.getText().toString();
        }if(itemDueDateEdit.getText().toString().isEmpty()) {
            itemDueDateEdit.setText(taskDefault.getTaskDueDate());
        }else{
            String dueDate = itemDueDateEdit.getText().toString();
        }if(itemCreationDateEdit.getText().toString().isEmpty()){
            itemCreationDateEdit.setText(taskDefault.getTaskCreatedDate());
        }else{
            String createdDate = itemCreationDateEdit.getText().toString();
        }
            addTaskDatabase(title, details, dueDate, createdDate);



        }


    private void addTaskDatabase(final String title, final String details, final String dueDate, final String createdDate){
        TaskDefault taskDefault = new TaskDefault(title, details, dueDate, createdDate);
        taskDatabase.taskDao().addTaskDefault(taskDefault);
        activityCallback2.addClicked();
        Toast.makeText(getActivity(), "Task Added!!!",Toast.LENGTH_LONG).show();

    }
    public void attachParentEdit(ActivityCallback2 activityCallback2){
        this.activityCallback2 = activityCallback2;

    } public interface ActivityCallback2{
        void addClicked();
    }



}
