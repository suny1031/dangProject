// JS 전역변수로 설정
	let idCheckFlg = false;
	
// 회원가입시에 기존에 있는 아이디인지 확인을 위해 필요한 메소드
	let idCheck = () => {
		
		let headerObj = new Headers();
		headerObj.append('content-type', "application/x-www-form-urlencoded");
		
		
		// 사용자가 입력한 아이디값을 받아서
		let userId = id.value; //id가 id인 데이터의 value값
		let idCheck = document.querySelector('#idCheck');
		let url = "/user/idcheck.do"
		
		if(userId){ // true일때
			fetch(url,{
				method: "post",
				headers: headerObj,
				body: "userId=" + userId
				
			}).then(response => response.text()) // then해주면 응답(response)이 넘어옴, 바로 return
			  .then((message)=>{ // message가 넘어올 것
				if(message == 'available'){
					idCheckFlg = true;
					idCheck.innerHTML = '사용 가능한 아이디 입니다.';
				} else {
					idCheckFlg = false;
					idCheck.innerHTML = '사용 불가능한 아이디 입니다.';
				}				
			  }).catch(error => {
				 
				  error.alertMessage();
			  })
		} else {
			alert('아이디를 입력하지 않았습니다.');	
		}
	}

	
	
// 아이디체크와 비밀번호 조합이 잘되었는지 확인하고 비밀번호가 서로 일치하는지 확인하는 js
	if(document.querySelector('#form_join') != null){
		  document.querySelector('#form_join').addEventListener('submit',(e) => {
			     //  요소의 아이디로 엘리먼트 객체 호출 가능(웹표준이 아님)    
			      if(!idCheckFlg){
			         alert("아이디 중복검사를 통과하지 못했습니다.");
			         id.value = "";
			         e.preventDefault();
			      }
			      
			      let password = pw.value;
			      let regExp = /^(?!.*[ㄱ-힣])(?=.*\W)(?=.*\d)(?=.*[a-zA-Z])(?=.{8,})/;
			     
			      if(!(regExp.test(password))){
			         //form의 데이터 전송을 막음
			         e.preventDefault();
			         pw_confirm.innerHTML = '비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상이어야 합니다.';
			         pw.value = '';
			      } else {
			    	  
			    	  let firstPw =  pw.value;
					  let secondPw = checkpw.value;
					  //비밀번호 double check 메소드
					  if(firstPw != secondPw){
						
							document.querySelector("#pw_confirm").innerHTML = '비밀번호가 맞지 않습니다.';
							checkpw.value= ""; //pw의 value값 비워주기
							e.preventDefault(); //데이터전송 막기
						}
				   
			      }
			      
			
			   }); 
	}
	
	 

	
	
	
	
//member 로그인시 사용할 메소드
	let login = () => {
		let paramObj = new Object();
		paramObj.id = id.value;
		paramObj.pw = pw.value;
		
		let headerObj = new Headers();
		headerObj.append("content-type", "application/x-www-form-urlencoded");
		
	
		fetch(urlToLogin, { /* 해당 url로 아래의 객체를 포함하여 통신요청 */
			method:"post",
			headers:headerObj,
			body:"data="+JSON.stringify(paramObj) /* json으로 문자열로 전환하여 body에 저장 */
		
		}).then(response => { /* 200번대 코드가 넘어오면 ok => true */
			
			if(response.ok){
				return response.text(); /*controller 의 loginimpl에서 text값을 getwriter() */	
			}
			throw new AsyncPageError(response.text()); /* 200번코드가 아니면 catch 문으로 ! */
			
		}).then(text => { /* 위의 text() 값이 들어온다.*/
			console.dir(text);
			if(text == 'fail'){
				alert('아이디나 비밀번호를 확인하세요');
				
			}else if(text == 'success'){
				alert('로그인 되었습니다.');
				location.href = urlToMyPage;	
				
			}else if(text == 'withdraw'){
				alert('탈퇴한 회원입니다.');
			
			}
		}).catch(error =>{
			error.alertMsg();
			
		});
		
	}
	
	

	
	
	let mypageError = ()=> {
		alert("로그인 후 이용하실 수 있습니다.");
		location.href="/main.do";
	}

	
	
	
	
// Member의 id값 찾는 메소드
	let findUserId = () =>{ 
		let infoObj = new Object();
		infoObj.username = username.value;
		infoObj.phone = phone.value;
		
		let headerObj = new Headers();
		headerObj.append("content-type", "application/x-www-form-urlencoded");
		
		//비동기 처리해서 화면이 새로고침 되지않고 element에서만 바뀌도록 설정
		fetch(urlToFindId, { //해당 url로 객체정보 포함하여 통신요청
			method: "post",
			headers: headerObj,
			body:"userinfo=" + JSON.stringify(infoObj)
			
		}).then(response => {
			
			return response.text();
			
		}).then((text) => {
			
			if(text == 'fail'){
				alert('잘못된 회원정보를 입력하셨습니다.');
			}else{
				document.querySelector("#find_id_result").style.display = "flex";
				document.querySelector("#find_id_result").style.flexDirection = "column";
				document.querySelector('#find_id_result').innerHTML = '검색된 아이디는<h2>[' + text + ']</h2>입니다.';
				document.querySelector('#find_id_table').style.display = "none";
				
			}
		});
	};	
	

	
	
	
