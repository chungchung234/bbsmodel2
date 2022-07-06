package com.example.model2.controller;

import com.example.model2.dao.BbsDao;
import com.example.model2.dto.BbsDto;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BbsController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String param = req.getParameter("param");
		
		if(param.equals("bbslist")) {
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spage = req.getParameter("pageNumber");
			int page = 0;
			if(spage != null && !spage.equals("")) {
				page = Integer.parseInt(spage);
			}
			if(choice == null) {
				choice = "";
			}
			if(search == null) {
				search = "";
			}
			
			BbsDao dao = BbsDao.getInstance();
			List<BbsDto> list = dao.getBbsPagelist(choice, search, page);
			req.setAttribute("bbslist", list);
			
			int len = dao.getAllBbs(choice, search);
			int bbsPage = len / 10;		// 23 -> 2
			if((len % 10) > 0){
				bbsPage = bbsPage + 1;
			}
			req.setAttribute("bbsPage", bbsPage);
			req.setAttribute("pageNumber", page);
			
			forward("bbs/bbslist.jsp", req, resp);
		}
		else if(param.equals("bbswrite")) {
			resp.sendRedirect("bbs/bbswrite.jsp");
		}
		else if(param.equals("bbswriteAf")) {
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			BbsDao dao = BbsDao.getInstance();
			boolean isS = dao.writeBbs(new BbsDto(id, title, content));
			if(!isS) {
				resp.sendRedirect("bbs/bbswrite.jsp");
			}
			
			resp.sendRedirect("bbs?param=bbslist");
		}
		else if(param.equals("bbsdetail")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			
			BbsDao dao = BbsDao.getInstance();
			
			dao.readcount(seq);		// 조회수 늘리기
			
			BbsDto dto = dao.getBbs(seq);			
			
			req.setAttribute("bbs", dto);
			
			forward("bbs/bbsdetail.jsp", req, resp);
		}
		else if(param.equals("bbsupdate")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq.trim());

			BbsDao dao = BbsDao.getInstance();
			BbsDto dto = dao.getBbs(seq);
			
			req.setAttribute("bbs", dto);
			
			forward("bbs/bbsupdate.jsp", req, resp);
		}
		else if(param.equals("bbsupdateAf")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq.trim());

			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			BbsDao dao = BbsDao.getInstance();			
			boolean isS = dao.updateBbs(seq, title, content);				
			if(!isS) {
				resp.sendRedirect("bbs?param=bbsupdate&seq=" + sseq);
			}
			else {
				resp.sendRedirect("bbs?param=bbslist");
			}
		}
		else if(param.equals("bbsdelete")) {
			int seq = Integer.parseInt( req.getParameter("seq") );
			System.out.println("seq:" + seq);

			BbsDao dao = BbsDao.getInstance();
			dao.deleteBbs(seq);
			
			resp.sendRedirect("bbs?param=bbslist");
		}
		else if(param.equals("answer")) {
			int seq = Integer.parseInt( req.getParameter("seq") );
			BbsDto dto = BbsDao.getInstance().getBbs(seq);
			
			req.setAttribute("bbs", dto);
			
			forward("bbs/answer.jsp", req, resp);
		}
		else if(param.equals("answerAf")) {
			int seq = Integer.parseInt( req.getParameter("seq") );
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			BbsDao dao = BbsDao.getInstance();
			boolean isS = dao.answer(seq, new BbsDto(id, title, content));
			if(!isS){
				resp.sendRedirect("bbs?param=answer&seq=" + seq);
			}else {
				resp.sendRedirect("bbs?param=bbslist");
			}
		}
	}
	
	public void forward(String arg, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(arg);
		dispatch.forward(req, resp);			
	}

}
