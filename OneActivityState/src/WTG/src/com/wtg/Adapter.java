package com.wtg;


	

	import java.util.ArrayList;
	import android.content.Context;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.BaseAdapter;
	import android.widget.CheckBox;
	import android.widget.CompoundButton;
	import android.widget.CompoundButton.OnCheckedChangeListener;
	import android.widget.TextView;

	public class Adapter extends BaseAdapter {
	  Context ctx;
	  LayoutInflater lInflater;
	  ArrayList<Tag> objects;

	  Adapter(Context context, ArrayList<Tag> tags) {
	    ctx = context;
	    objects = tags;
	    lInflater = (LayoutInflater) ctx
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  }

	  // ���-�� ���������
	  @Override
	  public int getCount() {
	    return objects.size();
	  }

	  // ������� �� �������
	  @Override
	  public Object getItem(int position) {
	    return objects.get(position);
	  }

	  // id �� �������
	  @Override
	  public long getItemId(int position) {
	    return position;
	  }

	  // ����� ������
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    // ���������� ���������, �� �� ������������ view
	    View view = convertView;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.adapter, parent, false);
	    }

	    Tag p = getSportInfo(position);

	    // ��������� View � ������ ������ ������� �� �������: ������������, ����
	    // � ��������
	    ((TextView) view.findViewById(R.id.sport_name)).setText(p.name);
	    
	    CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
	    // ����������� �������� ����������
	    cbBuy.setOnCheckedChangeListener(myCheckChangList);
	    // ����� �������
	    cbBuy.setTag(position);
	    // ��������� ������� �� �������: � ������� ��� ���
	    cbBuy.setChecked(p.box);
	    return view;
	  }

	  // ����� �� �������
	  Tag getSportInfo(int position) {
	    return ((Tag) getItem(position));
	  }

	  // ���������� �������
	  ArrayList<Tag> getBox() {
	    ArrayList<Tag> box = new ArrayList<Tag>();
	    for (Tag p : objects) {
	      // ���� � �������
	      if (p.box){}
	        box.add(p);
	    }
	    return box;
	  }

	  // ���������� ��� ���������
	  OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
	    public void onCheckedChanged(CompoundButton buttonView,
	        boolean isChecked) {
	      // ������ ������ ������ (� ������� ��� ���)
	      getSportInfo((Integer) buttonView.getTag()).box = isChecked;
	    }
	  };
	}


