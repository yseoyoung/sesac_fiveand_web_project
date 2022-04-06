package com.fiveand.auction.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.fiveand.auction.board.service.AuctionBoardService;
import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.controller.Controller;
import com.fiveand.util.SesacFileNamePolicy;
import com.oreilly.servlet.MultipartRequest;

public class BoardWriteController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");

		String savePath = request.getRealPath("/upload");

		int sizeLimit = 5000 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new SesacFileNamePolicy());
		

		String pdName = multi.getParameter("pdName"); // 제품명
		String id = multi.getParameter("id"); // 등록자 아이디
		int hopePrice = Integer.parseInt(multi.getParameter("hopePrice").trim()); // 희망가
		String pdSimpleInfo = multi.getParameter("pdSimpleInfo"); // 한줄정보
		int startPrice = Integer.parseInt(multi.getParameter("startPrice").trim()); // 시작가
		String dueDate = multi.getParameter("dueDate"); // 마감일
		String pdInfo = multi.getParameter("pdInfo").trim(); // 상세정보
		//System.out.println(multi.getParameter("cNo"));
		int cNo = Integer.parseInt(multi.getParameter("cNo")); // 카테고리수
		
		
		ProductVO product = new ProductVO();
		product.setPdName(pdName);
		product.setId(id);
		product.setHopePrice(hopePrice);
		product.setPdSimpleInfo(pdSimpleInfo);
		product.setStartPrice(startPrice);
		product.setDueDate(dueDate);
		product.setPdInfo(pdInfo);
		product.setcNo(cNo);

		List<ProductFileVO> fileList = new ArrayList<>();
		Enumeration<String> files = multi.getFileNames();

		while (files.hasMoreElements()) {

			String fileName = files.nextElement();

			File file = multi.getFile(fileName);

			if (file != null) {

				String fileOriName = multi.getOriginalFileName(fileName);

				String fileSaveName = multi.getFilesystemName(fileName);

				int fileSize = (int) file.length();
				
				ProductFileVO fileVO = new ProductFileVO();
				fileVO.setFileOriName(fileOriName);
				fileVO.setFileSaveName(fileSaveName);
				fileVO.setFileSize(fileSize);

				fileList.add(fileVO);
			}
		}

		AuctionBoardService service = new AuctionBoardService();

		int no = service.insertBoard(product, fileList);


		System.out.println("--BoardWriteController 등록한 게시글 정보--");
		System.out.println("pdName : " + pdName);
		System.out.println("pdId : " + id);
		System.out.println("hopePrice : " + hopePrice);
		System.out.println("pdSimpleInfo : " + pdSimpleInfo);
		System.out.println("startPrice : " + startPrice);
		System.out.println("dueDate : " + dueDate);
		System.out.println("pdInfo : " + pdInfo);
		System.out.println("cNo : " + cNo);
		System.out.println("-----------------");
		//return "redirect:/auction/detail.do?pdNo="+pdNo;
		return "redirect:/auction/detail.do?no="+no;
	}

}
