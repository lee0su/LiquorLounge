document.addEventListener('DOMContentLoaded', () => {
    const navbar = document.querySelector('.navbar');
    const buttons = navbar.querySelectorAll('button');
    const btn = document.querySelector('.header-button.four');

    // data-target-text 속성에서 버튼 텍스트 가져오기
    const targetText = document.body.getAttribute('data-target-text');

    buttons.forEach(button => {
        if (button.textContent.trim() === targetText) {
            button.classList.add('active');
        }

        if (button === btn) {
            signInCheck().then(data => {
               if (data.loggedIn) {
                   button.innerText = 'Sign out';
               } else {
                   button.innerText = 'Sign in';
               }
            });
        }
    });

    getUsername().then(data => {});

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

async function getUsername() {
    const response = await fetch('/api/session/username');
    if (response.ok) {
        const data = await response.json();
        console.log(data);
        const username = document.querySelector('.username');
        username.innerText = data.username;
    } else {
        alert('데이터 가져오기 실패');
    }
}