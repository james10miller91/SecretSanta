package com.tenable.ss.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class SecretSantaGroup {
	private String _name;
	private List<Person> _group;

	public SecretSantaGroup(String name) {
		_name = name;
		_group = new ArrayList<Person>();
	}

	public SecretSantaGroup() {
		_group = new ArrayList<Person>();
	}

	@Override
	public String toString() {
		String str = "Secret Santa Group name: " + _name + "\n";
		for (Person p : _group) {
			str += p.toString();
		}
		return str;
	}

	public void addPeople(Person... people) {
		for (Person p : people) {
			_group.add(p);
		}
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public void performSecretSanta() {
		int randomNum = 0;
		List<SecretSantaPair> listOfPairs = new ArrayList<SecretSantaPair>();
		List<Person> editableGroup = new ArrayList<Person>(_group);
		Set<Integer> uniqueNumbers = new LinkedHashSet();

		for (Person p : _group) {
			uniqueNumbers.clear();
			outerloop: while (true) {
				randomNum = ThreadLocalRandom.current().nextInt(0, editableGroup.size());
				uniqueNumbers.add(randomNum);
				Person possiblePair = editableGroup.get(randomNum);
				if (p.get_immediateFamily().contains(possiblePair) || p.isPreviousGiftGiver(possiblePair)) {
				} else {
					listOfPairs.add(new SecretSantaPair(p, possiblePair));
					p.addGiftGiver(possiblePair);
					editableGroup.remove(possiblePair);
					break outerloop;
				}
				if (uniqueNumbers.size() == editableGroup.size()) {
					System.out.println(
							"Not enough people in group. Unable to compute Secret Santa with the rules in place. Try adding more people to this group.");
					break;
				}
			}
		}

		System.out.println("Printing pairs...");
		for (SecretSantaPair pair : listOfPairs) {
			System.out.println(pair);
		}
	}

}
