package com.fiveand.auction.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fiveand.auction.board.vo.ProductFileVO;
import com.fiveand.auction.board.vo.ProductVO;
import com.fiveand.util.ConnectionFactory;

public class AuctionBoardDAO {

	public int selectProductNo() {
		String sql = " select seq_ftbl_product_pd_no.nextval from dual ";
		int pdNo = 0;

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			pdNo = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pdNo;
	}

	public void insertProduct(ProductVO product) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" insert into ftbl_product(pd_no, id, pd_name, hope_price, start_price, due_date, pd_simple_info, pd_info, c_no) ");
		sql.append(" values(?, ?, ?, ?, ?, ?, ?, ?, ? ) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, product.getPdNo());
			pstmt.setString(2, product.getId());
			pstmt.setString(3, product.getPdName());
			pstmt.setInt(4, product.getHopePrice());
			pstmt.setInt(5, product.getStartPrice());
			pstmt.setString(6, product.getDueDate());
			pstmt.setString(7, product.getPdSimpleInfo());
			pstmt.setString(8, product.getPdInfo());
			pstmt.setInt(9, product.getcNo());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertFile(ProductFileVO fileVO) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into ftbl_product_file( ");
		sql.append(" no, pd_no, file_ori_name, file_save_name, file_size ) ");
		sql.append(" values( seq_ftbl_product_file_no.nextval, ?, ?, ?, ?) ");

		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			pstmt.setInt(1, fileVO.getPdNo());
			pstmt.setString(2, fileVO.getFileOriName());
			pstmt.setString(3, fileVO.getFileSaveName());
			pstmt.setInt(4, fileVO.getFileSize());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addViewCnt(int pdNo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update ftbl_product ");
		sql.append(" set view_cnt = view_cnt+1 ");
		sql.append(" where pd_no = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, pdNo);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProductVO selectProductByNo(int pdNo) {
		ProductVO product = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" select  pd_no, id, pd_name, hope_price, start_price, ");
		sql.append(" 		to_char(reg_date, 'yyyy-mm-dd') reg_date, to_char(due_date, 'yyyy-mm-dd') due_date, ");
		sql.append(" 		pd_simple_info, pd_info, view_cnt, like_cnt, sug_cnt, category ");
		sql.append(" from ftbl_category c, ftbl_product p ");
		sql.append(" where c.c_no = p.c_no and pd_no = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, pdNo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				product = new ProductVO();
				product.setPdNo(rs.getInt("pd_no"));
				product.setId(rs.getString("id"));
				product.setPdName(rs.getString("pd_name"));
				product.setHopePrice(rs.getInt("hope_price"));
				product.setStartPrice(rs.getInt("start_price"));
				product.setRegDate(rs.getString("reg_date"));
				product.setDueDate(rs.getString("due_date"));
				product.setPdSimpleInfo(rs.getString("pd_simple_info"));
				product.setPdInfo(rs.getString("pd_info"));
				product.setViewCnt(rs.getInt("view_cnt"));
				product.setLikeCnt(rs.getInt("like_cnt"));
				product.setSugCnt(rs.getInt("sug_cnt"));
				product.setcName(rs.getString("category"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return product;
	}

	public List<ProductFileVO> selectFileByNo(int pdNo) {
		List<ProductFileVO> files = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select pd_no, file_ori_name, file_save_name, file_size ");
		sql.append(" from ftbl_product_file ");
		sql.append(" where pd_no = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, pdNo);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductFileVO file = new ProductFileVO();
				file.setPdNo(rs.getInt("pd_no"));
				file.setFileOriName(rs.getString("file_ori_name"));
				file.setFileSaveName(rs.getString("file_save_name"));
				file.setFileSize(rs.getInt("file_size"));
				files.add(file);
				System.out.println("세이브 파일명 : " + file.getFileSaveName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return files;
	}

	public void removeFileByNo(int pdNo) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from ftbl_product_file ");
		sql.append(" where pd_no = ? ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, pdNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeProduct(int pdNo) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ftbl_product ");
		sql.append("where pd_no = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, pdNo);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateProduct(ProductVO product) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update ftbl_product ");
		sql.append(" set pd_name = ?, pd_simple_info = ?, pd_info = ?, c_no = ? ");
		sql.append(" where pd_no = ? ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, product.getPdName());
			pstmt.setString(2, product.getPdSimpleInfo());
			pstmt.setString(3, product.getPdInfo());
			pstmt.setInt(4, product.getcNo());
			pstmt.setInt(5, product.getPdNo());
			
			pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Object> relatedList(String id) {
		List<Object> list = new ArrayList<Object>();
		//System.out.println("경매 올린 판매자 : " +id);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select rownum as row_num, board.* from( ");
		sql.append("select p.pd_no, p.id, p.pd_name, p.start_price, p.reg_date, to_char(p.due_date, 'mm-dd') as due_date, ");
		sql.append("p.c_no, p.like_cnt, p.view_cnt, c.category, f.file_save_name ");
		sql.append("from ftbl_product p,  ");
		sql.append("(select pd_no,  row_number() over(partition by pd_no order by pd_no) row_num, file_save_name from  ftbl_product_file) f, ");
		sql.append("ftbl_category c ");
		sql.append("where row_num=1 and p.pd_no = f.pd_no and p.c_no = c.c_no and p.id= ? ");
		sql.append("order by like_cnt desc) board) ");
		sql.append("where row_num <= 4 ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO productVO = new ProductVO();
				
				productVO.setPdNo(rs.getInt("pd_no"));
				productVO.setPdName(rs.getString("pd_name"));
				productVO.setStartPrice(rs.getInt("start_price"));
				productVO.setDueDate(rs.getString("due_date"));
				productVO.setcNo(rs.getInt("c_no"));
				productVO.setcName(rs.getString("category"));
				productVO.setLikeCnt(rs.getInt("like_cnt"));
				productVO.setViewCnt(rs.getInt("view_cnt"));
				productVO.setFileSaveName(rs.getString("file_save_name"));
			
				
				list.add(productVO);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		//System.out.println("판매자가 올린 리스트 : "+list);
		return list;
	}
}
