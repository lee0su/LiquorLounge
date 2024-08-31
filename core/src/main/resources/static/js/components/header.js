document.addEventListener('DOMContentLoaded', () => {
    const navbar = document.querySelector('.navbar');
    const buttons = navbar.querySelectorAll('.header-button');

    // data-target-text 속성에서 버튼 텍스트 가져오기
    const targetText = document.body.getAttribute('data-target-text');

    buttons.forEach(button => {
        if (button.textContent.trim() === targetText) {
            button.classList.add('active');
        }
    });

    const signOutBtn = document.querySelector('.header-button.out');
    const signInBtn = document.querySelector('.header-button.in');

    signInCheck().then(data => {
        if (data.loggedIn) {
            signOutBtn.style.display = 'inline-block';
            signInBtn.style.display = 'none';
        } else {
            signInBtn.style.display = 'inline-block';
            signOutBtn.style.display = 'none';
        }
    });
});

function signInAndOut() {
    signInCheck().then(data => {
        if (data.loggedIn) {
            signOut().then(d => {});
        } else {
            window.location.href = '/pages/sign-in';
        }
    });
}

async function signInCheck() {
    try {
        const response = await fetch('/api/users/check-session',);
        if (response.ok) {
            return await response.json();
        }
    } catch (error) {
        console.log(error);
    }
}

async function signOut() {
    const response = await fetch('/api/users/logout', {
        method: 'POST',
    });

    if (response.ok) {
        alert('로그아웃 되었습니다.');
        window.location.href = '/pages/main';
    } else {
        alert('로그아웃 중 오류가 발생했습니다.');
    }
}