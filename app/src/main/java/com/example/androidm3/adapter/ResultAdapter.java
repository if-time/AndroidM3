package com.example.androidm3.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidm3.Operate.InternetDownload.InternetImageActivity;
import com.example.androidm3.Operate.SetPhoto;
import com.example.androidm3.R;
import com.example.androidm3.databinding.ResultItemBinding;

import com.example.androidm3.orm.trecogntionresult.Result;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultHolder> {

    private List<Result> mResultList = new ArrayList<>();

    public ResultAdapter(List<Result> data) {
        this.mResultList = data;
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ResultHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        holder.bindTo(mResultList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mResultList == null)
            return 0;
        return mResultList.size();
    }

    static class ResultHolder extends RecyclerView.ViewHolder {

       /* View ResultView;
        Button lookup;

        public ResultHolder(View view) {
            super(view);
            ResultView = view;
            lookup = view.findViewById(R.id.lookup);
        }*/

        private ResultItemBinding mBinding;

        static ResultHolder create(LayoutInflater inflater, final ViewGroup parent) {
            ResultItemBinding binding = ResultItemBinding.inflate(inflater, parent, false);

            /*final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);
            final ResultHolder resultHolder = new ResultHolder(view);
            resultHolder.lookup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ResultHolder", "onClick");
                    Toast.makeText(parent.getContext(), "yes", Toast.LENGTH_SHORT).show();
                }
            });*/
            return new ResultHolder(binding);
        }

        private ResultHolder(ResultItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bindTo(Result result) {
            mBinding.lookup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("onClick", "onClick: rNumber " + mBinding.rNumber.getText());

                    /*
                     * 自定义布局
                     * setView()只会覆盖AlertDialog的Title与Button之间的那部分，而setContentView()则会覆盖全部，
                     * setContentView()必须放在show()的后面
                     * */
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                    LayoutInflater inflater = LayoutInflater.from(v.getContext());
                    View view = inflater.inflate(R.layout.photoshow, null);

                    ImageView photo_result = view.findViewById(R.id.photo_result);


                    /*
                     * 获取照片
                     * */
                    String photo = SetPhoto.Lookup(view, mBinding.rNumber.getText().toString());


                    String path= Environment.getExternalStorageDirectory() + File.separator + photo;
                    Bitmap bm = BitmapFactory.decodeFile(path);
                    photo_result.setImageBitmap(bm);//不会变形

//                    photo_result.setImageDrawable();
//                    photo_result.setImageResource(R.drawable.test);

                    final Dialog dialog = builder.create();
                    dialog.show();

                    dialog.getWindow().setContentView(view);       //自定义布局应该在这里添加，要在dialog.show()的后面
                    //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置

                }
            });
            mBinding.setResult(result);
            mBinding.executePendingBindings();
        }
    }
}
