package uk.nhs.nhsbsa.research.userresearchapp.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;

@SuppressWarnings("serial")
@Scope("session")
public class ParticipantVO implements Serializable {

	private String name;
	
	private String telephone;
	
	private String age;
	
	private String email;
	
	private String postcode;
	
	private List<ScreeningQuestion> screening;
	
	public ParticipantVO() {
		screening = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public List<ScreeningQuestion> getScreening() {
		return screening;
	}

	@Override
	public String toString() {
		return "ParticipantVO [name=" + name + ", telephone=" + telephone + ", age=" + age + ", email=" + email
				+ ", postcode=" + postcode + ", screening=" + screening + "]";
	}
}
