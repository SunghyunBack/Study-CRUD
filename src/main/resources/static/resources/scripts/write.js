const writeForm = document.getElementById('writeForm');

ClassicEditor.create(writeForm['content'], {
    simpleUpload: {
        uploadUrl: '/main/uploadImage'
    }
});

// let tags=[]
// const Tags= document.querySelectorAll('.files');
//
// Tags.forEach(element =>{
//     const content = element.textContent;
//     tags.push(content);
// });

writeForm.onsubmit = e => {
    if (writeForm['title'].value == '') {
        alert('제목을 입력해주세요!');
        writeForm['title'].focus();
        return false;
    }
    if (writeForm['content'].value == '') {
        alert('내용을 입력해주세요!');
        writeForm['content'].focus();
        return false;
    }
    if (writeForm['category'].value == '') {
        alert('주제를 선택해주세요!');
        writeForm['category'].focus();
        return false;
    }

}