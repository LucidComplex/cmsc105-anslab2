package anslab2;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		int compared = String.CASE_INSENSITIVE_ORDER.compare(o1, o2);
		String digit = "[0-9]+";
		if(o1.matches(digit) && o2.matches(digit)) {
			int i1 = Integer.parseInt(o1);
			int i2 = Integer.parseInt(o2);
			if(i1 < i2)
				return -1;
			if(i1 == i2)
				return 0;
			if(i1 > i2)
				return 1;
		}
		return compared;
	}
	
}
