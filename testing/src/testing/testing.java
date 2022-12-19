package testing;

public class testing {

	public static void main(String[] args) {
		String pat = "21B030771 21B030945";
		int delimeter = pat.indexOf(' ');
		System.out.println(pat.substring(0, delimeter));
		System.out.println(pat.substring(delimeter+1, pat.length()));

	}

}
