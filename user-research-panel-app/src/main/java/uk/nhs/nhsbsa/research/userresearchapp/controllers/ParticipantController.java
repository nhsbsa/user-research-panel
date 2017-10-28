package uk.nhs.nhsbsa.research.userresearchapp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.nhs.nhsbsa.research.userresearchapp.forms.ParticipantAddress;
import uk.nhs.nhsbsa.research.userresearchapp.forms.ParticipantAge;
import uk.nhs.nhsbsa.research.userresearchapp.forms.ParticipantConsent;
import uk.nhs.nhsbsa.research.userresearchapp.forms.ParticipantEmail;
import uk.nhs.nhsbsa.research.userresearchapp.forms.ParticipantName;
import uk.nhs.nhsbsa.research.userresearchapp.forms.ParticipantTelephone;
import uk.nhs.nhsbsa.research.userresearchapp.service.IParticipantService;
import uk.nhs.nhsbsa.research.userresearchapp.vo.ParticipantVO;
import uk.nhs.nhsbsa.research.userresearchapp.vo.SessionAttributes;

@Controller
public class ParticipantController {
	
	private static final String PAGE_COMPLETE = "complete";
	private static final String PAGE_CONSENT = "consent";
	private static final String PAGE_PARTICIPANT_CONTACT_AGE = "participant/contact-age";
	private static final String PAGE_PARTICIPANT_CONTACT_ADDRESS = "participant/contact-address";
	private static final String PAGE_PARTICIPANT_CONTACT_NAME = "participant/contact-name";
	private static final String PAGE_HOME = "home";
	private static final String PAGE_PARTICIPANT_CONTACT_TELEPHONE = "participant/contact-telephone";
	private static final String PAGE_PARTICIPANT_CONTACT_EMAIL = "participant/contact-email";
	
	private IParticipantService service;
	
	@Autowired
	public ParticipantController(IParticipantService service) {
		this.service = service;
	}
	
	@RequestMapping(ResearchPaths.PATH_ROOT)
	public String entryPoint() {
		return ResearchPaths.PATH_REDIRECT + ResearchPaths.PATH_START;
	}
	
	@RequestMapping(ResearchPaths.PATH_START)
	public String homepage(HttpSession session) {
		ParticipantVO participant = new ParticipantVO();
		session.setAttribute(SessionAttributes.SESSION_PARTICIPANT, participant);
		return PAGE_HOME;
	}
	
	@RequestMapping(ResearchPaths.PATH_START + "/{system}")
	public String homepage(@PathVariable("system") String system,
			HttpSession session) {
		ParticipantVO participant = new ParticipantVO();
	
		if(StringUtils.isNotBlank(system)) {
			participant.setSystem(system.toLowerCase());
		}
		
		session.setAttribute(SessionAttributes.SESSION_PARTICIPANT, participant);
		return PAGE_HOME;
	}

	@RequestMapping(value = ResearchPaths.PATH_NAME, method = RequestMethod.GET)
	public ModelAndView name() {
		ModelAndView mv = new ModelAndView(PAGE_PARTICIPANT_CONTACT_NAME);
		mv.addObject(new ParticipantName());
		return mv;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_NAME_SUBMIT, method = RequestMethod.POST)
	public String nameSubmit(@Valid @ModelAttribute ParticipantName model,
			HttpSession session) {
		
		ParticipantVO participant = (ParticipantVO) session.getAttribute(SessionAttributes.SESSION_PARTICIPANT);
		participant.setName(model.getName());
		session.setAttribute(SessionAttributes.SESSION_PARTICIPANT, participant);
		
		return ResearchPaths.PATH_REDIRECT + ResearchPaths.PATH_CONTACT_NUMBER;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_CONTACT_NUMBER, method = RequestMethod.GET)
	public ModelAndView telephone() {
		ModelAndView mv = new ModelAndView(PAGE_PARTICIPANT_CONTACT_TELEPHONE);
		mv.addObject(new ParticipantTelephone());
		return mv;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_TELEPHONE_SUBMIT, method = RequestMethod.POST)
	public String telephoneSubmit(@Valid @ModelAttribute ParticipantTelephone model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute(SessionAttributes.SESSION_PARTICIPANT);
		participant.setTelephone(model.getTelephone());
		session.setAttribute(SessionAttributes.SESSION_PARTICIPANT, participant);
		return ResearchPaths.PATH_REDIRECT + ResearchPaths.PATH_EMAIL;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_EMAIL, method = RequestMethod.GET)
	public ModelAndView email() {
		ModelAndView mv = new ModelAndView(PAGE_PARTICIPANT_CONTACT_EMAIL);
		mv.addObject(new ParticipantEmail());
		return mv;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_EMAIL_SUBMIT, method = RequestMethod.POST)
	public String emailSubmit(@Valid @ModelAttribute ParticipantEmail model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute(SessionAttributes.SESSION_PARTICIPANT);
		participant.setEmail(model.getEmail());
		session.setAttribute(SessionAttributes.SESSION_PARTICIPANT, participant);
		return ResearchPaths.PATH_REDIRECT + ResearchPaths.PATH_ADDRESS;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_ADDRESS, method = RequestMethod.GET)
	public ModelAndView address() {
		ModelAndView mv = new ModelAndView(PAGE_PARTICIPANT_CONTACT_ADDRESS);
		mv.addObject(new ParticipantAddress());
		return mv;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_ADDRESS_SUBMIT, method = RequestMethod.POST)
	public String addressSubmit(@Valid @ModelAttribute ParticipantAddress model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute(SessionAttributes.SESSION_PARTICIPANT);
		participant.setPostcode(model.getPostcode());
		session.setAttribute(SessionAttributes.SESSION_PARTICIPANT, participant);
		return ResearchPaths.PATH_REDIRECT + ResearchPaths.PATH_AGE;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_AGE, method = RequestMethod.GET)
	public ModelAndView age() {
		ModelAndView mv = new ModelAndView(PAGE_PARTICIPANT_CONTACT_AGE);
		mv.addObject(new ParticipantAge());
		return mv;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_AGE_SUBMIT, method = RequestMethod.POST)
	public String ageSubmit(@Valid @ModelAttribute ParticipantAge model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute(SessionAttributes.SESSION_PARTICIPANT);		
		participant.setAge(model.getAge());
		session.setAttribute(SessionAttributes.SESSION_PARTICIPANT, participant);
		return PPCScreeningRouter.getNextScreenInFlow(participant);
	}
	
	@RequestMapping(value = ResearchPaths.PATH_PARTICIPANT_CONSENT, method = RequestMethod.GET)
	public ModelAndView consent() {
		ModelAndView mv = new ModelAndView(PAGE_CONSENT);
		mv.addObject(new ParticipantConsent());
		return mv;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_CONSENT_SUBMIT, method = RequestMethod.POST)
	public String consentSubmit(@Valid @ModelAttribute ParticipantConsent model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute(SessionAttributes.SESSION_PARTICIPANT);
		service.register(participant);
		return ResearchPaths.PATH_REDIRECT + ResearchPaths.PATH_END;
	}
	
	@RequestMapping(ResearchPaths.PATH_END)
	public String complete() {
		return PAGE_COMPLETE;
	}
}
