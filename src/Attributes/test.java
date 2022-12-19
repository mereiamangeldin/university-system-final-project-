package Attributes;

import Actors.*;
import Enums.*;
import javafx.util.Pair;

import java.text.*;
import java.util.*;
public class test {

	public static void main(String[] args) throws ParseException {
		Questionnaire q = new Questionnaire();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Dean d = new Dean("Alibek", "Bisembayev", "alibeksitetop", dateFormat.parse("1986/06/02"), "D3334", dateFormat.parse("2006/02/28"), 800000, "203-139");
		Dean d2 = new Dean("Assylbek", "Issakhov", "mkmtoptop", dateFormat.parse("1986/12/20"), "D2345", dateFormat.parse("2006/04/12"), 800000, "405-294");
		School SITE = new School("School of Information Technologies and Engineering", d);
		School SAM = new School("School of Applied Mathematics", d2);
		Teacher t = new Teacher("Asel", "Kereeva", "aselkapuper", dateFormat.parse("1976/09/22"), "T3214", dateFormat.parse("1996/05/05"), 360000, "125-999", SITE, TeacherTypes.TUTOR);
		Teacher t2 = new Teacher("Kemel", "Uteev", "kemel123", dateFormat.parse("1990/08/21"), "T9384", dateFormat.parse("2020/01/25"), 380000, "101-199", SAM, TeacherTypes.PROFESSOR);
		Teacher t3 = new Teacher("Usibaliyeva", "Medina", "kemel123", dateFormat.parse("1990/08/21"), "T9386", dateFormat.parse("2010/01/15"), 400000, "101-199", SAM, TeacherTypes.PROFESSOR);
		q.getRating().put(t, new Vector<Double>());
		q.getRating().put(t2, new Vector<Double>());
		q.getRating().put(t3, new Vector<Double>());
		q.getRating().get(t).add(45.0);
		q.getRating().get(t).add(60.0);
		q.getRating().get(t).add(90.0);
		q.getRating().get(t2).add(55.0);
		q.getRating().get(t2).add(76.0);
		q.getRating().get(t2).add(100.0);
		q.getRating().get(t3).add(45.6);
		q.getRating().get(t3).add(97.6);
		q.getRating().get(t3).add(100.0);
		q.getRating().get(t3).add(85.0);
		q.getRating().get(t3).add(90.4);
		
		for(Pair<Teacher, Double> x: q.printTeachersByRate()){
			System.out.println(x);
		}
//		HashMap<Teacher, Vector<Double>> stream = q.getRating().entrySet().stream().sorted(Collections.reverseOrder(HashMap.Entry.comparingByValue(new RateComparator()))).collect(Collectors.toMap
//				(HashMap.Entry::getKey, HashMap.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
//		System.out.println(stream);
//		for(HashMap.Entry<String, Vector<Double>> r: rate.entrySet()) {
//			System.out.print(r.getKey());
//			System.out.println(r.getValue());
//		}
	}

}