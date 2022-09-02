package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor
@RestController
public class UsersController {

	private final UsersDao usersDao;
	
	@GetMapping("/users/{id}")
	public RespDto<?> getUsers(@PathVariable Integer id) {
		return new RespDto<>(1, "성공", usersDao.findById(id));
	}
	
	@GetMapping("/users")
	public RespDto<?> getUsersList(){
		return new RespDto<>(1, "성공", usersDao.findAll());
	}
	
	@PostMapping("/users")
	public RespDto<?> insert(JoinDto joinDto) {
		usersDao.insert(joinDto);
		return new RespDto<>(1, "회원가입완료", null);
	}
	
	@PutMapping("/users/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
		
		// 1. id로 select 하자 (영속화)
		Users usersPS = usersDao.findById(id);
		
		// 2. 변경
		usersPS.전체수정(updateDto);
		
		// 3. 영속화된 오브젝트로 update 하기 (전체 update)
		usersDao.update(usersPS);
		
		return new RespDto<>(1, "회원수정완료", null);
	}
	
	@PutMapping("/users/{id}/password")	// users의 id의 password를 수정하겠다
	public RespDto<?> updatePassword(@PathVariable Integer id, String password){
		
		// 1. 영속화
		Users usersPS = usersDao.findById(id);
		
		// 2. 부분변경
		usersPS.패스워드수정(password);
		
		// 3. 전체 update
		usersDao.update(usersPS);
		
		return new RespDto<>(1, "회원패스워드수정완료", null);
	}
	
	@DeleteMapping("/users/{id}")
	public RespDto<?> delete(@PathVariable Integer id) {
		usersDao.delete(id);
		return new RespDto<>(1, "회원삭제완료", null);
	}
}
