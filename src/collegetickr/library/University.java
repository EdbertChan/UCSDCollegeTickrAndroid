package collegetickr.library;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class University implements Comparable {
	private String universityID, universityURL;

	public University() {
		// default values
		universityID = "UCSD";
		universityURL = "ucsd";
	}

	public University(String id, String url) {
		universityID = id;
		universityURL = url;
	}
	public String getUniversityID() {
		return universityID;
	}

	public static Set<University> defaultSet() {
		Set s = new HashSet<University>();
		s.add(new University());
		s.add(new University());
		s.add(new University());
		s.add(new University());
		s.add(new University());
		s.add(new University());
		s.add(new University());
		s.add(new University());
		s.add(new University());
		s.add(new University());
		return s;
	}

	public void setUniversityID(String universityID) {
		this.universityID = universityID;
	}

	public String getUniversityURL() {
		return universityURL;
	}

	public void setUniversityURL(String universityURL) {
		this.universityURL = universityURL;
	}

	@Override
	public int compareTo(Object arg0) {

		// TODO Auto-generated method stub
		return universityID.compareTo(((University) arg0).universityID);
	}

	public static class UniversityComparator implements Comparator<University> {

		public int compare(University u1, University u2) {
			return u1.getUniversityID().compareTo(u2.getUniversityID());
		}

	}
}