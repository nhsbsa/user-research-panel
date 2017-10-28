package uk.nhs.nhsbsa.research.userresearchapp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

@Controller
public class ParticipantController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParticipantController.class);
	
	private IParticipantService service;
	
	@Autowired
	public ParticipantController(IParticipantService service) {
		this.service = service;
	}
	
	@RequestMapping("/")
	public String entryPoint() {
		return "redirect:start";
	}
	
	@RequestMapping("/start")
	public String homepage() {
		return "home";
	}

	@RequestMapping(value = "/name", method = RequestMethod.GET)
	public ModelAndView name() {
		ModelAndView mv = new ModelAndView("participant/contact-name");
		mv.addObject(new ParticipantName());
		return mv;
	}
	
	@RequestMapping(value = "/name-submit", method = RequestMethod.POST)
	public String nameSubmit(@Valid @ModelAttribute ParticipantName model,
			HttpSession session) {
		
		ParticipantVO participant = new ParticipantVO();
		participant.setName(model.getName());
		session.setAttribute("participant", participant);
		
		return "redirect:contact-number";
	}
	
	@RequestMapping(value = "/contact-number", method = RequestMethod.GET)
	public ModelAndView telephone() {
		ModelAndView mv = new ModelAndView("participant/contact-telephone");
		mv.addObject(new ParticipantTelephone());
		return mv;
	}
	
	@RequestMapping(value = "/telephone-submit", method = RequestMethod.POST)
	public String telephoneSubmit(@Valid @ModelAttribute ParticipantTelephone model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute("participant");
		participant.setTelephone(model.getTelephone());
		session.setAttribute("participant", participant);
		return "redirect:email";
	}
	
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ModelAndView email() {
		ModelAndView mv = new ModelAndView("participant/contact-email");
		mv.addObject(new ParticipantEmail());
		return mv;
	}
	
	@RequestMapping(value = "/email-submit", method = RequestMethod.POST)
	public String emailSubmit(@Valid @ModelAttribute ParticipantEmail model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute("participant");
		participant.setEmail(model.getEmail());
		session.setAttribute("participant", participant);
		return "redirect:address";
	}
	
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	public ModelAndView address() {
		ModelAndView mv = new ModelAndView("participant/contact-address");
		mv.addObject(new ParticipantAddress());
		return mv;
	}
	
	@RequestMapping(value = "/address-submit", method = RequestMethod.POST)
	public String addressSubmit(@Valid @ModelAttribute ParticipantAddress model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute("participant");
		participant.setPostcode(model.getPostcode());
		session.setAttribute("participant", participant);
		return "redirect:age";
	}
	
	@RequestMapping(value = "/age", method = RequestMethod.GET)
	public ModelAndView age() {
		ModelAndView mv = new ModelAndView("participant/contact-age");
		mv.addObject(new ParticipantAge());
		return mv;
	}
	
	@RequestMapping(value = "/age-submit", method = RequestMethod.POST)
	public String ageSubmit(@Valid @ModelAttribute ParticipantAge model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute("participant");
		participant.setAge(model.getAge());
		session.setAttribute("participant", participant);
		return "redirect:participant-consent";
	}
	
	@RequestMapping(value = "/participant-consent", method = RequestMethod.GET)
	public ModelAndView consent() {
		ModelAndView mv = new ModelAndView("consent");
		mv.addObject(new ParticipantConsent());
		return mv;
	}
	
	@RequestMapping(value = "/consent-submit", method = RequestMethod.POST)
	public String consentSubmit(@Valid @ModelAttribute ParticipantConsent model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute("participant");
		service.register(participant);
		return "redirect:end";
	}
	
	@RequestMapping("/end")
	public String complete() {
		return "complete";
	}
}
