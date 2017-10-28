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
import uk.nhs.nhsbsa.research.userresearchapp.vo.SessionAttributes;

@Controller
public class ScreeningController {

	private static final String PATH_SCREENING_PURCHASED_PPC = "screening/purchased-ppc";
	private static final String PATH_SCREENING_PURCHASED_PPC_METHOD = "screening/purchased-ppc-method";
	private static final String PATH_SCREENING_PURCHASED_PPC_DURATION = "screening/purchased-ppc-duration";

	@RequestMapping(value = ResearchPaths.PATH_SCREENING_PPC_PREVIOUS, method = RequestMethod.GET)
	public ModelAndView ppc() {
		ModelAndView mv = new ModelAndView(PATH_SCREENING_PURCHASED_PPC);
		mv.addObject(new ScreeningPurchasedPPC());
		return mv;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_PPC_PAST_SUBMIT, method = RequestMethod.POST)
	public String screeningPPCPurchasedPreviously(@Valid @ModelAttribute ScreeningPurchasedPPC model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute(SessionAttributes.SESSION_PARTICIPANT);
		
		ScreeningQuestion<Boolean> previousPpc = new ScreeningQuestion<>();
		previousPpc.setAnswer(model.isPreviousPpc());
		
		participant.getScreening().add(previousPpc);
		
		session.setAttribute(SessionAttributes.SESSION_PARTICIPANT, participant);
		return PPCScreeningRouter.getNextScreenInFlow(model);
	}
	
	@RequestMapping(value = ResearchPaths.PATH_SCREENING_PPC_PREVIOUS_METHOD, 
			method = RequestMethod.GET)
	public ModelAndView ppcMethod() {
		ModelAndView mv = new ModelAndView(PATH_SCREENING_PURCHASED_PPC_METHOD);
		mv.addObject(new ScreeningPurchasedMethodPPC());
		return mv;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_PPC_PAST_METHOD_SUBMIT, method = RequestMethod.POST)
	public String screeningPPCPurchasedPreviousMethod(@Valid @ModelAttribute ScreeningPurchasedMethodPPC model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute(SessionAttributes.SESSION_PARTICIPANT);
		
		ScreeningQuestion<String> ppcMethod = new ScreeningQuestion<>();
		ppcMethod.setAnswer(model.getMethod());
		
		participant.getScreening().add(ppcMethod);
		
		session.setAttribute(SessionAttributes.SESSION_PARTICIPANT, participant);
		return ResearchPaths.PATH_REDIRECT + ResearchPaths.PATH_SCREENING_PPC_PREVIOUS_DURATION;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_SCREENING_PPC_PREVIOUS_DURATION, method = RequestMethod.GET)
	public ModelAndView ppcDuration() {
		ModelAndView mv = new ModelAndView(PATH_SCREENING_PURCHASED_PPC_DURATION);
		mv.addObject(new ScreeningPurchasedDurationPPC());
		return mv;
	}
	
	@RequestMapping(value = ResearchPaths.PATH_PPC_PAST_DURATION_SUBMIT, method = RequestMethod.POST)
	public String screeningPPCPurchasedPreviousDuration(@Valid @ModelAttribute ScreeningPurchasedDurationPPC model,
			HttpSession session) {
		ParticipantVO participant = (ParticipantVO) session.getAttribute(SessionAttributes.SESSION_PARTICIPANT);
		
		ScreeningQuestion<Integer> ppcMethod = new ScreeningQuestion<>();
		ppcMethod.setAnswer(model.getDuration());
		
		participant.getScreening().add(ppcMethod);
		
		session.setAttribute(SessionAttributes.SESSION_PARTICIPANT, participant);
		return ResearchPaths.PATH_REDIRECT + ResearchPaths.PATH_PARTICIPANT_CONSENT;
	}
}
