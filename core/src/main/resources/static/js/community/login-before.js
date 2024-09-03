function showLoginAlert() {
    const url = new URL(window.location.href);
    const params = new URLSearchParams(url.search);
    const username = params.get('username');

    console.log(username);

    if (username === '') {
        alert("게시글 작성은 로그인 후에 가능합니다.");
    } else {
        window.location.href='/pages/written'
    }
}
