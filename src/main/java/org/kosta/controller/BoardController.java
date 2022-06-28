package org.kosta.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.dto.BoardDTO;
import com.kosta.myapp.model.BoardService;
@Controller
@RequestMapping("/board") 
public class BoardController {
	@Autowired
	BoardService bService;
	
	
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	
	//Spring3버젼에서 요청문서의 body값을 전달하고자한다. 
	@RequestMapping("/test1")
	@ResponseBody
	public String test1() {
		return "Hello~~~~";
	}
	 
	
	
	//조회
	@RequestMapping("/boardList.do")
	public String boardRetrieve(Model model, HttpServletRequest request) {
		Map<String, ?> flashMap =   
		         RequestContextUtils.getInputFlashMap(request);
		if(flashMap!=null) {
			String message = (String)flashMap.get("resultMessage");
			model.addAttribute("resultMessage",message);
		}
		List<BoardDTO> blist = bService.selectAll();
		logger.debug("조회:" + blist.size() + "건");
		logger.info("조회:" + blist.size() + "건");
		logger.warn("조회:" + blist.size() + "건");
		logger.error("조회:" + blist.size() + "건");
		model.addAttribute("boardDatas", blist);
		return "board/boardList";
	}
	//입력
	@RequestMapping(value = "/boardInsert.do", method = RequestMethod.GET)
	public String boardInsertView() {
		logger.info("입력Form보이기 ");
		return "board/boardInsert";
	}
	@RequestMapping(value = "/boardInsert.do", method = RequestMethod.POST)
	public String boardInsertDB(BoardDTO board, RedirectAttributes attr, HttpServletRequest request) {
		logger.info("입력:" + board.toString());
		
		MultipartFile uploadfile = board.getPhotos();
		
		if (uploadfile == null) return "redirect:/board/boardList.do"; 
		String path = request.getSession().getServletContext().getRealPath("/resources/uploads"); 
		String fileName = uploadfile.getOriginalFilename(); 
		String fpath = path +"\\" + fileName ; 
		board.setPic(fileName);
		
		try {
			File file = new File(fpath); 
			uploadfile.transferTo(file); 
		} catch (IOException e) { e.printStackTrace(); }  

	
		int result = bService.insert(board);
		attr.addFlashAttribute("resultMessage", result + "건 입력");
		return "redirect:/board/boardList.do";
	}
	//수정
	@RequestMapping(value = "/boardDetail.do", method = RequestMethod.GET)
	public String boardDetailView(int boardid, Model model) {
		logger.info("상세보기:"+ boardid);
		model.addAttribute("board", bService.selectById(boardid));
		return "board/boardDetail"; //page로 forward된다. 
	}
	@RequestMapping(value = "/boardUpdate.do", method = RequestMethod.POST)
	public String boardDetailDB(BoardDTO board, RedirectAttributes attr) {
		logger.info("수정:"+ board);
		int result = bService.update(board);
		attr.addFlashAttribute("resultMessage", board.getBno()+"번이 수정됨");
		return "redirect:/board/boardList.do"; //재요청지정 
	}
	//삭제
	@RequestMapping(value = "/boardDelete.do", method = RequestMethod.GET)
	public String boardDelete(int bno, RedirectAttributes attr) {
		logger.info("삭제:"+ bno);
		int result = bService.delete(bno);
		attr.addFlashAttribute("resultMessage", bno + "번이 삭제됨");
		return "redirect:/board/boardList.do";
	}
	
	//title로 조회
	@RequestMapping("/titleSearch.do")
	public String titleSearch(String title, Model model) {
 
		List<BoardDTO> blist = bService.selectByTitle(title==null||title==""?"":"%"+title+"%");
		model.addAttribute("boardDatas", blist);
		return "board/boardListFrame";
	}
	//writer로 조회
	@RequestMapping("/writerSearch.do")
	public String writerSearch(int writer, Model model) {
		List<BoardDTO> blist = bService.selectByWriter(writer);
		model.addAttribute("boardDatas", blist);
		return "board/boardListFrame";
	}
	
	//date로 조회하기 
	@RequestMapping("/dateSearch.do")
	public String dateSearch(Date sdate, Date edate, Model model) {
		List<BoardDTO> blist = bService.selectByRegDate(sdate, edate);
		model.addAttribute("boardDatas", blist);
		return "board/boardListFrame";
	}
	
	
}








