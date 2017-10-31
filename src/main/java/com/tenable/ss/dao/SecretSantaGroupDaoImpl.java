package com.tenable.ss.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tenable.ss.model.SecretSantaGroup;

public class SecretSantaGroupDaoImpl implements SecretSantaGroupDao {
	private List<SecretSantaGroup> _groups;

	public SecretSantaGroupDaoImpl() {
		_groups = new ArrayList<SecretSantaGroup>();
	}

	@Override
	public synchronized void store(SecretSantaGroup secretSantaGroup) {
		_groups.add(secretSantaGroup);
	}

	@Override
	public synchronized List<SecretSantaGroup> getAll() {
		return _groups;
	}

	@Override
	public synchronized SecretSantaGroup getByName(String name) {
		for (SecretSantaGroup ssg : _groups) {
			if (name.equals(ssg.get_name())) {
				return ssg;
			}
		}
		return null;
	}

}
