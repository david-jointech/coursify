package com.example.coursify.view;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.coursify.R;
import com.example.coursify.model.Lecture;
import com.example.coursify.model.User;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class MainActivity extends ActionBarActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		mockCurrentUser();

		initializeDrawer();
		initializeMenu();

		mTitle = "Meine Vorlesungen";
	}

	private void addFragment(MainActivityHoldingFragment fragment) {
		fragment.setMain(this);
		mDrawerLayout.addView(fragment.onCreateView(getLayoutInflater(),
				mDrawerLayout, null));
	}

	private void initializeDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		addFragment(new MyLecturesFragment());
		addFragment(new MainMenuFragment());

		mDrawerTitle = "Menü";

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_navigation_drawer, 0, 0) {

			/** Called when a drawer has settled in a completely closed state. */
			@Override
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	private void initializeMenu() {
		ListView menuItemsList = (ListView) this
				.findViewById(R.id.menu_items_list);
		String[] listItems = getResources()
				.getStringArray(R.array.menu_entries);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.menu_list_item, R.id.list_content, listItems);

		menuItemsList.setAdapter(adapter);
	}

	public void initializeComments() {
		ListView commentList = (ListView) this.findViewById(R.id.comment_list);
		String[] listItems = { "Hans Dampf", "Mann Mustermax",
				"xXOvGU_Student98Xx" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.comment_list_item, R.id.comment_name, listItems);
		if (commentList != null)
			commentList.setAdapter(adapter);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}

	public DrawerLayout getDrawerLayout() {
		return mDrawerLayout;
	}

	public void setMTitle(CharSequence mTitle) {
		this.mTitle = mTitle;
		getActionBar().setTitle(this.mTitle);
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void mockCurrentUser() {
		currentUser = new User();
		ArrayList<Lecture> lectures = new ArrayList<Lecture>();
		lectures.add(new Lecture("Vorlesung 1"));
		lectures.add(new Lecture("Vorlesung 2"));
		lectures.add(new Lecture("Vorlesung 3"));
		lectures.add(new Lecture("Vorlesung 4"));
		lectures.add(new Lecture("Vorlesung 5"));
		lectures.add(new Lecture("Vorlesung 6"));
		lectures.add(new Lecture("Vorlesung 7"));
		currentUser.setLectures(lectures);
	}
}
