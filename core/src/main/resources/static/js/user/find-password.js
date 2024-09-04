const idInput = document.querySelector('.forgotPassword-input.id');
const nameInput = document.querySelector('.forgotPassword-input.name');
const emailInput = document.querySelector('.forgotPassword-input.email');

const pwInput = document.querySelector('.change-password-input.pw');
const pwCheckInput = document.querySelector('.change-password-input.pw-check');

const passwordRegex = /^[a-zA-Z0-9!@#$%^&*()_+]+$/;

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
                window.location.href=`/pages/change-password?userId=${userId}`;
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
    const password = pwInput.value;
    const passwordCheck = pwCheckInput.value;

    if (password.length < 8 || password.length > 16 || !/[a-zA-Z]/.test(password) || !/[0-9]/.test(password) || !passwordRegex.test(password)) {
        alert('비밀번호는 8~16글자 사이여야 하며, 영어와 숫자, 특수기호를 포함해야 합니다.');
        pwInput.focus();
        return;
    }

    if (password !== passwordCheck) {
        alert('비밀번호가 일치하지 않습니다.');
        pwCheckInput.focus();
        return;
    }

    const urlParams = new URL(location.href).searchParams;
    const id = urlParams.get('userId');

    updatePassword(id, password.value).then(data => {
       if (data) {
           alert('비밀번호 변경 완료 !');
           window.location.href='/pages/sign-in';
       } else {
           alert('비밀번호 변경 실패..');
       }
    });

}

async function updatePassword(id, password) {
    try {
        const response = await fetch(`/api/users/change-password?userId=${id}&password=${password}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({id, password})
        });
        console.log('OK');
        return response;
    } catch(error) {
        console.error(error);
    }
}