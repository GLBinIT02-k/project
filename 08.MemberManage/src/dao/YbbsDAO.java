package dao;

import java.util.List;

import model.Ybbs;

public interface YbbsDAO {
	
	List<Ybbs> selectAll();
	Ybbs selectByNo(int no);
	boolean Insert(Ybbs ybbs);
	
	void insertReply (Ybbs ybbs);
	void update(Ybbs ybbs);
	void delete(int no);
	void updateVisited(int no);
}
