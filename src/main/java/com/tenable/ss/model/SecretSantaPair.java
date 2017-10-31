package com.tenable.ss.model;

public class SecretSantaPair {
	private Person _recievingPerson;
	private Person _givingPerson;
	
	public SecretSantaPair(Person recievingPerson, Person givingPerson) {
		_recievingPerson = recievingPerson;
		_givingPerson = givingPerson;
	}

	public Person get_recievingPerson() {
		return _recievingPerson;
	}

	public void set_recievingPerson(Person _recievingPerson) {
		this._recievingPerson = _recievingPerson;
	}

	public Person get_givingPerson() {
		return _givingPerson;
	}

	public void set_givingPerson(Person _givingPerson) {
		this._givingPerson = _givingPerson;
	}

	@Override
	public String toString() {
		return "SecretSantaPair [_recievingPerson=" + _recievingPerson.get_name() + ", _givingPerson=" + _givingPerson.get_name() + "]";
	}
	
	
}
