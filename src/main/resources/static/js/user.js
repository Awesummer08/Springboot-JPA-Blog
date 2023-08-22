let index = {
	init:function() {
		$("#btn-save").on("click", ()=>{
			this.save();
		});
		
		$("#btn-update").on("click", ()=>{
			this.update();
		});
	},
	
	save:function(){
		//alert("user의 save함수 호출됨");
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		//console.log(data);
		
				//ajax호출시 default가 비동기 호출!
		$.ajax({
			//회원가입 수행 요청(100초 가정)
			type: "POST",
			url: "/auth/joinProc",  //메서드가 포스트니까 insert라는거 알잖아.
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"  //요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascriptObject로 변경
 		}).done(function(resp){
			if(resp.status === 500) { 
			alert("회원가입에 실패하였습니다.");				
			} else {
			alert("회원가입이 완료되었습니다.");
			location.href="/";
			}
		}).fail(function(){
			//수행 요청이 비정상이면 여기 실행			
			alert(JSON.stringify(error));
		});  //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert를 요청할거다.
	},
	
	
		update:function(){
		let data = {
			id:$("#id").val(),
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};


		$.ajax({
			type: "PUT",
			url: "/user",  
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"  
 		}).done(function(resp){
			alert("회원수정이 완료되었습니다.");
			console.log(resp);
			location.href="/";
		}).fail(function(){
			alert(JSON.stringify(error));
		}); 
	}
	
	
	
}

index.init();