package Actors;
import Decorators.*;
import Interfaces.*;
import Attributes.*;
import java.util.*;
/**for decorating Student*/

public class WithStudentResearcher extends StudentDecorator implements CanResearch{
	private Vector<ResearchPaper> researchPapers;
	private Vector<ResearchProject> researchProjects;
	public WithStudentResearcher(User user) {
		super((Student)user);
	}
	// researcher methods
	/**adds research paper*/

	public void addResearchPaper(ResearchPaper researchPaper) {
		getResearchPapers().add(researchPaper);
	}
	/**adds research project*/
	public void addResearchProject(ResearchProject researchProject) {
		getResearchProjects().add(researchProject);
	}
	/**calculates h-index*/

	public int getHindex() {
		int hIndex = 0;
		for(ResearchProject r : researchProjects) {
			int temp = r.getCitations().size();
			int cnt = 0;
			for(ResearchProject w : researchProjects) {
				if(w.getCitations().size()>=temp) cnt++;
				if(cnt>=temp)break;
			}
			if(cnt>=temp && temp>hIndex) hIndex = temp;
		}
		return hIndex;
	}

	public Vector<ResearchPaper> getResearchPapers() {
		return researchPapers;
	}


	public Vector<ResearchProject> getResearchProjects() {
		return researchProjects;
	}

}
