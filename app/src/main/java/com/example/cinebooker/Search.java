package com.example.cinebooker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cinebooker.search.SearchAdapter;
import com.example.cinebooker.search.search_movies;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private List<search_movies> searchMoviesList;
    private TextView moreThan;

    public Search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search.
     */
    // TODO: Rename and change types and number of parameters
    public static Search newInstance(String param1, String param2) {
        Search fragment = new Search();
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
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        searchMoviesList = new ArrayList<>();

        searchMoviesList.add(new search_movies(R.drawable.camposter, "18+", "Cám", "Kinh dị", 6.2, 15000.0, 3200.0));
        searchMoviesList.add(new search_movies(R.drawable.camposter, "18+", "Cám", "Kinh dị", 6.2, 15000.0, 3200.0));
        searchMoviesList.add(new search_movies(R.drawable.camposter, "18+", "Cám", "Kinh dị", 6.2, 15000.0, 3200.0));
        searchMoviesList.add(new search_movies(R.drawable.camposter, "18+", "Cám", "Kinh dị", 6.2, 15000.0, 3200.0));
        searchMoviesList.add(new search_movies(R.drawable.camposter, "18+", "Cám", "Kinh dị", 6.2, 15000.0, 3200.0));
        searchMoviesList.add(new search_movies(R.drawable.camposter, "18+", "Cám", "Kinh dị", 6.2, 15000.0, 3200.0));
        searchMoviesList.add(new search_movies(R.drawable.camposter, "18+", "Cám", "Kinh dị", 6.2, 15000.0, 3200.0));
        searchMoviesList.add(new search_movies(R.drawable.camposter, "18+", "Cám", "Kinh dị", 6.2, 15000.0, 3200.0));
        searchMoviesList.add(new search_movies(R.drawable.camposter, "18+", "Cám", "Kinh dị", 6.2, 15000.0, 3200.0));
        searchMoviesList.add(new search_movies(R.drawable.camposter, "18+", "Cám", "Kinh dị", 6.2, 15000.0, 3200.0));


        searchAdapter = new SearchAdapter(searchMoviesList);
        recyclerView.setAdapter(searchAdapter);

        moreThan = view.findViewById(R.id.search_more_than);
        moreThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMoreItems();
            }
        });
        return view;
    }

    // Phương thức tải thêm item
    private void loadMoreItems() {
        int currentItemCount = searchAdapter.getCurrentItemCount();
        if (currentItemCount < searchMoviesList.size()) {
            currentItemCount += 10;
            searchAdapter.updateItemCount(currentItemCount);
        } else {
            moreThan.setVisibility(View.GONE);
        }
    }
}