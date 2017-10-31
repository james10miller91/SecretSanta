package com.tenable.ss.dao;

import java.util.List;

import com.tenable.ss.model.SecretSantaGroup;

public interface SecretSantaGroupDao {
	public void store(SecretSantaGroup secretSantaGroup);
	public List<SecretSantaGroup> getAll();
	public SecretSantaGroup getByName(String name);
}
