package com.example.coursify.view;

import android.support.v4.app.ListFragment;

public abstract class MainActivityHoldingFragment extends ListFragment {
	private MainActivity main;

	public void setMain(MainActivity main) {
		this.main = main;
	}

	public MainActivity getMain() {
		return this.main;
	}
}
