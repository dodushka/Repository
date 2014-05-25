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

	  // кол-во элементов
	  @Override
	  public int getCount() {
	    return objects.size();
	  }

	  // элемент по позиции
	  @Override
	  public Object getItem(int position) {
	    return objects.get(position);
	  }

	  // id по позиции
	  @Override
	  public long getItemId(int position) {
	    return position;
	  }

	  // пункт списка
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    // используем созданные, но не используемые view
	    View view = convertView;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.adapter, parent, false);
	    }

	    Tag p = getSportInfo(position);

	    // заполняем View в пункте списка данными из товаров: наименование, цена
	    // и картинка
	    ((TextView) view.findViewById(R.id.sport_name)).setText(p.name);
	    
	    CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
	    // присваиваем чекбоксу обработчик
	    cbBuy.setOnCheckedChangeListener(myCheckChangList);
	    // пишем позицию
	    cbBuy.setTag(position);
	    // заполняем данными из товаров: в корзине или нет
	    cbBuy.setChecked(p.box);
	    return view;
	  }

	  // товар по позиции
	  Tag getSportInfo(int position) {
	    return ((Tag) getItem(position));
	  }

	  // содержимое корзины
	  ArrayList<Tag> getBox() {
	    ArrayList<Tag> box = new ArrayList<Tag>();
	    for (Tag p : objects) {
	      // если в корзине
	      if (p.box){}
	        box.add(p);
	    }
	    return box;
	  }

	  // обработчик для чекбоксов
	  OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
	    public void onCheckedChanged(CompoundButton buttonView,
	        boolean isChecked) {
	      // меняем данные товара (в корзине или нет)
	      getSportInfo((Integer) buttonView.getTag()).box = isChecked;
	    }
	  };
	}


