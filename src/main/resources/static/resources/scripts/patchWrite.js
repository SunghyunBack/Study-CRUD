const patchWriteForm = document.getElementById('patch-writeForm');
const patchWriteButton = document.querySelector('[rel="patch"]');
ClassicEditor.create(patchWriteForm['content'], {
    simpleUpload: {
        uploadUrl: '/main/uploadImage'
    }
});


patchWriteForm.onsubmit = e => {
    e.preventDefault();
    if (patchWriteForm['title'].value == '') {
        patchWriteForm['title'].focus();
        return false;
    }
    if (patchWriteForm['content'].value == '') {
        patchWriteForm['content'].focus();
        return false;
    }
    if (patchWriteForm['category'].value = '') {
        patchWriteForm['catgory'].focus();
        return false;
    }
    const index = patchWriteButton.dataset.index;

    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('title', patchWriteForm['title'].value);
    formData.append('content', patchWriteForm['content'].value);
    formData.append('category', patchWriteForm['category'].value);
    xhr.open('PATCH', `/main/bulletin/patch?index=${index}`);
    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseObject = JSON.parse(xhr.responseText);
                switch (responseObject.result) {
                    case 'success':
                        alert('수정 성공!');
                        window.location.href = `/main/bulletin?index=`+index;
                        break;
                    case 'failure':
                        alert('수정 실패');
                        break;
                    default:
                        alert('알수 없음');
                }

            } else {
                alert('서버 문제');
            }
        }
    };
    xhr.send(formData);
}