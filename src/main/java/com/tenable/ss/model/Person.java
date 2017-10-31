package com.tenable.ss.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import com.google.common.collect.EvictingQueue;


public class Person {
	private String _name;
	private List<Person> _immediateFamily;
	private Queue<Person> _prevGiftGivers;

	public Person() {
		_immediateFamily = new ArrayList<Person>();
		_prevGiftGivers = EvictingQueue.create(3);
	}

	public Person(String name) {
		_immediateFamily = new ArrayList<Person>();
		_prevGiftGivers = EvictingQueue.create(3);
		_name = name;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public void addImmediateFamily(Person... people) {
		for (Person p : people) {
			_immediateFamily.add(p);
		}
	}

	public void addGiftGiver(Person person) {
		_prevGiftGivers.add(person);
	}

	public boolean isPreviousGiftGiver(Person person) {
		return _prevGiftGivers.contains(person);
	}

	public List<Person> get_immediateFamily() {
		return _immediateFamily;
	}

	public void set_immediateFamily(List<Person> _immediateFamily) {
		this._immediateFamily = _immediateFamily;
	}

	@Override
	public String toString() {
		String str = "Name: " + _name +"\n\tImmediate Family: ";
		for(Person p : _immediateFamily) {
			str+=p.get_name()+",";
		}
		str+="\n\tPrevious Gift Givers:";
		Iterator<Person> iterator = _prevGiftGivers.iterator();
		while(iterator.hasNext()) {
			str+=iterator.next().get_name() + ",";
		}
		str+="\n";
		return str;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Person) {
			Person p = (Person) o;
			if (this.get_name().equals(p.get_name())) {
				return true;
			}
		}
		return false;
	}
}
