const idInput = document.querySelector('.forgotPassword-input.id');
const nameInput = document.querySelector('.forgotPassword-input.name');
const emailInput = document.querySelector('.forgotPassword-input.email');

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
                alert('비밀번호 변경 페이지로 이동합니다.');
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