const idInput = document.querySelector('.forgotPassword-input.id');
const nameInput = document.querySelector('.forgotPassword-input.name');
const emailInput = document.querySelector('.forgotPassword-input.email');

let id = '';

function findPassword() {


    const userId = idInput.value;
    const userName = nameInput.value;
    const userEmail = emailInput.value;

    if (userId.length < 1) {
        alert('아이디 입력칸이 빈칸입니다.');
        idInput.focus();
    } else if (userName.length < 1) {
        alert('이름 입력칸이 빈칸입니다.');
        nameInput.focus();
    } else if (userEmail.length < 1) {
        alert('이메일 입력칸이 빈칸입니다.');
        emailInput.focus();
    } else {
        loadPassword(userId, userName, userEmail).then(data => {
            if (data) {
                id = userId;
                window.location.href='/pages/change-password';
            } else {
                alert('일치하는 정보가 없습니다.');
            }
        });
    }
}

async function loadPassword(userId, userName, userEmail) {
    try {
        const response = await fetch(`/api/users/find-password?id=${userId}&name=${userName}&email=${userEmail}`);
        return await response.json();
    } catch(error) {
        console.error(error);
    }
}

/*
    비밀번호를 바꾸려는 유저의 정보를 저장
    -> 변경하려는 비밀번호가 올바른지 확인
    -> 유저의 정보 중 비밀번호를 기존 비밀번호에서 변경하려는 비밀번호로 변경
        `/api/users/change-password?userId=${id}&password=${password.value}`
    -> 데이터베이스 저장
    -> 로그인창으로 이동
 */

function changePassword() {

}