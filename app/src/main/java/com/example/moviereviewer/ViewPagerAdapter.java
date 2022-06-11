package com.example.moviereviewer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private String login;

    public ViewPagerAdapter(FragmentActivity fragmentActivity, String login){
        super(fragmentActivity);
        this.login = login;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new AddMovieFragment(login);
            case 1:
                return new MoviesFragment(login);
            case 2:
                return new ProfileFragment(login);
            default:
                return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
