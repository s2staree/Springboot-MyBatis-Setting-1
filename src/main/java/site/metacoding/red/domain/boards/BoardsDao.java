package site.metacoding.red.domain.boards;

import java.util.List;

import site.metacoding.red.domain.users.Users;

public interface BoardsDao {
//	public void insert(WriteDto joinDto);
	public Users findById(Integer id);
	public List<Users> findAll();
	public void update(Users users);
	public void delete(Integer id);
}
