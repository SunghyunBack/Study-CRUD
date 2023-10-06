//  다음과 같이 팝업창을 만들수 있지만 요즘은 이렇게 만들지 않는다.!!!
// window.onload = function () {
//     window.open('popup.html', 'popup01', 'width=300, height=400, scrollbars=0, toolbar=0, menubar=0');
// }


/* Javascript */
var $layerPopup = document.querySelector('.layerPopup');
var $btnLayerPopupClose = document.querySelector('.layerPopup .btnClose');
var $btnLayerPopupTodayHide = document.querySelector('.layerPopup .btnTodayHide');

//최초 레이어팝업 노출 (testCookie라는 이름의 쿠키가 존재하지 않으면 레이어 노출)
if(!$.cookie('testCookie')){
    layerPopupShow();
}else{
    layerPopupHide(1);
}

//레이어팝업 닫기 버튼 클릭
$btnLayerPopupClose.addEventListener('click', function(){
    layerPopupHide(0);
});

//레이어팝업 오늘 하루 보지 않기 버튼 클릭
$btnLayerPopupTodayHide.addEventListener('click', function(){
    layerPopupHide(1);
});

//레이어팝업 노출
function layerPopupShow(){
    $layerPopup.style.display = 'block'
}
//레이어팝업 비노출
function layerPopupHide(state){
    //닫기버튼 오늘하루보지않기 버튼 무관하계 레이어팝업은 닫는다.
    $layerPopup.style.display = 'none'

    //오늘하루보지않기 버튼을 누른 경우
    if(state === 1){
        //'testCookie' 이름의 쿠키가 있는지 체크한다.
        if($.cookie('testCookie') == undefined){
            //쿠키가 없는 경우 testCookie 쿠키를 추가
            $.cookie('testCookie', 'Y', { expires: 1, path: '/' });
        }
    }
}
