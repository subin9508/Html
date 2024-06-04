/**
 * /post/modify.jsp에 포함.
 */

 // 자바스크립트에서는 함수 이름이 같아도 오버로딩이 아님. (나중에 만들어진 함수가 남고, 처음 만들어진 함수가 무효화 됨.)
 // HTML DOM(Document Object Model) 컨텐트 로딩이 끝났을 때, 이벤트 리스너를 실행.
document.addEventListener('DOMContentLoaded', () => { // DOMContentLoaded: html 안에 있는 컨텐트(document object)들의 로딩이 끝났을 때 이벤트를 발생시킴.
    // form 요소를 찾음:
    const modifyForm = document.querySelector('form#modifyForm');
    
    // 글 번호가 입력된 input#id 요소를 찾음:
    const inputId = document.querySelector('input#id');
    
    // 글 제목이 입력된 input#title 요소를 찾음:
    const inputTitle = document.querySelector('input#title');
     
    // 글 내용이 작성된 textarea#content 요소를 찾음:
    const textareaContent = document.querySelector('textarea#content');
    
    // 삭제 버튼 찾음:
    const btnDelete = document.querySelector('button#btnDelete');
    
    // 업데이트 버튼 찾음:
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    // 삭제 버튼에 클릭 이벤트 리스너를 설정.
    btnDelete.addEventListener('click', () => {
        const result = confirm('정말 삭제할까요?');
//        console.log(result); //-> confirm() 함수의 리턴 값은 true(확인 클릭)/false(취소 클릭) 
        if(result) {
            // 삭제(GET 방식) 요청을 서버로 보냄.
            location.href = `delete?id=${inputId.value}`; // `${}`: 자바스크립트의 문자열 템플릿, EL 아님.
                                                          //        중괄호 안에 위치한 변수 속성 값(.value)을 사용하겠다는 의미.  
        }
    }); 
    
    // 업데이트 버튼에 클릭 이벤트 리스너를 설정.
    btnUpdate.addEventListener('click', () => {
        // 제목과 내용이 비어있는 지 체크:
        const title = inputTitle.value; // input 태그 요소에 입력된 값.
        const content = textareaContent.value; // textarea 태그 요소에 입력된 값.
        if(title === '' || content === '') {
            alert('제목과 내용은 반드시 입력해야 합니다!');
            return; // 함수 종료
        }
        
        const result = confirm('변경된 내용을 저장할까요?');
        if(result) {
            modifyForm.method = 'post'; // 폼 제출 방식 설정. 따로 설정하지 않으면 기본값은 get방식
            modifyForm.action = 'update'; // 폼 제출 요청 주소 설정. (url)
            modifyForm.submit(); // 폼 제출(서버로 요청을 보냄).
        }
        
    });
    
}); 
 
 