package dz.cdta.smartbin;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dz.cdta.smartbin.data.AppDatabase;
import dz.cdta.smartbin.data.TaskDao;
import dz.cdta.smartbin.model.Task;

/**
 * Provides UI for the view with Cards.
 */
public class CardContentFragment extends Fragment {

    private ContentAdapter adapter;
    private TaskViewModel taskViewModel;
    private Context context;
    private int index;

    public static CardContentFragment newInstance(int index) {
        CardContentFragment fragment = new CardContentFragment();
        Bundle args = new Bundle();
        args.putInt("index",index);
        Log.d("Fragment index:" , ""+index);
        fragment.setIndex(index);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        return recyclerView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getTasksList(index).observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.setTasksList(AppDatabase.getAppDatabase(context).taskDao().getUpcomingTasks().getValue());
                Log.d("Fragment : set Tasks",""+ tasks.size());
            }
        });
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public ImageView picture;
        public TextView name;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card, parent, false));
            //picture = (ImageView) itemView.findViewById(R.id.card_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, TaskDetailActivity.class);
                    context.startActivity(intent);
                }
            });
            name = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
        }
    }
    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.

        private List<Task> tasksList = new ArrayList<>();

        public ContentAdapter(Context context) {
        }

        public void setTasksList(List<Task> tasksList) {
            this.tasksList = tasksList;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //holder.picture.setImageDrawable(mPlacePictures[position % mPlacePictures.length]);
            holder.name.setText("Task id : " + tasksList.get(position).getIdTask() );
            holder.description.setText(tasksList.get(position).getDescriptionTask());
        }

        @Override
        public int getItemCount() {
            Log.d("Fragment", Integer.toString(tasksList==null ? 0 : tasksList.size()));
            return tasksList==null ? 0 : tasksList.size();
        }
    }

}