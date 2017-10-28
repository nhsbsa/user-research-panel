package uk.nhs.nhsbsa.research.userresearchapp.vo;

public enum NHSBSASystem {

	PPC("ppc");
	
	private String system;
	
	private NHSBSASystem(String system) {
	    this.system = system;	
	}
	
	@Override
	public String toString() {
		return this.system;
	}
}
