package uk.nhs.nhsbsa.research.userresearchapp.forms;

import javax.validation.constraints.AssertTrue;

@SuppressWarnings("serial")
public class ParticipantConsent extends AbstractForm {

	@AssertTrue
	private boolean consent;

	public boolean isConsent() {
		return consent;
	}

	public void setConsent(boolean consent) {
		this.consent = consent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (consent ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipantConsent other = (ParticipantConsent) obj;
		if (consent != other.consent)
			return false;
		return true;
	}
}
