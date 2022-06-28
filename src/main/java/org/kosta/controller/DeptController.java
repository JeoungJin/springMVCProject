package org.kosta.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.dto.DeptDTO;
import com.kosta.myapp.model.DeptService;

@Controller
public class DeptController {
	Logger Logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired //A가 B를 사용하면 A는 B를 의존한다. 의존관계를 없애고자한다.Spring이... B를 생성해서 inject하도록한다. 
	DeptService dService;
	
	@RequestMapping(value = "/dept/deptList.do", method = RequestMethod.GET)
	public void deptList(Model model, HttpServletRequest request) {		
		Logger.debug("debug부서의 모든 데이터를 조회합니다.");
		Logger.info("info부서의 모든 데이터를 조회합니다.");
		Logger.warn("warn부서의 모든 데이터를 조회합니다.");
		Logger.error("error부서의 모든 데이터를 조회합니다.");
		List<DeptDTO> dlist = dService.selectAll();
		
		Map<String, ?> flashMap =   
		         RequestContextUtils.getInputFlashMap(request);
		String resultMessage = null;
		if(flashMap !=null ) {
			resultMessage = (String)flashMap.get("resultMessage");
		}
		model.addAttribute("resultMessage", resultMessage);	
		model.addAttribute("deptlist", dlist);
	}
	
	@RequestMapping(value = "/dept/deptDelete.do", method = RequestMethod.GET)
	public String deptDelete(int deptid ,RedirectAttributes redirectAttr) {
		int result = dService.deptDelete(deptid);
		Logger.info(deptid + "를" + result +  "건 삭제합니다.");
		redirectAttr.addFlashAttribute("resultMessage", result +  "건 삭제");
		return "redirect:/dept/deptList.do";
	}
	@RequestMapping(value = "/dept/deptInsert.do", method = RequestMethod.GET)
	public String deptInsertGet() {		
		Logger.info("입력페이지 보여주기.");
		return "dept/deptInsert";
	}
	
	@RequestMapping(value = "/dept/deptInsert.do", method = RequestMethod.POST)
	public String deptInsertPost(DeptDTO dept,RedirectAttributes redirectAttr) {
		Logger.info("입력:" +  dept.toString());		 
		int result = dService.deptInsert(dept);
		Logger.info("입력:" + result + "건 입력");
		redirectAttr.addFlashAttribute("resultMessage", result +  "건 입력");
		return "redirect:/dept/deptList.do";
	}
	
	@RequestMapping(value = "/dept/deptUpdate.do", method = RequestMethod.GET)
	public String deptUpdateGet(int deptid, Model model) {
		//상세보기는 부서번호로 정보를 조회한후 page보이기
		Logger.info(deptid + " : 부서정보 상세보기");
		DeptDTO deptvo = dService.selectById(deptid);
		model.addAttribute("dept", deptvo);
		return "dept/deptDetail";
	}
	@RequestMapping(value = "/dept/deptUpdate.do", method = RequestMethod.POST)
	public String deptUpdatePost(DeptDTO dept, RedirectAttributes redirectAttr) { //new DeptDTO();dept.setDepartment_id(request.getParameter("department_id")
		Logger.info("DB에 수정:" + dept.toString());
		int result =dService.deptUpdate(dept);
		Logger.info("수정:" + result + "건 수정");
		redirectAttr.addFlashAttribute("resultMessage", result +  "건 수정");
		return "redirect:/dept/deptList.do";
	}
	
	
	
	
	
	
}
