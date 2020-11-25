package tech.thdev.java_udemy_sample.view.image;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.thdev.java_udemy_sample.R;
import tech.thdev.java_udemy_sample.data.PhotoItem;
import tech.thdev.java_udemy_sample.data.source.image.ImageRepository;
import tech.thdev.java_udemy_sample.view.detail.DetailMoreActivity;
import tech.thdev.java_udemy_sample.view.image.adapter.PhotoViewAdapter;
import tech.thdev.java_udemy_sample.view.image.presenter.PhotoViewPresenter;
import tech.thdev.java_udemy_sample.view.image.presenter.PhotoViewPresenterImpl;

/**
 * Created by tae-hwan on 10/29/16.
 */

public class PhotoViewFragment extends Fragment implements PhotoViewPresenter.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    // MVPPresenter 추가
    private PhotoViewPresenterImpl presenter;

    private PhotoViewAdapter adapter;

    // static instance 생성
    public static PhotoViewFragment getInstance() {
        return new PhotoViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_sample, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        adapter = new PhotoViewAdapter(getContext());
        recyclerView.setAdapter(adapter);

        presenter = new PhotoViewPresenterImpl(this, ImageRepository.getInstance());

        // Adapter의 View/Model을 셋팅한다
        presenter.setAdapterModel(adapter);
        presenter.setAdapterView(adapter);


        // Activity의 {@link FloatingActionButton}
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.recentPhotoData();
            }
        });

        // 새로운 데이터 불러오기
        presenter.recentPhotoData();
    }

    @Override
    public void showLoaded() {
        Toast.makeText(getContext(), "Load success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailLoaded() {
        Toast.makeText(getContext(), "Load fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadFailMessage(int code, String message) {
        Toast.makeText(getContext(), "Code " + code + ", message " + message, Toast.LENGTH_LONG).show();
        Log.e("TAG", "Code " + code + ", message " + message);
    }

    @Override
    public void showDetailPage(PhotoItem photoItem) {
        Intent intent = new Intent(getContext(), DetailMoreActivity.class);
        intent.putExtra(DetailMoreActivity.KEY_PHOTO_ID, photoItem.getId());
        startActivity(intent);
    }
}
