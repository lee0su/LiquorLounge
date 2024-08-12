// 입력 값 검증과 중복 확인을 위한 로직
document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById('signUpForm');
    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const username = form.username.value;
        const password = form.password.value;
        const confirmPassword = form.confirmPassword.value;
        const name = form.name.value;
        const birthdate = form.birthdate.value;

        if (password !== confirmPassword) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        try {
            const response = await fetch(`/api/users/check-username?username=${username}`);
            const data = await response.json();

            if (!data.available) {
                alert('이미 사용중인 아이디입니다.');
                return;
            }

            const formData = new FormData(form);
            const registerResponse = await fetch(form.action, {
                method: form.method,
                body: formData
            });

            if (registerResponse.redirected) {
                window.location.href = registerResponse.url;
            } else if (registerResponse.status === 409) {
                alert('이미 사용중인 아이디입니다.');
            } else {
                alert('오류가 발생했습니다. 다시 시도해주세요.');
            }

        } catch (error) {
            console.error('register Error1:', error);
            alert('오류가 발생했습니다. 다시 시도해주세요.');
        }
    })
})