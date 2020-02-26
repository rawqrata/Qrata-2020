package com.insonix.qrata.constants;

public class Constants {

	public static enum Roles {

		ADMIN((short) 1), EDITOR((short) 2), EXPERT((short) 3);

		short value;

		Roles(short val) {
			this.value = val;

		}

		public short getValue() {
			return this.value;
		}
	}

	public static enum CategoryType {

		DOMAIN((short) 1), CATEGORY((short) 2), SUBCATEGORY((short) 3);

		short value;

		CategoryType(short val) {
			this.value = val;
		}

		public short getValue() {
			return this.value;
		}
	}
	public static enum Topics {
		TOPIC((short) 4), SUBTOPIC((short) 5);

		short value;

		Topics(short val) {
			this.value = val;

		}

		public short getValue() {
			return this.value;

		}
	}

	public static enum RatingCriteriaType {
		RATINGCRITERIACATEGORY((short) 1), RATINGCRITERIA((short) 2);

		short value;

		RatingCriteriaType(short val) {
			this.value = val;

		}

		public short getValue() {
			return this.value;

		}
	}
	
}
