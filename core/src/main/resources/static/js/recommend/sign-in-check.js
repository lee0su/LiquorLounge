document.addEventListener("DOMContentLoaded", function() {
    const url = new URL(window.location.href);
    const params = new URLSearchParams(url.search);
    const username = params.get('username');

    console.log(username);

    if (username === '') {
        alert("로그인 후에 가능한 서비스입니다.");
        window.location.href = '/pages/login';
    }
})