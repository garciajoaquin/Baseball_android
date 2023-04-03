package com.garciaa.baseball.ui.practice;

import android.app.Dialog;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.garciaa.baseball.R;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PracticeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    static String dataTxt = "";
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_practice, container, false);

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       dataTxt = getTxt();
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        return root;
    }
    private String getTxt() {
        String text = "";
        try {
            AssetManager assetManager = getContext().getAssets();
            InputStream inputStream = assetManager.open("drills.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            text = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


        private List<String> dataList;

        public MyAdapter() {

            dataList = new ArrayList<>();



            String[] separated = dataTxt.split("<");
            for (int i=0; i<=separated.length - 1; i++) {

                //Log.v("Text",separated[3]);
                dataList.add(separated[i]);

            }
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text_view);
                    textView.setOnClickListener(v -> {
                        Dialog dialog = new Dialog(requireActivity());
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.setContentView(R.layout.desc_modal);
                        Window window = dialog.getWindow();
                        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, Constraints.LayoutParams.MATCH_PARENT);
                        dialog.show();

                        TextView title = dialog.findViewById(R.id.title_text);
                        TextView desc = dialog.findViewById(R.id.desc_text);
                        title.setText(textView.getText());

                        String text = textView.getTag().toString();
                        String[] wordsToHighlight = {"What you need (set up)","What you need", "How the drills works","How this drill works","Note:", "Result:", "Results:"};

                        SpannableStringBuilder builder = new SpannableStringBuilder(text);

                        for (String word : wordsToHighlight) {
                            int start = 0;
                            int end = 0;

                            while ((start = text.indexOf(word, end)) >= 0) {
                                end = start + word.length();

                                BackgroundColorSpan highlightSpan = new BackgroundColorSpan(Color.YELLOW);

                                builder.setSpan(highlightSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                builder.setSpan(new RelativeSizeSpan(1f), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                            }
                        }

                        desc.setText(builder);
                    });
                }

        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.data_model, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            String data = dataList.get(position);
            String[] drills = data.split(">");
            holder.textView.setText(drills[0]);
            holder.textView.setTag(drills[1]);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}
