package uk.nhs.nhsbsa.research.userresearchapp.forms;

@SuppressWarnings("serial")
public class ScreeningPurchasedPPC extends AbstractForm {

	private boolean previousPpc;

	public boolean isPreviousPpc() {
		return previousPpc;
	}

	public void setPreviousPpc(boolean previousPpc) {
		this.previousPpc = previousPpc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (previousPpc ? 1231 : 1237);
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
		ScreeningPurchasedPPC other = (ScreeningPurchasedPPC) obj;
		if (previousPpc != other.previousPpc)
			return false;
		return true;
	}
}
