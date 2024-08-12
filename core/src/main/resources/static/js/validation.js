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

        const usernameRegex = /^[a-zA-Z0-9]+$/;
        const passwordRegex = /^[a-zA-Z0-9!@#$%^&*()_+]+$/;
        const nameRegex = /^[a-zA-Z가-힣]+$/;

        if (username.length < 6 || username.length > 12 || !usernameRegex.test(username)) {
            alert('아이디는 6~12글자 사이여야 하며, 영어와 숫자만 사용이 가능합니다.');
            return;
        }

        if (password.length < 8 || password.length > 16 || !/[a-zA-Z]/.test(password) || !/[0-9]/.test(password) || !passwordRegex.test(password)) {
            alert('비밀번호는 8~16글자 사이여야 하며, 영어와 숫자, 특수기호를 포함해야 합니다.');
            return;
        }

        if (password !== confirmPassword) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        if (!nameRegex.test(name) || name.length < 2 || name.length > 10) {
            alert('이름은 2~10글자 사이여야 하며, 영어와 한글만 사용이 가능합니다.');
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
                alert('회원가입이 완료되었습니다 !')
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