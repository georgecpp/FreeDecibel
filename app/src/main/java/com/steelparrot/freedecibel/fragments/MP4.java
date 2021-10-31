package com.steelparrot.freedecibel.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.steelparrot.freedecibel.R;
import com.steelparrot.freedecibel.network.YoutubeDLFactory;
import com.yausername.youtubedl_android.DownloadProgressCallback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MP4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MP4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button mDownloadMP4;

    private YoutubeDLFactory mYoutubeDLFactory = null;

    private final DownloadProgressCallback mCallback = new DownloadProgressCallback() {
        @Override
        public void onProgressUpdate(float progress, long etaInSeconds) {
            getActivity().runOnUiThread(() -> {
                Toast.makeText(getActivity(), String.valueOf(progress), Toast.LENGTH_SHORT).show();
//                mProgressBar.setProgress((int) progress);
//                tvDownloadStatus.setText(String.valueOf(progress) + "% (ETA " + String.valueOf(etaInSeconds) + " seconds)");
            });
        }
    };

    public MP4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MP4.
     */
    // TODO: Rename and change types and number of parameters
    public static MP4 newInstance(String param1, String param2) {
        MP4 fragment = new MP4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View binding = inflater.inflate(R.layout.fragment_mp4, container, false);

        mDownloadMP4 = binding.findViewById(R.id.button_mp4);
        mDownloadMP4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYoutubeDLFactory = YoutubeDLFactory.getInstance(YoutubeDLFactory.Format.MP4,"https://www.youtube.com/watch?v=L397TWLwrUU");
                if(mYoutubeDLFactory.isDownloading()) {
                    Toast.makeText(getActivity(),"cannot start downloading. a download is already in progress", Toast.LENGTH_LONG).show();
                    return;
                }
                mYoutubeDLFactory.startDownload(getActivity(),mCallback);
            }
        });

        return binding.getRootView();
    }
}