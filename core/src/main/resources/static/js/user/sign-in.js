document.addEventListener("DOMContentLoaded",() => {
    const signInButton = document.querySelector('.sign-in-button');
    signInButton.addEventListener('click', async () => {
        const usernameInput = document.querySelector('.sign-in-input[type="text"]');
        const passwordInput = document.querySelector('.sign-in-input[type="password"]');

        const username = usernameInput.value;
        const password = passwordInput.value;

        try {
            const response = await fetch('/api/users/sign-in', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({username, password})
            });

            if (response.ok) {
                const data = await response.json();
                alert(`${data.username} 님, 환영합니다!`);
                localStorage.setItem('loggedIn', 'true');
                window.location.href = '/member/main';
            } else {
                alert('아이디 또는 비밀번호가 잘못되었습니다.');
                usernameInput.value = '';
                passwordInput.value = '';
            }
        } catch (error) {
            console.error('Sign in Error1:', error);
            alert('로그인 중 오류가 발생했습니다. 다시 시도해주세요.');
        }

    })
})