import java.util.Arrays;

class IsAnagram {
	public boolean isAnagram(String s, String t) {

		// Option 1 : keep count char[26], increase count for each char in s and
		// decrease for t,
		// after these operations count must be all Zero

		// Option 2: Sort the characters of s and t and compare
		char sa[] = s.toCharArray();
		char st[] = t.toCharArray();
		Arrays.sort(sa);
		Arrays.sort(st);

		s = new String(sa);
		t = new String(st);

		return s.equals(t);

	}
}
