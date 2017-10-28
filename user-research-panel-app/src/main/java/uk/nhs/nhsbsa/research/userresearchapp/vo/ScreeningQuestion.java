package uk.nhs.nhsbsa.research.userresearchapp.vo;

public class ScreeningQuestion<T> {

	private T answer;
	
	public T getAnswer() {
		return answer;
	}
	
	public void setAnswer(T answer) {
		this.answer = answer;
	}
	
	public Class<?> getType() {
		return answer.getClass();
	}
}
