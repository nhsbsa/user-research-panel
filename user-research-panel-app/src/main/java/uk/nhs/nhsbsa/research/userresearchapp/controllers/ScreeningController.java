package uk.nhs.nhsbsa.research.userresearchapp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.nhs.nhsbsa.research.userresearchapp.forms.ScreeningPurchasedDurationPPC;
import uk.nhs.nhsbsa.research.userresearchapp.forms.ScreeningPurchasedMethodPPC;
import uk.nhs.nhsbsa.research.userresearchapp.forms.ScreeningPurchasedPPC;
import uk.nhs.nhsbsa.research.userresearchapp.vo.ParticipantVO;
import uk.nhs.nhsbsa.research.userresearchapp.vo.ScreeningQuestion;

@Controller
public class ScreeningController {

	@RequestMapping(value = "/screening-ppc-previously", method = RequestMethod.GET)
	public ModelAndView ppc() {
		ModelAndView mv = new ModelAndView("screening/purchased-ppc");
		mv.addObject(new ScreeningPurchasedPPC());
		return mv;
	}
	
	@RequestMapping(value = "/ppc-past-submit", method = RequestMethod.POST)
	public String screeningPPCPurchasedPreviously(@Valid @ModelAttribute ScreeningPurchasedPPC model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute("participant");
		
		ScreeningQuestion<Boolean> previousPpc = new ScreeningQuestion<>();
		previousPpc.setAnswer(model.isPreviousPpc());
		
		participant.getScreening().add(previousPpc);
		
		String page;
		if(model.isPreviousPpc()) {
		    page = "redirect:screening-ppc-previous-method";
		}
		else {
			page = "redirect:participant-consent";
		}
		
		session.setAttribute("participant", participant);
		return page;
	}
	
	@RequestMapping(value = "/screening-ppc-previous-method", method = RequestMethod.GET)
	public ModelAndView ppcMethod() {
		ModelAndView mv = new ModelAndView("screening/purchased-ppc-method");
		mv.addObject(new ScreeningPurchasedMethodPPC());
		return mv;
	}
	
	@RequestMapping(value = "/ppc-past-method-submit", method = RequestMethod.POST)
	public String screeningPPCPurchasedPreviousMethod(@Valid @ModelAttribute ScreeningPurchasedMethodPPC model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute("participant");
		
		ScreeningQuestion<String> ppcMethod = new ScreeningQuestion<>();
		ppcMethod.setAnswer(model.getMethod());
		
		participant.getScreening().add(ppcMethod);
		
		session.setAttribute("participant", participant);
		return "redirect:screening-ppc-previous-duration";
	}
	
	@RequestMapping(value = "/screening-ppc-previous-duration", method = RequestMethod.GET)
	public ModelAndView ppcDuration() {
		ModelAndView mv = new ModelAndView("screening/purchased-ppc-duration");
		mv.addObject(new ScreeningPurchasedDurationPPC());
		return mv;
	}
	
	@RequestMapping(value = "/ppc-past-duration-submit", method = RequestMethod.POST)
	public String screeningPPCPurchasedPreviousDuration(@Valid @ModelAttribute ScreeningPurchasedDurationPPC model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute("participant");
		
		ScreeningQuestion<Integer> ppcMethod = new ScreeningQuestion<>();
		ppcMethod.setAnswer(model.getDuration());
		
		participant.getScreening().add(ppcMethod);
		
		session.setAttribute("participant", participant);
		return "redirect:participant-consent";
	}
}
