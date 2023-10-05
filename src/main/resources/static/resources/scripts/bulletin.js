const bulletinForm = document.getElementById('bulletinForm');
const bulletinDelete = bulletinForm.querySelector('[rel="delete"]');
const bulletinPatch = bulletinForm.querySelector('[rel="patch"]');


bulletinDelete.addEventListener('click', e => {
    e.preventDefault()
    const index = bulletinDelete.dataset.index;
    // console.log('Index', index);
    const xhr = new XMLHttpRequest();
    xhr.open('DELETE', `delete?index=${index}`);
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseText = xhr.responseText;
                const confirmResult = confirm('삭제할래요?');
                if (confirmResult === true) {
                    if (responseText === 'true') {
                        window.location.replace('/main');
                    } else {
                        alert('알수 없는 이유로 불가!')
                    }
                } else if (confirmResult === false) {
                    alert('삭제 안해요!')
                    return;
                }
            } else {
                alert('서버 연결 이상해요!');
            }
        }
    };
    xhr.send();
});

bulletinPatch.addEventListener('click', e => {
    e.preventDefault();
    const indexs = bulletinPatch.dataset.index;
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `/main/bulletin/patch?index=${indexs}`);
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const confirmResult = confirm('수정할꺼야?');
                if(confirmResult ===true){
                    location =`/main/bulletin/patch?index=${indexs}`;
                }else if(confirmResult ===false){
                    alert('수정 취소');
                    return;
                }
            } else {
                alert('서버 문제');
            }
        }
    };
    xhr.send();
})

function downloadFile(){
    // const encFileName = encodeURI(filename);
    $.ajax({
        method:"GET",
        url : `/fileDownLoad.do`,
        success : function(data) {
            window.location =`/fileDownLoad.do?FileName=계약서`;
            alert('다운 성공');
        },
        error:function(request,status){
            alert("오류가 발생했습니다.");
        }
    });
}