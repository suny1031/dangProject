
	let login = () => {
		let paramObj = new Object();
		paramObj.id = id.value;
		paramObj.pw = pw.value;
		
		let headerObj = new Headers();
		headerObj.append("content-type", "application/x-www-form-urlencoded");
		
	
		fetch(urlToLogoin, { /* 해당 url로 아래의 객체를 포함하여 통신요청 */
			method:"post",
			headers:headerObj,
			body:"data="+JSON.stringify(paramObj) /* json으로 문자열로 전환하여 body에 저장 */
		
		}).then(response => { /*200번대 코드가 넘어오면 ok => true */
			if(response.ok){
				return response.text(); /*controller 의 loginimpl에서 text값을 getwriter()*/	
			}
			throw new AsyncPageError(response.text()); /*200번코드가 아니면 catch 문으로 ! */
		}).then((text) => { /* 위의 text() 값이 들어온다.*/
			if(text == 'fail'){
				alert('아이디나 비밀번호를 확인하세요');
				
			}else{
				 location.href=urlToMyPage;	 
			}
		}).catch(error =>{
			alert('로그인 중에 오류가 발생하였습니다.');
			
		});
		
	}
	
	
	let mypageError = ()=> {
		alert("로그인 후 이용하실 수 있습니다.");
		location.href="/main.do";
	}

	

