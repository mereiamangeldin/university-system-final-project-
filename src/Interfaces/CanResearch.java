package Interfaces;

import Attributes.ResearchPaper;
import Attributes.ResearchProject;

public interface CanResearch {
	public void addResearchPaper(ResearchPaper researchPaper);
	public void addResearchProject(ResearchProject researchProject);
	public int getHindex();
}
