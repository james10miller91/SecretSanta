package com.tenable.ss.entrypoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tenable.ss.dao.SecretSantaGroupDao;
import com.tenable.ss.dao.SecretSantaGroupDaoImpl;
import com.tenable.ss.model.Person;
import com.tenable.ss.model.SecretSantaGroup;

public class SecretSanta {
	private static Scanner _reader;
	private static SecretSantaGroupDao _dao;

	public static void main(String[] args) {
		_reader = new Scanner(System.in);
		_dao = new SecretSantaGroupDaoImpl();

		System.out.println("Welcome to the Secret Santa Application!\n");
		introMenu();
	}

	private static void introMenu() {
		System.out.println("Select an option...");
		System.out.println("1. Create new Secret Santa group" + "\n" + "2. List Secret Santa groups" + "\n"
				+ "3. Select Secret Santa group" + "\n" + "4. Exit");

		int selectionNumber = _reader.nextInt();

		switch (selectionNumber) {
		case 1: {
			createSecretSantaGroup();
			break;
		}
		case 2: {
			listSecretSantaGroups();
			introMenu();
			break;
		}
		case 3: {
			selectSecretSantaGroup();
			break;
		}
		case 4: {
			System.exit(0);
		}
		}

	}

	private static void selectSecretSantaGroup() {
		System.out.println("Enter Secret Santa group name...");
		SecretSantaGroup secretSantaGroup = _dao.getByName(_reader.next());
		if (secretSantaGroup == null) {
			System.out.println("Error. Name not found.");
			introMenu();
		}

		while (true) {
			System.out.println(
					"1. Display Secret Santa Group information." + "\n" + "2. Run Secret Santa" + "\n" + "3. Exit");

			int selectionNumber = _reader.nextInt();

			switch (selectionNumber) {
			case 1: {
				System.out.println(secretSantaGroup);
				break;
			}
			case 2: {
				secretSantaGroup.performSecretSanta();
				break;
			}
			case 3: {
				introMenu();
				break;
			}
			}
		}
	}

	private static void listSecretSantaGroups() {
		System.out.println();
		List<SecretSantaGroup> secretSantaGroups = _dao.getAll();
		int i = 1;
		for (SecretSantaGroup ssg : secretSantaGroups) {
			System.out.print(ssg.get_name());
			if (i % 4 == 0) {
				System.out.println();
			} else {
				System.out.print("\t");
			}
			i++;
		}
		System.out.println("\n");
	}

	private static void createSecretSantaGroup() {
		System.out.println("Enter Secret Santa group name...");
		String groupName = _reader.next();
		SecretSantaGroup secretSantaGroup = new SecretSantaGroup(groupName);

		System.out
				.println("Add immediate family groups. Next for next immediate family group. Done to finish entry...");
		String personPrev = "";
		List<Person> familyGroup = new ArrayList<Person>();
		while (true) {
			String personsName = _reader.nextLine();
			if(personsName.trim().equals(""))
				continue;
			personsName = personsName.toLowerCase();
			switch (personsName) {
			case "next": {
				for (Person p : familyGroup) {
					p.set_immediateFamily(familyGroup);
					secretSantaGroup.addPeople(p);
				}
				familyGroup = new ArrayList<Person>();
				System.out.println("Next immediate family group started...");
				break;
			}
			case "done": {
				for (Person p : familyGroup) {
					p.set_immediateFamily(familyGroup);
					secretSantaGroup.addPeople(p);
				}
				_dao.store(secretSantaGroup);
				introMenu();
				break;
			}
			default: {
				familyGroup.add(new Person(personsName));
				break;
			}
			}
		}
	}

}
