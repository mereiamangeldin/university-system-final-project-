package Actors;
import java.util.Vector;

import Attributes.ResearchPaper;
import Attributes.ResearchProject;
import Decorators.*;
import Interfaces.*;
/**for decorating Teacher*/

public class WithTeacherResearcher extends TeacherDecorator implements CanResearch {
	private Vector<ResearchPaper> researchPapers;
	private Vector<ResearchProject> researchProjects;
	
	public WithTeacherResearcher(User user) {
		super((Teacher)user);
	}
	
	//research methods
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
