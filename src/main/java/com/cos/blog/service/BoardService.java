package com.cos.blog.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional 
	public void 글쓰기(Board board, User user) { //title, content는 받아온다.     // count, user 조회수랑 유저정보는 강제로 넣어준다.   //id와 createTime은 자동
		board.setCount(0);  // count 넣기
		board.setUser(user);  
		boardRepository.save(board); 
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);  
	}  
	
	@Transactional(readOnly = true) 
	public Board 글상세보기(int id) {
		return boardRepository.findById(id) 
				.orElseThrow(() -> {
					 return new IllegalArgumentException("글 상세보기 실패:아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void  글삭제하기(int id) { 
		boardRepository.deleteById(id); 
	}
	
	@Transactional 
	public void 글수정하기(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> {
					 return new IllegalArgumentException("글 찾기 실패:아이디를 찾을 수 없습니다.");
				}); //영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
	}
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		//<ReplyRepository에 Navtive Query를 작성해서 아래가 다 필요없어졌다.>
//		User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
//			return new IllegalArgumentException("댓글 쓰기 실패 : 해당 유저를 찾을 수 없습니다.");
//		}); //영속화 완료
//
//		Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
//			return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
//		}); //영속화 완료
//		
//		Reply reply = Reply.builder()
//				.user(user)
//				.board(board)
//				.content(replySaveRequestDto.getContent())
//				.build();
		
		int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
		System.out.println("BoadrdService : "+result);
	}
	
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}

	
}
