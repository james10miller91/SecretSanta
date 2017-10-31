package com.tenable.ss.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SecretSantaGroupTest {

	@Test
	public void testSecretSantaSmallScale() {
		SecretSantaGroup smallGroup = new SecretSantaGroup("SmallGroup");

		Person James = new Person("James M");
		Person Jake = new Person("Jake M");
		James.addImmediateFamily(James, Jake);
		Jake.addImmediateFamily(James, Jake);

		Person Ben = new Person("Ben K");
		Person Mike = new Person("Mike K");
		Ben.addImmediateFamily(Ben, Mike);
		Mike.addImmediateFamily(Ben, Mike);

		smallGroup.addPeople(James, Jake, Ben, Mike);

		smallGroup.performSecretSanta();
		smallGroup.performSecretSanta();
		//Should refuse to perform another secret santa
		smallGroup.performSecretSanta();

		if (James.isPreviousGiftGiver(Ben) && James.isPreviousGiftGiver(Mike) && !James.isPreviousGiftGiver(Jake))
			assert true;
		else
			assert false;
	}

	@Test
	public void testSecretSantaNeverEnding() {
		SecretSantaGroup smallGroup = new SecretSantaGroup("SmallGroup");

		Person James = new Person("James M");
		Person Jake = new Person("Jake M");
		Person Jessy = new Person("Jessy M");
		Person Jim = new Person("Jim M");
		James.addImmediateFamily(James, Jake, Jessy, Jim);
		Jake.addImmediateFamily(James, Jake, Jessy, Jim);
		Jessy.addImmediateFamily(James, Jake, Jessy, Jim);
		Jim.addImmediateFamily(James, Jake, Jessy, Jim);

		Person Ben = new Person("Ben K");
		Person Mike = new Person("Mike K");
		Person John = new Person("John K");
		Person Kay = new Person("Kay K");
		Ben.addImmediateFamily(Ben, Mike, John, Kay);
		Mike.addImmediateFamily(Ben, Mike, John, Kay);
		John.addImmediateFamily(Ben, Mike, John, Kay);
		Kay.addImmediateFamily(Ben, Mike, John, Kay);

		smallGroup.addPeople(James, Jake, Ben, Mike, Jessy, John, Jim, Kay);

		boolean failed = false;
		for (int i = 0; i < 100; i++) {
			smallGroup.performSecretSanta();
			if (James.isPreviousGiftGiver(James) || James.isPreviousGiftGiver(Jake) || James.isPreviousGiftGiver(Jessy)
					|| James.isPreviousGiftGiver(Jim)) {
				failed = true;
				assert false;
			} else if (Jake.isPreviousGiftGiver(James) || Jake.isPreviousGiftGiver(Jake)
					|| Jake.isPreviousGiftGiver(Jessy) || Jake.isPreviousGiftGiver(Jim)) {
				failed = true;
				assert false;
			} else if (Jessy.isPreviousGiftGiver(James) || Jessy.isPreviousGiftGiver(Jake)
					|| Jessy.isPreviousGiftGiver(Jessy) || Jessy.isPreviousGiftGiver(Jim)) {
				failed = true;
				assert false;
			} else if (Jim.isPreviousGiftGiver(James) || Jim.isPreviousGiftGiver(Jake)
					|| Jim.isPreviousGiftGiver(Jessy) || Jim.isPreviousGiftGiver(Jim)) {
				failed = true;
				assert false;
			}
		}
		if (!failed)
			assert true;
	}

}