//멤버 탈퇴시 사용하는 메소드
	let withdraw = () =>{
		let result = confirm("정말 탈퇴하시겠습니까 ?");
		
		if(result){
			let deleteUserObj = new Object();
			console.dir(id.value);
			deleteUserObj.userId = id.value;
			let url ="/user/withdraw.do";
			
			let headerObj = new Headers();
			headerObj.append("content-type", "application/x-www-form-urlencoded");
			
			fetch(url, {
				method:"post",
				headers:headerObj,
				body:"deleteUser="+JSON.stringify(deleteUserObj)
				
			}).then(response => {
				console.dir(response);
				if(response.ok){
					return response.text();
				}
				throw new AsyncPageError(response.text());
			}).then((text) => {
				if(text == 'success'){
					alert('회원탈퇴가 완료되었습니다.');
					location.href = "/main.do";
				
				}else{
					alert('회원탈퇴 중 오류가 발생하였습니다. 다시 시도해주세요.');
				}
			})

		}
	}
	
	
//User 정보 수정시 사용할 메소드
	let modifyInfo= () => {
		
	    
		
		let memberObj = new Object();
		memberObj.userId = id.value;
		memberObj.userPw = pw.value;
		memberObj.userName = username.value;
		memberObj.userNick = nickname.value;
		memberObj.userEmail = email.value;
		memberObj.userBirth = birth.value;
		memberObj.userPhone = phonenumber.value;
		memberObj.userKg = kg_info.textContent;
		console.dir(JSON.stringify(memberObj));
		
		//1. 비밀번호 조합확인
		let password = pw.value;
	    let regExp = /^(?!.*[ㄱ-힣])(?=.*\W)(?=.*\d)(?=.*[a-zA-Z])(?=.{8,})/;
	     
	    if(!(regExp.test(password))){
	    	document.querySelector("#pw_confirm").style.display = 'flex';
			document.querySelector("#pw_confirm").innerHTML = '비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상이어야 합니다.';
			pw.value = "";
			checkpw.value= "";
	    	document.querySelector('#modify_user_info').addEventListener('submit', (e) =>{
	    		e.preventDefault(); //데이터전송 막기
			})

	      } else {
	    	  let firstPw =  pw.value;
			  let secondPw = checkpw.value;
			  //2. 비밀번호 double check 메소드
			  if(firstPw != secondPw){
				  	document.querySelector("#pw_confirm").style.display = 'flex';
					document.querySelector("#pw_confirm").innerHTML = '비밀번호가 일치하지 않습니다.';
					checkpw.value= ""; //pw의 value값 비워주기
					document.querySelector('#modify_user_info').addEventListener('submit', (e) =>{
						e.preventDefault(); //데이터전송 막기
					})
				
				} else {
					// 3. 회원정보 수정
					let url = "/user/modifyinfo.do";		
					let headerObj = new Headers();
					headerObj.append("content-type", "application/x-www-form-urlencoded");
					
					fetch(url,{
						method:"post",
						headers:headerObj,
						body:"modifyinfo="+JSON.stringify(memberObj)
						
					}).then(response =>{
						console.dir(response);
						if(response.ok){
							return response.text();
						}
						
					}).then((text) =>{
						console.dir(text);
						if(text == 'success'){
							alert('회원정보가 성공적으로 수정되었습니다.');
							location.href ='/user/userprofile.do';
							
						}else {
							alert('회원정보 수정에 실패하였습니다.');
						}
							
					});
				
				}
	      }
	}

//School 정보 수정시 사용할 메소드
	let schoolModifyInfo = () =>{
		let schoolModifyObj = new Object();
		schoolModifyObj.kgId = kgId.value;
		schoolModifyObj.kgName = kgName.value;
		schoolModifyObj.kgAddress = kgAddress.value;
		schoolModifyObj.kgTell = kgTell.value;
		schoolModifyObj.kgEmail = kgEmail.value;
		schoolModifyObj.kgOperateTime = kgOperateTime.value;
		schoolModifyObj.kgNotice = kgNotice.value;
		
		
		let url = "/school/modifyinfo.do";		
		let headerObj = new Headers();
		headerObj.append("content-type", "application/x-www-form-urlencoded");
		
		console.dir(JSON.stringify(schoolModifyObj));
		fetch(url, {
			method:"post",
			headers:headerObj,
			body:"schoolModifyInfo="+JSON.stringify(schoolModifyObj)
		}).then(response =>{
			if(response.ok){
				return response.text();
			} 
			alert('유치원정보 수정중 오류발생');
			
		}).then((text) => {
			if(text == 'fail'){
				alert('유치원정보 업데이트 중 오류가 발생하였습니다.');
			}else if(text =='success'){
				alert('유치원정보가 성공적으로 업데이트 되었습니다.');
				location.href= '/school/schoolprofile.do';
			}else{
				alert('유치원정보 업데이트 중 오류발생');
			}
		});
		
		
		
	}

	
	