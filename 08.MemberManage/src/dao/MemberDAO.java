package dao;

import model.Member;

public interface MemberDAO {
	
	Member selectById(String id);
}
