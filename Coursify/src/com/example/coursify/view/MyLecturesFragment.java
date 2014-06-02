package com.example.coursify.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.coursify.LecturesFragment;
import com.example.coursify.R;
import com.example.coursify.model.Lecture;

public class MyLecturesFragment extends LecturesFragment {
	private ListView mListView = null;
	private int _toDelete = -1;
	ArrayList<Lecture> lectures = null;

	public void setItemToDelete(int i) {
		_toDelete = i;
	}

	public void deleteItem() {
		lectures.remove(_toDelete);
		setListAdapter();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_my_lectures, container,
				false);
		mListView = (ListView) view.findViewById(R.id.my_lectures_list);
		setListAdapter();
		mListView
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {

						setItemToDelete(position);
						new AlertDialog.Builder(parent.getContext())
								.setIcon(android.R.drawable.ic_dialog_alert)
								.setTitle("Vorlesung löschen")
								.setMessage(
										"Möchten Sie die Vorlesung wirklich löschen?")
								.setPositiveButton("Ja",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												deleteItem();
											}

										}).setNegativeButton("Nope", null)
								.show();

						return true;
					}
				});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				getMain().setMTitle(mockLectures().get(position).getName());
				FragmentSwitcher.switchToFragment(new LectureFragment(),
						getMain());
				getMain().initializeComments();
			}

		});

		return view;
	}

	private ArrayList<Lecture> mockLectures() {
		if (lectures == null) {
			lectures = new ArrayList<Lecture>();
			lectures.add(new Lecture("Vorlesung 1"));
			lectures.add(new Lecture("Vorlesung 2"));
			lectures.add(new Lecture("Vorlesung 3"));
			lectures.add(new Lecture("Vorlesung 4"));
			lectures.add(new Lecture("Vorlesung 5"));
			lectures.add(new Lecture("Vorlesung 6"));
			lectures.add(new Lecture("Vorlesung 7"));
		}
		return lectures;
	}

	private void setListAdapter() {
		ArrayList<String> values = new ArrayList<String>();

		for (Lecture lecture : mockLectures()) {
			values.add((String) lecture.getName());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getMain(),
				R.layout.course_list_item, R.id.course_name, values);
		mListView.setAdapter(adapter);
	}

	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

}
